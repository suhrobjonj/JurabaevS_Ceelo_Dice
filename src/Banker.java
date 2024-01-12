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

    public boolean gameOver() {
        if (chips <= 0) {
            return true;
        }
        return false;
    }

    public void playerRoll(Die die) {
        int[] rolls = new int[3];
        for (int i = 0; i < rolls.length; i++) {
            die.roll();
            rolls[i] = die.getResult();
        }
        System.out.print("Banker rolled a ");
        for (int i : rolls) {
            System.out.print(i + " ");
        }
        score = Die.checkRoll(rolls);
        while (score == -1) {
            for (int i = 0; i < rolls.length; i++) {
                die.roll();
                rolls[i] = die.getResult();
            }
            System.out.print("\nBanker rolled a ");
            for (int i : rolls) {
                System.out.print(i + " ");
            }
            score = Die.checkRoll(rolls);
        }
    }
}
