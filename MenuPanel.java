import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPanel extends JPanel implements ActionListener {
	private JButton btnGame;
	private JButton btnInstructions;
	private JButton btnQuit;

	public MenuPanel() {
		setLayout(null);
		
		//Play Button
		btnGame = new JButton("Play");
		btnGame.addActionListener(this);
		btnGame.setBounds(700, 300, 300, 70);
		
		//Instructions Button
		btnInstructions = new JButton("Instructions");
		btnInstructions.addActionListener(this);
		btnInstructions.setBounds(700, 400, 300, 70);
		
		//Quit Button
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(this);
		btnQuit.setBounds(700, 500, 300, 70);
		
		add(btnGame);
		add(btnInstructions);
		add(btnQuit);
		// Set Background
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGame) {
			Frame.layout.show(Frame.pane, "Game");
		}
		else if (e.getSource() == btnInstructions) {
			Frame.layout.show(Frame.pane, "Instructions");
		}
		else {
			System.exit(0);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
