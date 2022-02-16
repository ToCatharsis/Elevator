import java.util.ArrayList;

public class Elevator extends Thread
{
    private ArrayList<Floor> floors; //Lista z piętrami
    private ArrayList<Passenger> passengersLift = new ArrayList<>(); //Lista pasażerów w windzie
    private int time; //Czas zarówno na przejazd, jak i na wejście/wyjście pasażera
    private final int maxCapacity = 8; //Maksymalna liczba osób
    private int currentFloor = 0; //Obecne piętro
    private boolean goingUp = true; //Kierunek jazdy

    Elevator(ArrayList<Floor> floors, int time)
    {
        this.floors = floors;
        this.time = time;
    }

    public void run()
    {
        try
        {
            while(true)
            {
                //Writing a state
                for (int i=floors.size(); i>0; i--)
                {
                    System.out.println("Floor " + (i-1) + ": " + floors.get(i-1).toString());
                }
                System.out.println(this);
                System.out.println();


                //This allows to change direction of elevator at first and the last floor
                if ((goingUp && currentFloor == floors.size()-1) || (!goingUp && currentFloor == 0))
                    goingUp = !goingUp;

                if (passengersLift.size() == 0)
                {
                    takePassengers();
                }
                else
                {
                    //If there are passengers int the elevator - checking, if someone can exit
                    for (int i=0; i<passengersLift.size(); i++)
                    {
                        if (passengersLift.get(i).getTo() == currentFloor)
                        {
                            passengersLift.remove(i);
                            Thread.sleep(time);
                        }
                    }
                    takePassengers();
                }

                //Going up/down by 1 floor
                if (goingUp)
                {
                    currentFloor++;
                    Thread.sleep(time);
                }
                else
                {
                    currentFloor--;
                    Thread.sleep(time);
                }
            }

        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void takePassengers() throws InterruptedException
    {
        if (goingUp) //Takes only those passengers, who have the same direction, as elevator
        {
            while (floors.get(currentFloor).getListPassengersUp().size() > 0)
            {
                if (passengersLift.size() == maxCapacity) //Checking, if there is too many passengers in the elevator
                    break;
                passengersLift.add(floors.get(currentFloor).getPassengerUp()); //Adding a passenger
                Thread.sleep(time);
            }
        }
        else
        {
            while (floors.get(currentFloor).getListPassengersDown().size() > 0)
            {
                if (passengersLift.size() == maxCapacity) //The same
                    break;
                passengersLift.add(floors.get(currentFloor).getPassengerDown());
                Thread.sleep(time);
            }
        }
    }

    public String toString()
    {
        String s = "Elevator is on the floor " + currentFloor + ((goingUp)? ", going up." : ", going down.") +
                " Passengers: ";
        for (int i=0; i<passengersLift.size(); i++)
            s += passengersLift.get(i).toString() + ", ";
        return s;
    }


}
