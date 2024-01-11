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

    public Ceelo() {
        System.out.println("Welocome to Ceelo Dice!");
        System.out.print("Enter your name Player 1: ");
        p1 = new Player(scan.nextLine());
        System.out.print("Enter your name Player 2: ");
        p2 = new Player(scan.nextLine());
        System.out.print("Enter your name Player 3: ");
        p3 = new Player(scan.nextLine());
        banker = new Banker();
    }

    public void play() {
        System.out.println("Enter wagers");
        System.out.print(p1.getName() + ": ");
        p1.setWager(scan.nextInt());
        System.out.print(p2.getName() + ": ");
        p2.setWager(scan.nextInt());
        System.out.print(p3.getName() + ": ");
        p3.setWager(scan.nextInt());

        System.out.println("Banker's turn!");
        int[] banker_roll = banker.playerRoll(die);
        System.out.print("Banker rolled a ");
        for (int i : banker_roll) {
            System.out.print(i + " ");
        }
        int banker_score = checkRoll(banker_roll);
        while (banker_score == -1) {
            banker_roll = banker.playerRoll(die);
            System.out.print("\nBanker rolled a ");
            for (int i : banker_roll) {
                System.out.print(i + " ");
            }
            banker_score = checkRoll(banker_roll);
        }

        if (banker_score == 0) {
            System.out.println("\nBanker loses and pays each player the wagered amount!");
            p1.setChips(p1.getWager());
            p2.setChips(p2.getWager());
            p3.setChips(p3.getWager());
            banker.setChips(-(p1.getWager() + p2.getWager() + p3.getWager()));
        } else if (banker_score == 7) {
            System.out.println("\nBanker wins and takes each player's wagered amount!");
            banker.setChips(p1.getWager() + p2.getWager() + p3.getWager());
            p1.setChips(-p1.getWager());
            p2.setChips(-p2.getWager());
            p3.setChips(-p3.getWager());
        } else {
            scan.nextLine();
            System.out.println("\nBanker has a score of " + banker_score);
            System.out.println("\nRoll higher than the banker to earn your wagered amount!");

            System.out.println(p1.getName() + "'s turn! Press enter to roll");
            scan.nextLine();
            int[] p1_roll = p1.playerRoll(die);
            System.out.print(p1.getName() + " rolled a ");
            for (int i : p1_roll) {
                System.out.print(i + " ");
            }
            int p1_score = checkRoll(p1_roll);
            while (p1_score == -1) {
                System.out.print("Keep rolling");
                scan.nextLine();
                p1_roll = p1.playerRoll(die);
                System.out.print("\n" + p1.getName() + " rolled a ");
                for (int i : p1_roll) {
                    System.out.print(i + " ");
                }
                p1_score = checkRoll(p1_roll);
            }

            if (p1_score == 7 || p1_score >= banker_score) {
                System.out.println("You win!");
                p1.setChips(p1.getWager());
                banker.setChips(-p1.getWager());
            } else if (p1_score == 0 || p1_score < banker_score) {
                System.out.println("You lose!");
                banker.setChips(p1.getWager());
                p1.setChips(-p1.getWager());
            }

            System.out.println(p2.getName() + "'s turn! Press enter to roll");
            scan.nextLine();
            int[] p2_roll = p2.playerRoll(die);
            System.out.print(p2.getName() + " rolled a ");
            for (int i : p2_roll) {
                System.out.print(i + " ");
            }
            int p2_score = checkRoll(p2_roll);
            while (p2_score == -1) {
                System.out.print("Keep rolling");
                scan.nextLine();
                p2_roll = p2.playerRoll(die);
                System.out.print("\n" + p2.getName() + " rolled a ");
                for (int i : p2_roll) {
                    System.out.print(i + " ");
                }
                p2_score = checkRoll(p2_roll);
            }

            if (p2_score == 7 || p2_score >= banker_score) {
                System.out.println("You win!");
                p2.setChips(p2.getWager());
                banker.setChips(-p2.getWager());
            } else if (p1_score == 0 || p1_score < banker_score) {
                System.out.println("You lose!");
                banker.setChips(p2.getWager());
                p2.setChips(-p2.getWager());
            }


            System.out.println(p3.getName() + "'s turn! Press enter to roll");
            scan.nextLine();
            int[] p3_roll = p3.playerRoll(die);
            System.out.print(p3.getName() + " rolled a ");
            for (int i : p3_roll) {
                System.out.print(i + " ");
            }
            int p3_score = checkRoll(p3_roll);
            while (p3_score == -1) {
                System.out.print("Keep rolling");
                scan.nextLine();
                p3_roll = p3.playerRoll(die);
                System.out.print("\n" + p3.getName() + " rolled a ");
                for (int i : p3_roll) {
                    System.out.print(i + " ");
                }
                p3_score = checkRoll(p3_roll);
            }

            if (p3_score == 7 || p3_score >= banker_score) {
                System.out.println("You win!");
                p3.setChips(p3.getWager());
                banker.setChips(-p3.getWager());
            } else if (p3_score == 0 || p1_score < banker_score) {
                System.out.println("You lose!");
                banker.setChips(p3.getWager());
                p3.setChips(-p3.getWager());
            }

        }

        System.out.println("Round Stats:");
        System.out.println("Banker: " + banker.getChips());
        System.out.println("Player 1: " + p1.getChips());
        System.out.println("Player 2: " + p2.getChips());
        System.out.println("Player 3: " + p3.getChips());

    }

    private int checkRoll(int[] roll) {
        if (roll[0] == 4 || roll[1] == 4 || roll[2] == 4) {
            if (roll[0] == 5 || roll[1] == 5 || roll[2] == 5) {
                if (roll[0] == 6 || roll[1] == 6 || roll[2] == 6) {
                    return 7;
                }
            }
        }

        if (roll[0] == roll[1] && roll[1] == roll[2]) {
            return 7;
        }

        if (roll[0] == 1 || roll[1] == 1 || roll[2] == 1) {
            if (roll[0] == 2 || roll[1] == 2 || roll[2] == 2) {
                if (roll[0] == 3 || roll[1] == 3 || roll[2] == 3) {
                    return 0;
                }
            }
        }

        if (roll[0] == roll[1] && roll[0] != roll[2]) {
            return roll[0];
        }

        if (roll[1] == roll[2] && roll[1] != roll[0]) {
            return roll [1];
        }

        if (roll[2] == roll[0] && roll[2] != roll[1]) {
            return roll [2];
        }

        if (roll[0] != roll[1] && roll[1] != roll[2] && roll[0] != roll[2]) {
            return -1;
        }

        return -1;
    }
}
