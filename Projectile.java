/*
* David Zhai
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for player projectiles
 */

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;

public class Projectile extends Sprite {
    private Entity p;
    private double speed;
    private double accel;
    private Image img;
    private boolean hit = false;

    // used for drawing
    private double d_speed;
    private double d_accel;
    private int d_width;
    private int drawX = 0, drawY = 0;

    private char projOrientation;

    private double baseKB, scalingKB, angle, damage;

    public Projectile(Entity p, int width, int height, double speed, double accel, double baseKB, double scalingKB,
            double angle, double damage, String filePath, Camera c) {
        super(-200, 0, width, height, c);
        this.p = p;
        this.speed = speed;
        this.accel = accel;

        this.baseKB = baseKB;
        this.scalingKB = scalingKB;
        this.angle = angle;
        this.damage = damage;

        try {
            img = ImageIO.read(new File(filePath + ".png"));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void launch() {
        if (x <= -200 || x >= 1224) {
            hit = false;
            x = p.getX() + 50;
            y = p.getY();
            drawX = p.getDrawX();
            drawY = p.getDrawY();

            projOrientation = p.getOrientation();
            if (projOrientation == 'l') {
                d_speed = -speed;
                d_accel = -accel;
                d_width = width;
            } else {
                d_speed = speed;
                d_accel = accel;
                d_width = width;
            }
        }
    }

    public double[] getData() {
        double[] data = { baseKB, scalingKB, angle, damage };
        return data;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);

        if (projOrientation == 'r') {
            g.drawImage(img, drawX, drawY, d_width, height, null);
            // g.drawRect(drawX, drawY, d_width, height);
        } else {
            g.drawImage(img, drawX, drawY, -d_width, height, null);
            // g.drawRect(drawX - d_width, drawY, d_width, height);
        }

        update();
    }

    private void update() {
        x += d_speed;

        drawX += d_speed;
        d_speed += d_accel;

        drawY = y + c.getPosShiftY();
    }

    public void setHit(boolean b) {
        hit = b;
    }

    public boolean hasHit() {
        return hit;
    }

}