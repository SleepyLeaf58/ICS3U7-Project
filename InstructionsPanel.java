import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InstructionsPanel extends JPanel implements ActionListener{
	private JButton buttonMenu;

    public InstructionsPanel() {
        buttonMenu = new JButton("Menu");
        buttonMenu.addActionListener(this);
        setLayout(new FlowLayout());
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
