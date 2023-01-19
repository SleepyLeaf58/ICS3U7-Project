/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for AI
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Computer extends Warrior {
    private Entity p;
    int targetX, targetY;
    int jumpCap = -19; // Stopgap Solution for broken computer jumping

    public Computer(int x, int y, ArrayList<Tile> platMap, ArrayList<Tile> stageMap, Camera c, Color color, Entity p) {
        super(x, y, platMap, stageMap, c, color);
        this.p = p;
    }

    private void move() {
        targetX = p.getX();
        targetY = p.getY();

        // Update X
        if (hitStun == 0 && !specAttacking) {
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
        } else if (!attacking) {
            attacking = true;
        }
    }

    // Since this class extends the player, we override the keyboard functions
    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void update(Graphics g) {
        if (ticks % 40 == 0) { // Increase reaction time
            move();
            attack();
        }
        super.update(g);
    }
}
