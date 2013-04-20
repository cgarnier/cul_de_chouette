package game.network.messages;

import java.io.Serializable;

public class StartMessage implements Serializable {
	private GameStatus status;
	
	public StartMessage(GameStatus status) {
		this.status = status;
		
	}

	public synchronized GameStatus getStatus() {
		return status;
	}

	public synchronized void setStatus(GameStatus status) {
		this.status = status;
	}



}
