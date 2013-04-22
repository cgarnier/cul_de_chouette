package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class OnePlayerPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private JLabel lName;
	private JLabel lScore;
	private JPanel coloredSquare;
	public OnePlayerPanel(String name, int score, Color c) {
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
			this.lName.setText(player.getPlayerLogin());
			this.lScore.setText(" (" + player.getPlayerScore() + ")");
			this.color = player.getPlayerColor();
		}
		
	}
	
}
