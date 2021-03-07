package lab5.marketEvent;
import lab5.State;
import lab5.event.EventQueue;
import lab5.market.Customer;

public class GoodsPaidEvent extends MarketEvent{
	
	private Customer customer;
	
	public GoodsPaidEvent(int time, Customer c) {
		super(time);
		this.customer = c;
	}
	
	
	public void runEvent(State state, EventQueue eventQueue) {
		//exit
	}
	

	private void exit() { //nödvändig??
		
	}
	
}
