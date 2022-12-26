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
                System.out.println(filePath + i + ".png");
                image = ImageIO.read(new File(filePath + i + ".png"));
                arr[i] = image;
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
    }

    public BufferedImage getNextFrame(Boolean cycle) {
        if (cycle) {
            if (cnt >= arr.length - 1)
                cnt = 0;
        } else {
            if (cnt >= arr.length - 1)
                cnt = arr.length - 1;
        }
        return arr[cnt++];
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
