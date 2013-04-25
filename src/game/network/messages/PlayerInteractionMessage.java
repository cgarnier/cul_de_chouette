package game.network.messages;

import java.io.Serializable;

import game.gui.Interaction;
import game.network.NetPlayer;

/**
 * Player interaction message
 * @author clement
 *
 */
public class PlayerInteractionMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NetPlayer player;
	Interaction.Type type;
	NetPlayer creator;
	public PlayerInteractionMessage(NetPlayer c, NetPlayer p, Interaction.Type t) {
		creator = c;
		player = p;
		type = t;
	}
	public synchronized NetPlayer getPlayer() {
		return player;
	}
	public synchronized void setPlayer(NetPlayer player) {
		this.player = player;
	}
	public synchronized Interaction.Type getType() {
		return type;
	}
	public synchronized void setType(Interaction.Type type) {
		this.type = type;
	}
	public NetPlayer getCreator() {
		// TODO Auto-generated method stub
		return creator;
	}
	
}
