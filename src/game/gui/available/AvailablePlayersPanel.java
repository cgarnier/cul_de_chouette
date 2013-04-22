package game.gui.available;

import game.gui.GameControler;
import game.gui.PlayerModel;

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
import java.util.Observable;
import java.util.Observer;

public class AvailablePlayersPanel extends JPanel implements Observer {

	/**
	 * Create the panel.
	 */
	JPanel panel;
	boolean test = false;
	GameControler controler;

	public AvailablePlayersPanel(GameControler c) {
		controler = c;
		this.controler.getModel().getAvailableModel().addObserver(this);
		setLayout(new BorderLayout(0, 0));
		// setPreferredSize(new Dimension(230, 350));
		JLabel lblListeDesJoueurs = new JLabel(
				"Liste des joueurs que vous pouvez inviter:");
		add(lblListeDesJoueurs, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
//		panel.add(new AnAvailablePlayerPanel());
//		panel.add(new AnAvailablePlayerPanel());
//		panel.add(new AnAvailablePlayerPanel());

		

		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);

		JButton button = new JButton("Rafraichir");
		panel_1.add(button);

		JButton btnCancel = new JButton("Retour");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.cancel();
			}
		});
		panel_1.add(btnCancel);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				controler.refresh();
			}
		});

	}

	public void reset() {

		panel.removeAll();

		panel.revalidate();
		panel.repaint();

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof AvailableModel){
			AvailableModel model = (AvailableModel) arg0;
			reset();
			for (PlayerModel player : model.getPlayers()) {
				panel.add(new AnAvailablePlayerPanel(controler, player));
			}
			panel.revalidate();
			panel.repaint();
		}
		
	}

}
