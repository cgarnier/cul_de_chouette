package game.gui.playerlist;

import game.gui.PlayerModel;
import game.network.NetPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;


/**
 * Observable player list model.
 * Contains players model.
 * @author clement
 *
 */
public class PlayerListModel extends Observable {
	public static final int MAXPLAYER = 6;
	private ArrayList<PlayerModel> players;
	private PlayerModel winner;
	
	public PlayerListModel() {
		
		players = new ArrayList<PlayerModel>();
		
	}
	/**
	 * Search the player model which is equal to the simplified network player model.
	 * @param np a network player
	 * @return corresponding player model
	 */
	public PlayerModel getFromNet(NetPlayer np){
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).toNet().equals(np)) return players.get(i);
		}
		return null;
	}
	
	/**
	 * Reset the player list
	 */
	public void reset() {
		players.clear();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Add a player model to the list
	 * @param aPlayer
	 */
	public void add(PlayerModel aPlayer) {
		if(players.contains(aPlayer)) return;
		players.add(aPlayer);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * remove a player model from the list
	 * @param aPlayer
	 */
	public void remove(PlayerModel aPlayer) {
		if(!players.contains(aPlayer)) return;
		players.remove(aPlayer);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * remove the player model corresponding to the network player
	 * @param aPlayer a network player
	 */
	public void remove(NetPlayer aPlayer) {
		if(!players.contains(aPlayer)) return;
		players.remove(aPlayer);
		setChanged();
		notifyObservers();
	}

	
	/**
	 * @return size of the player model list
	 */
	public int size() {
		// TODO Auto-generated method stub
		return players.size();
	}

	
	/**
	 * @return the player model list
	 * 
	 */
	public synchronized ArrayList<PlayerModel> getPlayers() {
		return players;
	}
	

	/** 
	 * Set the players model list 
	 * @param players Player model list
	 */
	public synchronized void setPlayers(ArrayList<PlayerModel> players) {
		this.players = players;
		setChanged();
		notifyObservers();
	}
	
	
	/**
	 * Shuffle the player list
	 */
	public void shuffle() {
		Collections.shuffle(players);
		setChanged();
		notifyObservers();
		
	}
	
	/**
	 * 
	 * @return the winner
	 */
	public PlayerModel getWinner() {
		// TODO Auto-generated method stub
		return winner;
	}
	
	/**
	 * Set the winner
	 * @param winner the winner
	 */
	public void setWinner(PlayerModel winner) {
		this.winner = winner;
	}
	
	/**
	 * Does the max players count in player list is reached ?
	 * @return
	 */
	public boolean isLimitReached() {
		if(players.size() >= 6) return true;
		return false;
	}
}
