/*
 * Frank Huang
 * 1/18/2023
 * For ICS3U7 Ms.Strelkovska
 * Class used for instructions
 */

import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;
import javax.swing.*;
import java.io.*;

public class InstructionsPanel extends JPanel implements ActionListener {
    private JButton buttonMenu;
    private Image image;

    public InstructionsPanel() {
        setLayout(null);

        buttonMenu = new JButton("Menu");
        Icon i = new ImageIcon("Images/Buttons/Back.png");
        buttonMenu.setIcon(i);
        buttonMenu.setBorderPainted(true);
        buttonMenu.setFocusPainted(false);
        buttonMenu.setContentAreaFilled(false);
        buttonMenu.setBounds(0, 0, 300, 70);
        buttonMenu.addActionListener(this);
        add(buttonMenu);

        try {
            image = ImageIO.read(new File("Images/Menu/InstructionsPg.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        add(buttonMenu);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonMenu) {
            Frame.layout.show(Frame.pane, "Menu");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 1024, 768, null);
    }
}
