package lab5.marketEvent;
import lab5.State;
import lab5.event.EventQueue;

public class MarketClosedEvent extends MarketEvent{
	
	public MarketClosedEvent(int time) {
		super(time);
	}

	public void runEvent(State state, EventQueue queue) {
		
	}
	
}
