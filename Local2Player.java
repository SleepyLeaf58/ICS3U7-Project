/*
* Frank Huang
* 7/26/2024
* For Fun
 */


 import java.awt.*;
 import java.awt.event.*;
 import java.util.*;
 
 public class Local2Player {
     private Map map;
     private Camera camera;
     private ArrayList<Tile> platMap;
     private ArrayList<Tile> stageMap;
 
     private Warrior w1;
     private Warrior w2;
     private ArrayList<Entity> activeSprites = new ArrayList<Entity>();
     private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
 
     // player1
     private boolean checkP1Hitbox = false;
 
     // player2
     private boolean checkP2Hitbox = false;
 
     // Indicators
     private PIndicator p1Percent, p2Percent;
 
     private boolean p1Win = false, p2Win = false;
 
     public Local2Player() {
         // Initialization
         camera = new Camera(activeSprites);
         map = new Map("Levels/Level.txt", camera);
         map.setupMap();
         platMap = map.getPlatMap();
         stageMap = map.getStageMap();

         w1 = new Warrior(400, 400, platMap, stageMap, camera, new Color(0, 255, 0), KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_E, KeyEvent.VK_R);
         w2 = new Warrior(700, 400, platMap, stageMap, camera, new Color(0, 0, 255), KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_COMMA, KeyEvent.VK_PERIOD);
         activeSprites.add(w1);
         activeSprites.add(w2);
 
         p1Percent = new PIndicator(w1, 160, 650);
         p2Percent = new PIndicator(w2, 740, 650);
 
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
         if (!w1.attacking()) {
             checkP1Hitbox = true;
         }
 
         // Checks for warrior hitbox, and only checks on hitbox if multiple collide, and
         // only for that frame
         if (checkP1Hitbox) {
             for (Hitbox h : w1.getHitboxes()) {
                 if (h.intersects(w2.getBounds())) {
                     w2.applyKB(h, w1.getOrientation());
                     checkP1Hitbox = false;
                     break;
                 }
             }
         }
 
         // Checks for warrior projectiles
         for (Projectile p : w1.getProjectiles()) {
             if (!p.hasHit() && p.getBounds().intersects(w2.getBounds())) {
                 w2.applyKB(p, w1.getOrientation());
                 p.setHit(true);
             }
         }
 
         if (!w2.attacking()) {
             checkP2Hitbox = true;
         }
 
         // Checks computer hitboxes
         if (checkP2Hitbox) {
             for (Hitbox h : w2.getHitboxes()) {
                 if (h.intersects(w1.getBounds())) {
                     w1.applyKB(h, w2.getOrientation());
                     checkP2Hitbox = false;
                     break;
                 }
             }
         }
 
         // Checks computer projectiles
         for (Projectile p : w2.getProjectiles()) {
             if (!p.hasHit() && p.getBounds().intersects(w1.getBounds())) {
                 w1.applyKB(p, w2.getOrientation());
                 p.setHit(true);
             }
         }
     }
 
     public boolean getP1Win() {
         return p1Win;
     }
 
     public boolean getP2Win() {
         return p2Win;
     }
 
     public void run(Graphics g) {
         for (Sprite sprite : sprites) {
             checkGetHit();
             sprite.update(g);
         }
         camera.update(g); // Updates Camera
         p1Percent.update(g); // Updates percents
         p2Percent.update(g);
         Cosmetics.playerProfile(g, 0, 570); // Draws cosmetics
         Cosmetics.player2Profile(g, 724, 570);
 
         // Ending Game
         if (w2.getY() >= 1600 && !p1Win) {
             System.out.println("");
             p1Win = true;
             reset();
             Frame.getEndScreen().setWin2Player("P1");
             Frame.flipToCard("EndScreen");
         }
 
         if (w1.getY() >= 1600 && !p2Win) {
             p2Win = true;
             reset();
             Frame.getEndScreen().setWin2Player("P2");
             Frame.flipToCard("EndScreen");
         }
     }
 
     // Resetting the game
     public void reset() {
         p1Win = false;
         p2Win= false;
         w1.resetPercent();
         w2.resetPercent();
         w1.setX(200);
         w1.setY(400);
         w1.getDir().setX(0);
         w1.getDir().setY(0);
         w1.resetHitStun();
         w2.setX(700);
         w2.setY(400);
         w2.getDir().setX(0);
         w2.getDir().setY(0);
         w2.resetHitStun();
     }
 }
 