package game.network.messages;

import java.io.Serializable;

public class JoinAnswerMessage implements Serializable{
	private NetPlayer creator;
	private NetPlayer player;


	public JoinAnswerMessage(NetPlayer me, NetPlayer creator) {
		this.creator = creator;
		this.player = me;
	}


	public synchronized NetPlayer getCreator() {
		return creator;
	}


	public synchronized void setCreator(NetPlayer creator) {
		this.creator = creator;
	}


	public synchronized NetPlayer getPlayer() {
		return player;
	}


	public synchronized void setPlayer(NetPlayer player) {
		this.player = player;
	}
}
