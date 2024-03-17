/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for dynamic camera, allowing for better tracking of players
 */

import java.util.*;
import java.awt.*;

public class Camera {
    private ArrayList<Entity> players; // Arraylist of players that need to be tracked
    // Camera bounds in which we factor the player into calculations
    // This ensures that the camera prioritizes the center of the stage
    // and prevents it from panning to players too far off stage
    private Rectangle bounds;

    private double positionUpdateSpeed = 5; // The speed at which the camera pans

    // The initial camera location
    private int camX = 512;
    private int camY = 384;

    // The target camera values where the camera must "go"
    private int targetX;
    private int targetY;

    public Camera(ArrayList<Entity> players) {
        this.players = players;
        bounds = new Rectangle(0, 0, 1024, 768);
    }

    // Calculates the average of the locations of the player, and calculates a
    // corresponding x-shift and y-shift to create a camera that follows the players
    // In a game engine, you would move the camera itself, but as Java Swing does
    // not allow for this
    // we calculate the camera position, and bind that position to the center of the
    // screen, so
    // we need to shift all other objects on the screen with corresponding values
    private void calculateLocation() {
        int totalX = 0, totalY = 0; // Total x and y positions
        int pX = 0, pY = 0;

        for (Entity p : players) {
            if (!bounds.contains(p.getX(), p.getY())) { // If the player is outside the bounds,
                pX = clamp(p.getX(), 0, 1024); // the total is instead incremented by
                pY = clamp(p.getY(), 0, 768); // the values at the edges of the bounds rectangle
            } else {
                pX = p.getX();
                pY = p.getY();
            }

            totalX += pX; // Incrementing total by player positions
            totalY += pY;
        }
        targetX = totalX / players.size();
        targetY = totalY / players.size();
    }

    // General function that clamps the values for a integer within a specified
    // range
    private static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    // Moving the camera
    private void move() {
        // If the camera location not close enough to the target location, we move
        // the camera to the location
        // Updating X
        if (targetX - camX > 5) {
            camX += positionUpdateSpeed;
        } else if (targetX - camX < -5) {
            camX -= positionUpdateSpeed;
        }

        // Updating Y
        if (targetY - camY > 5) {
            camY += positionUpdateSpeed;
        } else if (targetY - camY < -5) {
            camY -= positionUpdateSpeed;
        }

    }

    // All of the game logic is calculated with the regular X and Y values
    // Effectively, the logic is done on a static, cameraless version of the game
    // Only the viewer sees the output with the shifts, creating the illusion of a
    // camera

    // Returns x-shift
    public int getPosShiftX() {
        return -(camX - 512);
    }

    // Returns y-shift
    public int getPosShiftY() {
        return -(camY - 384);
    }

    // Updating the position of the bounds so that it stays in the same position
    // on the cameraless "version"
    public void update(Graphics g) {
        bounds = new Rectangle(0 + getPosShiftX(), 0 + getPosShiftY(), 1024, 768);

        // Drawing Camera Bounds
        // g.setColor(Color.blue);
        // g.drawRect(0 + getPosShiftX(), 0 + getPosShiftY(), 1024, 768);
        calculateLocation();
        move();
    }
}