public class Banker {

    private int chips;
    private int score;

    public Banker() {
        chips = 1000;
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

    //method to add chips to the bank in case of win
    public void setChips(Player p) {
        int total = 0;
        if (p.getChips() >= p.getWager()) { //checking if player has enough chips
            total = p.getWager();
            chips += total;
            p.setChips(-p.getWager());
            //printing
            System.out.println("The banker takes " + total + " chips from " + p.getName());
            System.out.println("The player's remaining balance is: " + p.getChips());
        } else {
            total = p.getChips();
            chips += total;
            p.setChips(-p.getChips());
            System.out.println("The banker takes " + total + " chips from " + p.getName());
            System.out.println(p.getName() + " lost all of the chips!");
        }
        ConsoleUtility.shortWait();
    }

    //checking if banker is still in the game
    public boolean gameOver() {
        if (chips <= 0) {
            return true;
        }
        return false;
    }

    //main rolling logic, similar to the Player class
    //mainly uses static method from Die class
    public void roll(Die die) {
        int[] rolls = new int[3];
        for (int i = 0; i < rolls.length; i++) {
            die.roll(); //using the static method of the Die class
            rolls[i] = die.getResult();
        }
        System.out.print("Banker rolled a ");
        for (int i : rolls) {
            System.out.print(i + " "); //printing the three rolls
            ConsoleUtility.shortWait();
        }
        score = Die.checkRoll(rolls); // using another static method to get the score
        while (score == -1) { //making sure that the rolls are not unrelated, have no outcome
            for (int i = 0; i < rolls.length; i++) {
                die.roll();
                rolls[i] = die.getResult();
            }
            System.out.print("\nBanker rolled a ");
            for (int i : rolls) {
                ConsoleUtility.shortWait();
                System.out.print(i + " ");
            }
            score = Die.checkRoll(rolls);
        }
    }
}
