import java.awt.EventQueue;

import game.gui.GameControler;
import game.gui.GameModel;

import game.gui.Gui;

/*
 * 
 * 
 */


/**
 * Main class
 * @author clement
 *
 */
public class Main {
	GameModel gm ;
	GameControler gc;

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new Main();
	}
	
	public Main() {
		
		gm = new GameModel();
		gc = new GameControler(gm);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Gui frame = new Gui(gc);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
