/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for player animations
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Animation {
    protected BufferedImage[] arr; // Array of buffered images used to store different frames
    protected int cnt = 0; // Counter tracking current frame

    protected int frames; // Number of Frames
    protected String filePath; // Filepath for the folder containing the individual frames

    public Animation(String filePath, int frames) {
        this.filePath = filePath;
        this.frames = frames;
    }

    // Loads frames needed for animation
    public void load() {
        arr = new BufferedImage[frames];
        BufferedImage image = null;

        // Loads Images
        for (int i = 0; i < frames; i++) {
            try {
                System.out.println(filePath + i + ".png");
                image = ImageIO.read(new File(filePath + i + ".png")); // Takes given filepaths and adds the corrent
                                                                       // endings
                arr[i] = image;
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
    }

    // Gets the next frame in the animation. Constantly cycles animation if cycle is
    // true, and if not, it stops after one cycle
    public BufferedImage getNextFrame(Boolean cycle) {
        cnt++; // increments count
        if (cycle) {
            if (cnt >= arr.length - 1) // Count is greater than the largest index value, it is reset
                cnt = 0; // if cycle is true
        } else {
            if (cnt >= arr.length - 1)
                cnt = arr.length - 1; // If cycle is false, it keeps the index at the last frame
        }
        return arr[cnt];
    }

    // Sets animation frame to a certain frame
    // Used to reset animations when transitioning between animations
    // Also used for developing and testing
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    // Returns the current count
    public int getCnt() {
        return cnt;
    }
}
