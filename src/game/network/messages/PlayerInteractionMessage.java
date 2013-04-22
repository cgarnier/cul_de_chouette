package game.network.messages;

import java.io.Serializable;

import game.gui.Interaction;

public class PlayerInteractionMessage implements Serializable{
	NetPlayer player;
	Interaction.Type type;
	public PlayerInteractionMessage(NetPlayer p, Interaction.Type t) {
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
	
}
