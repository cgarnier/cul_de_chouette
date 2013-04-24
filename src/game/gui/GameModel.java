package game.gui;

import game.gui.available.AvailableModel;
import game.gui.game.DiceModel;
import game.gui.playerlist.PlayerListModel;
import game.network.DicesCombo;
import game.network.messages.GameStatus;
import game.network.messages.NetPlayer;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.internal.TwoPhaseLoad;

public class GameModel extends Observable implements Observer {

	private Session session;
	private DiceModel dice1, dice2, dice3;
	private DicesCombo dices;
	//private ArrayList<PlayerModel> players;
	private PlayerModel me;
	private PlayerModel creator;
	//private ArrayList<PlayerModel> lobbyPlayers;
	private Interaction interaction;
	public static final List<Color> ColorMap;
	static{
		List<Color> aMap = new ArrayList<Color>();
		aMap.add(Color.GREEN);
		aMap.add(Color.BLUE);
		aMap.add(Color.CYAN);
		aMap.add(Color.ORANGE);
		aMap.add(Color.PINK);
		aMap.add(Color.RED);
		ColorMap =  Collections.unmodifiableList(aMap);
		
	}
	public static enum Event {CANCEL, CONNECTED, NEWPLAYER, CREATED, JOINED, STARTED, NEWAVAILABLE};
	
	/*
	 * Modeles specifique
	 * 
	 */

	private AvailableModel availableModel;
	private PlayerListModel playersModel;
	
	private Games game = null;

	/*
	 * GamePhase WAITING - Waiting for player or for game start START - The game
	 * init and start TWODICE - Turn's player will roll 2 dices ONEDICE - Turn's
	 * player have roll 2 dices and will roll the last one CHECKDICE - Turn's
	 * player have roll all dices, it's time to check for special result
	 * INTERACTION - Special result has occur, players interaction phase SCORING
	 * - Compute new scores (next: TWODICE or FINISH) FINISH - Game is finish
	 */
	public static enum GamePhase {
		WAITING, START, TWODICES, ONEDICE, CHECKDICE, INTERACTION, SCORING, FINISH, MENU
	};

	private GamePhase gamePhase;
	private PlayerModel turn;
	private PlayerModel winner;
	private PlayerModel oneGain = null;
	private int gain;
	public GameModel() {
		interaction = new Interaction();
		availableModel = new AvailableModel();
		playersModel = new PlayerListModel();
		creator = null;
		dice1 = new DiceModel();
		dice2 = new DiceModel();
		dice3 = new DiceModel();
		dices = new DicesCombo();
		gamePhase = GamePhase.MENU;
		//players = new ArrayList<PlayerModel>();
		//lobbyPlayers = new ArrayList<PlayerModel>();
		//session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		session = (new Configuration().configure().buildSessionFactory()).openSession();

	}
	public Interaction getInteraction() {
		return interaction;
	}

	public synchronized AvailableModel getAvailableModel() {
		return availableModel;
	}



//	public ArrayList<PlayerModel> getPlayerList() {
//		return players;
//	}

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
	///////////////////////////////////////////////////////////////////////////////////////////////
	public void setPhase(GamePhase phase) {
		this.gamePhase = phase;
		if(gamePhase.equals(GamePhase.TWODICES))
			nextTurn();
		this.setChanged();
		this.notifyObservers();

	}

//	public void addPlayer(Color color, String string) {
//		players.add(new PlayerModel(string, color));
//		this.setChanged();
//		this.notifyObservers(Event.NEWPLAYER);
//
//	}

//	public void addPlayer(PlayerModel player) {
//		players.add(player);
//		this.setChanged();
//		this.notifyObservers(Event.NEWPLAYER);
//
//	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public boolean isInGame() {
		if (creator == null)
			return false;
		return true;
	}

	public PlayerModel getMe() {
		// TODO Auto-generated method stub
		return me;
	}

	public void setCreator(NetPlayer creator) {
		this.creator = new PlayerModel(creator, Color.black);
		
		
	}
	public GameStatus getGameStatus() {
		GameStatus gs = new GameStatus(this.creator.toNet(), gamePhase, dices);
		gs.fromPlayerModels(playersModel);
		return gs;
	}
	public synchronized PlayerModel getCreator() {
		return creator;
	}

	public  void setCreator(PlayerModel creator) {
		this.creator = creator;
	}

	public void setMe(PlayerModel me) {

		this.me = me;
		//players.add(me);
		//playersModel.add(me);
		setChanged();
		notifyObservers(Event.CONNECTED);
	}

//	public ArrayList<PlayerModel> getPlayers() {
//		return players;
//	}
//	public void setPlayers(ArrayList<PlayerModel> players) {
//		this.players = players;
//	}
//	public ArrayList<PlayerModel> getLobbyPlayers() {
//		return lobbyPlayers;
//	}
//	public void setLobbyPlayers(ArrayList<PlayerModel> lobbyPlayers) {
//		this.lobbyPlayers = lobbyPlayers;
//	}
	public void addPlayer(NetPlayer np) {
		Color c = ColorMap.get(playersModel.getPlayers().size());
		PlayerModel p = new PlayerModel(np,c);
		playersModel.add(p);
		setChanged();
		notifyObservers(p);
		
	}

//	public void addLobbyPlayer(NetPlayer player) {
//		lobbyPlayers.add(new PlayerModel(player, Color.black));
//		setChanged();
//		notifyObservers(Event.NEWAVAILABLE);
//		
//	}

	public void unsetCreator() {
		this.creator = null;
		
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	public Session getSession(){
		return this.session;
	}

	public synchronized PlayerListModel getPlayersModel() {
		return playersModel;
	}

	public synchronized void setPlayersModel(PlayerListModel playersModel) {
		this.playersModel = playersModel;
	}

	public void addPlayer(PlayerModel me2) {
		Color c = ColorMap.get(playersModel.getPlayers().size());
		me2.setPlayerColor(c);
		playersModel.add(me2);
		setChanged();
		notifyObservers(me2);
		
	}

	public void setDices(DicesCombo dices2) {
		this.dices.getD1().setFace(dices2.getD1().getFace());
		this.dices.getD2().setFace(dices2.getD2().getFace());
		this.dices.getD3().setFace(dices2.getD3().getFace());
		
		//this.dices = dices2;
		
		
	}

	public DicesCombo getDices() {
		// TODO Auto-generated method stub
		return dices;
	}
	public PlayerModel getTurn() {
		return turn;
		
	}
	
	public void setGame(Games game){
		this.game = game;
	}
	public void setWinner(PlayerModel p) {
		winner = p;
		
	}
	public void nextTurn() {
		turn = playersModel.getPlayers().get(
				(playersModel.getPlayers().indexOf(turn) +1)
				% playersModel.size());
		System.err.println("new turn : " + turn.getPlayerLogin() + " i "+ playersModel.getPlayers().indexOf(turn));
		
	}
	public PlayerModel getOneGain() {
		return oneGain;
	}
	public void setOneGain(PlayerModel oneGain, int i) {
		this.oneGain = oneGain;
		this.gain = i;
		setChanged();
		notifyObservers();
	}
	public int getGain() {
		return gain;
	}
	
	public Games getGame(){
		return this.game;
	}

}
