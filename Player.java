/*

 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends Sprite{
    private int speed = 8;
    private int gravity = 1;
    private int jumpHeight = 20;
    private Vector2 dir = new Vector2();
    private ArrayList<Tile> tileMap;

    public Player(int x, int y, ArrayList<Tile> tileMap) {
        super(x, y, 50, 50);
        this.tileMap = tileMap;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            dir.setX(-1);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            dir.setX(1);
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            dir.incrementY(jumpHeight);;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            dir.setX(0);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            dir.setX(0);
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            dir.setY(0);
    }

    private void verticalCollisions() {
        for (Tile tile : tileMap) {
            if (getBounds().intersects(tile.getBounds())) {
                if (dir.getY() > 0) {
                    y = tile.getTBot() - tile.getTileHeight();
                    dir.setY(0);
                }
            }
        }
    }

    private void horizontalCollisions() {
        for (Tile tile : tileMap) {
            if (getBounds().intersects(tile.getBounds())) {
                ;
            }
        }
    }

    private void applyGravity() {
        dir.incrementY(gravity);
        y += dir.getY();
    }
    public void update(Graphics g) {
        x += dir.getX() * speed;
        applyGravity();
        verticalCollisions();
        drawSprite(g);
    }
    public void drawSprite(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(x, y, width, height);
    }
}
