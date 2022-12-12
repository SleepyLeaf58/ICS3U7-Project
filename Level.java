/*

 */
import java.io.*;
import java.util.*;
public class Level {
    final int xMapShift = 250;
    final int yMapShift = 200;
    String filePath;
    ArrayList<char[]> levelMap = new ArrayList<char[]>();
    public Level(String filePath) {
        loadLevel(filePath);
    }

    public void loadLevel(String filePath) {
        String line;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                levelMap.add(line.toCharArray());
            }
        }
        catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void setupLevel() {
        //Work on Enumerating level next
    }
}
