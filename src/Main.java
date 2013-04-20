import game.gui.GameControler;
import game.gui.GameModel;
import game.gui.GameView;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameModel gm = new GameModel();
		GameControler gc = new GameControler(gm);
		GameView gw = new GameView(gc);
		gm.test();
		
	}

}
