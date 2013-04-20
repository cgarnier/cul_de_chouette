package game.gui;

import game.gui.GameModel.GamePhase;
import game.network.DicesCombo;
import game.network.NetworkHandler;
import game.network.messages.GameStatus;
import game.network.messages.NetPlayer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import communication.ProcessIdentifier;

public class GameModel extends Observable implements Observer {

	public static final Object NEWPLAYER = 10;
	private DiceModel dice1, dice2, dice3;
	private DicesCombo dices;
	private ArrayList<OnePlayerModel> playerList;
	private OnePlayerModel me;
	private OnePlayerModel creator;
	

	/*
	 * GamePhase WAITING - Waiting for player or for game start START - The game
	 * init and start TWODICE - Turn's player will roll 2 dices ONEDICE - Turn's
	 * player have roll 2 dices and will roll the last one CHECKDICE - Turn's
	 * player have roll all dices, it's time to check for special result
	 * INTERACTION - Special result has occur, players interaction phase SCORING
	 * - Compute new scores (next: TWODICE or FINISH) FINISH - Game is finish
	 */
	public static enum GamePhase {
		WAITING, START, TWODICES, ONEDICE, CHECKDICE, INTERACTION, SCORING, FINISH
	};

	private GamePhase gamePhase;

	public GameModel() {
		
		creator = null;
		dice1 = new DiceModel();
		dice2 = new DiceModel();
		dice3 = new DiceModel();
		gamePhase = GamePhase.TWODICES;
		playerList = new ArrayList<OnePlayerModel>();

	}

	public void test() {
		me = new OnePlayerModel("Me", Color.GREEN);
		this.addPlayer(me);
	}

	public ArrayList<OnePlayerModel> getPlayerList() {
		return playerList;
	}

	public GamePhase getGamePhase() {
		return gamePhase;
	}

	public DiceModel getDice1() {
		return dice1;
	}

	// public void setDice1(DiceModel dice1) {
	// this.dice1 = dice1;
	// }
	public DiceModel getDice2() {
		return dice2;
	}

	// public void setDice2(DiceModel dice2) {
	// this.dice2 = dice2;
	// }
	public DiceModel getDice3() {
		return dice3;
	}

	// public void setDice3(DiceModel dice3) {
	// this.dice3 = dice3;
	// }
	public void setPhase(GamePhase phase) {
		this.gamePhase = phase;
		this.setChanged();
		this.notifyObservers();

	}

	public void addPlayer(Color color, String string) {
		playerList.add(new OnePlayerModel(string, color));
		this.setChanged();
		this.notifyObservers(NEWPLAYER);

	}

	public void addPlayer(OnePlayerModel player) {
		playerList.add(player);
		this.setChanged();
		this.notifyObservers(NEWPLAYER);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public boolean isInGame() {
		if (creator == null)
			return false;
		return true;
	}

	public OnePlayerModel getMe() {
		// TODO Auto-generated method stub
		return me;
	}

	public void setCreator(NetPlayer creator) {
		this.creator = new OnePlayerModel(creator, Color.black);
		
		
	}
	public GameStatus getGameStatus() {
		GameStatus gs = new GameStatus(this.creator.toNet(), gamePhase, dices);
		gs.fromOnePlayerModel(playerList);
		return gs;
	}

}
