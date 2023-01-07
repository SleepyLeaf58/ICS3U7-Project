import java.awt.*;
import java.util.*;

public class Hitbox {
    private int x, y, radius, baseKB, KBgrowth, angle, damage;

    public Hitbox(ArrayList<Integer> data) {
        x = data.get(0);
        y = data.get(1);
        radius = data.get(2);
        baseKB = data.get(3);
        KBgrowth = data.get(4);
        angle = data.get(5);
        damage = data.get(7);
    }

    // Source: https://yal.cc/rectangle-circle-intersection-test/comment-page-1/
    public boolean intersects(Rectangle r) {
        int deltaX = x - (int) Math.max(r.getX(), Math.min(x, r.getX() + r.getWidth()));
        int deltaY = y - (int) Math.max(r.getY(), Math.min(y, r.getY() + r.getHeight()));
        return (deltaX * deltaX + deltaY * deltaY) < (radius * radius);
    }

    public void apply_kb(Entity p) {

    }

    public void draw(Graphics g) {
        g.fillOval(x, y, radius, radius);
    }
}