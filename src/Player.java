import java.util.Scanner;
public class Player {

    private String name;
    private int chips;
    private int score;
    private int wager;

    public Player(String name) {
        this.name = name;
        chips = 100;
    }

    public String getName() {
        return name;
    }
    public int getWager() {
        return wager;
    }
    public int getChips() {
        return chips;
    }
    public int getScore() {
        return score;
    }
    public void setChips(int diff) {
        chips += diff;
    }
    public void setWager(Scanner s) {
        System.out.print(name + ": ");
        wager = s.nextInt();
        while (wager > chips) {
            System.out.println("Can't bet more than what you have!");
            System.out.print(name + ": ");
            wager = s.nextInt();
        }

    }

    public boolean gameOver() {
        if (chips <= 0) {
            return true;
        }
        return false;
    }

    public void playerRoll(Die die, Scanner s) {
        int[] rolls = new int[3];
        for (int i = 0; i < rolls.length; i++) {
            die.roll();
            rolls[i] = die.getResult();
        }
        System.out.print(name+ " rolled a ");
        for (int i : rolls) {
            System.out.print(i + " ");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        score = Die.checkRoll(rolls);
        while (score == -1) {
            s.nextLine();
            for (int i = 0; i < rolls.length; i++) {
                die.roll();
                rolls[i] = die.getResult();
            }
            System.out.print(name + " rolled a ");
            for (int i : rolls) {
                System.out.print(i + " ");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
            score = Die.checkRoll(rolls);
        }
    }


}
