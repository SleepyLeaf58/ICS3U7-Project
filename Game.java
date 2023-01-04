import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game {
    private Map map;
    private ArrayList<Tile> platMap;
    private ArrayList<Tile> stageMap;
    private ArrayList<Player> activeSprites = new ArrayList<Player>();
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    public Game() {
        map = new Map("Levels/Level.txt");
        map.setupMap();
        platMap = map.getPlatMap();
        stageMap = map.getStageMap();
        activeSprites.add(new Player(400, 400, platMap, stageMap));

        sprites.addAll(platMap);
        sprites.addAll(stageMap);
        sprites.addAll(activeSprites);
    }

    public void keyPressed(KeyEvent key) {
        for (Player player : activeSprites) {
            player.keyPressed(key);
        }
    }

    public void keyReleased(KeyEvent key) {
        for (Player player : activeSprites) {
            player.keyReleased(key);
        }
    }

    public void run(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.update(g);
        }
    }
}
