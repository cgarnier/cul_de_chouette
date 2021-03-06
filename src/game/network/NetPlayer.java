package game.network;

import java.io.Serializable;

import game.gui.PlayerModel;
import communication.ProcessIdentifier;

/*
 * Light player data for network
 * 
 */
public class NetPlayer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6197386597780936627L;
	protected ProcessIdentifier netId;
	protected int globalId;
	public NetPlayer(PlayerModel model) {
		this.netId = model.toNet().getNetId();
		this.globalId = model.toNet().getGlobalId();
	}
	public NetPlayer() {
		netId = null;
		globalId = 0;
	}
	public synchronized ProcessIdentifier getNetId() {
		return netId;
	}
	public synchronized void setNetId(ProcessIdentifier netId) {
		this.netId = netId;
	}
	public synchronized int getGlobalId() {
		return globalId;
	}
	public synchronized void setGlobalId(int globalId) {
		this.globalId = globalId;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof NetPlayer)
			return (((NetPlayer)obj).getNetId().equals(this.netId) &&
					((NetPlayer)obj).getGlobalId() == this.globalId);
		if(obj instanceof PlayerModel){
			PlayerModel pm = (PlayerModel) obj;
			return pm.toNet().equals(this);
		}
		
		return super.equals(obj);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[NetPlayer netid: " + netId.getId() + ", "+ globalId + " ]";
	}
	
	
}
