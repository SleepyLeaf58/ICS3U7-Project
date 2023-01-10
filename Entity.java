/*

 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Entity extends Sprite {
    protected int imgWidth;
    protected int imgHeight;

    protected double speed;
    protected double accel;
    protected int maxSpeed;

    protected int gravity = 1;
    protected int jumpHeight = 20;
    protected int percent;

    protected int jumpCount = 2;

    protected Vector2 dir = new Vector2();
    protected ArrayList<Tile> platMap;
    protected ArrayList<Tile> stageMap;
    protected ArrayList<Tile> allMap = new ArrayList<Tile>();

    protected char orientation = 'l';

    protected int xShiftR;
    protected int xShiftL;
    protected int yShift;

    protected Camera c;

    protected int drawX = 0, drawY = 0;

    public Entity(int x, int y, int width, int height, int imgWidth, int imgHeight, ArrayList<Tile> platMap,
            ArrayList<Tile> stageMap, Camera c) {
        super(x, y, width, height, c);
        this.platMap = platMap;
        this.stageMap = stageMap;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.c = c;
        allMap.addAll(platMap);
        allMap.addAll(stageMap);
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDrawX() {
        return drawX;
    }

    public int getDrawY() {
        return drawY;
    }

    private void verticalCollisions() {
        for (Tile tile : allMap) {
            if (getBounds().intersects(tile.getBounds())) {
                if (dir.getY() > 0 && y + height - tile.getTTop() < dir.getY() + 1) {
                    y = tile.getTTop() - height;
                    dir.setY(0);
                }
            }
        }

        for (Tile tile : stageMap) {
            if (getBounds().intersects(tile.getBounds())) {
                if (dir.getY() < 0) {
                    y = tile.getTBot();
                    dir.setY(0);
                }
            }
        }
    }

    private void horizontalCollisions() {
        for (Tile tile : stageMap) {
            if (getBounds().intersects(tile.getBounds())) {
                if (dir.getX() < 0) {
                    x = tile.getTRight();
                } else if (dir.getX() > 0) {
                    x = tile.getTLeft() - width;
                }
            }
        }
    }

    protected boolean onGround() {
        for (Tile tile : allMap) {
            if (y == tile.getTTop() - height) {
                jumpCount = 2;
                return true;
            }
        }
        return false;
    }

    public int getPercent() {
        return percent;
    }

    protected void applyGravity() {
        dir.incrementY(gravity);
        y += dir.getY();
    }

    public char getOrientation() {
        return orientation;
    }

    public void update(Graphics g) {
        drawX = x + c.getPosShiftX();
        drawY = y + c.getPosShiftY();

        // Only allows for 1 midair jump
        if (!onGround() && jumpCount == 2)
            jumpCount--;

        horizontalCollisions();
        applyGravity();
        verticalCollisions();

        drawSprite(g);
    }

    public void drawSprite(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(drawX, drawY, width, height);
        if (orientation == 'r')
            g.drawImage(image, drawX - xShiftR, drawY - yShift, imgWidth, imgHeight, null);
        else
            g.drawImage(image, drawX + 125 - xShiftL, drawY - yShift, -imgWidth, imgHeight, null);
    }
}