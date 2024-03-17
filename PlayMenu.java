/*
* Frank Huang
* 1/18/2023
* For ICS3U7 Ms.Strelkovska
* Class used for the menu
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class PlayMenu extends JPanel implements ActionListener {
    private JLabel title;
    private JButton btnGame;
    private JButton btnPractice;
    private JButton btnCheats;
    private JButton btnBack;
    private BufferedImage menuGraphic = null;

    public PlayMenu() {
        setLayout(null);

        // Graphic
        try {
            menuGraphic = ImageIO.read(new File("Images/Menu/Idle/Idle_0.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        // Title
        title = new JLabel(new ImageIcon("Images/Buttons/Smash.png"));
        title.setBounds(655, 200, 300, 70);

        // Play Button
        btnGame = new JButton(new ImageIcon("Images/Buttons/vsAI.png"));
        btnGame.setBorderPainted(true);
        btnGame.setFocusPainted(false);
        btnGame.setContentAreaFilled(false);
        btnGame.addActionListener(this);
        btnGame.setBounds(650, 300, 300, 70);

        // Instructions Button
        btnPractice = new JButton(new ImageIcon("Images/Buttons/Practice.png"));
        btnPractice.setBorderPainted(true);
        btnPractice.setFocusPainted(false);
        btnPractice.setContentAreaFilled(false);
        btnPractice.addActionListener(this);
        btnPractice.setBounds(650, 400, 300, 70);

        // Cheats Button
        btnCheats = new JButton(new ImageIcon("Images/Buttons/Cheats.png"));
        btnCheats.setBorderPainted(true);
        btnCheats.setFocusPainted(false);
        btnCheats.setContentAreaFilled(false);
        btnCheats.addActionListener(this);
        btnCheats.setBounds(650, 500, 300, 70);

        // Quit Button
        btnBack = new JButton(new ImageIcon("Images/Buttons/Back.png"));
        btnBack.setBorderPainted(true);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(this);
        btnBack.setBounds(650, 600, 300, 70);

        add(title);
        add(btnGame);
        add(btnPractice);
        add(btnCheats);
        add(btnBack);
        setBackground(new Color(255, 255, 255)); // Set Background
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGame) {
            Frame.flipToCard("Game");
        } else if (e.getSource() == btnPractice) {
            Frame.flipToCard("Practice");
        } else if (e.getSource() == btnCheats) {
            Frame.flipToCard("CheatsGame");
        } else {
            Frame.flipToCard("Menu");
        }
    }

    public void paintComponent(Graphics g) {
        // Improve Resolution
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 700, 1200, 100);
        g.drawImage(menuGraphic, -250, -200, 1196, 937, null);
    }
}