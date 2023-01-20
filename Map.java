/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for loading map
 */

import java.awt.*;
import java.io.*;
import java.util.*;

public class Map {
    private final int xMapShift = 162; // Mapshifts to center the map
    private final int yMapShift = 200;
    private ArrayList<char[]> mapArr = new ArrayList<char[]>();
    private ArrayList<Tile> platMap = new ArrayList<Tile>();
    private ArrayList<Tile> stageMap = new ArrayList<Tile>();
    private Camera c;

    public Map(String filePath, Camera c) {
        loadMap(filePath);
        this.c = c;
    }

    public void loadMap(String filePath) {
        String line;
        BufferedReader br = null;

        // Reads with a BufferedReader to improve speed
        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                mapArr.add(line.toCharArray());
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void setupMap() {
        // Loops through array to create tiles at the correct positions
        for (int row = 0; row < mapArr.size(); row++) {
            for (int col = 0; col < mapArr.get(row).length; col++) {
                if (mapArr.get(row)[col] == 'P') {
                    platMap.add(
                            new Tile(col * Tile.getTileWidth() + xMapShift, row * Tile.getTileHeight() + yMapShift, c));
                } else if (mapArr.get(row)[col] == 'S') {
                    stageMap.add(
                            new Tile(col * Tile.getTileWidth() + xMapShift, row * Tile.getTileHeight() + yMapShift, c));
                }
            }
        }
    }

    public ArrayList<Tile> getPlatMap() {
        return platMap;
    }

    public ArrayList<Tile> getStageMap() {
        return stageMap;
    }
}
