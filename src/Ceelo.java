import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class Ceelo {
    private Player p1;
    private Player p2;
    private Player p3;
    private Banker banker;
    private String b;
    private Scanner scan = new Scanner(System.in);
    private Die die = new Die();
    private int round = 1;

    public Ceelo() {
        System.out.println(ConsoleUtility.BLUE + "Welcome to Ceelo Dice!");
        System.out.print("Enter your name Player 1: ");
        p1 = new Player(scan.nextLine());
        System.out.print("Enter your name Player 2: ");
        p2 = new Player(scan.nextLine());
        System.out.print("Enter your name Player 3: ");
        p3 = new Player(scan.nextLine());
        banker = new Banker();
    }

    public void play() {
        while (!banker.gameOver() && !(p1.gameOver() && p2.gameOver() && p3.gameOver())) {
            System.out.println(ConsoleUtility.YELLOW + "\n****************\nStats:");
            System.out.println("Banker: " + banker.getChips());
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
            System.out.println("************\n");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("error");
            }
            System.out.println("\nRound " + round + ":");
            round++;
            System.out.println(ConsoleUtility.GREEN + "Enter wagers:");
            if (!p1.gameOver()) {
                p1.setWager(scan);
            }
            if (!p2.gameOver()) {
                p2.setWager(scan);
            }
            if (!p3.gameOver()) {
                p3.setWager(scan);
            }
            ConsoleUtility.clearScreen();

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("error");
            }


            System.out.println(ConsoleUtility.BLUE + "Banker's turn!");
            banker.playerRoll(die);

            if (banker.getScore() == 0) {
                System.out.println("\nAutomatic Loss!\nBanker loses and pays each player the wagered amount!");
                p1.setChips(banker);
                p2.setChips(banker);
                p3.setChips(banker);
            } else if (banker.getScore() == 7) {
                System.out.println("\nAutomatic Win!\nBanker wins and takes each player's wagered amount!");
                banker.setChips(p1);
                banker.setChips(p2);
                banker.setChips(p3);
            } else {
                scan.nextLine();
                System.out.println(ConsoleUtility.RESET + "\nBanker has a score of " + banker.getScore());
                System.out.println("\nRoll higher than the banker to earn your wagered amount!");

                if (!banker.gameOver()) {
                    if (!p1.gameOver()) {
                        System.out.print("\n" + p1.getName() + "'s turn! Score higher than " + banker.getScore() + "\nPress enter to roll");
                        scan.nextLine();
                        p1.playerRoll(die, scan);
                        Die.printScore(p1.getScore());
                        if (p1.getScore() == 7 || p1.getScore() >= banker.getScore()) {
                            System.out.println("\n" + p1.getName() + " won " + p1.getWager() + " chips!");
                            p1.setChips(banker);
                        } else if (p1.getScore() == 0 || p1.getScore() < banker.getScore()) {
                            System.out.println("\n" + p1.getName() + " lost " + p1.getWager() + " chips!");
                            banker.setChips(p1);
                        }
                    }

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.out.println("error");
                    }

                    if (!p2.gameOver()) {
                        System.out.print("\n" + p2.getName() + "'s turn! Score higher than " + banker.getScore() + "\nPress enter to roll");
                        scan.nextLine();
                        p2.playerRoll(die, scan);
                        Die.printScore(p2.getScore());
                        if (p2.getScore() == 7 || p2.getScore() >= banker.getScore()) {
                            System.out.println("\n" + p2.getName() + " won " + p2.getWager() + " chips!");
                            p2.setChips(banker);
                        } else if (p2.getScore() == 0 || p2.getScore() < banker.getScore()) {
                            System.out.println("\n" + p2.getName() + " lost " + p2.getWager() + " chips!");
                            banker.setChips(p2);
                        }
                    }

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.out.println("error");
                    }

                    if (!p3.gameOver()) {
                        System.out.print("\n" + p3.getName() + "'s turn! Score higher than " + banker.getScore() + "\nPress enter to roll");
                        scan.nextLine();
                        p3.playerRoll(die, scan);
                        Die.printScore(p3.getScore());
                        if (p3.getScore() == 7 || p3.getScore() >= banker.getScore()) {
                            System.out.println("\n" + p3.getName() + " won " + p3.getWager() + " chips!");
                            p3.setChips(banker);
                        } else if (p3.getScore() == 0 || p3.getScore() < banker.getScore()) {
                            System.out.println("\n" + p3.getName() + " lost " + p3.getWager() + " chips!");
                            banker.setChips(p3);
                        }
                    }

                }

            }
        }

        if (banker.gameOver()) {
            System.out.println("The players have broken the bank!");
            if (p1.getChips() > p2.getChips() && p1.getChips() > p3.getChips()) {
                System.out.println(p1.getName() + " has won the game!");
            } else if (p2.getChips() > p3.getChips() && p2.getChips() > p1.getChips()) {
                System.out.println(p2.getName() + " has won the game!");
            } else if (p3.getChips() > p2.getChips() && p1.getChips() < p3.getChips()) {
                System.out.println(p3.getName() + " has won the game!");
            }
        } else {
            System.out.println("The banker has won!");
        }

    }
}

