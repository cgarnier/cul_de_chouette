package game.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class AvailablePlayersPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AvailablePlayersPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblListeDesJoueurs = new JLabel("Liste des joueurs que vous pouvez inviter:");
		add(lblListeDesJoueurs, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

	}

}
