public class Vector2 {
    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Vector2() {
        this(0, 0);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrementY(int increment) {
        this.y += increment;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
