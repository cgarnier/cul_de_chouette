package game.gui;

import game.gui.available.AvailablePlayersPanel;
import game.gui.game.GamePanel;
import game.gui.login.LoginPanel;
import game.gui.menu.MenuPanel;
import game.gui.playerlist.PlayerListPanel;
import game.gui.waiting.WaitingPanel;

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

	JPanel rightPanel;

	/**
	 * Create the frame.
	 */
	public GuiTest(GameControler controler) {
		this.controler = controler;
		this.controler.setView(this);

		availablePlayersPanel = new AvailablePlayersPanel(controler);
		loginPanel = new LoginPanel(controler);
		menuPanel = new MenuPanel(controler);
		waitingPanel = new WaitingPanel(controler);
		gamePanel = new GamePanel(controler);
		playerListPanel = new PlayerListPanel(controler);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 820);
		contentPane = new ImagePanel("Dices/v1.jpg");
		// contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		rightPanel = new JPanel();
		rightPanel.setBounds(256, 84, 246, 691);
		contentPane.add(rightPanel);
		rightPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(24, 84, 139, 467);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(leftPanel);

		rightPanel.setOpaque(false);
		leftPanel.setOpaque(false);

		leftPanel.add(playerListPanel);
		rightPanel.add(loginPanel);
		rightPanel.add(menuPanel);
		rightPanel.add(waitingPanel);
		rightPanel.add(gamePanel);
		rightPanel.add(availablePlayersPanel);

		showLogin();
	}

	public void showLogin() {
		rightPanel.removeAll();
		rightPanel.add(loginPanel);
		playerListPanel.setVisible(false);
		reDraw();
	}

	public void showMenu() {
		rightPanel.removeAll();
		rightPanel.add(menuPanel);
		playerListPanel.setVisible(false);
		reDraw();

	}

	public void showAvailable() {
		rightPanel.removeAll();
		rightPanel.add(availablePlayersPanel);
		playerListPanel.setVisible(true);
		reDraw();

	}

	public void showWaiting() {
		rightPanel.removeAll();
		rightPanel.add(waitingPanel);
		playerListPanel.setVisible(true);
		reDraw();

	}

	public void showGame() {
		rightPanel.removeAll();
		rightPanel.add(gamePanel);
		playerListPanel.setVisible(true);
		reDraw();

	}

	private void reDraw() {
		this.rightPanel.revalidate();
		this.repaint();
	}

}
