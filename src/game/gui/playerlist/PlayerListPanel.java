package game.gui.playerlist;

import game.gui.GameControler;
import game.gui.ImagePanel;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * The player list view
 * @author clement
 *
 */
public class PlayerListPanel extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameControler controler;
	ArrayList<PlayerPanel> players;
	/**
	 * Create the panel.
	 */
	public PlayerListPanel(GameControler controler) {
		this.controler = controler;
		controler.getModel().getPlayersModel().addObserver(this);
		players = new ArrayList<PlayerPanel>();
		this.setBackground(Color.green);
		this.setOpaque(false);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		ImagePanel panel = new ImagePanel("Images/theme/panneaux_deroulants.png");
		add(panel);
		panel.setLayout(null);
		PlayerPanel playerPanel2 = new PlayerPanel(controler);
		playerPanel2.setBounds(24, 80, 196, 28);
		panel.add(playerPanel2);
		
		players.add(playerPanel2);
		PlayerPanel playerPanel2_1 = new PlayerPanel(controler);
		playerPanel2_1.setBounds(24, 118, 196, 28);
		panel.add(playerPanel2_1);
		players.add(playerPanel2_1);
		PlayerPanel playerPanel2_2 = new PlayerPanel(controler);
		playerPanel2_2.setBounds(24, 156, 196, 28);
		panel.add(playerPanel2_2);
		players.add(playerPanel2_2);
		PlayerPanel playerPanel2_3 = new PlayerPanel(controler);
		playerPanel2_3.setBounds(24, 192, 196, 28);
		panel.add(playerPanel2_3);
		players.add(playerPanel2_3);
		PlayerPanel playerPanel2_4 = new PlayerPanel(controler);
		playerPanel2_4.setBounds(24, 238, 196, 28);
		panel.add(playerPanel2_4);
		players.add(playerPanel2_4);
		PlayerPanel playerPanel2_5 = new PlayerPanel(controler);
		playerPanel2_5.setBounds(24, 278, 196, 28);
		panel.add(playerPanel2_5);
		players.add(playerPanel2_5);
		
		for (PlayerPanel p : players) {
			p.setModel(null);
		}
		
		
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
