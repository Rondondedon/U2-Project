import java.util.Scanner;
public class Gamering
{
    public static void startGame()
    {
        //yapping
        Scanner playerResponse = new Scanner(System.in);
        int playerHealth = 100;
        System.out.println("This game requires you to survive x encounters with enemies in-order to win");
        System.out.println("Each turn you can choose to either attack or defend");
        System.out.println("Defending negates all but 5 damage, and attacking does 20 damage to the enemy");
        System.out.println("Your character starts with " + playerHealth + " hp, and heals fully after each encounter");
        System.out.println();
        System.out.println("Dying loses the game.");
        System.out.println();
        System.out.println("How many encounters would you like to face? 5 is fit for beginners, while 10 and more is quite hard");
        int encounters = playerResponse.nextInt();
        System.out.println();
        boolean playerAlive = true;




        //monster type and stat generation
        for (int i = 1; i <= encounters && playerAlive; i++)
        {
            int monsterHealth = (int) (Math.random() * 36) + 70;
            int monsterAttack = ((int) Math.random() * 11) + 25;
            int monsterRand = (int) (Math.random() * 3) + 1;

            String monsterType = "";
            String monsterDescription = "";

            if (monsterRand == 1)
            {
                monsterType = "Ghoul";
                monsterHealth -= 30;
                monsterAttack += 20;
                monsterDescription = "Ghouls deal incredible damage, but in exchange have meager hp";
            } else if (monsterRand == 2)
            {
                monsterType = "Zombie";
                monsterHealth += 35;
                monsterDescription = "Zombies are quite tanky, on account of their undead nature";
            } else if (monsterRand == 3)
            {
                monsterType = "Beast";
                monsterAttack += 10;
                monsterDescription = "Beasts are savage by nature, and so, they do extra damage";
            }
            System.out.println("Encounters remaining: " + (encounters - i));
            System.out.println("In encounter number " + i + " you face against a " + monsterType);
            System.out.println(monsterDescription);
            System.out.println(monsterType + "'s hp: " + monsterHealth);
            System.out.println(monsterType + "'s attack: " + monsterAttack);
            System.out.println("Your hp: " + playerHealth);




            //fight sequence
            for (int hp = monsterHealth; hp > 0 && playerAlive;)
            {
                int attackChance = (int) (Math.random() * 5) * 25;
                System.out.println();
                System.out.println("The monster has a " + attackChance + "% chance of attacking this turn");
                boolean attacking = false;

                //implementation of attack chances
                if (attackChance == 25)
                {
                    attacking = Math.random() > 0.75;
                }if (attackChance == 50)
                {
                    attacking = Math.random() > 0.5;
                }if (attackChance == 75)
                {
                    attacking = Math.random() > 0.25;
                }if (attackChance == 100)
                {
                    attacking = true;
                }




                //player action
                System.out.println("Will you attack or defend? Type 1 to attack, or 2 to defend");
                int turnAction = playerResponse.nextInt();
                System.out.println();
                if (turnAction == 1)
                {
                    System.out.println("You deal 20 damage to the monster");
                    monsterHealth -= 20;
                    hp = monsterHealth;
                    System.out.println(monsterType + "'s current hp: " + monsterHealth);
                }

                //monster attacks
                if (attacking && monsterHealth > 0)
                {
                    System.out.print("The " + monsterType + " succeeds in launching an attack");
                    if (turnAction == 1)
                    {
                        System.out.println(", dealing " + monsterAttack + " damage");
                        playerHealth -= monsterAttack;
                        System.out.println("Your current hp: " + playerHealth);
                    }
                    if (turnAction == 2)
                    {
                        System.out.println();
                        System.out.println("But since you defended, the monster only does 5 damage to you");
                        playerHealth -= 5;
                        System.out.println("Your hp: " + playerHealth);
                    }
                } else if (!attacking) {
                    System.out.println("The monster doesn't attack");
                }

                //this boy dead
                if (playerHealth <=0)
                {
                    System.out.println("You have succumbed to your wounds");
                    System.out.println("Bested by the " + monsterType + ", you have lost the game");
                    playerAlive = false;
                }
            }
            if (playerAlive)
            {
                playerHealth = 100;
                System.out.println("You've slain the monster, and thus healed fully to " + playerHealth + " hp!");
                System.out.println();
                System.out.println();
            }
        }
        if (playerAlive)
        {
            System.out.println("You've survived all encounters, you win!11!1!!!");
        }
    }
}
