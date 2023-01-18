/*

*/

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

    private Warrior w;
    private Computer c;
    private Dummy d;
    private ArrayList<Entity> activeSprites = new ArrayList<Entity>();
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private boolean toCheckHitbox = false;
    private boolean toCheckHitbox2 = false;

    public Game() {
        camera = new Camera(activeSprites);
        map = new Map("Levels/Level.txt", camera);
        map.setupMap();
        platMap = map.getPlatMap();
        stageMap = map.getStageMap();

        w = new Warrior(200, 400, platMap, stageMap, camera, new Color(255, 0, 0));
        activeSprites.add(w);
        c = new Computer(700, 400, platMap, stageMap, camera, new Color(0, 255, 0),
                w);
        activeSprites.add(c);
        d = new Dummy(200, 400, platMap, stageMap, camera);
        activeSprites.add(d);

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

    public void checkGetHit() {

        if (!w.attacking())
            toCheckHitbox = true;

        if (toCheckHitbox)
            for (Hitbox h : w.getHitboxes()) {
                if (h.intersects(c.getBounds())) {
                    System.out.println("c got hit.");
                    c.applyKB(h, w.getOrientation());
                    toCheckHitbox = false;
                    break;
                }
                if (h.intersects(d.getBounds())) {
                    System.out.println("Jason Yuen got hit.");
                    d.applyKB(h, w.getOrientation());
                    toCheckHitbox = false;
                    break;
                }
            }

        if (!c.attacking())
            toCheckHitbox2 = true;

        if (toCheckHitbox2)
            for (Hitbox h : c.getHitboxes()) {
                if (h.intersects(w.getBounds())) {
                    System.out.println("Warrior got hit.");
                    w.applyKB(h, c.getOrientation());
                    toCheckHitbox2 = false;
                    break;
                }
            }

    }

    public void run(Graphics g) {
        for (Sprite sprite : sprites) {
            checkGetHit();
            sprite.update(g);
        }
        camera.update(g);
    }

}