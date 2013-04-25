package game.network;

import game.gui.game.DiceModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The dices combination calculator.
 * Allow to detect combinations and calculate their values.
 * @author clement
 *
 */
public class DicesCombo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DiceModel d1, d2, d3;
	public DicesCombo() {
		d1 = new DiceModel();
		d2 = new DiceModel();
		d3 = new DiceModel();
		
	}


	/**
	 * @return first dice
	 */
	public synchronized DiceModel getD1() {
		return d1;
	}


	/**
	 * @return second dice
	 */
	public synchronized DiceModel getD2() {
		return d2;
	}



	/**
	 * @return third dice
	 */
	public synchronized DiceModel getD3() {
		return d3;
	}



	
	/**
	 * @return true if combination is veloute
	 */
	public boolean isVeloute() {
		if (d1.getFace() + d2.getFace() == d3.getFace()) {
			
			return true;
		}
		return false;
	}

	/**
	 * @return true if combination is chouette
	 */
	public boolean isChouette() {
		if (d1.getFace() == d2.getFace()) {
			
			return true;
		}
		return false;

	}

	/**
	 * @returntrue if combination is cul de chouette
	 */
	public boolean isCulDeChouette() {
		if (d1.getFace() == d2.getFace() && d2.getFace() == d3.getFace()) {
			
			return true;
		}
		return false;

	}

	/**
	 * @return true if combination is suite
	 */
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

	/**
	 * @return true if combination is chouette veloute
	 */
	public boolean isChouetteVelute() {
		if (isChouette())
			return isVeloute();
		return false;
	}

	
	/**
	 * @return score of the combination
	 */
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


}
