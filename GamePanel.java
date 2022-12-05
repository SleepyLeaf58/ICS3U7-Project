import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
    private JButton buttonMenu;

    public GamePanel() {
        buttonMenu = new JButton("Menu");
        buttonMenu.addActionListener(this);
        setLayout(new FlowLayout());
        add(buttonMenu);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonMenu) {
            Frame.layout.next(Frame.c);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
