package lab5.marketEvent;
import lab5.State;
import lab5.event.Event;
import lab5.event.EventQueue;

abstract class MarketEvent extends Event{
	
	public MarketEvent(int time) {
		super(time);
	}

	abstract public void runEvent(State state, EventQueue queue);
	
}
