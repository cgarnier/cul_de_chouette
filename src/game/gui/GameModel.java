package game.gui;

import game.gui.available.AvailableModel;
import game.gui.playerlist.PlayerListModel;
import game.network.DicesCombo;
import game.network.GameStatus;
import game.network.NetPlayer;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Main game model
 * @author clement
 *
 */
public class GameModel extends Observable implements Observer {

	private Session session;

	private DicesCombo dices;
	
	private PlayerModel me;
	private PlayerModel creator;

	private Interaction interaction;
	
	/*
	 * Static Color map
	 */
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
	
	
	/*
	 * Modeles specifique
	 * 
	 */

	private AvailableModel availableModel;
	private PlayerListModel playersModel;
	
	private Games game = null;



	public static enum GamePhase {
		WAITING, START, TWODICES, ONEDICE, CHECKDICE, INTERACTION, SCORING, FINISH, MENU
	};

	private GamePhase gamePhase;
	private PlayerModel turn;
	private PlayerModel oneGain = null;
	private int gain;
	
	@SuppressWarnings("deprecation")
	public GameModel() {
		interaction = new Interaction();
		availableModel = new AvailableModel();
		playersModel = new PlayerListModel();
		creator = null;

		dices = new DicesCombo();
		gamePhase = GamePhase.MENU;

		session = (new Configuration().configure().buildSessionFactory()).openSession();

	}
	
	
	/**
	 * @return Interaction object
	 */
	public Interaction getInteraction() {
		return interaction;
	}

	/**
	 * @return Return waiting for game players
	 */
	public synchronized AvailableModel getAvailableModel() {
		return availableModel;
	}



	/**
	 * Get the game Phase
	 * Could be:
	 * WAITING 		- Player is waiting for a game invite or have create a game
	 * START 		- The game has start
	 * TWODICES 	- Turn player must roll the 'Chouette'
	 * ONEDICE		- Turn player must roll the 'cul'
	 * CHECKDICE	- Turn has end, time to check the dices for interactions
	 * INTERACTION	- A dice special combination has occur, all player must say the good thing
	 * SCORING		- Time to calculate the new scores and update them on views
	 * FINISH		- A player have reach the max score
	 * MENU			- Player is in menu
	 * @return the game phase
	 */
	public GamePhase getGamePhase() {
		return gamePhase;
	}


	/**
	 * Set the game Phase
	 * Could be:
	 * WAITING 		- Player is waiting for a game invite or have create a game
	 * START 		- The game has start
	 * TWODICES 	- Turn player must roll the 'Chouette'
	 * ONEDICE		- Turn player must roll the 'cul'
	 * CHECKDICE	- Turn has end, time to check the dices for interactions
	 * INTERACTION	- A dice special combination has occur, all player must say the good thing
	 * SCORING		- Time to calculate the new scores and update them on views
	 * FINISH		- A player have reach the max score
	 * MENU			- Player is in menu
	 * 
	 * Update the turn player when reach a TWODICES game phase.
	 */
	public void setPhase(GamePhase phase) {
		this.gamePhase = phase;
		if(gamePhase.equals(GamePhase.TWODICES))
			nextTurn();
		this.setChanged();
		this.notifyObservers();

	}



	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * Does the player is in game?
	 * @return
	 */
	public boolean isInGame() {
		if (creator == null)
			return false;
		return true;
	}

	
	/**
	 * 
	 * @return the logged in player
	 */
	public PlayerModel getMe() {
		// TODO Auto-generated method stub
		return me;
	}

	
	/**
	 * Set the game creator
	 * @param creator
	 */
	public void setCreator(NetPlayer creator) {
		this.creator = new PlayerModel(creator, Color.black);
		
		
	}
	
	
	/**
	 * @return a ready to send over network game status
	 */
	public GameStatus getGameStatus() {
		GameStatus gs = new GameStatus(this.creator.toNet(), gamePhase, dices);
		gs.fromPlayerModels(playersModel);
		return gs;
	}
	
	
	/**
	 * @return the game creator, null if not in game
	 */
	public synchronized PlayerModel getCreator() {
		return creator;
	}

	/**
	 * Set the game creator
	 * @param creator
	 */
	public  void setCreator(PlayerModel creator) {
		this.creator = creator;
	}

	/**
	 * Set the logged in player
	 * @param me
	 */
	public void setMe(PlayerModel me) {
		this.me = me;
		setChanged();
		notifyObservers();
	}


	/**
	 * Add a new player the player list model
	 * (With NetPlayer)
	 * @param np
	 */
	public void addPlayer(NetPlayer np) {
		Color c = ColorMap.get(playersModel.getPlayers().size());
		PlayerModel p = new PlayerModel(np,c);
		playersModel.add(p);
		setChanged();
		notifyObservers(p);
		
	}


	/**
	 * Set the creator to null
	 */
	public void unsetCreator() {
		this.creator = null;
		
	}
	
	/**
	 * Set hibernate session
	 * @param session
	 */
	public void setSession(Session session){
		this.session = session;
	}
	
	/**
	 * @return hibernate session
	 */
	public Session getSession(){
		return this.session;
	}

	/**
	 * @return Player list model
	 */
	public synchronized PlayerListModel getPlayersModel() {
		return playersModel;
	}

	/**
	 * Set player list model
	 * @param playersModel
	 */
	public synchronized void setPlayersModel(PlayerListModel playersModel) {
		this.playersModel = playersModel;
	}

	/**
	 * Add a new player the player list model
	 * (With player model)
	 * @param me2
	 */
	public void addPlayer(PlayerModel me2) {
		Color c = ColorMap.get(playersModel.getPlayers().size());
		me2.setPlayerColor(c);
		playersModel.add(me2);
		setChanged();
		notifyObservers(me2);
		
	}

	
	/**
	 * Update dices models
	 * @param dices2
	 */
	public void setDices(DicesCombo dices2) {
		this.dices.getD1().setFace(dices2.getD1().getFace());
		this.dices.getD2().setFace(dices2.getD2().getFace());
		this.dices.getD3().setFace(dices2.getD3().getFace());
		
	}

	
	/**
	 * @return dice combination object
	 */
	public DicesCombo getDices() {
		return dices;
	}
	
	/**
	 * @return Turn player
	 */
	public PlayerModel getTurn() {
		return turn;
		
	}
	
	/**
	 * Set hibernate game mapped model
	 * @param game
	 */
	public void setGame(Games game){
		// TODO Wtf it s doing there :o
		this.game = game;
	}

	/**
	 * Select the next player
	 */
	public void nextTurn() {
		turn = playersModel.getPlayers().get(
				(playersModel.getPlayers().indexOf(turn) +1)
				% playersModel.size());
		
		
	}
	
	/**
	 * @return the winner game model
	 */
	public PlayerModel getOneGain() {
		return oneGain;
	}
	
	/**
	 * Set the winner game model with his gains
	 * @param oneGain the winner
	 * @param i his gains
	 */
	public void setOneGain(PlayerModel oneGain, int i) {
		this.oneGain = oneGain;
		this.gain = i;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @return gains of the winner
	 */
	public int getGain() {
		return gain;
	}
	
	
	public Games getGame(){
		// TODO Remove it
		return this.game;
	}

}
