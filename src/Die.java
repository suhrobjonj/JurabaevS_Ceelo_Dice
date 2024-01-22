public class Die {
    private int sides = 0;
    private int result;

    public Die() {
        sides = 6;
    }

    public static int checkRoll(int[] roll) {
        // checking 456
        if (roll[0] == 4 || roll[1] == 4 || roll[2] == 4) {
            if (roll[0] == 5 || roll[1] == 5 || roll[2] == 5) {
                if (roll[0] == 6 || roll[1] == 6 || roll[2] == 6) {
                    return 7;
                }
            }
        }

        // checking if all rolls are equal
        if (roll[0] == roll[1] && roll[1] == roll[2]) {
            return 7;
        }

        // checking 123
        if (roll[0] == 1 || roll[1] == 1 || roll[2] == 1) {
            if (roll[0] == 2 || roll[1] == 2 || roll[2] == 2) {
                if (roll[0] == 3 || roll[1] == 3 || roll[2] == 3) {
                    return 0;
                }
            }
        }

        // checking doubles
        if (roll[0] == roll[1] && roll[0] != roll[2]) {
            return roll[2];
        }
        if (roll[1] == roll[2] && roll[1] != roll[0]) {
            return roll [0];
        }
        if (roll[2] == roll[0] && roll[2] != roll[1]) {
            return roll [1];
        }

        // checking if none of the rolls are equal
        if (roll[0] != roll[1] && roll[1] != roll[2] && roll[0] != roll[2]) {
            return -1;
        }
        return -1;
    }

    // method used to print the message corresponding to a score
    public static void printScore(int score) {
        if (score == 7) {
            System.out.println("\nAutomatic Win!");
        } else if (score == 0) {
            System.out.println("\nAutomatic Loss!");
        } else {
            System.out.println("\nScore of " + score);
        }
    }

    public int getResult() {
        return result;
    }

    // random number from 1 to 6
    public void roll() {
        result = (int) (Math.random() * 6) + 1;
    }

    // one of the main static methods used to calculate the score corresponding to the rules
    // 7 (win) 0 (loss) 1-6 (score) -1 (roll again)


}
