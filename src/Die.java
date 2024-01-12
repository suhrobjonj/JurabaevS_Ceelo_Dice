public class Die {
    private int sides = 0;
    private int result;

    public Die() {
        sides = 6;
    }

    public int getResult() {
        return result;
    }

    public void roll() {
        result = (int) (Math.random() * 6) + 1;
    }

    public static int checkRoll(int[] roll) {
        if (roll[0] == 4 || roll[1] == 4 || roll[2] == 4) {
            if (roll[0] == 5 || roll[1] == 5 || roll[2] == 5) {
                if (roll[0] == 6 || roll[1] == 6 || roll[2] == 6) {
                    return 7;
                }
            }
        }

        if (roll[0] == roll[1] && roll[1] == roll[2]) {
            return 7;
        }

        if (roll[0] == 1 || roll[1] == 1 || roll[2] == 1) {
            if (roll[0] == 2 || roll[1] == 2 || roll[2] == 2) {
                if (roll[0] == 3 || roll[1] == 3 || roll[2] == 3) {
                    return 0;
                }
            }
        }

        if (roll[0] == roll[1] && roll[0] != roll[2]) {
            return roll[0];
        }

        if (roll[1] == roll[2] && roll[1] != roll[0]) {
            return roll [1];
        }

        if (roll[2] == roll[0] && roll[2] != roll[1]) {
            return roll [2];
        }

        if (roll[0] != roll[1] && roll[1] != roll[2] && roll[0] != roll[2]) {
            return -1;
        }

        return -1;
    }


}
