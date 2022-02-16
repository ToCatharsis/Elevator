import java.util.concurrent.LinkedBlockingQueue;

public class Floor
{
    private LinkedBlockingQueue<Passenger> listPassengersUp = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Passenger> listPassengersDown = new LinkedBlockingQueue<>();


    public LinkedBlockingQueue<Passenger> getListPassengersDown()
    {
        return listPassengersDown;
    }

    public LinkedBlockingQueue<Passenger> getListPassengersUp()
    {
        return listPassengersUp;
    }

    public Passenger getPassengerUp()
    {
        return listPassengersUp.poll();
    }

    public Passenger getPassengerDown()
    {
        return listPassengersDown.poll();
    }


    public void addPassenger(Passenger passenger) throws InterruptedException
    {
        if (passenger.isGoingUp())
            listPassengersUp.put(passenger);
        else
            listPassengersDown.put(passenger);
    }

    public boolean isEmpty()
    {
        return listPassengersUp.isEmpty() && listPassengersDown.isEmpty();
    }

    public String toString()
    {
        String s = listPassengersUp.toString();
        s+= listPassengersDown.toString();
        return s;
    }
}
