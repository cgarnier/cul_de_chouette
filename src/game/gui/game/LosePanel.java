package game.gui.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.gui.GameControler;
import game.gui.ImagePanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class LosePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JTextPane textPane;
	GameControler controler;
	public LosePanel(GameControler c) {
		controler = c;
		setLayout(null);
		
		ImagePanel panel = new ImagePanel("Images/Lose.png");
		panel.setBounds(12, 12, 426, 276);
		add(panel);
		this.setOpaque(false);
		JButton btnQuiter = new JButton("Quiter");
		
		btnQuiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controler.cancel();
			}
		});
		
		
		btnQuiter.setBounds(93, 239, 117, 25);
		panel.add(btnQuiter);
		
		textPane = new JTextPane();
		textPane.setBounds(59, 139, 218, 88);
		textPane.setOpaque(false);
		textPane.setForeground(Color.white);
		panel.add(textPane);

	}
	public void setText(String t) {
		textPane.setText(t);
	}
}
