package game.gui;

import game.gui.GameModel.GamePhase;
import game.network.messages.NetPlayer;

import java.awt.Color;
import java.util.Random;


public class GameControler {
	//private GameView view;
	
	private Random rand;
	private GameModel model;
	private GameHandler gh;
	private GuiTest view;
	public synchronized GuiTest getView() {
		return view;
	}

	public synchronized void setView(GuiTest view) {
		this.view = view;
	}

	public GameControler(GameModel gm) {
		rand = new Random();
		model = gm;
		gh = new GameHandler(model);
		
	}

	public void playPhase1() {
		int dice1 = rand.nextInt(6)+1;
		int dice2 = rand.nextInt(6)+1;
	}
	public void playPhase2(){
		
	}

	public void roll2Dice() {
		model.getDice1().setFace(rand.nextInt(6)+1);
		model.getDice2().setFace(rand.nextInt(6)+1);
		System.out.println("roll");
		
		
	}

	public GameModel getModel() {
		return model;
	}

	public void nextPhase() {
		switch (model.getGamePhase()) {
		case START:
			model.setPhase(GameModel.GamePhase.TWODICES);
			break;
		case TWODICES:
			model.setPhase(GameModel.GamePhase.ONEDICE);
			break;
		case ONEDICE:
			// GO TO SCORING OR INTERACTION
			model.setPhase(GameModel.GamePhase.SCORING);
			break;
		case INTERACTION:
			// GO TO SCORING
			model.setPhase(GameModel.GamePhase.SCORING);
			break;
		case SCORING:
			// Next player or FINISH if max score is reached.
			
			model.setPhase(GameModel.GamePhase.TWODICES);
			break;

		default:
			break;
		}
		
	}
	public void connect(String text) {
		
		
		NetPlayer myId = new NetPlayer();
		myId.setNetId(gh.getService().getMyNetId());
		PlayerModel me = new PlayerModel(text, Color.GREEN);
		me.setNetId(myId);
		model.setMe(me);
		
	}

	public void roll1Dice() {
		// TODO Auto-generated method stub
		model.getDice3().setFace(rand.nextInt(6)+1);
	}

	public void create() {
		model.setPhase(GamePhase.WAITING);
		model.setCreator(model.getMe());
		refresh();
		
	}

	public void join() {
		model.setPhase(GamePhase.WAITING);
		gh.getService().sendWaiting(model.getMe().toNet());
		
	}
	public void cancel() {
		model.unsetCreator();
		model.setPhase(GamePhase.MENU);
	}

	public void refresh() {
		gh.refresh();
		
	}

	public void invite(NetPlayer player) {
		gh.getService().invitPlayer(model.getMe().toNet(), player);
		
	}
	
}
