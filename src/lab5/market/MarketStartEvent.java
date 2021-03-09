package lab5.market;

import lab5.State;
import lab5.event.EventQueue;
import lab5.event.StartEvent;

/**
 * Extension of abstract class StartEvent, run at start of simulation
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class MarketStartEvent extends StartEvent{	
	
	/**
	 * Constructor
	 * @param time Current (simulated) time
	 */
	public MarketStartEvent(double time) {
		super(time);
		
	}

	/**
	 * Start simulation by adding a arrivalEvent, notify state of market opening
	 * @param state
	 * @param eventQueue
	 */
	protected void postRunEvent(State state, EventQueue eventQueue) {
		eventQueue.addEvent(new CustomerArrivalEvent(this.queueTime + ((MarketState)state).expR.next() ));
		((MarketState)state).setMarketOpen(); //Kollar enbart om market == open
		((MarketState)state).recivedChange();
		//Incomplete
		
	}
	
}
