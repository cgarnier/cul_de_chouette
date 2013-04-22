package game.gui;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AvailablePlayersPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JPanel panel;
	boolean test = false;

	public AvailablePlayersPanel(GameControler controler) {
		setLayout(new BorderLayout(0, 0));
		//setPreferredSize(new Dimension(230, 350));
		JLabel lblListeDesJoueurs = new JLabel(
				"Liste des joueurs que vous pouvez inviter:");
		add(lblListeDesJoueurs, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());
		panel.add(new AnAvailablePlayerPanel());

		panel.add(new AnAvailablePlayerPanel());
		AnAvailablePlayerPanel anAvailablePlayerPanel = new AnAvailablePlayerPanel();
		panel.add(anAvailablePlayerPanel);

		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton button = new JButton("Rafraichir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});
		add(button, BorderLayout.SOUTH);
		

	}

	public void reset() {
		if (!test) {
			System.out.println("removeall");
			panel.removeAll();
			test = true;

		} else
			panel.add(new AnAvailablePlayerPanel());
		panel.revalidate();
		panel.repaint();

	}

}
