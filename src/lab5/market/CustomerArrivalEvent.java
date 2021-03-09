package lab5.market;
import lab5.State;
import lab5.event.EventQueue;
public class CustomerArrivalEvent extends MarketEvent{
	
	public CustomerArrivalEvent(double time) {
		super(time);
	}


	public void runEvent(State state, EventQueue queue) {
		MarketState mstate = ((MarketState) state);
		Customer c = mstate.cf.makeCustomer(this.queueTime);
		this.customer = c;
				
		if(mstate.isOpen() && mstate.isSpace()) {
			mstate.customer.add(c);
			queue.addEvent(new GoodsPickedEvent(this.queueTime + mstate.uniP.next(), c));
			queue.addEvent(new CustomerArrivalEvent(this.queueTime + mstate.expR.next() ));
		}
		
		else if(mstate.isOpen() && mstate.isSpace() == false) {
			mstate.incMissedCustomer();
			queue.addEvent(new CustomerArrivalEvent(this.queueTime + mstate.expR.next()));
		}
		mstate.lastN = mstate.getN();
		mstate.lastRegisterQueueSize = mstate.registerQueue.size();
		mstate.recivedChange();
	}
}
