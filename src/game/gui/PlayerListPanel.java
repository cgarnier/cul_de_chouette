package game.gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

public class PlayerListPanel extends JPanel {
	private GameControler controler;
	/**
	 * Create the panel.
	 */
	public PlayerListPanel(GameControler controler) {
		this.controler = controler;
		setLayout(null);
		this.setBackground(Color.green);
		this.setOpaque(false);
		PlayerPanel2 playerPanel2 = new PlayerPanel2(controler);
		playerPanel2.setBounds(0, 0, 197, 25);
		add(playerPanel2);
		PlayerPanel2 playerPanel2_1 = new PlayerPanel2(controler);
		playerPanel2_1.setBounds(0, 37, 197, 25);
		add(playerPanel2_1);
		PlayerPanel2 playerPanel2_2 = new PlayerPanel2(controler);
		playerPanel2_2.setBounds(0, 75, 197, 25);
		add(playerPanel2_2);
		PlayerPanel2 playerPanel2_3 = new PlayerPanel2(controler);
		playerPanel2_3.setBounds(0, 112, 197, 25);
		add(playerPanel2_3);
		PlayerPanel2 playerPanel2_4 = new PlayerPanel2(controler);
		playerPanel2_4.setBounds(0, 153, 197, 25);
		add(playerPanel2_4);
		PlayerPanel2 playerPanel2_5 = new PlayerPanel2(controler);
		playerPanel2_5.setBounds(0, 198, 197, 25);
		add(playerPanel2_5);
		
	}

}
