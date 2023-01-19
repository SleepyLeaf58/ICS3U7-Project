import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Cosmetics {
    public static void playerProfile(Graphics g, int x, int y) {
        // Loading image
        Image image = null;

        try {
            image = ImageIO.read(new File("Images/PlayerProfile.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        g.drawImage(image, x, y, 300, 150, null);
    }

    public static void computerProfile(Graphics g, int x, int y) {
        // Loading image
        Image image = null;

        try {
            image = ImageIO.read(new File("Images/ComputerProfile.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        g.drawImage(image, x, y, 300, 150, null);
    }

    public static void dummyProfile(Graphics g, int x, int y) {
        // Loading image
        Image image = null;

        try {
            image = ImageIO.read(new File("Images/DummyProfile.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        g.drawImage(image, x, y, 300, 150, null);
    }
}
