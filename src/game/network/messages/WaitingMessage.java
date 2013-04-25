package game.network.messages;

import game.network.NetPlayer;

import java.io.Serializable;

/**
 * Waiting for game invite message
 * @author clement
 *
 */
public class WaitingMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NetPlayer player;
	public WaitingMessage(NetPlayer player) {
		this.player = player;
	}
	public NetPlayer getPlayer() {
		return player;
	}
	public void setPlayer(NetPlayer player) {
		this.player = player;
	}
}
