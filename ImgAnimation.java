import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImgAnimation {
    private static BufferedImage idleImg;
    private static BufferedImage[] idleArr;
    private static int idleCnt = 0;

    public static void loadIdle() {
        idleArr = new BufferedImage[32];
        String filePath = "";
        BufferedImage image = null;

        try {
            idleImg = ImageIO.read(new File("Images/Player/Idle/Idle_000.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        for (int i = 0; i <= 31; i++) {
            try {
                if (i <= 9) {
                    filePath = "Images/Player/Idle/Idle_00" + i + ".png";
                } else {
                    filePath = "Images/Player/Idle/Idle_0" + i + ".png";
                }

                image = ImageIO.read(new File(filePath));
                idleArr[i] = image;
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
    }

    public static BufferedImage getNextIdle() {
        idleCnt = (idleCnt + 1) % idleArr.length;
        return idleArr[idleCnt];
    }

    public static BufferedImage getIdleImg() {
        idleCnt = 0;
        return idleImg;
    }
}
