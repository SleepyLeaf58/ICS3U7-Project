/*
 * Frank Huang
 * 1/18/2023
 * For ICS3U7 Ms.Strelkovska
 * Class used for instructions
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InstructionsPanel extends JPanel implements ActionListener {
    private JButton buttonMenu;
    private JLabel instructions;

    public InstructionsPanel() {
        setLayout(null);

        buttonMenu = new JButton("Menu");
        Icon i = new ImageIcon("Images/Buttons/Back.png");
        buttonMenu.setIcon(i);
        buttonMenu.setBounds(0, 0, 300, 70);
        buttonMenu.addActionListener(this);

        add(buttonMenu);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonMenu) {
            Frame.layout.show(Frame.pane, "Menu");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
