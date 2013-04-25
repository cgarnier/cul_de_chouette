package game.gui.game;

import java.io.Serializable;
import java.util.Observable;


/**
 * Simple observable model for dices
 * @author clement
 *
 */
public class DiceModel  extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int face =5;

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		//System.out.println("set face " + face + "obs " +this.countObservers());
		
		this.face = face;
		this.setChanged();
		notifyObservers();
	}
	
}
