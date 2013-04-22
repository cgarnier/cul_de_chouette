package game.gui;

import game.gui.login.LoginPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class GuiTest extends JFrame {

	private JPanel contentPane;
	private GameControler controler;

	
	private AvailablePlayersPanel availablePlayersPanel;
	private LoginPanel loginPanel;
	private MenuPanel menuPanel;
	private WaitingPanel waitingPanel;
	private GamePanel gamePanel;
	private PlayerListPanel playerListPanel;

	/**
	 * Create the frame.
	 */
	public GuiTest(GameControler controler) {
		this.controler = controler;
		this.controler.setView(this);
		
		availablePlayersPanel = new AvailablePlayersPanel(controler);
		loginPanel = new LoginPanel(controler);
		menuPanel = new MenuPanel();
		waitingPanel = new WaitingPanel(controler);
		gamePanel = new GamePanel(controler);
		playerListPanel = new PlayerListPanel(controler);
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 820);
		contentPane = new ImagePanel("Dices/v1.jpg");
		//contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(256, 84, 246, 691);
		contentPane.add(rightPanel);
		rightPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(24, 84, 139, 467);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(leftPanel);
		
		rightPanel.setOpaque(false);
		leftPanel.setOpaque(false);
		
		
		rightPanel.add(playerListPanel);
		rightPanel.add(loginPanel);
		rightPanel.add(menuPanel);
		rightPanel.add(waitingPanel);
		rightPanel.add(gamePanel);
		leftPanel.add(availablePlayersPanel);
		
		showLogin();
	}
	public void showLogin() {
		playerListPanel.setVisible(false);
		loginPanel.setVisible(true);
		menuPanel.setVisible(false);
		waitingPanel.setVisible(false);
		gamePanel.setVisible(false);
		availablePlayersPanel.setVisible(false);
	}
	public void showMenu() {
		playerListPanel.setVisible(false);
		loginPanel.setVisible(false);
		menuPanel.setVisible(true);
		waitingPanel.setVisible(false);
		gamePanel.setVisible(false);
		availablePlayersPanel.setVisible(false);
	}
	public void showAvailable() {
		playerListPanel.setVisible(true);
		loginPanel.setVisible(false);
		menuPanel.setVisible(false);
		waitingPanel.setVisible(false);
		gamePanel.setVisible(false);
		availablePlayersPanel.setVisible(true);
	}
	public void showWaiting() {
		playerListPanel.setVisible(true);
		loginPanel.setVisible(false);
		menuPanel.setVisible(false);
		waitingPanel.setVisible(true);
		gamePanel.setVisible(false);
		availablePlayersPanel.setVisible(false);
	}
	public void showGame() {
		playerListPanel.setVisible(true);
		loginPanel.setVisible(false);
		menuPanel.setVisible(false);
		waitingPanel.setVisible(false);
		gamePanel.setVisible(true);
		availablePlayersPanel.setVisible(false);
	}
	
}
