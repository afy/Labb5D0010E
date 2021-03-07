package lab5.marketEvent;
import lab5.State;
import lab5.event.EventQueue;
import lab5.market.Customer;

public class GoodsPickedEvent extends MarketEvent{
	
	private Customer customer; //används bara här*, nödvändigt att skicka med customer i konstruktorn?
	
	public GoodsPickedEvent(int time, Customer c) {
		super(time);
		this.customer = c;
	}
	
	
	public void runEvent(State state, EventQueue queue) {
		//queue.enqueue(customer); //här
	}
	
}
