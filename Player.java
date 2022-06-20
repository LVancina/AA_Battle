import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player
{
    protected enum playerSideEnum {ATTACKER, DEFENDER}; //there are only two possible player types in a battle
    protected playerSideEnum playerSide;
    protected ArrayList<Unit> units = new ArrayList<Unit>();

    public Player()
    {
        playerSide = playerSideEnum.ATTACKER;
    }

    public Player(playerSideEnum s)
    {
        playerSide = s;
    }

    public void setPlayerSide(playerSideEnum s)
    {
        playerSide = s;
    }
    
    public int getInfantry()
    {
        int result = 0;
        for(Unit i : units)
        {
            if(i instanceof Infantry)
                result++;
        }
        return result;
    }
    
    public int getArtillery()
    {
        int result = 0;
        for(Unit i : units)
        {
            if(i instanceof Artillery)
                result++;
        }
        return result;
    }

    public int getArmor()
    {
        int result = 0;
        for(Unit i : units)
        {
            if(i instanceof Armor)
                result++;
        }
        return result;
    }

    public int getBombers()
    {
        int result = 0;
        for(Unit i : units)
        {
            if(i instanceof Bomber)
                result++;
        }
        return result;
    }

    public int getFighters()
    {
        int result = 0;
        for(Unit i : units)
        {
            if(i instanceof Fighter)
                result++;
        }
        return result;
    }

    
    public void setUnits() //allow the user to specify how many of each unit they are bringing to the battle
    {
        Scanner scan = new Scanner(System.in);
        int i, x;

        try
        {
            System.out.println("Enter the number of " +playerSide+ " infantry:");
            i = scan.nextInt();
            for(x = 0; x < i; x++)
            {
                units.add(new Infantry());
            }
            System.out.println("Enter the number of " +playerSide+ " artillery:");
            i = scan.nextInt();
            for(x = 0; x < i; x++)
            {
                units.add(new Artillery());
            }
            System.out.println("Enter the number of " +playerSide+ " armor:");
            i = scan.nextInt();
            for(x = 0; x < i; x++)
            {
                units.add(new Armor());
            }
            System.out.println("Enter the number of " +playerSide+ " bombers:");
            i = scan.nextInt();
            for(x = 0; x < i; x++)
            {
                units.add(new Bomber());
            }
            System.out.println("Enter the number of " +playerSide+ " fighters:");
            i = scan.nextInt();
            for(x = 0; x < i; x++)
            {
                units.add(new Fighter());
            }
        } catch(InputMismatchException e)
            {
                System.out.println("\nOnly enter integers\n");
                this.setUnits();
            }
    }
    
    public int getHits() //players roll one die for each unit they have. If the roll is less than or equal to the atk.def rating of that unit, then they score a hit
    {
        int hits = 0;
        Die die = new Die();

        switch(playerSide)
        {
            case ATTACKER:
                for(Unit i : units)
                {
                    if(die.roll() <= i.atkRating)
                        hits++;
                }
                break;
            case DEFENDER:
                for(Unit i : units)
                {
                    if(die.roll() <= i.defRating)
                        hits++;
                }
                break;
        }
        return hits;
    }

    public void chooseCasualties(int hits) //for each hit the opponent scores, the player must choose a unit to lose as a casualty
    {
        Scanner scan = new Scanner(System.in);
        int casualties, total = 0;
        System.out.println("Before casualties assessed:");
        getUnitRep();
        System.out.println("");
        while(total < hits && !units.isEmpty()) //keep looping as long as there are casualties left to assess and the player has units left
        {
            //print the options of units
            System.out.println("Choose which type of unit to take casualties. You have "+(hits-total)+" casulaties remaining to assess.");
            System.out.println("1. Infantry");
            System.out.println("2. Artillery");
            System.out.println("3. Armor");
            System.out.println("4. Bomber");
            System.out.println("5. Fighter");
            //get the users menu choice and execute the proper action
            int choice = scan.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("");
                    System.out.println("How many infantry:");
                    casualties = scan.nextInt();
                    System.out.println("");
                    if(casualties > getInfantry())
                        System.out.println("\nYou do not have that many infantry. Try a different number or unit type.\n");
                    else if(casualties > hits-total)
                        System.out.println("\nThe number you chose is greater than the number of casualties left to assess. Try again.\n");
                    else
                    {
                        while(casualties > 0)
                        {
                            for(Unit j : units)
                            {
                                if(j instanceof Infantry)
                                {
                                    units.remove(j);
                                    total++;
                                    casualties--;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("How many artillery:");
                    casualties = scan.nextInt();
                    System.out.println("");
                    if(casualties > getArtillery())
                    {
                        System.out.println("\nYou do not have that many artillery. Try a different number or unit type.\n");
                    }
                    else if(casualties > hits-total)
                    System.out.println("\nThe number you chose is greater than the number of casualties left to assess. Try again.\n");
                    else
                    {
                        while(casualties > 0)
                        {
                            for(Unit j : units)
                            {
                                if(j instanceof Artillery)
                                {
                                    units.remove(j);
                                    total++;
                                    casualties--;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("How many armor:");
                    casualties = scan.nextInt();
                    System.out.println("");
                    if(casualties > getArmor())
                    {
                        System.out.println("\nYou do not have that many armor. Try a different number or unit type.\n");
                    }
                    else if(casualties > hits-total)
                    System.out.println("\nThe number you chose is greater than the number of casualties left to assess. Try again.\n");
                    else
                    {
                        while(casualties > 0)
                        {
                            for(Unit j : units)
                            {
                                if(j instanceof Armor)
                                {
                                    units.remove(j);
                                    total++;
                                    casualties--;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("How many bombers:");
                    casualties = scan.nextInt();
                    System.out.println("");
                    if(casualties > getBombers())
                    {
                        System.out.println("\nYou do not have that many bombers. Try a different number or unit type.\n");
                    }
                    else if(casualties > hits-total)
                    System.out.println("\nThe number you chose is greater than the number of casualties left to assess. Try again.\n");
                    else
                    {
                        while(casualties > 0)
                        {
                            for(Unit j : units)
                            {
                                if(j instanceof Bomber)
                                {
                                    units.remove(j);
                                    total++;
                                    casualties--;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("");
                    break;
                case 5:
                    System.out.println("How many fighters:");
                    casualties = scan.nextInt();
                    System.out.println("");
                    if(casualties > getFighters())
                    {
                        System.out.println("\nYou do not have that many fighters. Try a different number or unit type.\n");
                    }
                    else if(casualties > hits-total)
                    System.out.println("\nThe number you chose is greater than the number of casualties left to assess. Try again.\n");
                    else
                    {
                        while(casualties > 0)
                        {
                            for(Unit j : units)
                            {
                                if(j instanceof Fighter)
                                {
                                    units.remove(j);
                                    total++;
                                    casualties--;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("");
                    break;
            }
            if(units.isEmpty())
            {
                System.out.println("\nAll "+playerSide+" units destroyed!\n");
                break;
            }
        }
        System.out.println("After casualties assessed");
        getUnitRep();
    }

    public void getUnitRep()
    {
        System.out.println(playerSide+ " unit report:");
        System.out.println("Infantry: " +getInfantry());
        System.out.println("Artillery: " +getArtillery());
        System.out.println("Armor: " +getArmor());
        System.out.println("Bombers: " +getBombers());
        System.out.println("Fighters: " +getFighters());
    }
}
