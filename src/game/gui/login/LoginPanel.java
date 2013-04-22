package game.gui.login;

import game.gui.GameControler;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	private JTextField txtUser;
	private JTextField txtPassword;
	private GameControler controler;
	/**
	 * Create the panel.
	 */
	public LoginPanel(GameControler controler) {
		this.controler = controler;
		this.setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		GridBagConstraints gbc_lblPseudo = new GridBagConstraints();
		gbc_lblPseudo.anchor = GridBagConstraints.EAST;
		gbc_lblPseudo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPseudo.gridx = 0;
		gbc_lblPseudo.gridy = 0;
		add(lblPseudo, gbc_lblPseudo);
		
		txtUser = new JTextField();
		txtUser.setText("user");
		GridBagConstraints gbc_txtUser = new GridBagConstraints();
		gbc_txtUser.insets = new Insets(0, 0, 5, 0);
		gbc_txtUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUser.gridx = 1;
		gbc_txtUser.gridy = 0;
		add(txtUser, gbc_txtUser);
		txtUser.setColumns(10);
		
		JLabel lblMotDePass = new JLabel("Mot de passe");
		GridBagConstraints gbc_lblMotDePass = new GridBagConstraints();
		gbc_lblMotDePass.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePass.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePass.gridx = 0;
		gbc_lblMotDePass.gridy = 1;
		add(lblMotDePass, gbc_lblMotDePass);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 1;
		add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controler.connect(txtUser.getText(), txtPassword.getText());
				
			}
		});
		GridBagConstraints gbc_btnSeConnecter = new GridBagConstraints();
		gbc_btnSeConnecter.insets = new Insets(0, 0, 0, 5);
		gbc_btnSeConnecter.gridx = 0;
		gbc_btnSeConnecter.gridy = 2;
		add(btnSeConnecter, gbc_btnSeConnecter);

	}

}
