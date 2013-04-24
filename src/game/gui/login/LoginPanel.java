package game.gui.login;

import game.gui.GameControler;
import game.gui.ImagePanel;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class LoginPanel extends ImagePanel {
	private JTextField txtUser;
	private JTextField txtPassword;
	private GameControler controler;
	/**
	 * Create the panel.
	 */
	public LoginPanel(GameControler c) {
		super("Images/theme/cadre_medium.png");
		this.controler = c;
		this.setOpaque(false);
		setLayout(null);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setForeground(Color.WHITE);
		lblPseudo.setBounds(70, 71, 53, 15);
		add(lblPseudo);
		
		txtUser = new JTextField();
		txtUser.setForeground(Color.WHITE);
		txtUser.setBounds(141, 69, 105, 19);
		txtUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUser.selectAll();
			}
		});
		txtUser.setText("clement");
		txtUser.setOpaque(false);
		add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblMotDePass = new JLabel("Mot de passe");
		lblMotDePass.setForeground(Color.WHITE);
		lblMotDePass.setBounds(27, 98, 96, 15);
		add(lblMotDePass);
		
		txtPassword = new JPasswordField();
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBounds(141, 96, 105, 19);
		txtPassword.setText("toto");
		
		txtPassword.setOpaque(false);
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtPassword.selectAll();
			}
		});
		add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.setBounds(84, 125, 127, 25);
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPanel.this.controler.connect(txtUser.getText(), txtPassword.getText());
				
			}
		});
		add(btnSeConnecter);
		
		JButton btnCrerUnCompte = new JButton("Créer");
		btnCrerUnCompte.setBounds(84, 162, 127, 25);
		btnCrerUnCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.getView().showNewAccount();
			}
		});
		add(btnCrerUnCompte);
		
		JLabel lblConnection = new JLabel("Connexion");
		lblConnection.setForeground(Color.WHITE);
		lblConnection.setBounds(129, 12, 105, 15);
		add(lblConnection);

	}
}
