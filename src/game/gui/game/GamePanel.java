package game.gui.game;

import game.gui.GameControler;
import game.gui.GameModel.GamePhase;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class GamePanel extends JPanel implements Observer {
	private GameControler controler;
	JButton btnLancerLesDs;
	JLabel lblCestJoueur;
	JLabel lblJoueurGagnepts;
	DicePanel d1;
	DicePanel d2;
	DicePanel d3;

	/**
	 * Create the panel.
	 */
	public GamePanel(GameControler controler) {
		this.controler = controler;
		this.controler.getModel().addObserver(this);
		setLayout(null);
		this.setOpaque(false);
		JPanel dicesPanel = new JPanel();
		dicesPanel.setBounds(15, 413, 213, 79);
		dicesPanel.setOpaque(false);

		d1 = new DicePanel(5);
		controler.getModel().getDices().getD1().addObserver(d1);
		d2 = new DicePanel(5);
		controler.getModel().getDices().getD2().addObserver(d2);

		d3 = new DicePanel(5);
		controler.getModel().getDices().getD3().addObserver(d3);

		dicesPanel.add(d1);
		dicesPanel.add(d2);
		dicesPanel.add(d3);

		// dicesPanel.add(new DicePanel(5));
		// dicesPanel.add(new DicePanel(5));

		add(dicesPanel);
		dicesPanel.setLayout(new GridLayout(0, 3, 0, 0));

		lblCestJoueur = new JLabel("C'est à joueur de jouer!");
		lblCestJoueur.setBounds(29, 247, 385, 15);
		add(lblCestJoueur);

		btnLancerLesDs = new JButton("Lancer les dés");
		btnLancerLesDs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (GamePanel.this.controler.getModel().getGamePhase() == GamePhase.TWODICES) {
					GamePanel.this.controler.roll2Dice();
					return;

				}
				if (GamePanel.this.controler.getModel().getGamePhase() == GamePhase.ONEDICE){
					GamePanel.this.controler.roll1Dice();
					return;
				}

			}
		});
		btnLancerLesDs.setBounds(29, 557, 184, 25);
		add(btnLancerLesDs);

		JButton btnPasMouLe = new JButton("Pas mou le cailloux");
		btnPasMouLe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePanel.this.controler.sayChouetteVeloutte();
			}
		});
		btnPasMouLe.setBounds(29, 604, 184, 25);
		add(btnPasMouLe);

		JButton btnGrellotteaPiquotte = new JButton("Grellotte ça piquotte");
		btnGrellotteaPiquotte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePanel.this.controler.saySuite();
			}
		});
		btnGrellotteaPiquotte.setBounds(29, 649, 184, 25);
		add(btnGrellotteaPiquotte);

		lblJoueurGagnepts = new JLabel(
				"Joueur gagne 100pts avec une chouette veloute!");
		lblJoueurGagnepts.setBounds(29, 189, 385, 25);
		add(lblJoueurGagnepts);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("--- Update game panel - phase "
				+ controler.getModel().getGamePhase());
		btnLancerLesDs.setEnabled(false);
		if (controler.getModel().getGamePhase() == GamePhase.ONEDICE) {
			d1.setVisible(true);
			d2.setVisible(true);

			d3.setVisible(false);
		} else {
			d1.setVisible(true);
			d2.setVisible(true);

			d3.setVisible(true);
		}

		if (controler.getModel().getGamePhase() == GamePhase.SCORING) {
			String msg ="";
			if(controler.getModel().getOneGain() != null){
				if(controler.getModel().getOneGain().equals(controler.getModel().getMe())){
					msg += "Vous avez ";

				}
				else msg += controler.getModel().getOneGain().getPlayerLogin() + " a ";
				if(controler.getModel().getGain() >= 0) msg += "gagné ";
				else msg += "perdu ";
				msg += controler.getModel().getGain() +" points!";
				
			}
			lblJoueurGagnepts.setText(msg);
		}
		if (controler.getModel().getGamePhase() == GamePhase.TWODICES
				|| controler.getModel().getGamePhase() == GamePhase.ONEDICE) {
			System.err.println("two or one");
			if (controler.getModel().getTurn()
					.equals(controler.getModel().getMe())) {
				System.err.println("my turn");
				lblCestJoueur.setText("C'est à toi de jouer!");
				btnLancerLesDs.setEnabled(true);
			} else {
				lblCestJoueur.setText("C'est à "
						+ controler.getModel().getTurn().getPlayerLogin()
						+ " de jouer!");
				btnLancerLesDs.setEnabled(false);
			}
		}

	}
}
