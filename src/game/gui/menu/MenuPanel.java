package game.gui.menu;

import game.gui.GameControler;
import game.gui.ImagePanel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;


/**
 * Menu view
 * @author clement
 *
 */
public class MenuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameControler controler;
	
	/**
	 * Create the panel.
	 */
	public MenuPanel(GameControler c) {
		controler = c;
		setLayout(new GridLayout(0, 1, 0, 0));
		this.setOpaque(false);
		
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
