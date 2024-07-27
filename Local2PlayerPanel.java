/*
* Frank Huang
* 7/26/2024
* For Fun
 */

 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 
 public class Local2PlayerPanel extends JPanel implements ActionListener, KeyListener {
     final int FPS = 60;
     Local2Player game;
     Timer timer = new Timer(1000 / FPS, this); // Timer for updating sprites
 
     public Local2PlayerPanel() {
         addKeyListener(this);
         timer.start();
         game = new Local2Player();
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
 
     // Keyboard functions
     public void keyPressed(KeyEvent key) {
         game.keyPressed(key);
     }
 
     public void keyReleased(KeyEvent key) {
         game.keyReleased(key);
     }
 
     public void keyTyped(KeyEvent key) {
 
     }
 }
 