package game.gui.lobby;

import game.gui.PlayerModel;
import java.util.ArrayList;
import java.util.Observable;

public class LobbyModel extends Observable{
	
	private ArrayList<PlayerModel> players;
	
	
	
	public LobbyModel() {
		players = new ArrayList<PlayerModel>();
		
	}
	

	

}
