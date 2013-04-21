package game.gui;

<<<<<<< HEAD
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Insets;

public class PlayerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PlayerPanel() {
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel.setSize(30, 30);
		add(panel, gbc_panel);
		
		JLabel lblUsername = new JLabel("username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 0;
		add(lblUsername, gbc_lblUsername);
		
		JLabel label = new JLabel(" (340)");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		add(label, gbc_label);

	}

=======
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayerPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private JLabel lName;
	private JLabel lScore;
	private JPanel coloredSquare;
	public PlayerPanel(String name, int score, Color c) {
		this.color = c;
		this.lName = new JLabel(name );
		this.lScore = new JLabel(" (" + score + ")");
		this.coloredSquare = new JPanel();
		this.setPreferredSize(new Dimension(360,30));
		
		this.coloredSquare.setBackground(this.color);
		this.coloredSquare.setSize(new Dimension(30, 30));
		this.setLayout(new GridBagLayout());
		
		
		this.add(coloredSquare);
		this.add(lName);
		this.add(lScore);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof PlayerModel){
			PlayerModel player = (PlayerModel)arg0;
			this.lName.setText(player.getPlayerName());
			this.lScore.setText(" (" + player.getPlayerScore() + ")");
			this.color = player.getPlayerColor();
		}
		
	}
	
>>>>>>> f6bf81f256612a5dc6a3ee4372fc9814c5d66d0a
}
