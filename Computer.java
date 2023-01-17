
/*

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
        if (!specAttacking) {
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
        if (dir.getX() == 0 && !specAttacking) {
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
        move();
        attack();
        super.update(g);
    }
}
