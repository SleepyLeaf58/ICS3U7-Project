/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for Practice Dummy
 */

import java.awt.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;

public class Dummy extends Entity { // Extending dummy from entity
    public Dummy(int x, int y, ArrayList<Tile> platMap, ArrayList<Tile> stageMap, Camera c) {
        super(x, y, 45, 74, 45, 74, platMap, stageMap, c);

        // Initilizes percent and orientation
        percent = 0;
        orientation = 'r';

        // Loading image
        try {
            image = ImageIO.read(new File("Images/dummy.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void update(Graphics g) {
        super.update(g);
        x += dir.getX() * speed;
    }
}
