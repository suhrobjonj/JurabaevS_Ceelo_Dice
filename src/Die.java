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
}
