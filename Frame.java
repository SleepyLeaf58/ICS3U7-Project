/*
 * Name: Frank H and David Z
 * 12/05/2022
 * For ICS3U7-01 Ms. Strelkovska
 * Creating the game Frame
 */

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	static CardLayout layout;
	static Container pane;

	private static MenuPanel menu;
	private static InstructionsPanel instructions;
	private static GamePanel game;
	private static PlayMenu playMenu;
	private static PracticePanel practicePanel;
	private static EndScreen endScreen;

	public Frame() {
		pane = getContentPane();
		layout = new CardLayout();
		pane.setLayout(layout);

		menu = new MenuPanel();
		instructions = new InstructionsPanel();
		game = new GamePanel();
		playMenu = new PlayMenu();
		practicePanel = new PracticePanel();
		endScreen = new EndScreen();

		pane.add("Menu", menu);
		pane.add("Instructions", instructions);
		pane.add("Game", game);
		pane.add("PlayMenu", playMenu);
		pane.add("Practice", practicePanel);
		pane.add("EndScreen", endScreen);
	}

	public static EndScreen getEndScreen() {
		return endScreen;
	}

	public static void flipToCard(String cardID) {
		layout.show(pane, cardID);

		if (cardID.equals("Game"))
			game.requestFocusInWindow();
		else if (cardID.equals("Instructions"))
			instructions.requestFocusInWindow();
		else if (cardID.equals("PlayMenu"))
			playMenu.requestFocus();
		else if (cardID.equals("Menu"))
			menu.requestFocus();
		else if (cardID.equals("Practice"))
			practicePanel.requestFocus();
		else if (cardID.equals("EndScreen"))
			endScreen.requestFocus();
	}
}
