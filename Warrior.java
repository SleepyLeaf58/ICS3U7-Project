import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Warrior extends Entity {

    private Animation idle;
    private Animation running;
    private Animation jumping;
    private Animation falling;
    private Animation slashing;
    private Animation runSlashing;
    private Animation airSlashing;

    private String status;

    private boolean attacking = false;
    private boolean specAttacking = false;

    private Projectile swordBeam;

    // Timer for attacks
    private int ticks = 0;

    public Warrior(int x, int y, ArrayList<Tile> platMap, ArrayList<Tile> stageMap, Camera c) {
        super(x, y, 45, 70, 160, 125, platMap, stageMap, c);

        percent = 0;
        speed = 8;
        accel = 0.15;
        maxSpeed = 11;
        xShiftL = 30;
        xShiftR = 50;
        yShift = 50;

        idle = new Animation("Images/Player/Idle/Idle_", 32);
        running = new Animation("Images/Player/Running/Running_", 24);
        jumping = new Animation("Images/Player/Jumping/Jumping_", 28);
        falling = new Animation("Images/Player/Falling/Falling_", 12);
        slashing = new Attack(this, 11, 12, 20, "Images/Player/Slashing/Slashing_", "HitboxData/slashing.txt");
        runSlashing = new Attack(this, 10, 12, 21, "Images/Player/Run_Slashing/Run_Slashing_", "");
        airSlashing = new Attack(this, 9, 12, 20, "Images/Player/Air_Slash/Air_Slash_", "");
        swordBeam = new Projectile(this, 40, 75, 8, 0.5, "Images/Player/swordBeam");

        swordBeam = new Projectile(this, 40, 75, 8, 0.5, "Images/Player/swordBeam", c);

        idle.load();
        running.load();
        jumping.load();
        falling.load();
        slashing.load();
        runSlashing.load();
        airSlashing.load();
    }

    public void keyPressed(KeyEvent e) {
        if (!specAttacking) {
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
        if (dir.getX() == 0 && !specAttacking && e.getKeyCode() == KeyEvent.VK_W) {
            specAttacking = true;
            swordBeam.launch();
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

    private void speed_accel() {

        if (onGround() && dir.getX() != 0) {
            if (speed < maxSpeed)
                speed += accel;

        } else {
            speed = 8;
        }
    }

    private void getStatus() {
        if (dir.getY() > 0)
            if (attacking)
                status = "airslash";
            else
                status = "fall";
        else if (dir.getY() < 0)
            if (attacking)
                status = "airslash";
            else
                status = "jump";
        else {
            if (dir.getX() != 0)
                if (attacking)
                    status = "runslash";
                else
                    status = "run";
            else {
                if (attacking)
                    status = "slash";
                else
                    status = "idle";
            }
        }
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
        } else if (status.equals("runslash")) {
            image = runSlashing.getNextFrame(false);
        } else if (status.equals("airslash")) {
            image = airSlashing.getNextFrame(false);
        }
    }

    public void update(Graphics g) {
        x += dir.getX() * speed;

        getStatus();
        animate();

        super.update(g);
        speed_accel();

        if ((specAttacking || attacking) && ticks < 20)
            ticks++;
        if (ticks == 20) {
            ticks = 0;
            attacking = false;
            specAttacking = false;
            slashing.setCnt(0);
            runSlashing.setCnt(0);
            airSlashing.setCnt(0);
        }
        swordBeam.draw(g);
    }
}
