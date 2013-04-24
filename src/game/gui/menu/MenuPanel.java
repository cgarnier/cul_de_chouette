package game.gui.menu;

import game.gui.GameControler;
import game.gui.ImagePanel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class MenuPanel extends JPanel {
	GameControler controler;
	
	/**
	 * Create the panel.
	 */
	public MenuPanel(GameControler c) {
		controler = c;
		setLayout(new GridLayout(0, 1, 0, 0));
		this.setOpaque(false);
		//this.setPreferredSize(new Dimension(279, 361));
		ImagePanel panel = new ImagePanel("Images/theme/invitez_vos_amis.png");
		panel.setLayout(null);
		add(panel);
		
		JButton btnCrerUnePartie = new JButton("Créer");
		btnCrerUnePartie.setBounds(25, 49, 73, 25);
		panel.add(btnCrerUnePartie);
		panel.setOpaque(false);
		
		JButton btnAttendreUneInvitation = new JButton("Se faire inviter");
		btnAttendreUneInvitation.setBounds(105, 49, 138, 25);
		panel.add(btnAttendreUneInvitation);
		btnAttendreUneInvitation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.join();
			}
		});
		btnCrerUnePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controler.create();
			}
		});

	}

}
