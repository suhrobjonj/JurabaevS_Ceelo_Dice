public class Player {

    private String name;
    private int chips;
    private int score;
    private int wager;

    public Player(String name) {
        this.name = name;
        chips = 100;
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
    public void setWager(int w) {
        wager = w;
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
