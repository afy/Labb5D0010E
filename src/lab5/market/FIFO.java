package lab5.market;
import java.util.ArrayList;

/**
 * 
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 *
**/

/**
 * First In First Out queue
*/
public class FIFO 
{
	private ArrayList<Object> queue = new ArrayList<Object>();
	
	/**
	 * Add an object to the queue. 
	 * @param o object to be added
	*/
	public void enqueue(Object o) {
		queue.add(o);
	}
	
	/**
	 * return the queue
	 * @return the queue
	*/
	public ArrayList<Object> getQueue() {
		return queue;
	}
	/**
	 * return the size of queue
	 * @return the size of queue
	*/
	public int size() {
		return queue.size();
	}
	/**
	 * return the first element in the queue
	 * @return the first object in list
	*/
	public Object getFirst() throws ArrayIndexOutOfBoundsException  {	
		if (queue.size() == 0) { throw new ArrayIndexOutOfBoundsException(); }
		else { return queue.get(0); }
	}
	
	/**
	 * remove the first element in the queue
	*/
	public void removeFirst() throws ArrayIndexOutOfBoundsException {
		if (queue.size() == 0) { throw new ArrayIndexOutOfBoundsException(); }
		else { queue.remove(0); }
	}
}