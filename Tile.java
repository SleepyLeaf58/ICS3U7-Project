/*
 * Class creating tiles for the stage and platforms
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Tile extends Sprite {
    private static final int TILE_WIDTH = 50;
    private static final int TILE_HEIGHT = 30;
    int tTop, tBot, tLeft, tRight;
    int drawX = 0, drawY = 0;

    public Tile(int x, int y, Camera c) {
        super(x, y, TILE_WIDTH, TILE_HEIGHT, c);
        tTop = y;
        tBot = y + TILE_HEIGHT;
        tLeft = x;
        tRight = x + getTileWidth();
    }

    public int getTTop() {
        return tTop;
    }

    public int getTBot() {
        return tBot;
    }

    public int getTLeft() {
        return tLeft;
    }

    public int getTRight() {
        return tRight;
    }

    public static int getTileWidth() {
        return TILE_WIDTH;
    }

    public static int getTileHeight() {
        return TILE_HEIGHT;
    }

    public void update(Graphics g) {
        drawX = x + c.getPosShiftX();
        drawY = y + c.getPosShiftY();
        drawSprite(g);
    }

    public void drawSprite(Graphics g) {
        g.fillRect(drawX, drawY, TILE_WIDTH, TILE_HEIGHT);
    }
}
