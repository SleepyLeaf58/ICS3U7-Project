/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for the Practice Panel
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PracticePanel extends JPanel implements ActionListener, KeyListener {
    private final int FPS = 60;
    private PracticeGame game;
    private Timer timer = new Timer(1000 / FPS, this);
    private JButton btnMenu;

    public PracticePanel() {
        setLayout(null);

        addKeyListener(this);
        timer.start();
        game = new PracticeGame();

        btnMenu = new JButton(new ImageIcon("Images/Buttons/Back.png"));
        btnMenu.setBorderPainted(true);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBounds(724, 0, 300, 70);
        btnMenu.addActionListener(this);
        add(btnMenu);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        } else if (e.getSource() == btnMenu) {
            Frame.layout.show(Frame.pane, "Menu");
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
