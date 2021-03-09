package lab5.market;
import lab5.State;
import lab5.event.EventQueue;

public class GoodsPickedEvent extends MarketEvent{
	
	public GoodsPickedEvent(double time, Customer c) {
		super(time);
		this.customer = c;
	}
	
	
	public void runEvent(State state, EventQueue queue) {
		MarketState mstate = ((MarketState)state);
		mstate.lastRegisterQueueSize = mstate.registerQueue.size();
		mstate.lastN = mstate.getN();
		if(mstate.getN() > 0 ) {
			mstate.decN();
			queue.addEvent(new GoodsPaidEvent(this.queueTime + mstate.uniK.next(), this.customer));
		}
		else {
			mstate.registerQueue.enqueue(this.customer);
			mstate.incHasQueued();
			this.customer.startedQueue = this.queueTime;
		}
		mstate.recivedChange();
		
	}
	
}
