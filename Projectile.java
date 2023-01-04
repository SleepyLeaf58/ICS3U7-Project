import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Projectile extends Sprite {
    private Player p;
    private int accel;
    private Image img;
    private boolean launched = false;
    private char orientation;

    // used for drawing
    private int d_accel;
    private int d_width;

    public Projectile (Player p, int width, int height, int accel, String filePath) {
        super(0, 0, width, height);
        this.p = p;
        this.accel = accel;

        try {
            img = ImageIO.read(new File(filePath + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setVisible(false);
    }

    public void launch () {
        launched = true;
        if (!visible) {
            setVisible(true);
        
            x = p.getX() + 50;
            y = p.getY();

            if (p.getOrientation() == 'l') {
                d_accel = -accel;
                d_width = -width;
            } else {
                d_accel = accel;
                d_width = width;
            }
        }
    }

    public void draw (Graphics g) {
        if (visible) {
            g.drawImage(img, x, y, d_width, height, null);
            g.setColor(Color.red);

            if (d_width < 0)
                g.drawRect(x - width, y, width, height);
            else
                g.drawRect(x, y, width, height);
        }

        update();
    }

    private void update () {
        if (x < 1224 || x > -200) 
            x += d_accel;
        
        if (launched)
            setVisible(x > -200 && x < 1224);
    }
}