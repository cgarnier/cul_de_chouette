package game.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;

public class AnAvailablePlayerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AnAvailablePlayerPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblUsername = new JLabel("username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		add(lblUsername, gbc_lblUsername);
		
		JButton btnInviter = new JButton("Inviter");
		GridBagConstraints gbc_btnInviter = new GridBagConstraints();
		gbc_btnInviter.anchor = GridBagConstraints.EAST;
		gbc_btnInviter.gridx = 1;
		gbc_btnInviter.gridy = 0;
		add(btnInviter, gbc_btnInviter);

	}

}
