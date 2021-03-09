package lab5.market;
import lab5.State;
import lab5.event.Event;
import lab5.event.EventQueue;

/**
 * Abstract class, extension from Event. Only meant as a "connection" for marketEvents.
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public abstract class MarketEvent extends Event{
	
	/**
	 * Customer the event regards. Events that need customer data will override constructor to include it
	 */
	public Customer customer;
	
	/**
	 * Constructor
	 * @param time Current simulation time
	 */
	public MarketEvent(double time) {
		super(time);
	}

	/**
	 * 
	 * @param state
	 * @param queue
	 */
	abstract public void runEvent(State state, EventQueue queue);	
}
