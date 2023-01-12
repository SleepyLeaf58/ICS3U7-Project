import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Computer extends Warrior {
    private Entity p;
    int targetX, targetY;

    public Computer(int x, int y, ArrayList<Tile> platMap, ArrayList<Tile> stageMap, Camera c, Entity p) {
        super(x, y, platMap, stageMap, c);
        this.p = p;
    }

    private void moveX() {
        targetX = p.getX();
        targetY = p.getY();

        // Update X
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

        // Update
    }

    private void moveY() {
        if ((onGround() || jumpCount > 0) && targetY - y < -40) {
            if (jumpCount == 1)
                dir.setY(0);
            dir.incrementY(-jumpHeight);
            jumpCount--;
        }
    }

    private void attack() {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void update(Graphics g) {
        moveX();
        moveY();
        attack();
        super.update(g);
    }
}
