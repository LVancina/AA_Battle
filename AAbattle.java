import java.util.InputMismatchException;
import java.util.Scanner;

public class AAbattle
{
    public static void main(String[] args)
    {
        boolean choice = true, result;
        int atkHits, defHits;
        Scanner scan = new Scanner(System.in);
        Player defender = new Player(Player.playerSideEnum.DEFENDER);
        Player attacker = new Player(Player.playerSideEnum.ATTACKER);

        System.out.println("Enter the number of each type of unit for the attacker:");
        attacker.setUnits();
        System.out.println("");
        attacker.getUnitRep();
        System.out.println("");
        System.out.println("Enter the number of each type of unit for the defender:");
        defender.setUnits();
        System.out.println("");
        defender.getUnitRep();
        System.out.println("");

        while(choice == true)
        {
            atkHits = attacker.getHits();
            defHits = defender.getHits();
            System.out.println("");
            System.out.println("Defender hits: "+defHits+", Attacker hits: "+atkHits);
            System.out.println("");
            try
            {
                defender.chooseCasualties(atkHits);
            }
            catch(InputMismatchException e)
            {
                System.out.println("\nOnly enter integers\n");
                defender.chooseCasualties(atkHits);
            }
            System.out.println("");
            try
            {
                attacker.chooseCasualties(defHits);
            }
            catch(InputMismatchException e)
            {
                System.out.println("\nOnly enter integers\n");
                attacker.chooseCasualties(defHits);
            }
            
            System.out.println("");
            
            result = AAbattle.checkWinner(attacker, defender); //check if there is a winner
            if(result == true)
                break;

            System.out.println("The attacker would like to continue?[true/false]"); //the attacker has the choice to continue or to break off the attack
            choice = scan.nextBoolean();
            if(choice == false)
                System.out.println("ATTACKER chooses to withdraw!");
        }
    }
    
    private static boolean checkWinner(Player atk, Player def)
    {
        if(atk.units.isEmpty() && !def.units.isEmpty())
        {
            System.out.println("Defender wins!");
            return true;
        }
        else if(def.units.isEmpty() && !atk.units.isEmpty())
        {
            System.out.println("Attacker wins!");
            return true;
        }
        else
            return false;
    }

}
