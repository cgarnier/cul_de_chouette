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
	
	private ArrayList<OnePlayerModel> players;
	
	
	
	public LobbyModel() {
		players = new ArrayList<OnePlayerModel>();
		
	}
	

	

}
