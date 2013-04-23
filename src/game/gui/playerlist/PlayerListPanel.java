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
	ArrayList<PlayerPanel> players;
	/**
	 * Create the panel.
	 */
	public PlayerListPanel(GameControler controler) {
		this.controler = controler;
		controler.getModel().getPlayersModel().addObserver(this);
		players = new ArrayList<PlayerPanel>();
		setLayout(null);
		this.setBackground(Color.green);
		this.setOpaque(false);
		PlayerPanel playerPanel2 = new PlayerPanel(controler);
		playerPanel2.setBounds(0, 0, 197, 25);
		add(playerPanel2);
		PlayerPanel playerPanel2_1 = new PlayerPanel(controler);
		playerPanel2_1.setBounds(0, 37, 197, 25);
		add(playerPanel2_1);
		PlayerPanel playerPanel2_2 = new PlayerPanel(controler);
		playerPanel2_2.setBounds(0, 75, 197, 25);
		add(playerPanel2_2);
		PlayerPanel playerPanel2_3 = new PlayerPanel(controler);
		playerPanel2_3.setBounds(0, 112, 197, 25);
		add(playerPanel2_3);
		PlayerPanel playerPanel2_4 = new PlayerPanel(controler);
		playerPanel2_4.setBounds(0, 153, 197, 25);
		add(playerPanel2_4);
		PlayerPanel playerPanel2_5 = new PlayerPanel(controler);
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
		
		for (PlayerPanel p : players) {
			p.setModel(null);
		}
		for (int i = 0; i < controler.getModel().getPlayersModel().size(); i++) {
			
			players.get(i).setModel(controler.getModel().getPlayersModel().getPlayers().get(i));
		}
		this.revalidate();
		this.repaint();
		
	}

}
