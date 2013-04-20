package game.gui;

import game.network.messages.NetPlayer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import communication.ProcessIdentifier;

public class OnePlayerModel extends Observable {

	protected String playerName;
	protected int playerScore;
	protected Color playerColor;
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	protected NetPlayer netId;
	
	public void setPlayerName(String name) {
		this.playerName = name;
	}

	public void setPlayerScore(int score) {
		this.playerScore = score;
		this.setChanged();
		this.notifyObservers();
	}

	public void setPlayerColor(int r, int g, int b) {
		this.playerColor = new Color(r, g, b);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPlayerColor(Color color) {
		this.playerColor = color;
		this.setChanged();
		this.notifyObservers();
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public int getPlayerScore() {
		return this.playerScore;
	}

	public Color getPlayerColor() {
		return this.playerColor;
	}

	public void increasePlayerScore(int points) {
		this.playerScore += points;
		this.setChanged();
		this.notifyObservers();
	}

	public void decreasePlayerScore(int points) {
		if (this.playerScore <= points) {
			this.playerScore = 0;
		} else {
			this.playerScore -= points;
		}
		
		this.setChanged();
		this.notifyObservers();

	}
	
	public OnePlayerModel(String name, Color color) {
		this.setPlayerName(name);
		this.setPlayerScore(0);
		this.setPlayerColor(color);
	}
	public OnePlayerModel(NetPlayer creator, Color black) {
		this.netId = creator;
		this.setPlayerColor(black);
	}

	public NetPlayer toNet() {
		netId.setGlobalId(0);
		return netId;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof NetPlayer){
			NetPlayer np = (NetPlayer) obj;
			return np.equals(this.toNet());
		}
		if(obj instanceof OnePlayerModel){
			OnePlayerModel np = (OnePlayerModel) obj;
			return np.equals(this.toNet());
		}
		
		return super.equals(obj);
	}

	public synchronized NetPlayer getNetId() {
		return netId;
	}

	public synchronized void setNetId(NetPlayer netId) {
		this.netId = netId;
	}

}
