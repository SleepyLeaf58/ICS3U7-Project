/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for the Game Panel
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    final int FPS = 60;
    Game game;
    Timer timer = new Timer(1000 / FPS, this);

    public GamePanel() {
        addKeyListener(this);
        timer.start();
        game = new Game();
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
