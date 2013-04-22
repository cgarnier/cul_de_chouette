package game.gui.playerlist;

import game.gui.GameControler;
import game.gui.PlayerModel;
import game.network.messages.NetPlayer;

import java.util.ArrayList;
import java.util.Observable;

public class PlayerListModel extends Observable {
	private ArrayList<PlayerModel> players;
	
	public PlayerListModel() {
		
		players = new ArrayList<PlayerModel>();
		
	}
	public PlayerModel getFromNet(NetPlayer np){
		return players.get(players.indexOf(np));
	}
	
	public void reset() {
		players.clear();
		setChanged();
		notifyObservers();
	}
	
	public void add(PlayerModel aPlayer) {
		if(players.contains(aPlayer)) return;
		players.add(aPlayer);
		setChanged();
		notifyObservers();
	}
	
	public void remove(PlayerModel aPlayer) {
		if(!players.contains(aPlayer)) return;
		players.remove(aPlayer);
		setChanged();
		notifyObservers();
	}

	public int size() {
		// TODO Auto-generated method stub
		return players.size();
	}

	public synchronized ArrayList<PlayerModel> getPlayers() {
		return players;
	}

	public synchronized void setPlayers(ArrayList<PlayerModel> players) {
		this.players = players;
	}
}
