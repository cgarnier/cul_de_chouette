package game.gui.game;

import game.gui.GameControler;
import game.gui.GameModel.GamePhase;
import game.gui.ImagePanel;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextPane;


/**
 * The game view
 * @author clement
 *
 */
public class GamePanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameControler controler;
	JButton btnLancerLesDs;
	JLabel lblCestJoueur;
	DicePanel d1;
	DicePanel d2;
	DicePanel d3;
	JTextPane txtpnGagne;

	/**
	 * Create the panel.
	 */
	public GamePanel(GameControler controler) {
		this.controler = controler;
		this.controler.getModel().addObserver(this);
		setLayout(null);
		this.setOpaque(false);
		JPanel dicesPanel = new JPanel();
		dicesPanel.setBounds(48, 300, 213, 79);
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
		 


		add(dicesPanel);
		dicesPanel.setLayout(new GridLayout(0, 3, 0, 0));

		ImagePanel panel = new ImagePanel("Images/theme/avous_de_jouer.png");
		panel.setBounds(0, 391, 319, 226);
		add(panel);
		panel.setLayout(null);
		panel.setOpaque(false);

		btnLancerLesDs = new JButton("Lancer la chouette!");
		btnLancerLesDs.setBounds(48, 39, 184, 25);
		panel.add(btnLancerLesDs);

		JButton btnPasMouLe = new JButton("Pas mou le caillou !");
		btnPasMouLe.setBounds(48, 84, 184, 25);
		panel.add(btnPasMouLe);

		JButton btnGrellotteaPiquotte = new JButton("Grelotte ça picote !");
		btnGrellotteaPiquotte.setBounds(48, 133, 184, 25);
		panel.add(btnGrellotteaPiquotte);
		
		ImagePanel panel_1 = new ImagePanel("Images/theme/cadre_small.png");
		panel_1.setBounds(0, 30, 280, 189);
		add(panel_1);
				panel_1.setLayout(null);
				
				txtpnGagne = new JTextPane();
				txtpnGagne.setForeground(Color.white);
				txtpnGagne.setFocusable(false);
				
				
				txtpnGagne.setOpaque(false);
				txtpnGagne.setText("");
				txtpnGagne.setBounds(30, 43, 235, 92);
				panel_1.add(txtpnGagne);
				
						lblCestJoueur = new JLabel("C'est à joueur de jouer!");
						lblCestJoueur.setBounds(58, 120, 385, 15);
						lblCestJoueur.setForeground(Color.white);
						panel_1.add(lblCestJoueur);
		btnGrellotteaPiquotte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePanel.this.controler.saySuite();
			}
		});
		btnPasMouLe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePanel.this.controler.sayChouetteVeloutte();
			}
		});
		btnLancerLesDs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (GamePanel.this.controler.getModel().getGamePhase() == GamePhase.TWODICES) {
					
					GamePanel.this.controler.roll2Dice();
					btnLancerLesDs.setText("Lancer le cul!");
					return;

				}
				if (GamePanel.this.controler.getModel().getGamePhase() == GamePhase.ONEDICE) {
					GamePanel.this.controler.roll1Dice();
					btnLancerLesDs.setText("Lancer la chouette!");
					return;
				}

			}
		});

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
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
			String msg = "";
			if (controler.getModel().getOneGain() != null) {
				if (controler.getModel().getOneGain()
						.equals(controler.getModel().getMe())) {
					msg += "Vous avez ";

				} else
					msg += controler.getModel().getOneGain().getPlayerLogin()
							+ " a ";
				if (controler.getModel().getGain() >= 0)
					msg += "gagné ";
				else
					msg += "perdu ";
				msg += controler.getModel().getGain() + " points!";

			}
			txtpnGagne.setText(msg);
		}
		if (controler.getModel().getGamePhase() == GamePhase.TWODICES
				|| controler.getModel().getGamePhase() == GamePhase.ONEDICE) {
			
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
