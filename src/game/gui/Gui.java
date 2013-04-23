package game.gui;

import game.gui.GameModel.GamePhase;
import game.gui.available.AvailablePlayersPanel;
import game.gui.createaccount.CreateAccountPanel;
import game.gui.game.GamePanel;
import game.gui.login.LoginPanel;
import game.gui.menu.MenuPanel;
import game.gui.playerlist.PlayerListPanel;
import game.gui.waiting.WaitingPanel;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

public class Gui extends JFrame implements Observer {

	private JPanel contentPane;
	private GameControler controler;

	private AvailablePlayersPanel availablePlayersPanel;
	private LoginPanel loginPanel;
	private MenuPanel menuPanel;
	private WaitingPanel waitingPanel;
	private GamePanel gamePanel;
	private PlayerListPanel playerListPanel;
	private CreateAccountPanel createAccountPanel;

	JPanel rightPanel;

	/**
	 * Create the frame.
	 */
	public Gui(GameControler controler) {
		this.controler = controler;
		this.controler.setView(this);
		this.controler.getModel().addObserver(this);
		availablePlayersPanel = new AvailablePlayersPanel(controler);
		loginPanel = new LoginPanel(controler);
		menuPanel = new MenuPanel(controler);
		waitingPanel = new WaitingPanel(controler);
		gamePanel = new GamePanel(controler);
		playerListPanel = new PlayerListPanel(controler);
		createAccountPanel = new CreateAccountPanel(controler);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 820);
		contentPane = new ImagePanel("Dices/v1.jpg");
		// contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		rightPanel = new JPanel();
		rightPanel.setBounds(256, 95, 246, 691);
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
		rightPanel.setBounds(256, 95, 246, 320);
		rightPanel.add(loginPanel);
		playerListPanel.setVisible(false);
		reDraw();
	}

	public void showMenu() {
		rightPanel.removeAll();
		rightPanel.setBounds(256, 95, 246, 320);
		rightPanel.add(menuPanel);
		playerListPanel.setVisible(false);
		reDraw();

	}

	public void showAvailable() {
		rightPanel.removeAll();
		rightPanel.setBounds(256, 95, 246, 320);
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
		rightPanel.setBounds(256, 84, 246, 691);
		rightPanel.add(gamePanel);
		playerListPanel.setVisible(true);
		reDraw();

	}

	public void showNewAccount() {
		rightPanel.removeAll();
		rightPanel.add(createAccountPanel);
		playerListPanel.setVisible(true);
		reDraw();

	}

	public void showError(String error) {
		ErrorDialog dialog = new ErrorDialog(error);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);

	}

	public void showFinish() {
		showMenu();
		if (controler.getModel().getPlayersModel().getWinner()
				.equals(controler.getModel().getMe())) {
			showError("Félicitation "
					+ controler.getModel().getMe().getPlayerLogin()
					+ " vous avez gagné la partie avec un score de "
					+ controler.getModel().getMe().getPlayerScore());
			
		}
		else showError(""
				+ controler.getModel().getPlayersModel().getWinner().getPlayerLogin()
				+ " gagne la partie avec un score de "
				+ controler.getModel().getPlayersModel().getWinner().getPlayerScore());

	}

	private void reDraw() {
		this.rightPanel.revalidate();
		this.repaint();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof GameModel) {
			GameModel model = (GameModel) arg0;
			if (model.getGamePhase() == GamePhase.TWODICES) {
				showGame();
			}
			if (model.getGamePhase() == GamePhase.FINISH) {
				showFinish();
			}

		}

	}

}
