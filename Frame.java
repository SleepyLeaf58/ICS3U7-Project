import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	static CardLayout layout;
	static Container c;

	private MenuPanel menu;
	private GamePanel game;

	public Frame() {
		c = getContentPane();
		layout = new CardLayout();
		c.setLayout(layout);

		menu = new MenuPanel();
		game = new GamePanel();

		c.add("Menu", menu);
		c.add("Game", game);
	}

	public static void main(String[] args) {
		Frame frame = new Frame();

		frame.setSize(1200, 800);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
