package game.network.messages;

import game.network.NetPlayer;

import java.io.Serializable;

/**
 * Invite message
 * @author clement
 *
 */
public class InviteMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NetPlayer creator;
	private NetPlayer guest;
	
	public InviteMessage(NetPlayer creator, NetPlayer guest) {
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
