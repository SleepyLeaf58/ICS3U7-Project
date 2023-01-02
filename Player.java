
/*

 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends Sprite {

    private double speed = 8;
    final private double accel = 0.15;
    private int max = 11;

    private int gravity = 1;
    private int jumpHeight = 20;
    private int percent = 0;

    private int jumpCount = 2;

    private Vector2 dir = new Vector2();
    private ArrayList<Tile> tileMap;

    private Animation idle;
    private Animation running;
    private Animation jumping;
    private Animation falling;
    private Animation slashing;

    private char orientation = 'l';
    private String status;
    private final int xShiftR = 50;
    private final int xShiftL = 30;
    private final int yShift = 50;

    private char move;

    private boolean attacking = false;

    // Timer for abilities
    private int ticks = 0;


    private Projectile swordBeam;

    public Player(int x, int y, ArrayList<Tile> tileMap) {
        super(x, y, 45, 70);
        this.tileMap = tileMap;

        idle = new Animation("Images/Player/Idle/Idle_", 32);
        running = new Animation("Images/Player/Running/Running_", 24);
        jumping = new Animation("Images/Player/Jumping/Jumping_", 28);
        falling = new Animation("Images/Player/Falling/Falling_", 12);
        slashing = new Animation("Images/Player/Slashing/Slashing_", 20);
        swordBeam = new Projectile (this, 40, 75, 8, "Images/Player/swordBeam");

        idle.load();
        running.load();
        jumping.load();
        falling.load();
        slashing.load();

    }

    public void keyPressed(KeyEvent e) {
        if (!attacking) {
            // Movement Handling
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                dir.setX(-1);
                if (orientation == 'r')
                    speed = 8;
                if (onGround())
                    orientation = 'l';
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                dir.setX(1);
                if (orientation == 'l')
                    speed = 8;
                if (onGround()) {
                    orientation = 'r';
                }
            } else if ((onGround() || jumpCount > 0) && e.getKeyCode() == KeyEvent.VK_UP) {
                if (jumpCount == 1)
                    dir.setY(0);
                dir.incrementY(-jumpHeight);
                jumpCount--;
            } 
        }

        attackHandler(e);
    }

   

    // prevent user input during attack animation 
    private void attackHandler(KeyEvent e) {
        if (!attacking && e.getKeyCode() == KeyEvent.VK_O) {
            attacking = true;
            swordBeam.launch();
        }
    }

    private void attackAnimate () {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            dir.setX(0);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            dir.setX(0);

    }

    private void getStatus() {
        if (dir.getY() > 0)
            status = "fall";
        else if (dir.getY() < 0)
            status = "jump";
        else {
            if (dir.getX() != 0)
                status = "run";
            else {
                if (move == '1')
                    status = "slash";
                else
                    status = "idle";
            }
        }
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

    private void speed_accel() {

        if (onGround() && dir.getX() != 0) {
            if (speed < max)
                speed += accel;

        } else {
            speed = 8;
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
        if (status.equals("idle") && onGround()) {
            image = idle.getNextFrame(true);
            jumping.setCnt(0);
            falling.setCnt(0);
        } else if (status.equals("run") && onGround()) {
            image = running.getNextFrame(true);
            jumping.setCnt(0);
            falling.setCnt(0);
        } else if (status.equals("jump")) {
            image = jumping.getNextFrame(false);
            falling.setCnt(0);
        } else if (status.equals("fall")) {
            image = falling.getNextFrame(false);
            jumping.setCnt(0);
        }

        else if (status.equals("slash")) {
            image = slashing.getNextFrame(false);
        }
    }

    public char getOrientation() {
        return orientation;
    }

   
    public void update(Graphics g) {
        x += dir.getX() * speed;

        getStatus();
        animate();

        // Only allows for 1 midair jump
        if (!onGround() && jumpCount == 2)
            jumpCount--;

        applyGravity();
        verticalCollisions();

        drawSprite(g);
        speed_accel();

        if (attacking && ticks < 20) 
            ticks++;
        if (ticks == 20) {
            ticks = 0;
            attacking = false;
        }
    }

    public void drawSprite(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(x, y, width, height);
        if (orientation == 'r')
            g.drawImage(image, x - xShiftR, y - yShift, 160, 125, null);
        else
            g.drawImage(image, x + 125 - xShiftL, y - yShift, -160, 125, null);    

        swordBeam.draw(g);
    }
}
