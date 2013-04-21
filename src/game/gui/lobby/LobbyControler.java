package game.gui.lobby;

import java.awt.Color;

import game.gui.GameHandler;
import game.gui.PlayerModel;

public class LobbyControler {

	private LobbyModel model;
	
	public LobbyControler(LobbyModel gm) {
		model = gm;
		gh = new GameHandler(model);
	}
	public LobbyModel getModel() {
		return model;
	}


}
