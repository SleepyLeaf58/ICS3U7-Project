import javax.smartcardio.CardException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game {
    private Map map;
    private Camera camera;
    private ArrayList<Tile> platMap;
    private ArrayList<Tile> stageMap;
    private ArrayList<Entity> activeSprites = new ArrayList<Entity>();
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    public Game() {
        map = new Map("Levels/Level.txt");
        map.setupMap();
        platMap = map.getPlatMap();
        stageMap = map.getStageMap();
        activeSprites.add(new Warrior(400, 400, platMap, stageMap));
        activeSprites.add(new Dummy(600, 400, platMap, stageMap));
        camera = new Camera(activeSprites);

        sprites.addAll(platMap);
        sprites.addAll(stageMap);
        sprites.addAll(activeSprites);
    }

    public void keyPressed(KeyEvent key) {
        for (Entity entity : activeSprites) {
            entity.keyPressed(key);
        }
    }

    public void keyReleased(KeyEvent key) {
        for (Entity entity : activeSprites) {
            entity.keyReleased(key);
        }
    }

    public void run(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.update(g);
        }
        camera.update(g);
    }
}
