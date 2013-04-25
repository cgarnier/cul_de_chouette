package game.network.messages;

import game.network.GameStatus;

import java.io.Serializable;

/**
 * Game status update message
 * @author clement
 *
 */
public class GameStatusMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"("+status.getPhase()+")";
	}
}
