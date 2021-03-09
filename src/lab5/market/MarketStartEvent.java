package lab5.market;

import lab5.State;
import lab5.event.EventQueue;
import lab5.event.StartEvent;

public class MarketStartEvent extends StartEvent{	
	public MarketStartEvent(double time) {
		super(time);
		
	}

	protected void postRunEvent(State state, EventQueue eventQueue) {
		eventQueue.addEvent(new CustomerArrivalEvent(this.queueTime + ((MarketState)state).expR.next() ));
		((MarketState)state).setMarketOpen(); //Kollar enbart om market == open
		((MarketState)state).recivedChange();
		//Incomplete
		
	}
	
}
