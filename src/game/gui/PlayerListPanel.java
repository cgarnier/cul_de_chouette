package game.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class PlayerListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PlayerListPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		add(new PlayerPanel());
		add(new PlayerPanel());
		add(new PlayerPanel());
		add(new PlayerPanel());
		add(new PlayerPanel());
		
	}

}
