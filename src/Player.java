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

    public boolean gameOver() {
        if (chips <= 0) {
            return true;
        }
        return false;
    }

    public int[] playerRoll(Die d1, Die d2, Die d3) {
        d1.roll();
        d2.roll();
        d3.roll();

        return new int[]{d1.getResult(), d2.getResult(), d3.getResult()};
    }

}
