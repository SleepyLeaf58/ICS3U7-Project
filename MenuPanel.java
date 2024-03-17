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

public class MenuPanel extends JPanel implements ActionListener {
	private JLabel title;
	private JButton btnPlay;
	private JButton btnInstructions;
	private JButton btnQuit;
	private BufferedImage menuGraphic = null;
	private Animation menuAnimation;

	public MenuPanel() {
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
		btnPlay = new JButton(new ImageIcon("Images/Buttons/Play.png"));
		btnPlay.setBorderPainted(true);
		btnPlay.setFocusPainted(false);
		btnPlay.setContentAreaFilled(false);
		btnPlay.addActionListener(this);
		btnPlay.setBounds(650, 300, 300, 70);

		// Instructions Button
		btnInstructions = new JButton(new ImageIcon("Images/Buttons/Instructions.png"));
		btnInstructions.setBorderPainted(true);
		btnInstructions.setFocusPainted(false);
		btnInstructions.setContentAreaFilled(false);
		btnInstructions.addActionListener(this);
		btnInstructions.setBounds(650, 400, 300, 70);

		// Quit Button
		btnQuit = new JButton(new ImageIcon("Images/Buttons/Quit.png"));
		btnQuit.setBorderPainted(true);
		btnQuit.setFocusPainted(false);
		btnQuit.setContentAreaFilled(false);
		btnQuit.addActionListener(this);
		btnQuit.setBounds(650, 500, 300, 70);

		add(title);
		add(btnPlay);
		add(btnInstructions);
		add(btnQuit);
		setBackground(new Color(255, 255, 255));
		// Set Background
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPlay) {
			Frame.flipToCard("PlayMenu");
		} else if (e.getSource() == btnInstructions) {
			Frame.flipToCard("Instructions");
		} else {
			System.exit(0);
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
