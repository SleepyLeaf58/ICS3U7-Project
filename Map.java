/*

 */
import java.awt.*;
import java.io.*;
import java.util.*;
public class Map {
    private final int xMapShift = 162;
    private final int yMapShift = 200;
    private String filePath;
    private ArrayList<char[]> mapArr = new ArrayList<char[]>();
    private ArrayList<Tile> tileMap = new ArrayList<Tile>();
    public Map(String filePath) {
        loadMap(filePath);
    }

    public void loadMap(String filePath) {
        String line;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                mapArr.add(line.toCharArray());
            }
        }
        catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void setupMap() {
        //Work on Enumerating level next
        for (int row = 0; row < mapArr.size(); row++) {
            for (int col = 0; col < mapArr.get(row).length; col++) {
                if (mapArr.get(row)[col] == 'X') {
                    tileMap.add(new Tile(col * Tile.getTileWidth() + xMapShift, row * Tile.getTileHeight() + yMapShift));
                }
            }
        }
    }

    public ArrayList<Tile> getTileMap() {
        return tileMap;
    }
}
