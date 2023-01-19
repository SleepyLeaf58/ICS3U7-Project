/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for tracking movement direction
 */

public class Vector2 {
    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this(0, 0);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void incrementY(int increment) {
        this.y += increment;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
