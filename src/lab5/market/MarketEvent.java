package lab5.market;
import lab5.State;
import lab5.event.Event;
import lab5.event.EventQueue;

public abstract class MarketEvent extends Event{
	public Customer customer;
	public MarketEvent(double time) {
		super(time);
	}

	abstract public void runEvent(State state, EventQueue queue);
	
}
