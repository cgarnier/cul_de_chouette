package game.gui.available;

import game.gui.GameControler;
import game.gui.ImagePanel;
import game.gui.PlayerModel;

import javax.swing.JPanel;

import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * 'Waiting for game' player list view
 * @author clement
 *
 */
public class AvailablePlayersPanel extends ImagePanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JPanel panel;
	boolean test = false;
	GameControler controler;


	public AvailablePlayersPanel(GameControler c) {
		super("Images/theme/invitez_vos_amis.png");
		controler = c;
		this.controler.getModel().getAvailableModel().addObserver(this);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 30, 248, 287);
		add(scrollPane);

		panel = new JPanel();
		panel.setOpaque(false);


		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(12, 316, 248, 57);
		add(panel_1);
				
						JButton btnLancer = new JButton("Go");
						btnLancer.setBounds(0, 0, 53, 25);
						btnLancer.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (controler.getModel().getPlayersModel().size() > 1)
									controler.launch();
							}
						});
						panel_1.setLayout(null);
						panel_1.add(btnLancer);
		
				JButton button = new JButton("Rafraichir");
				button.setBounds(135, 0, 113, 25);
				panel_1.add(button);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						controler.refresh();
					}
				});
				
						JButton btnCancel = new JButton("Retour");
						btnCancel.setBounds(53, 0, 82, 25);
						btnCancel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								controler.cancelGame();
							}
						});
						panel_1.add(btnCancel);

	}

	public void reset() {

		panel.removeAll();

		panel.revalidate();
		panel.repaint();

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof AvailableModel) {
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
