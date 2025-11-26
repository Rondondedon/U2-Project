import java.util.Scanner;
public class Gamering
{
    public static void startGame()
    {
        Scanner playerResponse = new Scanner(System.in);

        //player balancing
        int playerHealth = 90;
        int defendMax = 2;



        //yapping
        System.out.println("This game requires you to survive x encounters with enemies in-order to win");
        System.out.println("Each turn you can choose to either attack or defend");
        System.out.println("Defending negates all but 5 damage, and attacking does 20 damage to the enemy");
        System.out.println("Parrying allows you to reflect 50~% of the enemy's attack back onto them, avoiding all damage");
        System.out.println("But if the monster doesn't attack during the parry, you take full damage next turn");
        System.out.println("You can only defend " + defendMax + " times in a row before needing to attack or parry");
        System.out.println("Your character starts with " + playerHealth + " hp, and heals fully after each encounter");
        System.out.println();
        System.out.println("Dying loses the game.");
        System.out.println();
        System.out.println("How many encounters would you like to face? 5 is fit for beginners, while 10 and more is quite hard");

        //encounter setting
        int encounters = playerResponse.nextInt();
        System.out.println();
        boolean playerAlive = true;



        //monster type and stat generation
        for (int i = 1; i <= encounters && playerAlive; i++)
        {
            int monsterHealth = (int) (Math.random() * 36) + 70;
            int monsterAttack = (int) (Math.random() * 11) + 25;
            int monsterRand = (int) (Math.random() * 3) + 1;

            String monsterType = "";
            String monsterDescription = "";

            if (monsterRand == 1)
            {
                monsterType = "Ghoul";
                monsterDescription = "Ghouls deal incredible damage, but in exchange have meager hp";
                monsterHealth -= 30;
                monsterAttack += 20;
            } else if (monsterRand == 2)
            {
                monsterType = "Zombie";
                monsterDescription = "Zombies are quite tanky, on account of their undead nature";
                monsterHealth += 35;
            } else if (monsterRand == 3)
            {
                monsterType = "Beast";
                monsterDescription = "Beasts are savage by nature, and so, they do extra damage";
                monsterAttack += 10;
            }



            //encounter start details
            System.out.println("Encounters remaining: " + (encounters - i));
            System.out.println("In encounter number " + i + " you face against a " + monsterType);
            System.out.println(monsterDescription);
            System.out.println(monsterType + "'s hp: " + monsterHealth);
            System.out.println(monsterType + "'s attack: " + monsterAttack);
            System.out.println("Your hp: " + playerHealth);
            int defenseCount = 0;
            int turnAction;



            //fight sequence
            for (;monsterHealth > 0 && playerAlive;)
            {



                //implementation of attack chances
                int attackPossibility = (int) (Math.random() * 5);
                int attackChance = 0;

                if (attackPossibility == 1)
                {
                    attackChance = 20;
                }else if (attackPossibility == 2)
                {
                    attackChance = 50;
                }else if (attackPossibility == 3)
                {
                    attackChance = 80;
                }else if (attackPossibility == 4)
                {
                    attackChance = 100;
                }

                System.out.println();
                System.out.println("The monster has a " + attackChance + "% chance of attacking this turn");
                boolean attacking = false;

                if (attackChance == 20)
                {
                    attacking = Math.random() > 0.80;
                }else if (attackChance == 50)
                {
                    attacking = Math.random() > 0.5;
                }else if (attackChance == 80)
                {
                    attacking = Math.random() > 0.2;
                }else if (attackChance == 100)
                {
                    attacking = true;
                }



                //player action
                if (defenseCount < defendMax)
                {
                    System.out.println("Will you attack or defend? Type 1 to attack, 2 to defend, or 3 to parry");
                    turnAction = playerResponse.nextInt();
                    System.out.println();

                    //cheeky-game-breaker curbing
                    for (; turnAction != 1 && turnAction != 2 && turnAction != 3;)
                    {
                        System.out.println("              why.");
                        System.out.println();
                        System.out.println("Type 1 to attack, 2 to defend, or 3 to parry");
                        turnAction = playerResponse.nextInt();
                        System.out.println();
                    }



                    //player immediately attacks
                    if (turnAction == 1)
                    {
                        System.out.println("You deal 20 damage to the monster");
                        monsterHealth -= 20;
                        System.out.println(monsterType + "'s current hp: " + monsterHealth);
                        defenseCount = 0;
                    }

                    //monster attacks
                    if (attacking && monsterHealth > 0)
                    {
                        System.out.print("The " + monsterType + " succeeds in launching an attack");
                        if (turnAction == 1) {
                            System.out.println(", dealing " + monsterAttack + " damage");
                            playerHealth -= monsterAttack;
                            System.out.println("Your current hp: " + playerHealth);
                        }if (turnAction == 2) {
                            System.out.println();
                            System.out.println("But since you defended, the monster only does 5 damage to you");
                            playerHealth -= 5;
                            System.out.println("Your current hp: " + playerHealth);
                            defenseCount++;
                        }if (turnAction == 3)
                        {
                            System.out.println();
                            System.out.println("But because you parried in time, the monster loses hp equal to its own attack");
                            monsterHealth -= monsterAttack / 2;
                            System.out.println(monsterType + "'s current hp: " + monsterHealth);
                            System.out.println("Your hp: " + playerHealth);
                            defenseCount = 0;
                        }
                    } else if (!attacking)
                    {
                        System.out.println("The monster doesn't attack");
                        System.out.println("Your hp: " + playerHealth);
                        if (turnAction == 2)
                        {
                            defenseCount++;
                        }
                        if (turnAction == 3)
                        {
                            System.out.println();
                            System.out.println("But because you missed your parry, you are attacked next turn");
                            playerHealth -= monsterAttack;
                            System.out.println("Your current hp: " + playerHealth);
                            defenseCount = 0;
                        }
                    }
                    System.out.println("Times defended in a row: " + defenseCount);
                }



                //defended more than x times
                else
                {
                    System.out.println("You can no longer defend anymore, press 1 to attack or 3 to parry");
                    turnAction = playerResponse.nextInt();

                    //cheeky-game-breaker curbing 2
                    for (; turnAction != 1 && turnAction != 3;)
                    {
                        System.out.println("      why.");
                        System.out.println();
                        System.out.println("Type 1 to attack, or 3 to parry");
                        turnAction = playerResponse.nextInt();
                        System.out.println();
                    }



                    //player immediately attacks
                    if (turnAction == 1)
                    {
                        System.out.println("You deal 20 damage to the monster");
                        monsterHealth -= 20;
                        System.out.println(monsterType + "'s current hp: " + monsterHealth);
                        defenseCount = 0;
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
                        if (turnAction == 3)
                        {
                            System.out.println();
                            System.out.println("But because you parried in time, the monster loses hp equal to its own attack");
                            monsterHealth -= monsterAttack / 2;
                            System.out.println(monsterType + "'s current hp: " + monsterHealth);
                            System.out.println("Your hp: " + playerHealth);
                            defenseCount = 0;
                        }
                    } else if (!attacking)
                    {
                        System.out.println("The monster doesn't attack");
                        System.out.println("Your hp: " + playerHealth);
                        if (turnAction == 3)
                        {
                            System.out.println();
                            System.out.println("But because you missed your parry, you are attacked next turn");
                            playerHealth -= monsterAttack;
                            System.out.println("Your current hp: " + playerHealth);
                            defenseCount = 0;
                        }
                    }
                    defenseCount = 0;
                    System.out.println("Times defended in a row: " + defenseCount);
                }

                //this boy dead
                if (playerHealth <=0)
                {
                    System.out.println("You have succumbed to your wounds");
                    System.out.println("Bested by the " + monsterType + ", you have lost the game");
                    playerAlive = false;
                }
            }

            //beaten encounter, heals fully
            if (playerAlive)
            {
                playerHealth = 100;
                System.out.println("You've slain the monster, and thus healed fully to " + playerHealth + " hp!");
                System.out.println();
                System.out.println();
                System.out.println();
            }
        }

        //player win!!!!
        if (playerAlive)
        {
            System.out.println("You've survived all encounters, you win!11!1!!!");
        }
    }
}
