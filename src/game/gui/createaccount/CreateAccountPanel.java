package game.gui.createaccount;

import game.gui.GameControler;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CreateAccountPanel extends JPanel {

	GameControler controler;
	private JTextField txtLogin;
	private JTextField txtPass;
	private JTextField txtPass_1;
	private JTextField txtAge;
	private JTextField txtVille;
	JLabel lblVrificationMotDe;
	JLabel lblMotDePasse;
	JLabel lblAge;
	JRadioButton rdbtnM;
	/**
	 * Create the panel.
	 */
	public CreateAccountPanel(GameControler c) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblLogin = new JLabel("Login");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.EAST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		add(lblLogin, gbc_lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setText("login");
		GridBagConstraints gbc_txtLogin = new GridBagConstraints();
		gbc_txtLogin.insets = new Insets(0, 0, 5, 0);
		gbc_txtLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLogin.gridx = 1;
		gbc_txtLogin.gridy = 0;
		add(txtLogin, gbc_txtLogin);
		txtLogin.setColumns(10);
		
		lblMotDePasse = new JLabel("Mot de passe");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 1;
		add(lblMotDePasse, gbc_lblMotDePasse);
		
		txtPass = new JTextField();
		txtPass.setText("pass");
		GridBagConstraints gbc_txtPass = new GridBagConstraints();
		gbc_txtPass.insets = new Insets(0, 0, 5, 0);
		gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass.gridx = 1;
		gbc_txtPass.gridy = 1;
		add(txtPass, gbc_txtPass);
		txtPass.setColumns(10);
		
		lblVrificationMotDe = new JLabel("Vérification");
		GridBagConstraints gbc_lblVrificationMotDe = new GridBagConstraints();
		gbc_lblVrificationMotDe.anchor = GridBagConstraints.EAST;
		gbc_lblVrificationMotDe.insets = new Insets(0, 0, 5, 5);
		gbc_lblVrificationMotDe.gridx = 0;
		gbc_lblVrificationMotDe.gridy = 2;
		add(lblVrificationMotDe, gbc_lblVrificationMotDe);
		
		txtPass_1 = new JTextField();
		txtPass_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txtPass.getText().equals(txtPass_1.getText())){
					lblMotDePasse.setForeground(Color.red);
					lblVrificationMotDe.setForeground(Color.red);
					
				}
				else{
					lblMotDePasse.setForeground(Color.green);
					lblVrificationMotDe.setForeground(Color.green);
				}
			}
		});
		txtPass_1.setText("pass2");
		GridBagConstraints gbc_txtPass_1 = new GridBagConstraints();
		gbc_txtPass_1.insets = new Insets(0, 0, 5, 0);
		gbc_txtPass_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass_1.gridx = 1;
		gbc_txtPass_1.gridy = 2;
		add(txtPass_1, gbc_txtPass_1);
		txtPass_1.setColumns(10);
		
		lblAge = new JLabel("Age");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.anchor = GridBagConstraints.EAST;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 0;
		gbc_lblAge.gridy = 3;
		add(lblAge, gbc_lblAge);
		
		txtAge = new JTextField();
		txtAge.setText("age");
		GridBagConstraints gbc_txtAge = new GridBagConstraints();
		gbc_txtAge.insets = new Insets(0, 0, 5, 0);
		gbc_txtAge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAge.gridx = 1;
		gbc_txtAge.gridy = 3;
		add(txtAge, gbc_txtAge);
		txtAge.setColumns(10);
		
		JLabel lblSexe = new JLabel("Sexe");
		GridBagConstraints gbc_lblSexe = new GridBagConstraints();
		gbc_lblSexe.anchor = GridBagConstraints.EAST;
		gbc_lblSexe.insets = new Insets(0, 0, 5, 5);
		gbc_lblSexe.gridx = 0;
		gbc_lblSexe.gridy = 4;
		add(lblSexe, gbc_lblSexe);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);
		
		ButtonGroup btnGrp = new ButtonGroup();
		rdbtnM = new JRadioButton("M");
		panel.add(rdbtnM);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		panel.add(rdbtnF);
		
		btnGrp.add(rdbtnM);
		btnGrp.add(rdbtnF);
		rdbtnM.setSelected(true);
		
		JLabel lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.EAST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 0;
		gbc_lblVille.gridy = 5;
		add(lblVille, gbc_lblVille);
		
		txtVille = new JTextField();
		txtVille.setText("ville");
		GridBagConstraints gbc_txtVille = new GridBagConstraints();
		gbc_txtVille.insets = new Insets(0, 0, 5, 0);
		gbc_txtVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVille.gridx = 1;
		gbc_txtVille.gridy = 5;
		add(txtVille, gbc_txtVille);
		txtVille.setColumns(10);
		
		JButton btnCrerMonCompte = new JButton("Créer mon compte");
		btnCrerMonCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int age = 0;
				if(!txtPass.getText().equals(txtPass_1.getText())){
					lblMotDePasse.setForeground(Color.red);
					lblVrificationMotDe.setForeground(Color.red);
					return;
				}
				else{
					lblMotDePasse.setForeground(Color.green);
					lblVrificationMotDe.setForeground(Color.green);
				}
				try {age = Integer.parseInt(txtAge.getText());}
				catch(NumberFormatException exep){
					lblAge.setForeground(Color.red);
					return;
				}
				char sex = 'F';
				if(rdbtnM.isSelected()) sex = 'M';
				
				controler.createAccount(txtLogin.getText(), txtPass.getText(), age, sex, txtVille.getText());
			}
		});
		GridBagConstraints gbc_btnCrerMonCompte = new GridBagConstraints();
		gbc_btnCrerMonCompte.gridx = 1;
		gbc_btnCrerMonCompte.gridy = 6;
		add(btnCrerMonCompte, gbc_btnCrerMonCompte);
		controler = c;
	}

}
