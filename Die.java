import java.util.Random;

public class Die
{
    private final int SIDES = 6; //number of sides on a standard die

    Random rand = new Random();

    private int rollNum; //the number rolled at a given time

    public int roll()
    {
        rollNum = (rand.nextInt(SIDES) + 1);
        return rollNum;
    }
}