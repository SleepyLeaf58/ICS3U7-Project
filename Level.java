/*

 */
import java.awt.*;
import java.io.*;
import java.util.*;
public class Level {
    final int xMapShift = 162;
    final int yMapShift = 200;
    String filePath;
    ArrayList<char[]> levelMap = new ArrayList<char[]>();
    ArrayList<Tile> tileMap = new ArrayList<Tile>();
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
        for (int row = 0; row < levelMap.size(); row++) {
            for (int col = 0; col < levelMap.get(row).length; col++) {
                if (levelMap.get(row)[col] == 'X') {
                    tileMap.add(new Tile(col * Tile.getTileWidth() + xMapShift, row * Tile.getTileHeight() + yMapShift));
                }
            }
        }
    }

    public void drawLevel(Graphics g) {
        for (Tile tile : tileMap) {
            tile.drawTile(g);
            tile.debug();
        }
    }
}
