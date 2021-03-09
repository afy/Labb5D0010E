package lab5.market;
import lab5.State;
import lab5.event.EventQueue;

/**
 * Event called to close market; NOTE: not to be confused with StopEvent
 * @author Simon Engstr�m, Hannes Furhoff, Emil Wiklund, Johannes Sundstr�m
 */
public class MarketClosedEvent extends MarketEvent{
	
	/**
	 * Constructor
	 * @param time Current simulation time
	 */
	public MarketClosedEvent(double time) {
		super(time);
	}

	/**
	 * Set lastQueueSize and close market
	 * @param state Market state
	 * @param queue EventQueue
	 */
	public void runEvent(State state, EventQueue queue) {
		MarketState mstate = ((MarketState) state);
		mstate.lastN = mstate.getN();

		mstate.lastRegisterQueueSize = mstate.registerQueue.size();
		mstate.setMarketClosed();
		mstate.recivedChange();
	}
	
}
