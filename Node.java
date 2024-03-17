import javax.swing.*;
import java.awt.*;

public class Node extends Rectangle {
    public Node(int x, int y) {
        super(x, y, 200, 200);
    }

    public void draw(Graphics g, Camera c, int i) {
        g.setColor(new Color(255, 0, 0, 80));
        g.fillRect(x + c.getPosShiftX(), y + c.getPosShiftY(), 100, 100);
        g.setFont(new Font("Verdana", Font.BOLD, 40));
        g.setColor(Color.black);
        g.drawString(i + "", x + 25 + c.getPosShiftX(), y + 50 + c.getPosShiftY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
