
/*

 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends Sprite {

    private int speed = 8;
    private int gravity = 1;
    private int jumpHeight = 20;
    private int percent = 0;

    private int jumpCount = 2;

    private Vector2 dir = new Vector2();
    private ArrayList<Tile> tileMap;

    private Animation idle;
    private Animation running;
    private char orientation;
    private String status;
    private final int xShiftR = 50;
    private final int xShiftL = 30;
    private final int yShift = 50;

    public Player(int x, int y, ArrayList<Tile> tileMap) {
        super(x, y, 45, 70);
        this.tileMap = tileMap;

        idle = new Animation("Images/Player/Idle/Idle_", 32);
        running = new Animation("Images/Player/Running/Running_", 24);
        idle.load();
        running.load();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dir.setX(-1);
            orientation = 'l';
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dir.setX(1);
            orientation = 'r';
        } else if ((onGround() || jumpCount > 0) && e.getKeyCode() == KeyEvent.VK_UP) {
            if (jumpCount == 1)
                dir.setY(0);
            dir.incrementY(-jumpHeight);
            jumpCount--;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            dir.setX(0);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            dir.setX(0);

    }

    private void getStatus() {
        if (dir.getX() != 0)
            status = "run";
        else
            status = "idle";
    }

    private void verticalCollisions() {
        for (Tile tile : tileMap) {
            if (getBounds().intersects(tile.getBounds())) {
                if (dir.getY() > 0) {
                    y = tile.getTTop() - 70;
                    dir.setY(0);
                }
            }
        }
    }

    private void horizontalCollisions() {
        for (Tile tile : tileMap) {
            if (getBounds().intersects(tile.getBounds())) {
            }
        }
    }

    private boolean onGround() {
        for (Tile tile : tileMap) {
            if (y == tile.getTTop() - 70) {
                jumpCount = 2;
                return true;
            }
        }
        return false;
    }

    private void applyGravity() {
        dir.incrementY(gravity);
        y += dir.getY();
    }

    private void animate() {
        if (status.equals("idle"))
            image = idle.getNextFrame();
        else if (status.equals("run"))
            image = running.getNextFrame();
    }

    public void update(Graphics g) {
        x += dir.getX() * speed;
        getStatus();
        animate();

        applyGravity();
        verticalCollisions();

        drawSprite(g);
        System.out.println(status);
    }

    public void drawSprite(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(x, y, width, height);
        if (orientation == 'r')
            g.drawImage(image, x - xShiftR, y - yShift, 160, 125, null);
        else
            g.drawImage(image, x + 125 - xShiftL, y - yShift, -160, 125, null);
    }
}
