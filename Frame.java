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

	private static MenuPanel menu;
	private static InstructionsPanel instructions;
	private static GamePanel game;

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

	public static void flipToCard(String cardID) {
		layout.show(pane, cardID);

		if (cardID.equals("Game"))
			game.requestFocusInWindow();
		else if (cardID.equals("Instructions"))
			instructions.requestFocusInWindow();
	}
}
