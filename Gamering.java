import java.util.Scanner;
public class Gamering
{
    public static void gameStart ()
    {
        Scanner playerResponse = new Scanner(System.in);
        System.out.println("This game requires you to survive x encounters with enemies in-order to win");
        System.out.println("Each turn you can either choose to attack or defend");
        System.out.println("Defense negates all but 1 damage, and attacking does 4 damage to enemies");
        System.out.println("Your character starts with 25 hp, and heals fully after each encounter. Dying loses the game");
        System.out.println("How many encounters would you like to face? 3 is fit for beginners, while 5 and more is quite hard");
        int encounters = playerResponse.nextInt();

        //monster stat generation
        for (int i = 1; i <= encounters; i++)
        {
            int monsterHealth = (int) Math.random() * 7 + 14;
            int monsterAttack = (int) Math.random() * 3 + 3;
            int monsterRand = (int) (Math.random() * 3) + 1;
            String monsterType = "";
            String monsterDescription = "";
            if (monsterRand == 1)
            {
                monsterType = "Ghoul";
                monsterHealth -= 7;
                monsterAttack += 4;
                monsterDescription = "Ghouls deal incredible damage, but in exchange, have meager hp";
            } else if (monsterRand == 2)
            {
                monsterType = "Zombie";
                monsterHealth += 6;
                monsterDescription = "Zombies are quite tanky, on account of their undead nature";
            } else if (monsterRand == 3)
            {
                monsterType = "Beast";
                monsterAttack += 2;
                monsterDescription = "Beasts are savage by nature and so, does extra damage";
            }
            System.out.println("In encounter number " + i + " you face is against a " + monsterType);
            System.out.println(monsterDescription);

            //fight sequence
            for (int hp = monsterHealth; hp >= 0;)
            {
                int attackChance = (int) (Math.random() * 4) * 25 + 25;
                System.out.println("The monster has a " + attackChance + "% chance of attacking this turn");
                System.out.println("Will you attack or defend? Type 1 to attack, or 2 to defend");
                int turnAction = playerResponse.nextInt();
                if (turnAction == 1);
                {
                    System.out.println("You deal 4 damage to the monster");
                }
            }
        }
        System.out.println("You've survived all encounters, you win!11!1!!!");
    }
}
