import java.util.*;
import java.awt.*;

public class Camera {
    private ArrayList<Entity> players;
    private Rectangle bounds;

    private double positionUpdateSpeed = 5;

    private int posShiftX = 512;
    private int posShiftY = 384;

    private int targetX;
    private int targetY;

    public Camera(ArrayList<Entity> players) {
        this.players = players;
        bounds = new Rectangle(0, 0, 1024, 768);
    }

    // Calculates the average of the locations of the player, and calculates a
    // corresponding x-shift and y-shift
    // to create a camera that follows the players.

    private void calculateLocation() {
        int totalX = 0, totalY = 0;
        int pX = 0, pY = 0;

        for (Entity p : players) {
            if (!bounds.contains(p.getX(), p.getY())) {
                pX = clamp(p.getX(), 0, 1024);
                pY = clamp(p.getY(), 0, 768);
            } else {
                pX = p.getX();
                pY = p.getY();
            }

            totalX += pX;
            totalY += pY;
        }
        targetX = totalX / players.size();
        targetY = totalY / players.size();
    }

    private void calculateZoom() {

    }

    // General function that clamps the values for a integer within a specified
    // range
    private static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    private void move() {
        // Updating X
        if (targetX - posShiftX > 5) {
            posShiftX += positionUpdateSpeed;
        } else if (targetX - posShiftX < -5) {
            posShiftX -= positionUpdateSpeed;
        }

        // Updating Y
        if (targetY - posShiftY > 5) {
            posShiftY += positionUpdateSpeed;
        } else if (targetY - posShiftY < -5) {
            posShiftY -= positionUpdateSpeed;
        }

    }

    // Logic is done with normal x and y values, but the user sees the game
    // outputted with a coordinate shift to create the
    // illusion of a camera
    public int getPosShiftX() {
        return -(posShiftX - 512);
    }

    public int getPosShiftY() {
        return -(posShiftY - 384);
    }

    public void update(Graphics g) {
        bounds = new Rectangle(0 + getPosShiftX(), 0 + getPosShiftY(), 1024, 768);
        g.setColor(Color.blue);
        g.drawRect(0 + getPosShiftX(), 0 + getPosShiftY(), 1024, 768);
        calculateLocation();
        move();
    }
}