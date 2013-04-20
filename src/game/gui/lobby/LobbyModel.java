package game.gui.lobby;

import game.gui.OnePlayerModel;
import java.util.ArrayList;
import java.util.Observable;

public class LobbyModel extends Observable{
	
	private ArrayList<OnePlayerModel> players;
	
	
	
	public LobbyModel() {
		players = new ArrayList<OnePlayerModel>();
		
	}
	

	

}
