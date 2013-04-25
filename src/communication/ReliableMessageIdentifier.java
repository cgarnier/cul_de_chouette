package communication;

import java.io.Serializable;
/**
 * Allow to identify a reliable bmessage
 * @author clement
 *
 */
public class ReliableMessageIdentifier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1900049286746024186L;
	protected ProcessIdentifier senderId;
	protected int msgId;			// On sender process
	protected int dataHash;			// Hashcode of the msg data
	public ProcessIdentifier getSenderId() {
		return senderId;
	}
	public void setSenderId(ProcessIdentifier senderId) {
		this.senderId = senderId;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public int getDataHash() {
		return dataHash;
	}
	public void setDataHash(int dataHash) {
		this.dataHash = dataHash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ReliableMessageIdentifier){
			ReliableMessageIdentifier rmid = (ReliableMessageIdentifier) obj;
			if(rmid.getSenderId().getId() == this.senderId.getId() &&
					rmid.getMsgId() == this.getMsgId() &&
					rmid.getDataHash() == this.getDataHash()) return true;
			else return false;
		}
		
		return super.equals(obj);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{"+ senderId.getId() +", " + msgId + ", " + dataHash +"}";
	}
		
}
