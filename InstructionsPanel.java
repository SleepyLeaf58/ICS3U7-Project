/*
 * Frank Huang
 * 1/18/2023
 * For ICS3U7 Ms.Strelkovska
 * Class used for instructions. Similar to MenuPanel.java
 */

import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;
import javax.swing.*;
import java.io.*;

public class InstructionsPanel extends JPanel implements ActionListener {
    private JButton buttonBack, buttonNext;
    private Image page1, page2;
    private int currentPage;

    public InstructionsPanel() {
        setLayout(null);

        currentPage = 1;

        buttonBack = new JButton("Back");
        Icon backIcon = new ImageIcon("Images/Buttons/Back.png");
        buttonBack.setIcon(backIcon);
        buttonBack.setBorderPainted(true);
        buttonBack.setFocusPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.setBounds(0, 0, 300, 70);
        buttonBack.addActionListener(this);
        add(buttonBack);
        buttonNext = new JButton("Next");

        Icon nextIcon = new ImageIcon("Images/Buttons/Next.png");
        buttonNext.setIcon(nextIcon);
        buttonNext.setBorderPainted(true);
        buttonNext.setFocusPainted(false);
        buttonNext.setContentAreaFilled(false);
        buttonNext.setBounds(0, 100, 300, 70);
        buttonNext.addActionListener(this);
        add(buttonNext);

        try {
            page1 = ImageIO.read(new File("Images/Menu/InstructionsPg.png"));
            page2 = ImageIO.read(new File("Images/Menu/InstructionsPg2.png"));
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        add(buttonBack);
        add(buttonNext);
        buttonNext.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonBack) {
            if (currentPage == 1)
                Frame.layout.show(Frame.pane, "Menu");
            else if (currentPage == 2) {
                currentPage = 1;
                buttonNext.setVisible(true);
                paintComponent(getGraphics());
            }
        }
        else if (e.getSource() == buttonNext) {
            if (currentPage == 1) { 
                currentPage = 2;
                buttonNext.setVisible(false);
                paintComponent(getGraphics());
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentPage == 1)
            g.drawImage(page1, 0, 0, 1024, 768, null);
        else if (currentPage == 2)
            g.drawImage(page2, 0, 0, 1024, 768, null);
    }
}
