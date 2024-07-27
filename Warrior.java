/*
* Frank Huang and David Zhai
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Warrior Subclass of Entity
*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Warrior extends Entity {

    protected Animation idle;
    protected Animation running;
    protected Animation jumping;
    protected Animation falling;
    protected Attack slashing;
    protected Attack runSlashing;
    protected Attack airSlashing;
    protected Animation hit;

    protected boolean attacking = false;
    protected boolean specAttacking = false;

    protected Projectile swordBeam;
    private ArrayList<Hitbox> activeHitboxes = new ArrayList<Hitbox>();
    private ArrayList<Projectile> activeProjectiles = new ArrayList<>();

    // Timer for attacks
    protected int ticks = 0;
    private Color color;

    // Keys
    protected int left, right, up, slash, shoot;

    public Warrior(int x, int y, ArrayList<Tile> platMap, ArrayList<Tile> stageMap, Camera c, Color color, int left, int right, int up, int slash, int shoot) {
        super(x, y, 45, 70, 160, 125, platMap, stageMap, c);

        this.color = color;
        percent = 0;
        speed = 8;
        accel = 0.15;
        maxSpeed = 11;
        xShiftL = 30;
        xShiftR = 50;
        yShift = 50;

        // Keys
        this.left = left;
        this.right = right;
        this.up = up;
        this.slash = slash;
        this.shoot = shoot;

        idle = new Animation("Images/Player/Idle/Idle_", 32);
        running = new Animation("Images/Player/Running/Running_", 24);
        jumping = new Animation("Images/Player/Jumping/Jumping_", 28);
        falling = new Animation("Images/Player/Falling/Falling_", 12);

        hit = new Animation("Images/Player/Hit/Hit_", 1);
        slashing = new Attack(this, 11, 12, 20, "Images/Player/Slashing/Slashing_", "Data/Slashing.txt");
        runSlashing = new Attack(this, 10, 12, 21, "Images/Player/Run_Slashing/Run_Slashing_", "Data/RunSlashing.txt");
        airSlashing = new Attack(this, 9, 12, 20, "Images/Player/Air_Slash/Air_Slash_", "Data/AirSlashing.txt");
        swordBeam = new Projectile(this, 40, 75, 5, 0.5, 1, 1.4, 45, 5, "Images/Player/swordBeam", c);


        idle.load();
        running.load();
        jumping.load();
        falling.load();
        hit.load();
        slashing.load();
        runSlashing.load();
        airSlashing.load();
    }

    public void keyPressed(KeyEvent e) {
        // Movement Handling
        if (!specAttacking) { // doesn't allow player to move while launching projectile
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                dir.setX(-1);
                if (orientation == 'r') {
                    speed = 8;
                }
                if (onGround())
                    orientation = 'l';
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                dir.setX(1);
                if (orientation == 'l') {
                    speed = 8;
                }
                if (onGround()) {
                    orientation = 'r';
                }
            }

            if (jumpCount > 0 && e.getKeyCode() == KeyEvent.VK_UP) {
                dir.setY(0);
                dir.incrementY(-jumpHeight);
                jumpCount--;
            }
        }

        attackHandler(e);
    }

    // prevent user from attacking again during attack animation
    protected void attackHandler(KeyEvent e) {
        if (dir.getX() == 0 && !specAttacking && e.getKeyCode() == KeyEvent.VK_W) {
            specAttacking = true;
            swordBeam.launch();
            activeProjectiles.add(swordBeam);
        } else if (!attacking && e.getKeyCode() == KeyEvent.VK_Q) {
            attacking = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            dir.setX(0);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            dir.setX(0);
    }

    // accelerates if you continue going the same direction
    protected void speed_accel() {

        if (onGround() && dir.getX() != 0) {
            if (speed < maxSpeed)
                speed += accel;

        } else {
            speed = 8;
        }
    }

    // Gets the status of the player
    protected void getStatus() {
        if (dir.getY() > 0) {
            if (attacking)
                status = "airslash";
            else
                status = "fall";
        }

        else if (dir.getY() < 0) {
            if (attacking)
                status = "airslash";
            else
                status = "jump";
        }

        else if (dir.getX() != 0 && onGround()) {
            if (attacking)
                status = "runslash";
            else
                status = "run";
        }

        else if (onGround()) {
            if (attacking)
                status = "slash";
            else
                status = "idle";

        }
    }

    // overrides getStatus in special cases
    protected boolean specialCases() {
        if (onGround() && status.equals("airslash")) {
            status = "idle";
            ticks = 20;
            return true;
        }

        if (!onGround() && status.equals("runslash")) {
            status = "fall";
            ticks = 20;
            return true;
        }

        if (status.equals("runslash") && dir.getX() == 0) {
            status = "idle";
            ticks = 20;
            return true;
        }

        return false;
    }

    // Animates user
    protected void animate() {
        if (hitStun > 0) {
            image = hit.getNextFrame(false);
        } else if (status.equals("idle") && onGround()) {
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
        } else if (status.equals("slash")) {
            activeHitboxes.addAll(slashing.getNextHitbox());
            image = slashing.getNextFrame(false);
        } else if (status.equals("runslash")) {
            activeHitboxes.addAll(runSlashing.getNextHitbox());
            image = runSlashing.getNextFrame(false);
        } else if (status.equals("airslash")) {
            activeHitboxes.addAll(airSlashing.getNextHitbox());
            image = airSlashing.getNextFrame(false);
        }
    }

    public void update(Graphics g) {
        activeHitboxes.clear();
        if (hitStun == 0)
            x += dir.getX() * speed;
        
        String prevStatus = status;

        if (!specialCases())
            getStatus();
        
        // resets ticks if previous status is different; prevents "critical" attacks
        if (!prevStatus.equals(status) && ticks < 20) 
            ticks = 0;

        // resets tick if previous status is different; prevents "critical" attacks
        if (!prevStatus.equals(status) && ticks < 20) {
            ticks = 0;
        }

        if (onGround()) {
            if (dir.getX() < 0)
                orientation = 'l';

            if (dir.getX() > 0)
                orientation = 'r';
        }

        animate();

        // Drawing Hitboxes for Developmental Purposes
        /*
         * for (Hitbox h : activeHitboxes) {
         * h.draw(g);
         * }
         */

        super.update(g);
        speed_accel();

        if ((specAttacking || attacking) && ticks < 20)
            ticks++;
        if (ticks == 20) { // attacks ended
            ticks = 0;
            attacking = false;
            specAttacking = false;
            // resets all attacking animation
            slashing.setCnt(0);
            runSlashing.setCnt(0);
            airSlashing.setCnt(0);
        }

        swordBeam.draw(g);
        g.setColor(color);

        // Draws a triangle indicator
        g.fillPolygon(new int[] { drawX + 15, drawX + 35, drawX + 25 },
                new int[] { drawY - 35, drawY - 35, drawY - 20 }, 3);
    }

    public ArrayList<Hitbox> getHitboxes() {
        return activeHitboxes;
    }

    public ArrayList<Projectile> getProjectiles() {
        return activeProjectiles;
    }

    public boolean attacking() {
        return attacking;
    }

    public boolean specAttacking() {
        return specAttacking;
    }
}
