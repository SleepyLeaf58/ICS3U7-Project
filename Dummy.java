import java.awt.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;

public class Dummy extends Entity {
    public Dummy(int x, int y, ArrayList<Tile> platMap, ArrayList<Tile> stageMap, Camera c) {
        super(x, y, 45, 74, 45, 74, platMap, stageMap, c);

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
    }
}
