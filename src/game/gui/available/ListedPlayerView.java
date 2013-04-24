package game.gui.available;

import game.gui.PlayerModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListedPlayerView extends JPanel {
	JButton btInvite;
	JLabel lName;
	ActionListener actionListener;
	
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		btInvite.addActionListener(this.actionListener);
	}

	public ListedPlayerView(PlayerModel mod) {
		this.setLayout(new BorderLayout());
		
		btInvite = new JButton("Inviter");
		lName = new JLabel(mod.getPlayerLogin());
		
		this.add(lName, BorderLayout.CENTER);
		this.add(btInvite, BorderLayout.LINE_END);
		this.setPreferredSize(new Dimension(500, 30));
	}
	
	
}
