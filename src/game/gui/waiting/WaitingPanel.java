package game.gui.waiting;

import game.gui.GameControler;
import game.gui.ImagePanel;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaitingPanel extends ImagePanel {
	private GameControler controler;
	/**
	 * Create the panel.
	 */
	public WaitingPanel(GameControler c) {
		super("Images/theme/invitez_vos_amis.png");
		this.controler = c;
		setLayout(null);
		
		JLabel lblEnAttenteDune = new JLabel("En attente de la partie...");
		lblEnAttenteDune.setForeground(Color.white);
		lblEnAttenteDune.setBounds(25, 39, 209, 15);
		add(lblEnAttenteDune);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(93, 112, 82, 25);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.cancelWaiting();
			}
		});
		add(btnRetour);

	}

}
