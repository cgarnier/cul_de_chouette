package game.network;

import java.util.ArrayList;
import java.util.Collections;

public class DicesCombo {
	private Integer d1, d2, d3;
	private int score;

	public void setTwoDices(Integer d1, Integer d2) {
		this.d1 = d1;
		this.d2 = d2;

	}

	public void setOneDice(Integer d3) {
		this.d3 = d3;

	}

	public boolean isVeloute() {
		if (d1 + d2 == d3) {
			score = d3 * d3;
			return true;
		}
		return false;
	}

	public boolean isChouette() {
		if (d1 == d2) {
			score = d1 * d1;
			return true;
		}
		return false;

	}

	public boolean isCulDeChouette() {
		if (d1 == d2 && d2 == d3) {
			score = 40 + d1 * 10;
			return true;
		}
		return false;

	}

	public boolean isSuite() {
		ArrayList<Integer> suite = new ArrayList<Integer>();
		suite.add(d1);
		suite.add(d2);
		suite.add(d3);
		Collections.sort(suite);

		if (d1 == d2 + 1 && d2 == d3 + 1) {
			score = -10;
			return true;
		}
		return false;

	}

	public boolean isChouetteVeloute() {
		if (isChouette())
			return isVeloute();
		return false;
	}

}
