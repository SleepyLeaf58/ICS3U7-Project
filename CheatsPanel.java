/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for the CheatsGame Panel. See GamePanel.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheatsPanel extends JPanel implements ActionListener, KeyListener {
    final int FPS = 60;
    CheatsGame game;
    Timer timer = new Timer(1000 / FPS, this);

    public CheatsPanel() {
        addKeyListener(this);
        timer.start();
        game = new CheatsGame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.run(g);
    }

    public void keyPressed(KeyEvent key) {
        game.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        game.keyReleased(key);
    }

    public void keyTyped(KeyEvent key) {

    }
}
