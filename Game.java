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
        camera = new Camera(activeSprites);
        map = new Map("Levels/Level.txt", camera);
        map.setupMap();
        platMap = map.getPlatMap();
        stageMap = map.getStageMap();

        Warrior w = new Warrior(200, 400, platMap, stageMap, camera);
        activeSprites.add(w);
        activeSprites.add(new Computer(700, 400, platMap, stageMap, camera, w));

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
