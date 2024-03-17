/*
 * Frank Huang and David Zhai
 * 1/18/2023
 * For ICS3U7 Ms.Strelkovska
 * Entity class that includes all combatants in the game
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Entity extends Sprite {
    // Image dimensions
    protected int imgWidth;
    protected int imgHeight;

    // Speed Values
    protected double speed; // Normal speed
    protected double accel; // Acceleration
    protected int maxSpeed; // Capping acceleration
    protected double KBSpeed = 1.5; // Knockback speed

    protected int gravity = 1; // Gravity decrement value
    protected int jumpHeight = 20; // Jumpheight

    protected int jumpCount = 2; // Allows for doublejump

    protected Vector2 dir = new Vector2(); // Vector2 for regular movement
    protected Vector2 KBdir = new Vector2(); // Vector2 when knocked back
    protected ArrayList<Tile> platMap; // Platforms
    protected ArrayList<Tile> stageMap; // Stage
    protected ArrayList<Tile> allMap = new ArrayList<Tile>(); // All tiles

    protected char orientation = 'l';
    protected String status = ""; // Used for animation

    protected int xShiftR; // Shifting image position to match hitboxes
    protected int xShiftL;
    protected int yShift;

    protected Camera c;

    protected int drawX = 0, drawY = 0; // Position drawn on screen

    protected double percent = 0; // Percent
    protected double distance = 0; // Distance knocked back diagonally
    protected int hitStun = 0; // Frames stunned
    protected int gravityCap = 15; // Capping gravity to prevent phasing through tiles

    protected boolean isHit = false; // Boolean used for knockback
    protected double hitDist = 0; // X distance that must be travelled
    protected double hitDistTravelled = 0; // X Distance already travelled

    public Entity(int x, int y, int width, int height, int imgWidth, int imgHeight, ArrayList<Tile> platMap,
            ArrayList<Tile> stageMap, Camera c) {
        super(x, y, width, height, c);
        this.platMap = platMap;
        this.stageMap = stageMap;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.c = c;
        allMap.addAll(platMap); // COmbination of platforms and Stage
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

    // Vertical collisions
    private void verticalCollisions() {
        // Collision from top includes all tiles
        for (Tile tile : allMap) {
            if (getBounds().intersects(tile.getBounds())) {
                if (dir.getY() > 0 && y + height - tile.getTTop() < dir.getY() + 1) { // If going downwards and position
                                                                                      // is
                    y = tile.getTTop() - height; // the top of the tile, maintain position
                    dir.setY(0); // Sets Y value of Vector2 to 0
                }
            }
        }

        // Only the stage has bot collision
        for (Tile tile : stageMap) {
            if (getBounds().intersects(tile.getBounds())) {
                if (dir.getY() < 0) { // If going upwards and position is the bottom of the tile, maintain position
                    y = tile.getTBot();
                    dir.setY(0);
                }
            }
        }
    }

    // Horizontal collisions
    private void horizontalCollisions() {
        // Only stage needs horizontal collisions
        for (Tile tile : stageMap) {
            if (getBounds().intersects(tile.getBounds())) {
                if (dir.getX() < 0) { // player moving leftwards
                    x = tile.getTRight();
                } else if (dir.getX() > 0) { // player moving rightwards
                    x = tile.getTLeft() - width;
                }
            }
        }
    }

    // Checks if player is on the ground
    protected boolean onGround() {
        for (Tile tile : allMap) {
            if (y == tile.getTTop() - height) {
                return true;
            }
        }

        return false;
    }

    // Applies gravity by constantly decrementing Y value of Vector2
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

    // Resets hitstun
    public void resetHitStun() {
        this.hitStun = 0;
    }

    // Calculating Knockback Distance with a formula from
    // https://www.ssbwiki.com/Knockback
    public double calcKB(double damage, double weight, double scalingKB, double baseKB) {
        double distance = ((((percent / 10 + percent * damage / 20) * 200 / (weight + 100) * 1.4) + 18) * scalingKB)
                + baseKB;
        this.distance = distance;
        return distance;
    }

    // Applying Knockback for hitboxes
    public void applyKB(Hitbox h, char side) {
        isHit = true;

        // Initilizing values
        double baseKB = h.getData()[3];
        double scalingKB = h.getData()[4];
        double angle = Math.toRadians(h.getData()[5]);
        double damage = h.getData()[6];
        double weight = 100;

        double xDist = 0;
        double yDist = 0;
        double dist = calcKB(damage, weight, scalingKB, baseKB);

        xDist = dist * Math.cos(angle); // x-distance is cosine of angle
        hitDist = xDist;
        yDist = dist * Math.sin(angle); // y-distance is sine of angle

        // Updates hitstun and percent
        hitStun = (int) (dist * KBSpeed);
        percent += damage;

        // Sets KB dir accordingly to create diagnonal knockback
        if (side == 'r')
            KBdir.setX(xDist / 2);
        else
            KBdir.setX(-xDist / 2);
        KBdir.setY(-yDist / 2);
    }

    public void applyKB(Projectile p, char side) {
        // Similar to previous function, but for projectiles
        isHit = true;

        double baseKB = p.getData()[0];
        double scalingKB = p.getData()[1];
        double angle = Math.toRadians(p.getData()[2]);
        double damage = p.getData()[3];
        double weight = 100;

        double xDist = 0;
        double yDist = 0;
        double dist = calcKB(damage, weight, scalingKB, baseKB);

        xDist = dist * Math.cos(angle);
        hitDist = xDist;
        yDist = dist * Math.sin(angle);

        hitStun = (int) (dist * KBSpeed);
        percent += damage;

        if (side == 'r')
            KBdir.setX(xDist / 2);
        else
            KBdir.setX(-xDist / 2);
        KBdir.setY(-yDist / 2);
    }

    public void update(Graphics g) {
        if (!onGround()) // Cancels hitstun if on ground
            hitStun = 0;

        // Increments distance travelled
        if (isHit && hitDistTravelled < hitDist) {
            x += KBdir.getX() * KBSpeed;
            y += KBdir.getY() * KBSpeed;
            hitDistTravelled += KBSpeed;
        } else {
            hitDistTravelled = 0;
            isHit = false;
            KBdir.setX(0);
            KBdir.setY(0);
        }

        // Updates the drawing coordinates
        drawX = x + c.getPosShiftX();
        drawY = y + c.getPosShiftY();

        // Decrements hitstun
        if (hitStun > 0)
            hitStun--;

        horizontalCollisions();
        applyGravity();
        verticalCollisions();

        drawSprite(g);

        // double jump mechanic
        if (onGround() && status.equals("jumping"))
            jumpCount = 1;
        else if (onGround())
            jumpCount = 2;
    }

    public void drawSprite(Graphics g) {
        // Drawing Hitbox Bounds
        // g.setColor(Color.red);
        // g.drawRect(drawX, drawY, width, height);
        if (orientation == 'r')
            g.drawImage(image, drawX - xShiftR, drawY - yShift, imgWidth, imgHeight, null);
        else
            g.drawImage(image, drawX + 125 - xShiftL, drawY - yShift, -imgWidth, imgHeight, null);
    }
}
