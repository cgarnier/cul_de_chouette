package game.network.messages;

import java.io.Serializable;

public class InvitMessage implements Serializable{
	private NetPlayer creator;
	private NetPlayer guest;
	
	public InvitMessage(NetPlayer creator, NetPlayer guest) {
		this.creator = creator;
		this.guest = guest;
	}

	public synchronized NetPlayer getCreator() {
		return creator;
	}

	public synchronized void setCreator(NetPlayer creator) {
		this.creator = creator;
	}

	public synchronized NetPlayer getGuest() {
		return guest;
	}

	public synchronized void setGuest(NetPlayer guest) {
		this.guest = guest;
	}
	
	
}
