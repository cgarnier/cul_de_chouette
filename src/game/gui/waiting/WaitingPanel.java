package game.gui.waiting;

import game.gui.GameControler;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class WaitingPanel extends JPanel {
	private GameControler controler;
	/**
	 * Create the panel.
	 */
	public WaitingPanel(GameControler controler) {
		this.controler = controler;
		
		JLabel lblEnAttenteDune = new JLabel("En attente d'une invitation ...");
		add(lblEnAttenteDune);

	}

}
