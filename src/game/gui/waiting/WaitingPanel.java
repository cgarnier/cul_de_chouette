package game.gui.waiting;

import game.gui.GameControler;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaitingPanel extends JPanel {
	private GameControler controler;
	/**
	 * Create the panel.
	 */
	public WaitingPanel(GameControler c) {
		this.controler = c;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 209, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblEnAttenteDune = new JLabel("En attente d'une invitation ...");
		GridBagConstraints gbc_lblEnAttenteDune = new GridBagConstraints();
		gbc_lblEnAttenteDune.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnAttenteDune.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEnAttenteDune.gridx = 1;
		gbc_lblEnAttenteDune.gridy = 0;
		add(lblEnAttenteDune, gbc_lblEnAttenteDune);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.cancel();
			}
		});
		GridBagConstraints gbc_btnRetour = new GridBagConstraints();
		gbc_btnRetour.gridx = 1;
		gbc_btnRetour.gridy = 2;
		add(btnRetour, gbc_btnRetour);

	}

}
