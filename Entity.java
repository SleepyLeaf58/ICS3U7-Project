/*
 * Frank Huang
 * 1/18/2023
 * For ICS3U7 Ms.Strelkovska
 * Entity class that includes all combatants in the game
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

    protected double KBSpeed = 0.4;

    protected int gravity = 1;
    protected int jumpHeight = 20;

    protected int jumpCount = 2;

    protected Vector2 dir = new Vector2();
    protected ArrayList<Tile> platMap;
    protected ArrayList<Tile> stageMap;
    protected ArrayList<Tile> allMap = new ArrayList<Tile>();

    protected char orientation = 'l';
    protected String status = "";

    protected int xShiftR;
    protected int xShiftL;
    protected int yShift;

    protected Camera c;

    protected int drawX = 0, drawY = 0;

    protected double percent = 0;
    protected double distance = 0;
    protected int hitStun = 0;
    protected int gravityCap = 20;

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

    public Vector2 getDir() {
        return dir;
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
                return true;
            }
        }

        return false;
    }

    protected void applyGravity() {
        if (dir.getY() < gravityCap)
            dir.incrementY(gravity);
        y += dir.getY();
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char c) {
        orientation = c;
    }

    public double getPercent() {
        return percent;
    }

    public void resetPercent() {
        percent = 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Calculating Knockback Distance
    public double calcKB(double damage, double weight, double scalingKB, double baseKB) {
        double distance = ((((percent / 10 + percent * damage / 20) * 200 / (weight + 100) * 1.4) + 18) * scalingKB)
                + baseKB;
        this.distance = distance;
        return distance;
    }

    // Applying Knockback
    public void applyKB(Hitbox h, char side) {

        double baseKB = h.getData()[3];
        double scalingKB = h.getData()[4];
        double angle = Math.toRadians(h.getData()[5]);
        double damage = h.getData()[6];
        double weight = 100;

        double xRatio = 0;
        double yRatio = 0;
        double xDist = 0;
        double yDist = 0;
        double dist = calcKB(damage, weight, scalingKB, baseKB);

        dir.setX(0);
        dir.setY(0);

        xDist = dist * Math.cos(angle);
        yDist = dist * Math.sin(angle);

        hitStun = (int) (dist * 0.4);
        percent += damage;
        
        xRatio = xDist/hitStun;
        yRatio = yDist/hitStun;

        if (side == 'r') {
            dir.setX(xRatio);
            setOrientation('l');
        }

        else {
            dir.setX(-xRatio);
            setOrientation('r');
        }

        dir.setY(-yRatio);
        System.out.println(xDist + " " + yDist);
        System.out.println(dir.getX() + " " + dir.getY());
    }

    public void applyKB(Projectile p, char side) {
        isHit = true;

        double baseKB = p.getData()[0];
        double scalingKB = p.getData()[1];
        double angle = Math.toRadians(p.getData()[2]);
        double damage = p.getData()[3];
        int weight = 100;

        double xRatio = 0;
        double yRatio = 0;
        double xDist = 0;
        double yDist = 0;
        double dist = calcKB(damage, weight, scalingKB, baseKB);

        dir.setX(0);
        dir.setY(0);

        xDist = dist * Math.cos(angle);
        yDist = dist * Math.sin(angle);

        hitStun = (int) (dist * 0.4);
        percent += damage;
        
        xRatio = xDist/hitStun;
        yRatio = yDist/hitStun;

        if (side == 'r')
            dir.setX(xRatio);
        else
            dir.setX(-xRatio);
        dir.setY(-yRatio);
    }

    public void update(Graphics g) {
        if (hitStun > 0) {
            x += dir.getX() * KBSpeed;
            y += dir.getY() * KBSpeed;
        }

        if (onGround()) {
            hitStun = 0;
        }
        drawX = x + c.getPosShiftX();
        drawY = y + c.getPosShiftY();

        if (hitStun > 0)
            hitStun--;

        horizontalCollisions();
        applyGravity();
        verticalCollisions();

        drawSprite(g);

        if (onGround() && status.equals("jumping"))
            jumpCount = 1;
        else if (onGround())
            jumpCount = 2;
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
