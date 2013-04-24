package game.gui.available;

import game.gui.GameControler;
import game.gui.PlayerModel;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnAvailablePlayerPanel extends JPanel {

	PlayerModel player;
	GameControler controler;
	/**
	 * Create the panel.
	 * @param player 
	 * @param controler 
	 */
	public AnAvailablePlayerPanel(GameControler c, PlayerModel p) {
		player = p;
		controler = c;
		this.setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblUsername = new JLabel(p.getPlayerLogin());
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_lblUsername.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		add(lblUsername, gbc_lblUsername);
		lblUsername.setForeground(Color.white);
		
		JButton btnInviter = new JButton("Inviter");
		btnInviter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.invite(player.toNet());
			}
		});
		GridBagConstraints gbc_btnInviter = new GridBagConstraints();
		gbc_btnInviter.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc_btnInviter.gridx = 1;
		gbc_btnInviter.gridy = 0;
		add(btnInviter, gbc_btnInviter);
		
	}

}
