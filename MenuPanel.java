import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPanel extends JPanel implements ActionListener {
	private JButton buttonGame;

	public MenuPanel() {
		buttonGame = new JButton("Play");
		buttonGame.addActionListener(this);
		setLayout(new FlowLayout());
		add(buttonGame);
		// Set Background
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonGame) {
			Frame.layout.next(Frame.c);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
