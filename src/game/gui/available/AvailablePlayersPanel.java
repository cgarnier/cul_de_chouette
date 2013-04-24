package game.gui.available;

import game.gui.GameControler;
import game.gui.ImagePanel;
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
import java.awt.GridBagLayout;
import java.awt.Insets;

public class AvailablePlayersPanel extends ImagePanel implements Observer {

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
		scrollPane.setBounds(22, 30, 238, 247);
		add(scrollPane);

		panel = new JPanel();
		panel.setOpaque(false);
		// panel.add(new AnAvailablePlayerPanel());
		// panel.add(new AnAvailablePlayerPanel());
		// panel.add(new AnAvailablePlayerPanel());

		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(22, 278, 238, 64);
		add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{87, 82, 102, 82, 0};
		gbl_panel_1.rowHeights = new int[]{25, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
				
						JButton btnLancer = new JButton("Lancer");
						btnLancer.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (controler.getModel().getPlayersModel().size() > 1)
									controler.launch();
							}
						});
						// btnLancer.setEnabled(false);
						GridBagConstraints gbc_btnLancer = new GridBagConstraints();
						gbc_btnLancer.anchor = GridBagConstraints.NORTHWEST;
						gbc_btnLancer.insets = new Insets(0, 0, 5, 5);
						gbc_btnLancer.gridx = 1;
						gbc_btnLancer.gridy = 0;
						panel_1.add(btnLancer, gbc_btnLancer);
		
				JButton button = new JButton("Rafraichir");
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.anchor = GridBagConstraints.NORTHWEST;
				gbc_button.insets = new Insets(0, 0, 5, 5);
				gbc_button.gridx = 2;
				gbc_button.gridy = 0;
				panel_1.add(button, gbc_button);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						controler.refresh();
					}
				});
				
						JButton btnCancel = new JButton("Retour");
						btnCancel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								controler.cancelGame();
							}
						});
						GridBagConstraints gbc_btnCancel = new GridBagConstraints();
						gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
						gbc_btnCancel.anchor = GridBagConstraints.NORTHWEST;
						gbc_btnCancel.gridx = 2;
						gbc_btnCancel.gridy = 1;
						panel_1.add(btnCancel, gbc_btnCancel);

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
