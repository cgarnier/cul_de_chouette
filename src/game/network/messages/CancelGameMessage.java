package game.network.messages;

import game.network.NetPlayer;

import java.io.Serializable;

/**
 * Cancel Message
 * @author clement
 *
 */
public class CancelGameMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NetPlayer creator;
	public CancelGameMessage(NetPlayer ownerId) {
		this.creator = ownerId;
	}
	public NetPlayer getCreator() {
		return creator;
	}
}
