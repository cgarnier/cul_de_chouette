package game.network.messages;

import java.io.Serializable;

public class WaitingPlayerMessage implements Serializable {

	NetPlayer player;
	public WaitingPlayerMessage(NetPlayer player) {
		this.player = player;
	}
	public NetPlayer getPlayer() {
		return player;
	}
	public void setPlayer(NetPlayer player) {
		this.player = player;
	}
}
