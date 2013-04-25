package game.gui;

import game.gui.GameModel.GamePhase;
import game.gui.available.AvailablePlayersPanel;
import game.gui.createaccount.CreateAccountPanel;
import game.gui.game.GamePanel;
import game.gui.game.LosePanel;
import game.gui.game.WinPanel;
import game.gui.login.LoginPanel;
import game.gui.menu.MenuPanel;
import game.gui.playerlist.PlayerListPanel;
import game.gui.waiting.WaitingPanel;

import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

/**
 * Game graphical interface
 * @author clement, geoffrey
 *
 */
public class Gui extends JFrame implements Observer, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel contentPane;
	private GameControler controler;

	private AvailablePlayersPanel availablePlayersPanel;
	private LoginPanel loginPanel;
	private MenuPanel menuPanel;
	private WaitingPanel waitingPanel;
	private GamePanel gamePanel;
	private PlayerListPanel playerListPanel;
	private CreateAccountPanel createAccountPanel;
	private WinPanel winPanel;
	private LosePanel losePanel;

	JPanel rightPanel;
	JPanel leftPanel;

	/**
	 * Create the frame.
	 */
	public Gui(GameControler controler)  {
		super("Cul de chouette");
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

		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 750);
		setMaximizedBounds(new Rectangle(700, 695));
		
		contentPane = new ImagePanel("Images/theme/fond2.png");
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		rightPanel = new JPanel();
		rightPanel.setBounds(256, 95, 500, 691);
		contentPane.add(rightPanel);
		rightPanel.setLayout(new GridLayout(0, 1, 0, 0));

		leftPanel = new JPanel();
		leftPanel.setBounds(25, -250, 185, 323);
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

		ImagePanel panel = new ImagePanel("Images/theme/top.png");
		contentPane.add(panel, new Integer(5000));
		panel.setBounds(0, 0, 700, 77);

		
		winPanel = new WinPanel(controler);
		losePanel = new LosePanel(controler);
		showLogin();
		
		
		contentPane.setFocusable(true);
		contentPane.requestFocus();
		contentPane.addKeyListener(this);
		

	}

	/**
	 * Show login form
	 */
	public void showLogin() {
		rightPanel.removeAll();
		rightPanel.setBounds(220, 150, 278, 228);
		rightPanel.add(loginPanel);
		playerListPanel.setVisible(false);
		reDraw();
	}

	
	/**
	 * Show the game menu
	 */
	public void showMenu() {
		if(leftPanel.getBounds().getY() > -100)
			slidePlayerListUp();
		if(leftPanel.getBounds().getY() < -220)
			slidePlayerListInit();
		
		rightPanel.removeAll();
		rightPanel.setBounds(256, 95, 279, 361);
		rightPanel.add(menuPanel);
		playerListPanel.setVisible(true);
		reDraw();

	}

	
	/**
	 * Initialization slide. Slide a little down the player list.
	 */
	private void slidePlayerListInit() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int y = -250; y < -220; y = y + 2) {
					// System.out.println("y "+y);
					leftPanel.setBounds(25, y, 185, 323);
					Gui.this.reDraw();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				

			}
		}).start();
		
	}

	/**
	 * Show the 'waiting for a game' player list.
	 */
	public void showAvailable() {
		rightPanel.removeAll();

		rightPanel.setBounds(256, 95, 279, 361);
		rightPanel.add(availablePlayersPanel);
		playerListPanel.setVisible(true);

		slidePlayerList();

	}

	/**
	 * Slide down the player list.
	 */
	public void slidePlayerList() {
		System.out.println("Slide");
		new Thread(new Runnable() {

			// TODO Replace with math function arcsin or something
			@Override
			public void run() {
				for (int y = -200; y < 66; y = y + 2) {
					// System.out.println("y "+y);
					leftPanel.setBounds(25, y, 185, 323);
					Gui.this.reDraw();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int y = 66; y > 45; y = y - 2) {
					// System.out.println("y "+y);
					leftPanel.setBounds(25, y, 185, 323);
					Gui.this.reDraw();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int y = 45; y < 66; y = y + 1) {
					// System.out.println("y "+y);
					leftPanel.setBounds(25, y, 185, 323);
					Gui.this.reDraw();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int y = 66; y > 50; y = y - 1) {
					// System.out.println("y "+y);
					leftPanel.setBounds(25, y, 185, 323);
					Gui.this.reDraw();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
		
	}
	/**
	 * Slide up the player list.
	 */
	public void slidePlayerListUp() {
		System.out.println("Slide");
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int y = 50; y > -220; y = y - 2) {
					// System.out.println("y "+y);
					leftPanel.setBounds(25, y, 185, 323);
					Gui.this.reDraw();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				

			}
		}).start();
		
	}

	/**
	 * Show the 'waiting for invitation' view.
	 */
	public void showWaiting() {
		rightPanel.removeAll();

		rightPanel.add(waitingPanel);
		playerListPanel.setVisible(true);
		leftPanel.setOpaque(false);
		reDraw();
		//slidePlayerList();

	}

	/**
	 * Show the game view
	 */
	public void showGame() {

		rightPanel.removeAll();
		rightPanel.setBounds(300, 84, 400, 691);

		rightPanel.add(gamePanel);
		playerListPanel.setVisible(true);
		reDraw();
		contentPane.requestFocus();

	}

	
	/**
	 * Show the new account view
	 */
	public void showNewAccount() {
		rightPanel.removeAll();
		rightPanel.setBounds(256, 95, 279, 361);
		rightPanel.add(createAccountPanel);
		playerListPanel.setVisible(false);
		reDraw();

	}

	/**
	 * Show an error dialog
	 * @param error the error
	 */
	public void showError(String error) {
		ErrorDialog dialog = new ErrorDialog(error);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);

	}

	
	/**
	 * Prepare the end game view
	 */
	public void showFinish() {
		//showMenu();
		//leftPanel.setBounds(25, -220, 185, 323);
		slidePlayerListUp();
		if (controler.getModel().getPlayersModel().getWinner()
				.equals(controler.getModel().getMe())) {
			showWin("Félicitation "
					+ controler.getModel().getPlayersModel().getWinner().getPlayerLogin()
					+ " vous avez gagné la partie avec un score de "
					+ controler.getModel().getPlayersModel().getWinner().getPlayerScore());

		} else
			showLose(""
					+ controler.getModel().getPlayersModel().getWinner()
							.getPlayerLogin()
					+ " gagne la partie avec un score de "
					+ controler.getModel().getPlayersModel().getWinner()
							.getPlayerScore());

	}
	
	/**
	 * Show the winner view
	 * @param t bravo text
	 */
	public void showWin(String t) {
		rightPanel.removeAll();
		winPanel.setText(t);
		winPanel.setVisible(true);
		rightPanel.add(winPanel);
	}
	
	/**
	 * Show the looser view
	 * @param t Fail text
	 */
	public void showLose(String t) {
		rightPanel.removeAll();
		losePanel.setText(t);
		losePanel.setVisible(true);
		rightPanel.add(losePanel);
	}

	
	/**
	 * Re draw the right content pane
	 */
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
				contentPane.requestFocus();
			}
			if (model.getGamePhase() == GamePhase.FINISH) {
				showFinish();
			}
			if (model.getGamePhase() == GamePhase.WAITING && model.getCreator() == null) {
				if(leftPanel.getBounds().getY() > -100)
					slidePlayerListUp();
				
			}
			

		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.err.println("key pressed");
		if(arg0.getKeyChar() == 'x' )
			controler.cheat();
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
