package lab5.market;
import lab5.State;
import lab5.event.EventQueue;

public class MarketClosedEvent extends MarketEvent{
	
	public MarketClosedEvent(double time) {
		super(time);
	}

	public void runEvent(State state, EventQueue queue) {
		MarketState mstate = ((MarketState) state);
		mstate.lastN = mstate.getN();

		mstate.lastRegisterQueueSize = mstate.registerQueue.size();
		mstate.setMarketClosed();
		mstate.recivedChange();
	}
	
}
