import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Projectile {
    private BufferedImage img = null;
    private Player p;
    private int vel, x, y;

    public Projectile(String path, int vel, Player p) {
        this.vel = vel;
        this.p = p;

        this.x = p.getX() + 50;
        this.y = p.getY();

        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void drawProjectile(Graphics g) {
        g.drawImage(img, x, y, 100, 125, null);
        while (x < 1024)
            x += vel;
    }

}