package game.gui;

import java.util.ArrayList;

/**
 * Game interaction class.
 * Allow to initialize a new interaction phase.
 * Handle players interactions order.
 * @author clement
 *
 */
public class Interaction {
	public static enum Type {NONE, SUITE,CHOUETTEVELOUTE};
	private Type expected;
	private ArrayList<PlayerModel> playersWhoHaveInteracted;
	public Interaction() {
		playersWhoHaveInteracted = new ArrayList<PlayerModel>();
		expected = Type.NONE;
	}
	
	public Type getExpected() {
		return expected;
	}
	public void expectSuite() {
		expected = Type.SUITE;
	}
	public void expectChouetteVeloute() {
		expected = Type.CHOUETTEVELOUTE;
	}
	public PlayerModel getPlayer() {
		if(expected == Type.SUITE)
			return playersWhoHaveInteracted.get(playersWhoHaveInteracted.size()-1);
		else return playersWhoHaveInteracted.get(0);
		
	}
	public void setPlayer(PlayerModel player) {
	}

	public void addPlayer(PlayerModel p) {
		if(playersWhoHaveInteracted.contains(p)) return;
		playersWhoHaveInteracted.add(p);
		
	}
	public int interacCount() {
		System.out.println(playersWhoHaveInteracted.size());
		return playersWhoHaveInteracted.size();
	}
	public void reset() {
		playersWhoHaveInteracted.clear();
		expected = Type.NONE;
	}
}
