/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for the End Screen. Most of the code is similar to MenuPanel.java
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class EndScreen extends JPanel implements ActionListener {
    private JLabel title;
    private JButton btnBack;
    private BufferedImage menuGraphic = null;

    public EndScreen() {
        setLayout(null);

        // Graphic
        try {
            menuGraphic = ImageIO.read(new File("Images/Menu/Idle/Idle_0.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        // Title
        title = new JLabel(new ImageIcon("Images/Menu/Won.png"));
        title.setBounds(655, 200, 300, 70);

        // Back Button
        btnBack = new JButton(new ImageIcon("Images/Buttons/Back.png"));
        btnBack.setBorderPainted(true);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(this);
        btnBack.setBounds(650, 300, 300, 70);

        add(title);
        add(btnBack);
        setBackground(new Color(255, 255, 255));
        // Set Background
    }

    // Checks if player has won, or computer has won
    public void setWin(boolean bool) {
        if (bool) {
            title.setIcon(new ImageIcon("Images/Menu/Won.png"));
        } else {
            title.setIcon(new ImageIcon("Images/Menu/Lost.png"));
        }
    }

    public void setWin2Player(String winner) {
        if (winner.equals("P1")) {
            title.setIcon(new ImageIcon("Images/Menu/P1Win.png"));
        } else {
            title.setIcon(new ImageIcon("Images/Menu/P2Win.png"));
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            Frame.flipToCard("Menu"); // Returns to menu
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 700, 1200, 100);
        g.drawImage(menuGraphic, -250, -200, 1196, 937, null);
    }
}