package game.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class GamePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setLayout(null);
		
		JPanel dicesPanel = new JPanel();
		dicesPanel.setBounds(29, 52, 385, 125);
		
		//panel.add(new DicePanel(5));
		//panel.add(new DicePanel(5));
		//panel.add(new DicePanel(5));
		
		add(dicesPanel);
		dicesPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblCestJoueur = new JLabel("C'est à joueur de jouer!");
		lblCestJoueur.setBounds(29, 25, 385, 15);
		add(lblCestJoueur);
		
		JButton btnLancerLesDs = new JButton("Lancer les dés");
		btnLancerLesDs.setBounds(230, 189, 184, 25);
		add(btnLancerLesDs);
		
		JButton btnPasMouLe = new JButton("Pas mou le cailloux");
		btnPasMouLe.setBounds(230, 226, 184, 25);
		add(btnPasMouLe);
		
		JButton btnGrellotteaPiquotte = new JButton("Grellotte ça piquotte");
		btnGrellotteaPiquotte.setBounds(29, 226, 184, 25);
		add(btnGrellotteaPiquotte);


	}
}
