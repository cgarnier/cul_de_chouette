package communication;

import java.io.Serializable;

public class ReliableMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected ReliableMessageIdentifier id;
	protected Object data;
	
	public ReliableMessage(ProcessIdentifier senderId, int id, Object data) {
		
		this.data = data;
		this.id = new ReliableMessageIdentifier();
		this.id.setMsgId(id);
		this.id.setSenderId(senderId);
		this.id.setDataHash(data.hashCode());
		
	}

	public ReliableMessageIdentifier getId() {
		return id;
	}
	public Object getData() {
		return data;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id " + id +", hash "+ hashCode()+", data " + data +"]";
	}

}
