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
    public void setChips(Player p) {
        int total = 0;
        if (p.getChips() >= p.getWager()) {
            chips += p.getWager();
            p.setChips(-p.getWager());
            total = p.getWager();
            System.out.println("The banker takes " + total + " chips from " + p.getName());
            System.out.println("The player's remaining balance is: " + p.getChips());
        } else {
            chips += p.getChips();
            p.setChips(-p.getChips());
            total = p.getChips();
            System.out.println("The banker takes " + total + " chips from " + p.getName());
            System.out.println(p.getName() + " lost all of the chips!");
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("error");
        }
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
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("error");
            }

        }
        score = Die.checkRoll(rolls);
        while (score == -1) {
            for (int i = 0; i < rolls.length; i++) {
                die.roll();
                rolls[i] = die.getResult();
            }
            System.out.print("\nBanker rolled a ");
            for (int i : rolls) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("error");
                }
                System.out.print(i + " ");
            }
            score = Die.checkRoll(rolls);
        }
    }
}
