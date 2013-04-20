package game.gui;

import java.util.Observable;

public class DiceModel extends Observable{
	int face;

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		System.out.println("set face " + face + "obs " +this.countObservers());
		
		this.face = face;
		this.setChanged();
		notifyObservers();
	}
	
}
