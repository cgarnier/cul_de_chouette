package game.gui.lobby;

import game.gui.OnePlayerModel;
import game.network.messages.NetPlayer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class LobbyModel extends Observable{
	
	public static final ArrayList<Color> ColorMap;
	static{
		ArrayList<Color> aMap = new ArrayList<Color>();
		aMap.add(Color.GREEN);
		aMap.add(Color.BLUE);
		aMap.add(Color.CYAN);
		aMap.add(Color.ORANGE);
		aMap.add(Color.PINK);
		aMap.add(Color.RED);
		ColorMap = (ArrayList<Color>) Collections.unmodifiableList(aMap);
		
	}
	public static enum Event {CONNECTED, NEWPLAYER, CREATED, JOINED, STARTED};
	
	private OnePlayerModel me;
	private ArrayList<OnePlayerModel> players;
	private ArrayList<OnePlayerModel> lobbyPlayers;
	
	
	public LobbyModel() {
		players = new ArrayList<OnePlayerModel>();
		lobbyPlayers = new ArrayList<OnePlayerModel>();
	}
	public void setMe(OnePlayerModel me) {

		this.me = me;
		players.add(me);
		setChanged();
		notifyObservers(Event.CONNECTED);
	}
	public OnePlayerModel getMe() {
		return me;
	}
	public ArrayList<OnePlayerModel> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<OnePlayerModel> players) {
		this.players = players;
	}
	public ArrayList<OnePlayerModel> getLobbyPlayers() {
		return lobbyPlayers;
	}
	public void setLobbyPlayers(ArrayList<OnePlayerModel> lobbyPlayers) {
		this.lobbyPlayers = lobbyPlayers;
	}
	public void addPlayer(NetPlayer np) {
		Color c = ColorMap.get(players.size());
		OnePlayerModel p = new OnePlayerModel(np,c);
		setChanged();
		notifyObservers(p);
		
	}

	

}
