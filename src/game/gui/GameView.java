package game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameView extends JFrame implements ActionListener, Observer{

	private GameControler controler;

	private JPanel pMain;
	
	private ArrayList<OnePlayerPanel> playersView;
	private JPanel pTitle;
	private JPanel pPlayerList;
	private JPanel pScore;
	private JPanel pGame;
	private JPanel pContent;
	private JPanel pDices;
	
	private JLabel lTitle;
	private JLabel lPlayerList;
	private JLabel lContent;
	
	private JButton btPlay;
	
	private DicePanel d1;
	
	private DicePanel d2;
	
	private DicePanel d3;
	
	public GameView(GameControler controler)  {
		
		playersView = new ArrayList<OnePlayerPanel>();
		this.controler = controler;
		this.setSize(800, 700);
		this.setResizable(false);
		this.getControler().getModel().addObserver(this);
		
		pMain= new JPanel(new BorderLayout());
		
		pTitle = new JPanel();
		pPlayerList = new JPanel();
		
		pContent = new JPanel();
		pScore = new JPanel();
		pGame = new JPanel();
		pDices = new JPanel();
		
		pMain.setBackground(Color.BLACK);
		pTitle.setBackground(Color.gray);
		pContent.setBackground(Color.gray);
		pPlayerList.setBackground(Color.gray);
		pPlayerList.setPreferredSize(new Dimension(180, 400));
		pContent.setPreferredSize(new Dimension(600, 400));
		pContent.setBorder(new EmptyBorder(10, 10, 10, 10));
		pMain.add(pTitle,BorderLayout.PAGE_START);
		pMain.add(pContent, BorderLayout.LINE_START);
		pMain.add(pPlayerList, BorderLayout.LINE_END);
		
		lContent = new JLabel("Le content");
		lTitle = new JLabel("Cul de chouette");
		lPlayerList = new JLabel("Player list");
		
		pTitle.add(lTitle);
		
		pDices.setLayout(new GridLayout(0, 3));
		
		pContent.setLayout(new BorderLayout());
		pContent.add(lContent, BorderLayout.PAGE_START);
		pContent.add(pDices, BorderLayout.CENTER);
		btPlay = new JButton("Lancer deux dés");
		btPlay.addActionListener(new RollDiceListener());
		pContent.add(btPlay, BorderLayout.PAGE_END);
		
		pPlayerList.add(lPlayerList);
		
		initDices();
		initPlayerList();
		this.setContentPane(pMain);
		
		//this.testDice();
		this.setVisible(true);
		(new TestThread()).start();
		
	}
	private void initPlayerList() {
		GameView.this.getControler().getModel().addPlayer(Color.BLUE, "Robert");
		GameView.this.getControler().getModel().addPlayer(Color.RED, "Robert1");
		GameView.this.getControler().getModel().addPlayer(Color.GREEN, "Robert2");
		for (OnePlayerModel aPlayer : GameView.this.getControler().getModel().getPlayerList()) {
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

		
		
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random r = new Random();
			d1.setFace(r.nextInt(6)+1);
			d2.setFace(r.nextInt(6)+1);
			d3.setFace(r.nextInt(6)+1);
			
			
		}
	}	
	public void testDice2() {
		DicePanel d1 = new DicePanel(1);
		this.pDices.add(d1);
		DicePanel d2 = new DicePanel(2);
		this.pDices.add(d2);
		DicePanel d3 = new DicePanel(6);
		this.pDices.add(d3);
		
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random r = new Random();
			d1.setFace(r.nextInt()%7 +1);
			d2.setFace(r.nextInt()%7 +1);
			d3.setFace(r.nextInt()%7 +1);
			
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Observable o, Object arg) {
		System.err.println("ok up");
		if(arg != null){
			if(arg == GameModel.NEWPLAYER){
				GameModel mod = (GameModel) o;
				OnePlayerModel player = mod.getPlayerList().get(mod.getPlayerList().size()-1);
				OnePlayerPanel p = new OnePlayerPanel(player.getPlayerName(), player.getPlayerScore(), player.getPlayerColor());
				playersView.add(p);
				player.addObserver(p);
				this.pPlayerList.add(p);
			}
			
			return;
		}
		
		/*
		 * Changement de phase de jeu
		 * Initialisation du plateau
		 */
		if(o instanceof GameModel){
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
	class RollDiceListener implements ActionListener{

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
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (OnePlayerModel aPlayer : GameView.this.getControler().getModel().getPlayerList()) {
					aPlayer.decreasePlayerScore(30);
				}
				
			}
		}
	}
}
