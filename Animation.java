import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Animation {
    private BufferedImage[] arr;
    private int cnt = 0;

    private int frames;
    private String filePath;

    public Animation(String filePath, int frames) {
        this.filePath = filePath;
        this.frames = frames;
    }

    public void load() {
        arr = new BufferedImage[frames];
        BufferedImage image = null;

        for (int i = 0; i < frames; i++) {
            try {
                image = ImageIO.read(new File(filePath + i + ".png"));
                arr[i] = image;
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
    }

    public BufferedImage getNextFrame() {
        cnt = (cnt + 1) % arr.length;
        return arr[cnt];
    }
}
