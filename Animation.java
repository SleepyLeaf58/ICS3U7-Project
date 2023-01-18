/*
 * Creates animations
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Animation {
    protected BufferedImage[] arr;
    protected int cnt = 0;

    protected int frames;
    protected String filePath;

    public Animation(String filePath, int frames) {
        this.filePath = filePath;
        this.frames = frames;
    }

    // Loads frames needed for animation
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

    // Gets the next frame in the animation. Constantly cycles animation if cycle is
    // true, and if not, it stops after one cycle
    public BufferedImage getNextFrame(Boolean cycle) {
        cnt++;
        if (cycle) {
            if (cnt >= arr.length - 1)
                cnt = 0;
        } else {
            if (cnt >= arr.length - 1)
                cnt = arr.length - 1;
        }
        return arr[cnt];
    }

    // Sets animation frame to a certain frame
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getCnt() {
        return cnt;
    }

}
