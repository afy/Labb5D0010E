package lab5.market;

import lab5.State;
import lab5.event.EventQueue;
import lab5.event.StopEvent;

public class MarketStopEvent extends StopEvent{
	
	public MarketStopEvent(double time) {
		super(time);
	}

	protected void postRunEvent(State state, EventQueue eventQueue) {
		state.stopSim();
		((MarketState)state).recivedChange();
	}

}
