package lab5.market;
import lab5.State;
import lab5.event.EventQueue;
public class GoodsPaidEvent extends MarketEvent{
	
	
	public GoodsPaidEvent(double time, Customer c) {
		super(time);
		this.customer = c;
	}
	
	
	public void runEvent(State state, EventQueue eventQueue) {
		MarketState mstate = ((MarketState)state);
		mstate.customer.remove(this.customer);
		mstate.lastN = mstate.getN();
		
		if(mstate.registerQueue.size() == 0) {
			mstate.incN();
			mstate.lastRegisterQueueSize = mstate.registerQueue.size();
		}
		else {
			eventQueue.addEvent(new GoodsPaidEvent(this.queueTime + mstate.uniK.next(), (Customer)mstate.registerQueue.getFirst()));
		
//			mstate.totalQueueTime += this.queueTime - ((Customer)mstate.registerQueue.getFirst()).startedQueue;
			mstate.lastRegisterQueueSize = mstate.registerQueue.size();
			
			mstate.registerQueue.removeFirst();
			
			
		}
		
		if(mstate.customer.size() == 0 && !mstate.isOpen()  ) {
			mstate.lastcustomerPaid = queueTime;
			
			//eventQueue.addEvent(new MarketStopEvent(this.queueTime)); Alternativt 
		}
		mstate.incSales();
		mstate.recivedChange();
	}
}
