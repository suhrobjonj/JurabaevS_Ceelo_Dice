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

    public int[] playerRoll(Die dice) {
        int[] rolls = new int[3];
        for (int i = 0; i < rolls.length; i++) {
            dice .roll();
            rolls[i] = dice.getResult();
        }
        return rolls;
    }


}
