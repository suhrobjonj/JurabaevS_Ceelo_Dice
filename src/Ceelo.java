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
        System.out.println("Banker's turn!");
        int[] banker_roll = banker.playerRoll(die);
        System.out.print("Banker rolled a ");
        for (int i : banker_roll) {
            System.out.print(i + " ");
        }
        while (checkRoll(banker_roll)) {
            banker_roll = banker.playerRoll(die);
            System.out.print("\nBanker rolled a ");
            for (int i : banker_roll) {
                System.out.print(i + " ");
            }
        }
    }

    private boolean checkRoll(int[] roll) {
        if (roll[0] == 4 || roll[1] == 4 || roll[2] == 4) {
            if (roll[0] == 5 || roll[1] == 5 || roll[2] == 5) {
                if (roll[0] == 6 || roll[1] == 6 || roll[2] == 6) {
                    return false;
                }
            }
        }

        if (roll[0] == 1 || roll[1] == 1 || roll[2] == 1) {
            if (roll[0] == 2 || roll[1] == 2 || roll[2] == 2) {
                if (roll[0] == 3 || roll[1] == 3 || roll[2] == 3) {
                    return false;
                }
            }
        }

        if (roll[0] != roll[1] && roll[1] != roll[2] && roll[0] != roll[2]) {
            return true;
        }

        return false;
    }
}
