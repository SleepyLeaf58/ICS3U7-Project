/*
* Frank Huang and David Zhai
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Game Logic
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PracticeGame {
    private Map map;
    private Camera camera;
    private ArrayList<Tile> platMap;
    private ArrayList<Tile> stageMap;

    private Warrior w;
    private Dummy d;
    private ArrayList<Entity> activeSprites = new ArrayList<Entity>();
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private boolean checkWarriorHitbox = false;

    private PIndicator playerPercent, dummyPercent;

    public PracticeGame() {
        camera = new Camera(activeSprites);
        map = new Map("Levels/Level.txt", camera);
        map.setupMap();
        platMap = map.getPlatMap();
        stageMap = map.getStageMap();

        w = new Warrior(200, 400, platMap, stageMap, camera, new Color(0, 255, 0), KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_Q, KeyEvent.VK_W);
        activeSprites.add(w);
        d = new Dummy(200, 400, platMap, stageMap, camera);
        activeSprites.add(d);

        playerPercent = new PIndicator(w, 160, 650);
        dummyPercent = new PIndicator(d, 740, 650);

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
        // Checks for warrior hitboxes with the dummy
        if (!w.attacking()) {
            checkWarriorHitbox = true;
        }

        if (checkWarriorHitbox) {
            for (Hitbox h : w.getHitboxes()) {
                if (h.intersects(d.getBounds())) {
                    d.applyKB(h, w.getOrientation());
                    checkWarriorHitbox = false;
                    break;
                }
            }
        }

        for (Projectile p : w.getProjectiles()) {
            if (!p.hasHit() && p.getBounds().intersects(d.getBounds())) {
                d.applyKB(p, w.getOrientation());

                p.setHit(true);
            }
        }
    }


    // Resets Game
    public void warriorReset() {
        w.setX(400);
        w.setY(400);
    }

    public void dummyReset() {
        d.setX(400);
        d.setY(400);
        d.resetPercent();
        d.resetHitStun();
    }

    public void run(Graphics g) {

        for (Sprite sprite : sprites) {
            checkGetHit();
            sprite.update(g);
        }
        camera.update(g);
        playerPercent.update(g);
        dummyPercent.update(g);
        Cosmetics.playerProfile(g, 0, 570);
        Cosmetics.dummyProfile(g, 724, 570);

        if (w.getY() >= 1600)
            warriorReset();
        if (d.getY() >= 1600)
            dummyReset();
    }
}