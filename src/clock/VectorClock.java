package clock;

import java.io.Serializable;
import java.util.HashMap;

import service.IIdentification;

import communication.ProcessIdentifier;

/**
 * VectorClock Horloge vectoriel de Matern
 * @author clement
 *
 */
public class VectorClock implements Serializable, Comparable<VectorClock>{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HashMap<ProcessIdentifier, Integer> vector;
	
	public VectorClock(IIdentification idService) {
		
		this.vector = new HashMap<ProcessIdentifier, Integer>();
	}
	public synchronized void set(ProcessIdentifier id, int count) {
		this.vector.put(id, count);

	}
	public synchronized void inc(ProcessIdentifier id) {
		if(this.vector.containsKey(id)) this.vector.put(id, this.vector.get(id) +1);
		else this.vector.put(id, 1);
	}
	
	public synchronized Integer evaluate() {
		int total = 0;
		for (ProcessIdentifier aKey : this.vector.keySet()) {
			total += this.vector.get(aKey);
		}
		return total;
	}
	@Override
	public int compareTo(VectorClock o) {
		
		return this.evaluate().compareTo(o.evaluate());
	}
	public int get(ProcessIdentifier id) {
		if(this.vector.containsKey(id)) return this.vector.get(id);
		else return 0;
	}
	
	
}
