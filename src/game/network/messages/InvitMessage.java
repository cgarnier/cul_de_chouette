package game.network.messages;

import java.io.Serializable;

public class InvitMessage implements Serializable{
	private NetPlayer creator;
	private NetPlayer guest;
	
	public InvitMessage(NetPlayer creator, NetPlayer guest) {
		this.creator = creator;
		this.guest = guest;
	}

	public  NetPlayer getCreator() {
		return creator;
	}

	public  void setCreator(NetPlayer creator) {
		this.creator = creator;
	}

	public NetPlayer getGuest() {
		return guest;
	}

	public  void setGuest(NetPlayer guest) {
		this.guest = guest;
	}
	
	
}
