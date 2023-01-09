import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Projectile extends Sprite {
    private Entity p;
    private double speed;
    private double accel;
    private Image img;
    private boolean launched = false;
    private char orientation;

    // used for drawing
    private double d_speed;
    private double d_accel;
    private int d_width;

    public Projectile(Entity p, int width, int height, double speed, double accel, String filePath, Camera c) {
        super(0, 0, width, height, c);
        this.p = p;
        this.speed = speed;
        this.accel = accel;

        try {
            img = ImageIO.read(new File(filePath + ".png"));
        } catch (Exception e) {
            System.out.println(e);
        }

        setVisible(false);
    }

    public void launch() {
        launched = true;
        if (!visible) {
            setVisible(true);

            x = p.getX() + 50;
            y = p.getY();

            if (p.getOrientation() == 'l') {
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

    public void draw(Graphics g) {
        g.setColor(Color.red);
        if (visible) {
            if (p.getOrientation() == 'r') {
                g.drawImage(img, x, y, d_width, height, null);
                g.drawRect(x, y, d_width, height);
            } else {
                g.drawImage(img, x, y, -d_width, height, null);
                g.drawRect(x - d_width, y, d_width, height);
            }
        }

        update();
    }

    private void update() {
        if (x < 1224 || x > -200)
            x += d_speed;
        d_speed += d_accel;

        if (launched)
            setVisible(x > -200 && x < 1224);
    }
}