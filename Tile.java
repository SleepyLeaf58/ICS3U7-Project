import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Tile {
	private static final int TILE_WIDTH = 50;
    private static final int TILE_HEIGHT = 30;
    private int xPos, yPos;

    public Tile(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void debug() {
        System.out.println(xPos + " " + yPos);
    }
    public static int getTileWidth() {
        return TILE_WIDTH;
    }
    public static int getTileHeight() {
        return TILE_HEIGHT;
    }
    public void drawTile(Graphics g) {
        g.fillRect(xPos, yPos, TILE_WIDTH, TILE_HEIGHT);
    }
}
