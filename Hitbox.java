import java.awt.*;
import java.util.*;

public class Hitbox {
	private int x, y;
    private int xShift, yShift, radius, baseKB, KBgrowth, angle, damage, index;

    public Hitbox(int[] data) {
        xShift = data[0];
        yShift = data[1];
        radius = data[2];
        baseKB = data[3];
        KBgrowth = data[4];
        angle = data[5];
        damage = data[6];
        index = data[7];
    }

    // Source: https://yal.cc/rectangle-circle-intersection-test/comment-page-1/
    public boolean intersects(Rectangle r) {
        int deltaX = x - (int) Math.max(r.getX(), Math.min(x, r.getX() + r.getWidth()));
        int deltaY = y - (int) Math.max(r.getY(), Math.min(y, r.getY() + r.getHeight()));
        return (deltaX * deltaX + deltaY * deltaY) < (radius * radius);
    }

    public void apply_kb(Player p) {
        
    }
    
    public void incrementX(int val) {
    	x = val;
    }
    
    public void incrementY(int val) {
    	y = val;
    }


    public void draw(Graphics g) {
        g.fillOval(x, y, radius, radius);
    }
}
