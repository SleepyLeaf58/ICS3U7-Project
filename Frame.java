/*
 * Name: Frank H and David Z
 * 12/05/2022
 * For ICS3U7-01 Ms. Strelkovska
 */

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	static CardLayout layout;
	static Container pane;

	private MenuPanel menu;
	private InstructionsPanel instructions;
	private GamePanel game;

	public Frame() {
		pane = getContentPane();
		layout = new CardLayout();
		pane.setLayout(layout);

		menu = new MenuPanel();
		instructions = new InstructionsPanel();
		game = new GamePanel();

		pane.add("Menu", menu);
		pane.add("Instructions", instructions);
		pane.add("Game", game);
	}

	public static void main(String[] args) {
		Frame frame = new Frame();

		frame.setSize(1200, 800);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
