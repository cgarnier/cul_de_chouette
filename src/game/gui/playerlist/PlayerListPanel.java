package game.gui.playerlist;

import game.gui.GameControler;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PlayerListPanel extends JPanel implements Observer{
	private GameControler controler;
	ArrayList<PlayerPanel2> players;
	/**
	 * Create the panel.
	 */
	public PlayerListPanel(GameControler controler) {
		this.controler = controler;
		controler.getModel().getPlayersModel().addObserver(this);
		players = new ArrayList<PlayerPanel2>();
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
		
		players.add(playerPanel2);
		players.add(playerPanel2_1);
		players.add(playerPanel2_2);
		players.add(playerPanel2_3);
		players.add(playerPanel2_4);
		players.add(playerPanel2_5);
		

		
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Updated!!!");
		for (PlayerPanel2 p : players) {
			p.setModel(null);
		}
		for (int i = 0; i < controler.getModel().getPlayersModel().size(); i++) {
			System.out.println("!!!OK");
			players.get(i).setModel(controler.getModel().getPlayersModel().getPlayers().get(i));
		}
		this.revalidate();
		this.repaint();
		
	}

}
