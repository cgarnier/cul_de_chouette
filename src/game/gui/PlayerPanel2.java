package game.gui;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Insets;

public class PlayerPanel2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public PlayerPanel2() {
		this.setOpaque(false);
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
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(Color.white);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 0;
		add(lblUsername, gbc_lblUsername);
		
		JLabel label = new JLabel(" (340)");
		label.setForeground(Color.white);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		add(label, gbc_label);

	}
}