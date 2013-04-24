/* Coyright Eric Cariou, 2009 - 2011 */

package service.broadcast;

import java.util.Iterator;
import java.util.LinkedList;

import communication.CommunicationException;
import communication.CompoundException;
import communication.Message;
import communication.ProcessIdentifier;
import communication.ReliableMessage;
import communication.ReliableMessageIdentifier;
import communication.SynchronizedBuffer;
import service.IBroadcast;
import service.ICommunication;
import service.IIdentification;
import service.MessageDispatcher;
import service.MessageType;
import service.Service;
import service.TypedMessage;

public class ReliableBroadcastService extends Service implements IBroadcast {

	protected IIdentification idService;
	protected SynchronizedBuffer<Message> filteredBuffer;
	MessageFilter messageFilter;
	protected int IdCount = 0;
	
	public ReliableBroadcastService() {
		super();

		
		
	}
	@Override
	public void initialize(MessageDispatcher dispatcher,
			ICommunication commElt, MessageType myType) {
		// TODO Auto-generated method stub
		super.initialize(dispatcher, commElt, myType);
		messageFilter = new MessageFilter(2048, this.buffer);
		this.filteredBuffer = this.messageFilter.getFilteredBuffer();
		messageFilter.start();
	}

	public void setIdentificationService(IIdentification idService) {
		this.idService = idService;
	}

	@Override
	public void broadcast(Object data) throws CommunicationException {
		
		ProcessIdentifier id;
		Iterator it;
		CompoundException exceptions = null;
		CommunicationException firstException = null;
		it = idService.getAllIdentifiers().iterator();
		
		
		ReliableMessage rm = new ReliableMessage(idService.getMyIdentifier(), ++IdCount, data);
		messageFilter.add(rm.getId());
		// send the data to all the processes
		while (it.hasNext()) {
			id = (ProcessIdentifier) it.next();
			try {
				// simulate the crash of the process during the broadcast
				commElt.crashProcess();
				
				commElt.sendMessage(new TypedMessage(id, rm,
						MessageType.RELIABLE_BROADCAST));
				
			} catch (CommunicationException e) {
				if (firstException == null)
					firstException = e;
				else {
					if (exceptions == null) {
						exceptions = new CompoundException();
						exceptions.addException(firstException);
					}
					exceptions.addException(e);
				}
			}
		}
		if (exceptions != null)
			throw exceptions;
		if (firstException != null)
			throw firstException;
	}	
	
	private void _rebroadcast(ReliableMessage rm) throws CommunicationException {
		
		ProcessIdentifier id;
		Iterator it;
		CompoundException exceptions = null;
		CommunicationException firstException = null;
		it = idService.getAllIdentifiers().iterator();
		
		// send the data to all the processes
		while (it.hasNext()) {
			id = (ProcessIdentifier) it.next();
			try {
				// simulate the crash of the process during the broadcast
				commElt.crashProcess();
			
				
				commElt.sendMessage(new TypedMessage(id, rm,
						MessageType.RELIABLE_BROADCAST));
				
			} catch (CommunicationException e) {
				if (firstException == null)
					firstException = e;
				else {
					if (exceptions == null) {
						exceptions = new CompoundException();
						exceptions.addException(firstException);
					}
					exceptions.addException(e);
				}
			}
		}
		if (exceptions != null)
			throw exceptions;
		if (firstException != null)
			throw firstException;
	}

	@Override
	public Message synchDeliver() {
		Message msg = filteredBuffer.removeElement(true);
		
		
		ReliableMessage encMsg = (ReliableMessage) msg.getData();
		try {
			this._rebroadcast(encMsg);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg.setData(encMsg.getData());
		
		return msg;
	}

	@Override
	public Message asynchDeliver() {
		return buffer.removeElement(false);
	}

	@Override
	public boolean availableMessage() {
		return buffer.available() > 0;
	}
	
	public class MessageFilter extends Thread {
		
		protected LinkedList<ReliableMessageIdentifier> filter;
		protected int size;
		protected SynchronizedBuffer<Message> filteredBuffer;
		protected SynchronizedBuffer<Message> bufferToFilter;
		
		public MessageFilter(int size, SynchronizedBuffer<Message> bufferToFilter) {
			this.filter = new LinkedList<ReliableMessageIdentifier>();
			this.size = size;
			this.filteredBuffer = new SynchronizedBuffer<Message>();
			this.bufferToFilter = bufferToFilter;
		}
		
		@Override
		public void run() {
			while (true) {
				Message filteredMsg;
				Message msgToFilter = this.bufferToFilter.removeElement(true);
				
				System.out.println("Recut: " + msgToFilter);
				if(this._passfilter(msgToFilter)){
					
					filteredBuffer.addElement(msgToFilter);
				}
			}
		}
		public synchronized void add(ReliableMessageIdentifier id) {
			filter.addFirst(id);
		}
		private synchronized boolean _passfilter(Message msg) {
			ReliableMessage encapMsg = (ReliableMessage) msg.getData();
			
			
			// Discard if already knows
			if(filter.contains(encapMsg.getId())) return false;
			
			filter.addFirst(encapMsg.getId());
			
			while(filter.size() > this.size) filter.pollLast();
			
			return true;
		}
		
		public SynchronizedBuffer<Message> getFilteredBuffer() {
			return filteredBuffer;
		}
		
		
	}
	
	

}

