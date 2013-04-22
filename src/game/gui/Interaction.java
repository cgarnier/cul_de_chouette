package game.gui;

import java.util.ArrayList;

public class Interaction {
	public static enum Type {NONE, SUITE,CHOUETTEVELOUTE};
	private Type expected;
	private PlayerModel player;
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
		return player;
	}
	public void setPlayer(PlayerModel player) {
		this.player = player;
	}

	public void addPlayer(PlayerModel p) {
		if(playersWhoHaveInteracted.contains(p)) return;
		playersWhoHaveInteracted.add(p);
		
	}
	public int interacCount() {
		return playersWhoHaveInteracted.size();
	}
	public void reset() {
		playersWhoHaveInteracted.clear();
		player = null;
		expected = Type.NONE;
	}
}
