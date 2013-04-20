package game.gui.lobby;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LobbyModel gm = new LobbyModel();
		LobbyControler gc = new LobbyControler(gm);
		LobbyView gw = new LobbyView(gc);
	}

}
