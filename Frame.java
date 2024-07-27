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
	private static Local2PlayerPanel game2Player;
	private static PlayMenu playMenu;
	private static PracticePanel practicePanel;
	private static EndScreen endScreen;
	private static CheatsPanel cheatsPanel;

	public Frame() {
		pane = getContentPane();
		layout = new CardLayout();
		pane.setLayout(layout);

		menu = new MenuPanel();
		instructions = new InstructionsPanel();
		game = new GamePanel();
		game2Player = new Local2PlayerPanel();
		playMenu = new PlayMenu();
		practicePanel = new PracticePanel();
		endScreen = new EndScreen();
		cheatsPanel = new CheatsPanel();

		// Adds cards to pane
		pane.add("Menu", menu);
		pane.add("Instructions", instructions);
		pane.add("Game", game);
		pane.add("2Player", game2Player);
		pane.add("PlayMenu", playMenu);
		pane.add("Practice", practicePanel);
		pane.add("EndScreen", endScreen);
		pane.add("CheatsGame", cheatsPanel);
	}

	// Gets endscreen
	public static EndScreen getEndScreen() {
		return endScreen;
	}

	// Switchs card and requests focus
	public static void flipToCard(String cardID) {
		layout.show(pane, cardID);

		if (cardID.equals("Game"))
			game.requestFocusInWindow();
		else if (cardID.equals("2Player"))
			game2Player.requestFocusInWindow();
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
		else if (cardID.equals("CheatsGame"))
			;
		cheatsPanel.requestFocus();
	}
}
