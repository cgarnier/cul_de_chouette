package game.network.messages;

import java.io.Serializable;

public class CancelGameMessage implements Serializable{
	int ownerId;
	public CancelGameMessage(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getOwnerId() {
		return ownerId;
	}
}
