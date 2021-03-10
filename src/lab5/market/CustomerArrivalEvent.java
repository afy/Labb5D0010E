package lab5.market;
import lab5.State;
import lab5.event.EventQueue;

/**�
 * Specific event that is called when a customer "arrives"
 * @author Simon Engstr�m, Hannes Furhoff, Emil Wiklund, Johannes Sundstr�m
 */
public class CustomerArrivalEvent extends MarketEvent{
	
	/**
	 * Constructor for the class
	 * @param time The time used throughout runtime
	 */
	public CustomerArrivalEvent(double time) {
		super(time);
	}

	/**
	 * An method to run whatever the event is supposed to
	 * @param state to keep track of wich state
	 * @param queue to keep track of wich queue
	 */
	
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
