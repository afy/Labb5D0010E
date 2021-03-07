package lab5.marketEvent;
import lab5.State;
import lab5.event.EventQueue;

public class CustomerArrivalEvent extends MarketEvent{
	
	public CustomerArrivalEvent(int time) {
		super(time);
	}


	public void runEvent(State state, EventQueue queue) {
		pickGoods();
	}
	
	
	void pickGoods() { //nödvändig??
		
	}
	
}
