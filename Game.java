/*
* Frank Huang and David Zhai
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Game Logic
 */

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
    private ArrayList<Entity> activeSprites = new ArrayList<Entity>();
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    // player
    private boolean checkWarriorHitbox = false;

    // computer
    private boolean checkCompHitbox = false;

    // Indicators
    private PIndicator playerPercent, aiPercent;

    private boolean compWin = false, playerWin = false;

    public Game() {
        // Initialization
        camera = new Camera(activeSprites);
        map = new Map("Levels/Level.txt", camera);
        map.setupMap();
        platMap = map.getPlatMap();
        stageMap = map.getStageMap();

        w = new Warrior(200, 400, platMap, stageMap, camera, new Color(0, 255, 0));
        activeSprites.add(w);
        c = new Computer(700, 400, platMap, stageMap, camera, new Color(255, 0, 0), w);
        activeSprites.add(c);

        playerPercent = new PIndicator(w, 160, 650);
        aiPercent = new PIndicator(c, 740, 650);

        sprites.addAll(platMap);
        sprites.addAll(stageMap);
        sprites.addAll(activeSprites);
    }

    // Keyboard functions
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

    // Checks for hits
    public void checkGetHit() {
        if (!w.attacking()) {
            checkWarriorHitbox = true;
        }

        // Checks for warrior hitbox, and only checks one hitbox each attack
        if (checkWarriorHitbox) {
            for (Hitbox h : w.getHitboxes()) {
                if (h.intersects(c.getBounds())) {
                    c.applyKB(h, w.getOrientation());
                    checkWarriorHitbox = false;
                    break;
                }
            }
        }

        // Checks for warrior projectiles, only does so once per projectile
        for (Projectile p : w.getProjectiles()) {
            if (!p.hasHit() && p.getBounds().intersects(c.getBounds())) {
                c.applyKB(p, w.getOrientation());
                p.setHit(true);
            }
        }

        if (!c.attacking()) {
            checkCompHitbox = true;
        }

        // Checks computer hitboxes
        if (checkCompHitbox) {
            for (Hitbox h : c.getHitboxes()) {
                if (h.intersects(w.getBounds())) {
                    w.applyKB(h, c.getOrientation());
                    checkCompHitbox = false;
                    break;
                }
            }
        }

        // Checks computer projectiles
        for (Projectile p : c.getProjectiles()) {
            if (!p.hasHit() && p.getBounds().intersects(w.getBounds())) {
                w.applyKB(p, c.getOrientation());
                p.setHit(true);
            }
        }
    }

    public boolean getPlayerWin() {
        return playerWin;
    }

    public boolean getCompWin() {
        return compWin;
    }

    public void run(Graphics g) {
        for (Sprite sprite : sprites) {
            checkGetHit();
            sprite.update(g);
        }
        camera.update(g); // Updates Camera
        playerPercent.update(g); // Updates percents
        aiPercent.update(g);
        Cosmetics.playerProfile(g, 0, 570); // Draws cosmetics
        Cosmetics.computerProfile(g, 724, 570);

        // Ending Game
        if (c.getY() >= 1600 && !playerWin) {
            System.out.println("");
            playerWin = true;
            reset();
            Frame.getEndScreen().setWin(true);
            Frame.flipToCard("EndScreen");
        }

        if (w.getY() >= 1600 && !compWin) {
            compWin = true;
            reset();
            Frame.getEndScreen().setWin(false);
            Frame.flipToCard("EndScreen");
        }
    }

    // Resetting the game
    public void reset() {
        playerWin = false;
        compWin = false;
        w.resetPercent();
        c.resetPercent();
        w.setX(200);
        w.setY(400);
        w.getDir().setX(0);
        w.getDir().setY(0);
        w.resetHitStun();
        c.setX(700);
        c.setY(400);
        c.getDir().setX(0);
        c.getDir().setY(0);
        c.resetHitStun();
    }
}
