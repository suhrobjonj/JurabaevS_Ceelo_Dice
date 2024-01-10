import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class Ceelo {
    private Player p1;
    private Player p2;
    private Player p3;
    private Banker banker;
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
        scan.nextLine();
        System.out.print("Player 1: ");
        p1.setWager(scan.nextInt());
        System.out.print("Player 2: ");
        p2.setWager(scan.nextInt());
        System.out.print("Player 3: ");
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
            System.out.println("Banker loses and pays each player the wagered amount!");
            p1.setChips(p1.getWager());
            p2.setChips(p2.getWager());
            p3.setChips(p3.getWager());
        } else if (banker_score == 7) {
            System.out.println("Banker wins and takes each player's wagered amount!");
            banker.setChips(p1.getWager());
            banker.setChips(p2.getWager());
            banker.setChips(p3.getWager());
        } else {
            System.out.println("Banker has a score of " + banker_score);
            System.out.println("Roll higher than the banker to earn your wagered amount!");

            System.out.println("Player 1's turn!");
            int[] p1_roll = p1.playerRoll(die);
            System.out.print("Player 1 rolled a ");
            for (int i : p1_roll) {
                System.out.print(i + " ");
            }
            int p1_score = checkRoll(p1_roll);
            while (p1_score == -1) {
                banker_roll = banker.playerRoll(die);
                System.out.print("\nYou rolled a ");
                for (int i : banker_roll) {
                    System.out.print(i + " ");
                }
                p1_score = checkRoll(banker_roll);
            }
            if (p1_score == 7 || p1_score > banker_score) {
                System.out.println("You win!");
                p1.setChips(p1.getWager());
            } else if (p1_score == 0 || p1_score < banker_score) {
                System.out.println("You lose!");
                banker.setChips(p1.getWager());
            }

        }

    }

    private int checkRoll(int[] roll) {
        if (roll[0] == 4 || roll[1] == 4 || roll[2] == 4) {
            if (roll[0] == 5 || roll[1] == 5 || roll[2] == 5) {
                if (roll[0] == 6 || roll[1] == 6 || roll[2] == 6) {
                    return 7;
                }
            }
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
