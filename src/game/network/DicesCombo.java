package game.network;

import game.gui.game.DiceModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class DicesCombo implements Serializable{
	private DiceModel d1, d2, d3;
	private int score;

	public DicesCombo() {
		d1 = new DiceModel();
		d2 = new DiceModel();
		d3 = new DiceModel();
		
	}
	@Deprecated
	public void setTwoDices(DiceModel d1, DiceModel d2) {
		this.d1 = d1;
		this.d2 = d2;

	}

	@Deprecated
	public void setOneDice(DiceModel d3) {
		this.d3 = d3;

	}

	public synchronized DiceModel getD1() {
		return d1;
	}

//	public synchronized void setD1(DiceModel d1) {
//		this.d1 = d1;
//	}

	public synchronized DiceModel getD2() {
		return d2;
	}

//	public synchronized void setD2(DiceModel d2) {
//		this.d2 = d2;
//	}

	public synchronized DiceModel getD3() {
		return d3;
	}

//	public synchronized void setD3(DiceModel d3) {
//		this.d3 = d3;
//	}

	public boolean isVeloute() {
		if (d1.getFace() + d2.getFace() == d3.getFace()) {
			
			return true;
		}
		return false;
	}

	public boolean isChouette() {
		if (d1.getFace() == d2.getFace()) {
			
			return true;
		}
		return false;

	}

	public boolean isCulDeChouette() {
		if (d1.getFace() == d2.getFace() && d2.getFace() == d3.getFace()) {
			
			return true;
		}
		return false;

	}

	public boolean isSuite() {
		ArrayList<Integer> suite = new ArrayList<Integer>();
		suite.add(d1.getFace());
		suite.add(d2.getFace());
		suite.add(d3.getFace());
		Collections.sort(suite);

		
		if (suite.get(0) + 1 == suite.get(1) && suite.get(1) +1 == suite.get(2)) {
			
			return true;
		}
		return false;

	}

	public boolean isChouetteVelute() {
		if (isChouette())
			return isVeloute();
		return false;
	}

	public int getScore() {
		// Chouette veloute
		if (isChouette() && isVeloute())
			return d3.getFace()*d3.getFace();
		// Suite
		if(isSuite()) return -10;
		// Cul de chouette
		if (isCulDeChouette()) 
			return 40 + d1.getFace() * 10;
		//veloute
		if (isVeloute()) 
			return d3.getFace() * d3.getFace();
			
		
		// chouette
		if (isChouette()) 
			return d1.getFace() * d1.getFace();
			
		

			
		
		return 0;
		
		
		
		
	}

	public void setScore(int score) {
		this.score = score;
	}

}
