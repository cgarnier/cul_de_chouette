package game.gui.available;

import game.gui.PlayerModel;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Players models for 'waiting for game' player list.
 * @author clement
 *
 */
public class AvailableModel extends Observable {
	ArrayList<PlayerModel> players;

	public AvailableModel() {
		players = new ArrayList<PlayerModel>();
	}
	public void reset() {
		players.clear();
		setChanged();
		notifyObservers();
	}
	public void add(PlayerModel newPlayer) {
		players.add(newPlayer);
		setChanged();
		notifyObservers();
	}
	public ArrayList<PlayerModel> getPlayers() {
		return players;
	}
}
