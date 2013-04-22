package game.network;

import game.gui.game.DiceModel;

import java.util.ArrayList;
import java.util.Collections;

public class DicesCombo {
	private DiceModel d1, d2, d3;
	private int score;

	public void setTwoDices(DiceModel d1, DiceModel d2) {
		this.d1 = d1;
		this.d2 = d2;

	}

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
			setScore(d3.getFace() * d3.getFace());
			return true;
		}
		return false;
	}

	public boolean isChouette() {
		if (d1.getFace() == d2.getFace()) {
			setScore(d1.getFace() * d1.getFace());
			return true;
		}
		return false;

	}

	public boolean isCulDeChouette() {
		if (d1.getFace() == d2.getFace() && d2.getFace() == d3.getFace()) {
			setScore(40 + d1.getFace() * 10);
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

		if (suite.get(0) == suite.get(1) + 1 && suite.get(1) == suite.get(2) + 1) {
			setScore(-10);
			return true;
		}
		return false;

	}

	public boolean isChouetteVeloute() {
		if (isChouette())
			return isVeloute();
		return false;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
