import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
    private Level level = null;
    public GamePanel() {
        level = new Level("Levels/Level.txt");
        level.setupLevel();
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        level.drawLevel(g);
    }
}
