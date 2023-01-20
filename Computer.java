/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for the AI player
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Computer extends Warrior { // Extension of the Warrior Class
    private Entity p; // The human player that the computer follows
    int targetX, targetY; // Target X and Y positions
    int jumpCap = -19; // Stopgap Solution for broken computer jumping

    public Computer(int x, int y, ArrayList<Tile> platMap, ArrayList<Tile> stageMap, Camera c, Color color, Entity p) {
        super(x, y, platMap, stageMap, c, color);
        this.p = p;
    }

    // Movement
    private void move() {
        targetX = p.getX(); // The computer follows the player position
        targetY = p.getY();

        // Update X
        if (hitStun == 0 && !specAttacking) { // If not stunned and not doing a special attack
            // Ensuring the computer does not follow the player off stage
            if (x <= 180) {
                dir.setX(1);
                if (orientation == 'l')
                    speed = 8;
                if (onGround())
                    orientation = 'r';
            } else if (x >= 800) {
                dir.setX(-1);
                if (orientation == 'r')
                    speed = 8;
                if (onGround())
                    orientation = 'l';
            } else {
                // Movement towards Player
                if (targetX - x > 40) {
                    dir.setX(1);
                    if (orientation == 'l')
                        speed = 8;
                    if (onGround())
                        orientation = 'r';
                } else if (targetX - x < -32) {
                    dir.setX(-1);
                    if (orientation == 'r')
                        speed = 8;
                    if (onGround())
                        orientation = 'l';
                } else
                    dir.setX(0);
            }

            // Update Y
            if ((onGround() || jumpCount > 0) && targetY - y < -20) {
                if (jumpCount == 1)
                    dir.setY(0);
                if (dir.getY() > jumpCap)
                    dir.incrementY(-jumpHeight);
                jumpCount--;
            }
        }
    }

    // Commands the computer to attack
    private void attack() {
        if (dir.getX() == 0 && !specAttacking && hitStun == 0) {
            specAttacking = true;
            swordBeam.launch();
        } else if (!attacking && hitStun == 0) {
            attacking = true;
        }
    }

    // Since this class extends the player, we override the keyboard functions
    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void update(Graphics g) {
        if (ticks % 10 == 0) { // Decreases computer reaction time to change difficulty
            move(); // Currently, it reacts once every 10 frames, but this value can be changed.
            attack();
        }

        super.update(g);
    }
}
