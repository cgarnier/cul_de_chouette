package game.gui.playerlist;

import game.gui.GameControler;
import game.gui.PlayerModel;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

public class PlayerPanel extends JPanel implements Observer {
	private GameControler controler;
	private PlayerModel model;
	JPanel panel;
	JLabel lblUsername;
	JLabel lblScore;
	/**
	 * Create the panel.
	 */
	public PlayerPanel(GameControler controler) {
		this.controler = controler;
		this.setOpaque(false);
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel.setSize(30, 30);
		add(panel, gbc_panel);
		
		lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(Color.white);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 0;
		add(lblUsername, gbc_lblUsername);
		
		lblScore = new JLabel(" (340)");
		lblScore.setForeground(Color.white);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		add(lblScore, gbc_label);

	}
	public void setModel(PlayerModel m) {
		if(model != null) model.deleteObserver(this);
		this.model = m;
		if(this.model == null){
			panel.setVisible(false);
			lblScore.setVisible(false);
			lblUsername.setText("<LIBRE>");
		}
		else{
			model.addObserver(this);
			panel.setVisible(true);
			panel.setBackground(model.getPlayerColor());
			lblScore.setVisible(true);
			lblScore.setText("("+model.getPlayerScore()+")");
			lblUsername.setText(model.getPlayerLogin());
	
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof PlayerModel){
			lblScore.setText("("+model.getPlayerScore()+")");
		}
		
	}
}