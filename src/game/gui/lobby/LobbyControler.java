package game.gui.lobby;

import java.awt.Color;

import game.gui.GameHandler;
import game.gui.OnePlayerModel;

public class LobbyControler {

	private LobbyModel model;
	private GameHandler gh;
	public LobbyControler(LobbyModel gm) {
		model = gm;
		gh = new GameHandler(model);
	}
	public LobbyModel getModel() {
		return model;
	}
	public void connect(String text) {
		model.setMe(new OnePlayerModel(text, Color.GREEN));
		
	}

}
