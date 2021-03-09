package lab5.market;

import lab5.State;
import lab5.event.EventQueue;
import lab5.event.StopEvent;

/**
 * Extension of abstract class StopEvent, run at stop of simulation
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class MarketStopEvent extends StopEvent{
	
	/**
	 * Constructor
	 * @param time Current (simulated) time
	 */
	public MarketStopEvent(double time) {
		super(time);
	}

	/**
	 * Stop simulation and notify state
	 * @param state
	 * @param eventQueue
	 */
	protected void postRunEvent(State state, EventQueue eventQueue) {
		state.stopSim();
		((MarketState)state).recivedChange();
	}

}
