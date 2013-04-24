package game.network.messages;

import java.io.Serializable;

public class CreateGameMessage implements Serializable{
	int ownerId;
	int ownerDbId;
	public CreateGameMessage(int ownerId, int ownerDbId) {
		this.ownerDbId = ownerDbId;
		this.ownerId = ownerId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getOwnerDbId() {
		return ownerDbId;
	}
	public void setOwnerDbId(int ownerDbId) {
		this.ownerDbId = ownerDbId;
	}
	

}
