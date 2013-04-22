package game.gui;




import game.gui.GameModel.Event;
import game.gui.available.ListedPlayerView;
import game.gui.game.DicePanel;
import game.network.messages.NetPlayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GameView extends JFrame implements ActionListener, Observer {

	private GameControler controler;

	private JPanel pMain;

	// //////////// Common ///////////
	//
	private ArrayList<PlayerPanel> playersView;
	private JPanel pTitle;
	private JPanel pPlayerList;
	private JPanel pContent;
	private JLabel lTitle;
	private JLabel lPlayerList;
	private JLabel lContent;
	private JButton btCancel;
	//
	// ///////////

	// /////////// GAME /////////////////
	//
	private JPanel pScore;
	private JPanel pGame;
	private JPanel pDices;
	private JButton btPlay;
	private DicePanel d1;
	private DicePanel d2;
	private DicePanel d3;
	//
	// ///////
	
	////////////// Create or Join ////////
	//
	private JPanel pCreateOrJoin;
	private JButton btCreate;
	private JButton btJoin;
	//
	////////////

	// /////////// LOBBY ////////////////
	//
	private JPanel pAvailablePlayers;
	private JPanel pConnect;
	private JPanel pWaiting;
	private JLabel lWaiting;

	// Connection
	private JTextField tfName;
	private JButton btConnect;

	// Players Views

	private ArrayList<ListedPlayerView> lobbyPlayers;

	// //////////

	public GameView(GameControler controler) {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		playersView = new ArrayList<PlayerPanel>();
		this.controler = controler;
		this.controler.getModel().addObserver(this);
		this.setSize(800, 700);
		this.setResizable(false);
		this.getControler().getModel().addObserver(this);

		initCommon();
		initGame();
		initLobby();
		initCreateOrJoin();
		initPlayerList();
		this.setContentPane(pMain);

		// this.testDice();
		this.setVisible(true);
		(new TestThread()).start();

	}

	private void initCreateOrJoin() {
		pCreateOrJoin = new JPanel();
		
		btCreate = new JButton("Créer une partie");
		btCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.create();
				pCreateOrJoin.setVisible(false);
				pAvailablePlayers.setVisible(true);
				
			}
		});
		pCreateOrJoin.add(btCreate);
		
		btJoin = new JButton("Attendre une invitation");
		btJoin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.join();
				pCreateOrJoin.setVisible(false);
				pWaiting.setVisible(true);
				
			}
		});
		pCreateOrJoin.add(btJoin);
		
		pContent.add(pCreateOrJoin);
	
		pCreateOrJoin.setVisible(false);
		
		
	}

	private void initLobby() {
		// // Connection
		pConnect = new JPanel();
		tfName = new JTextField("Joueur");
		tfName.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				tfName.selectAll();
				
			}
		});
		btConnect = new JButton("Se connecter");
		btConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//controler.connect(tfName.getText());
				GameView.this.setContentPane(pAvailablePlayers);
				
			}
		});
		pConnect.add(tfName);
		pConnect.add(btConnect);
		pContent.add(pConnect);

		pWaiting = new JPanel();
		pContent.add(pWaiting);
		lWaiting = new JLabel("En attente d'une invitation à jouer.");
		pWaiting.add(lWaiting);
		pWaiting.add(btCancel);
		pWaiting.setVisible(false);

		pAvailablePlayers = new JPanel();
		pAvailablePlayers.setPreferredSize(new Dimension(500, 200));
		pAvailablePlayers.setBackground(Color.green);
		pAvailablePlayers.setLayout(new GridLayout(0,2));
		JButton btRefresh = new JButton("Rafraichir");
		btRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.refresh();
				//pAvailablePlayers.add(new OnePlayerPanel("test", 0, Color.black));
			}
		});
		pAvailablePlayers.add(btRefresh);
		JButton btCancel2 = new JButton("Retour");
		btCancel2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.cancel();
				pAvailablePlayers.setVisible(false);
				pWaiting.setVisible(false);
				pCreateOrJoin.setVisible(true);
				
			}
		});
		pAvailablePlayers.add(btCancel2);
		pAvailablePlayers.setVisible(false);
		

		pContent.add(pAvailablePlayers);

	}
	private void initCommon() {
		pMain = new JPanel(new BorderLayout());

		pTitle = new JPanel();
		pPlayerList = new JPanel();

		pContent = new JPanel();
		pMain.setBackground(Color.BLACK);
		pTitle.setBackground(Color.gray);
		pContent.setBackground(Color.red);
		pPlayerList.setBackground(Color.gray);
		pPlayerList.setPreferredSize(new Dimension(180, 400));
		pContent.setPreferredSize(new Dimension(600, 400));
		pContent.setBorder(new EmptyBorder(10, 10, 10, 10));
		pMain.add(pTitle, BorderLayout.PAGE_START);
		pMain.add(pContent, BorderLayout.LINE_START);
		pMain.add(pPlayerList, BorderLayout.LINE_END);

		lContent = new JLabel("Le content");
		lTitle = new JLabel("Cul de chouette");
		lPlayerList = new JLabel("Player list");

		// Cancel
		btCancel = new JButton("Retour");
		btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.cancel();
				pAvailablePlayers.setVisible(false);
				pWaiting.setVisible(false);
				pCreateOrJoin.setVisible(true);
				
			}
		});
		pTitle.add(lTitle);
		pContent.setLayout(new FlowLayout());
		//pContent.add(lContent);
		pPlayerList.add(lPlayerList);
	}
	
	private void initGame() {
		pScore = new JPanel();
		pGame = new JPanel();
		pDices = new JPanel();

		

		pDices.setLayout(new GridLayout(0, 3));

		pGame.add(pDices);
		
		pContent.add(pGame);
		btPlay = new JButton("Lancer deux dés");
		btPlay.addActionListener(new RollDiceListener());
		pGame.add(btPlay, BorderLayout.PAGE_END);

		

		initDices();
		pGame.setVisible(false);

	}

	private void initPlayerList() {
		GameView.this.getControler().getModel().addPlayer(Color.BLUE, "Robert");
		GameView.this.getControler().getModel().addPlayer(Color.RED, "Robert1");
		GameView.this.getControler().getModel()
				.addPlayer(Color.GREEN, "Robert2");
		for (PlayerModel aPlayer : GameView.this.getControler().getModel()
				.getPlayerList()) {
			aPlayer.setPlayerScore(200);
		}
	}

	public void initDices() {
		d1 = new DicePanel(1);
		d1.setVisible(false);
		this.controler.getModel().getDice1().addObserver(d1);
		this.pDices.add(d1);
		d2 = new DicePanel(2);
		d2.setVisible(false);
		this.controler.getModel().getDice2().addObserver(d2);
		this.pDices.add(d2);
		d3 = new DicePanel(6);
		d3.setVisible(false);
		this.controler.getModel().getDice3().addObserver(d3);
		this.pDices.add(d3);
	}

	public void testDice() {

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random r = new Random();
			d1.setFace(r.nextInt(6) + 1);
			d2.setFace(r.nextInt(6) + 1);
			d3.setFace(r.nextInt(6) + 1);

		}
	}

	public void testDice2() {
		DicePanel d1 = new DicePanel(1);
		this.pDices.add(d1);
		DicePanel d2 = new DicePanel(2);
		this.pDices.add(d2);
		DicePanel d3 = new DicePanel(6);
		this.pDices.add(d3);

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random r = new Random();
			d1.setFace(r.nextInt() % 7 + 1);
			d2.setFace(r.nextInt() % 7 + 1);
			d3.setFace(r.nextInt() % 7 + 1);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		//System.err.println("ok up");
		if (arg != null && arg instanceof Event) {
//			if (arg == GameModel.Event.NEWPLAYER) {
//				GameModel mod = (GameModel) o;
//				OnePlayerModel player = mod.getPlayerList().get(
//						mod.getPlayerList().size() - 1);
//				OnePlayerPanel p = new OnePlayerPanel(player.getPlayerName(),
//						player.getPlayerScore(), player.getPlayerColor());
//				playersView.add(p);
//				player.addObserver(p);
//				this.pPlayerList.add(p);
//			}

			if ((Event) arg == Event.CONNECTED){
				PlayerPanel p = new PlayerPanel(controler.getModel().getMe().getPlayerLogin(),
						controler.getModel().getMe().getPlayerScore(),
						controler.getModel().getMe().getPlayerColor());
				controler.getModel().getMe().addObserver(p);
				playersView.add(p);
				pPlayerList.add(p);
				pConnect.setVisible(false);
				//pAvailablePlayers.setVisible(true);
				pCreateOrJoin.setVisible(true);
			}
			if ((Event) arg == Event.NEWPLAYER){
				PlayerModel m = controler.getModel().getPlayers().get(controler.getModel().getPlayers().size()-1);
				PlayerPanel p = new PlayerPanel(m.getPlayerLogin(), m.getPlayerScore(), m.getPlayerColor());
				m.addObserver(p);
				playersView.add(p);
				pPlayerList.add(p);
				
			}
			if ((Event) arg == Event.NEWAVAILABLE){
				System.out.println("new available...");
				PlayerModel m = controler.getModel().getLobbyPlayers().get(controler.getModel().getLobbyPlayers().size()-1);
				ListedPlayerView p = new ListedPlayerView(m);
				p.setActionListener(new InvitListener(m.toNet()));
				
				
				pAvailablePlayers.add(p);
				
				
			}
			

		}

		/*
		 * Changement de phase de jeu Initialisation du plateau
		 */
		if (o instanceof GameModel) {
			GameModel gm = (GameModel) o;
			switch (gm.getGamePhase()) {
			case START:
				d1.setVisible(false);
				d2.setVisible(false);
				d3.setVisible(false);
				btPlay.setText("Lancer deux dés");
				btPlay.setEnabled(true);
				break;
			case TWODICES:
				d1.setVisible(false);
				d2.setVisible(false);
				d3.setVisible(false);
				btPlay.setText("Lancer deux dés");
				btPlay.setEnabled(true);
				break;
			case ONEDICE:
				d1.setVisible(true);
				d2.setVisible(true);
				d3.setVisible(false);
				btPlay.setText("Lancer un dés");
				btPlay.setEnabled(true);
				break;
			case SCORING:
				d1.setVisible(true);
				d2.setVisible(true);
				d3.setVisible(true);
				btPlay.setText("Scores ...");
				btPlay.setEnabled(false);
				break;
			case INTERACTION:

				break;
			case FINISH:

				break;

			default:
				break;
			}

			return;
		}

	}

	public GameControler getControler() {
		return controler;
	}

	class RollDiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (GameView.this.getControler().getModel().getGamePhase()) {
			case TWODICES:
				GameView.this.getControler().roll2Dice();
				GameView.this.getControler().nextPhase();
				break;

			case ONEDICE:
				GameView.this.getControler().roll1Dice();
				GameView.this.getControler().nextPhase();
				break;
			case SCORING:
				GameView.this.getControler().nextPhase();
				break;

			default:
				break;
			}
			System.out.println("clik listener");

		}

	}

	class TestThread extends Thread {
		private Random rand = new Random();

		public TestThread() {

		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (PlayerModel aPlayer : GameView.this.getControler()
						.getModel().getPlayerList()) {
					aPlayer.decreasePlayerScore(30);
				}

			}
		}
	}
	class InvitListener implements ActionListener{
		NetPlayer player;
		public InvitListener(NetPlayer p) {
			player = p;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			controler.invite(player);
			((JButton)e.getSource()).setEnabled(false);
			
		}
		
	}
}
