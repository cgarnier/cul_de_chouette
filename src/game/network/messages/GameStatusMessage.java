package game.network.messages;

import game.gui.GameModel.GamePhase;

import java.io.Serializable;
import java.util.ArrayList;

import communication.ProcessIdentifier;

public class GameStatusMessage implements Serializable{
	protected GameStatus status;
	public GameStatusMessage(GameStatus status) {
		this.status = status;
	}
	public GameStatus getStatus() {
		return status;
	}
	public void setStatus(GameStatus status) {
		this.status = status;
	}
}
