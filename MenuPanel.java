import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class MenuPanel extends JPanel implements ActionListener {
	private JLabel title;
	private JButton btnGame;
	private JButton btnInstructions;
	private JButton btnQuit;
	private BufferedImage menuGraphic = null;
	private Timer t;

	public MenuPanel() {
		setLayout(null);
		ImgAnimation.loadIdle();
		t = new Timer(120, this);
		t.start();
		
		//Title
		title = new JLabel("SMASH");
		title.setBounds(705, 200, 300, 70);
		title.setFont(new Font("Arial", Font.TRUETYPE_FONT, 80));
		
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
		
		add(title);
		add(btnGame);
		add(btnInstructions);
		add(btnQuit);
		setBackground(new Color(255, 255, 255));
		// Set Background
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGame) {
			Frame.layout.show(Frame.pane, "Game");
		}
		else if (e.getSource() == btnInstructions) {
			Frame.layout.show(Frame.pane, "Instructions");
		}
		else if (e.getSource() == t) {
			menuGraphic = ImgAnimation.getNextIdle();
			repaint();
		}
		else {
			System.exit(0);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 700, 1200, 100);
		g.drawImage(menuGraphic, 0, 100,null);
	}
}
