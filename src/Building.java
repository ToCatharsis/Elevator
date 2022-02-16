import java.util.ArrayList;

public class Building
{
    public static void main(String[] args)
    {
        boolean b1 = true;

        while(b1)
        {
            try
            {
                int f = 4; //A number of floors
                if (f<2)
                    throw new RuntimeException();
                int t = 1000; //An amount of time for lift to move from one floor to another
                float p = 0.7f; //A chance of appearance of a passenger on a random floor (between 0 and 1)
                b1 = false;

                ArrayList<Floor> floors = new ArrayList<>();
                for (int i=0; i<f; i++)
                {
                    floors.add(new Floor());
                }

                Thread passengerGenerator = new Thread(new PassengerGenerator(p, t, floors));
                passengerGenerator.start();

                Thread lift = new Thread(new Elevator(floors, t));
                lift.start();
            }
            catch (RuntimeException e)
            {
                System.out.println("You've entered wrong value, please, try again.");
            }
        }
    }
}
