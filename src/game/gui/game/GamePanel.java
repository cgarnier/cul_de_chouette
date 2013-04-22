package game.gui.game;

import game.gui.GameControler;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class GamePanel extends JPanel {
	private GameControler controler;
	
	/**
	 * Create the panel.
	 */
	public GamePanel(GameControler controler) {
		this.controler = controler;
		setLayout(null);
		this.setOpaque(false);
		JPanel dicesPanel = new JPanel();
		dicesPanel.setBounds(0, 408, 233, 79);
		dicesPanel.setOpaque(false);
		//dicesPanel.add(new DicePanel(5));
		//dicesPanel.add(new DicePanel(5));
		//dicesPanel.add(new DicePanel(5));
		
		add(dicesPanel);
		dicesPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblCestJoueur = new JLabel("C'est à joueur de jouer!");
		lblCestJoueur.setBounds(29, 247, 385, 15);
		add(lblCestJoueur);
		
		JButton btnLancerLesDs = new JButton("Lancer les dés");
		btnLancerLesDs.setBounds(29, 557, 184, 25);
		add(btnLancerLesDs);
		
		JButton btnPasMouLe = new JButton("Pas mou le cailloux");
		btnPasMouLe.setBounds(29, 604, 184, 25);
		add(btnPasMouLe);
		
		JButton btnGrellotteaPiquotte = new JButton("Grellotte ça piquotte");
		btnGrellotteaPiquotte.setBounds(29, 649, 184, 25);
		add(btnGrellotteaPiquotte);
		
		JLabel lblJoueurGagnepts = new JLabel("Joueur gagne 100pts avec une chouette veloute!");
		lblJoueurGagnepts.setBounds(29, 189, 385, 25);
		add(lblJoueurGagnepts);


	}
}
