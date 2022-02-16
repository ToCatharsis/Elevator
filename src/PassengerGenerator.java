import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PassengerGenerator extends  Thread
{
    private float chance;
    private int time;
    private ArrayList<Floor> floors;


    PassengerGenerator(float chance, int time, ArrayList<Floor> floors)
    {
        this.chance = chance;
        this.floors = floors;
        this.time = time;
    }

    private void generatePassenger() throws InterruptedException
    {
        int from = ThreadLocalRandom.current().nextInt(floors.size());
        int to = ThreadLocalRandom.current().nextInt(floors.size());
        while(from == to)
        {
            to = ThreadLocalRandom.current().nextInt(floors.size());
        }
        floors.get(from).addPassenger(new Passenger(from, to));
    }

    public void run()
    {
        try
        {
            while(true)
            {
                Thread.sleep(time);
                if (ThreadLocalRandom.current().nextFloat() < chance)
                    generatePassenger();
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
