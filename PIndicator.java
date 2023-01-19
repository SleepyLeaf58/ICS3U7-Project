/*
* Frank Huang and David Zhai
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for percent indicator
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PIndicator {
    Font font = null;
    private Entity e;
    private double percent = 0;
    private int x, y;

    public PIndicator(Entity e, int x, int y) {
        percent = Math.round(e.getPercent());
        this.e = e;
        this.x = x;
        this.y = y;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/RodinProUB.otf"));
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        font = font.deriveFont(Font.BOLD, 40);
    }

    public void update(Graphics g) {
        percent = Math.round(e.getPercent());
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString(percent + "%", x, y);
    }
}
