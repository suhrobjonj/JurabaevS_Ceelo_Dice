import java.util.Scanner;
public class Player {

    private String name;
    private int chips;
    private int score;
    private int wager;

    public Player(String name) {
        this.name = name;
        chips = 200;
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
        int total = 0; //variable used later for changing the value and printing
        if (b.getChips() >= wager) { //making sure the bank has enough chips to give wagered amount
            chips += wager;
            b.setChips(-wager);
            total = wager;
            System.out.println(name + " takes " + total + " chips from the banker");
        } else {
            total = b.getChips();
            chips += total;
            b.setChips(-b.getChips());
            System.out.println(name + " takes " + total + " chips from the banker");
            System.out.println("The banker is out!");
        }
        ConsoleUtility.shortWait();

    }
    public void setWager(Scanner s) {
        System.out.print(name + ": ");
        wager = s.nextInt();
        while (wager > chips) { //making sure that the wager amount is less than or equal to the number of chips
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

    //method that returns a String containing the player's info after each round
    public String toString() {
        String str = name;
        if (gameOver()) {
            str += " is out!";
        } else {
            str += ": " + chips;
        }
        return str;
    }


    //main rolling logic, similar to the Player class
    //mainly uses static method from Die class
    public void playerRoll(Die die, Scanner s) {
        int[] rolls = new int[3];
        for (int i = 0; i < rolls.length; i++) {
            die.roll(); //using the static method of the Die class
            rolls[i] = die.getResult();
        }
        System.out.print(ConsoleUtility.CYAN + name+ " rolled a ");
        for (int i : rolls) {
            System.out.print(i + " "); //printing the three rolls
            ConsoleUtility.shortWait();
        }
        score = Die.checkRoll(rolls);
        while (score == -1) { // using another static method to get the score
            s.nextLine();
            for (int i = 0; i < rolls.length; i++) {
                die.roll();
                rolls[i] = die.getResult();
            }
            System.out.print(name + " rolled a " + ConsoleUtility.CYAN);
            for (int i : rolls) {
                System.out.print(i + " ");
                ConsoleUtility.shortWait();
            }
            score = Die.checkRoll(rolls);
        }
    }

}
