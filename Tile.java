import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Tile {
	final int TILE_SIZE = 50;
    int xPos, yPos;

    public Tile(int x_pos, int y_pos) {
        this.xPos = xPos;
        this.xPos = yPos;
    }

    public void drawTile(Graphics g) {
        g.fillRect(xPos, yPos, TILE_SIZE, TILE_SIZE);
    }
}
