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

    public void setChips(Banker b) {
        int total = 0;
        if (b.getChips() >= wager) {
            chips += wager;
            b.setChips(-wager);
            total = wager;
            System.out.println(name + " takes " + total + " chips from the banker");
        } else {
            chips += b.getChips();
            b.setChips(-b.getChips());
            total = b.getChips();
            System.out.println(name + " takes " + total + " chips from the banker");
            System.out.println("The banker is out!");
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("error");
        }

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

    public String toString() {
        String str = name;
        if (gameOver()) {
            str += " is out!";
        } else {
            str += ": " + chips;
        }
        return str;
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
