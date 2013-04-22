package game.gui.menu;

import game.gui.GameControler;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {
	GameControler controler;
	/**
	 * Create the panel.
	 */
	public MenuPanel(GameControler c) {
		controler = c;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnCrerUnePartie = new JButton("Créer une partie");
		btnCrerUnePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controler.create();
			}
		});
		GridBagConstraints gbc_btnCrerUnePartie = new GridBagConstraints();
		gbc_btnCrerUnePartie.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrerUnePartie.gridx = 0;
		gbc_btnCrerUnePartie.gridy = 0;
		add(btnCrerUnePartie, gbc_btnCrerUnePartie);
		
		JButton btnAttendreUneInvitation = new JButton("Attendre une invitation");
		btnAttendreUneInvitation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.join();
			}
		});
		GridBagConstraints gbc_btnAttendreUneInvitation = new GridBagConstraints();
		gbc_btnAttendreUneInvitation.gridx = 1;
		gbc_btnAttendreUneInvitation.gridy = 0;
		add(btnAttendreUneInvitation, gbc_btnAttendreUneInvitation);

	}

}
