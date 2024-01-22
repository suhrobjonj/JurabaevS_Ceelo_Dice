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
    private boolean gameOver = false;

    // introduction to the game with rules
    // getting Players' names
    // setting initial values for each object
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

    // main logic utilizing all of the classes o a functioning game
    public void play() {
        // double while loops
        while (!gameOver) { // option to play again
            while (!banker.gameOver() && !(p1.gameOver() && p2.gameOver() && p3.gameOver())) { // checking if the individual game is over
                ConsoleUtility.clearScreen();
                // printing stats
                System.out.println(ConsoleUtility.YELLOW + "\n****************\nStats:");
                System.out.println("Banker: " + banker.getChips());
                System.out.println(p1);
                System.out.println(p2);
                System.out.println(p3);
                System.out.println("************\n");
                ConsoleUtility.shortWait();

                // printing the round number and getting wager amount from each player
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

                // banker's turn
                System.out.println(ConsoleUtility.BLUE + "Banker's turn!");
                banker.roll(die);
                if (banker.getScore() == 0) { // loss
                    System.out.println("\nAutomatic Loss!\nBanker loses and pays each player the wagered amount!");
                    if (!p1.gameOver()) {
                        p1.setChips(banker);
                    }
                    if (!p2.gameOver()) {
                        p2.setChips(banker);
                    }
                    if (!p3.gameOver()) {
                        p3.setChips(banker);
                    }
                    ConsoleUtility.longWait();
                } else if (banker.getScore() == 7) { // win
                    System.out.println("\nAutomatic Win!\nBanker wins and takes each player's wagered amount!");
                    banker.setChips(p1);
                    banker.setChips(p2);
                    banker.setChips(p3);
                    ConsoleUtility.longWait();
                } else { // numeric score
                    scan.nextLine();
                    System.out.println(ConsoleUtility.RESET + "\nBanker has a score of " + banker.getScore());
                    System.out.println("\nRoll higher than the banker to earn your wagered amount!");
                    ConsoleUtility.longWait();
                    ConsoleUtility.clearScreen();

                    // Player 1's turn
                    if (!banker.gameOver()) {
                        if (!p1.gameOver()) {
                            System.out.print("\n" + p1.getName() + "'s turn! Score higher than or equal to " + banker.getScore() + "\nPress enter to roll");
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
                        // Player 2's turn
                        if (!p2.gameOver()) {
                            ConsoleUtility.longWait();
                            ConsoleUtility.clearScreen();
                            System.out.print("\n" + p2.getName() + "'s turn! Score higher than or equal to " + banker.getScore() + "\nPress enter to roll");
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
                        // Player 3's turn
                        if (!p3.gameOver()) {
                            ConsoleUtility.longWait();
                            ConsoleUtility.clearScreen();
                            System.out.print("\n" + p3.getName() + "'s turn! Score higher than or equal to " + banker.getScore() + "\nPress enter to roll");
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
                        ConsoleUtility.longWait();
                        ConsoleUtility.clearScreen();
                    }

                }
            }

            // game is over
            ConsoleUtility.longWait();
            ConsoleUtility.clearScreen();

            // printing results
            if (banker.gameOver()) {
                System.out.println("The players have broken the bank!\n");
                if (p1.getChips() > p2.getChips() && p1.getChips() > p3.getChips()) {
                    System.out.println(p1.getName() + " has won the game!\n");
                } else if (p2.getChips() > p3.getChips() && p2.getChips() > p1.getChips()) {
                    System.out.println(p2.getName() + " has won the game!\n");
                } else if (p3.getChips() > p2.getChips() && p1.getChips() < p3.getChips()) {
                    System.out.println(p3.getName() + " has won the game!\n");
                }
            } else {
                System.out.println("The banker has won!");
            }
            // option to play again
            System.out.print("Play again? (y/n) ");
            if (scan.nextLine().equals("y")) {
                gameOver = false;
                resetGame();
            } else {
                gameOver = true;
            }
        }

    }

    // sets the banker and players to default value in case of playing again
    private void resetGame() {
        banker = new Banker();
        p1 = new Player(p1.getName());
        p2 = new Player(p2.getName());
        p3 = new Player(p3.getName());
    }
}

