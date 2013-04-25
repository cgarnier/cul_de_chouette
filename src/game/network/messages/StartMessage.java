package game.network.messages;

import game.network.GameStatus;

import java.io.Serializable;

/**
 * Start game message
 * @author clement
 *
 */
public class StartMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
