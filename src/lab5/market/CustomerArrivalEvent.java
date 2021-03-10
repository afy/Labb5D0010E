package lab5.market;
import lab5.State;
import lab5.event.EventQueue;

/**
 * Specific event that is called when a customer "arrives"
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class CustomerArrivalEvent extends MarketEvent{
	
	/**
	 * Constructor for the class
	 * @param time Time when the event is executed (in simulation time)
	 */
	public CustomerArrivalEvent(double time) {
		super(time);
	}

	/**
	 * An method to run whatever the event is supposed to
	 */
	public void runEvent(State state, EventQueue queue) {
		MarketState mstate = ((MarketState) state);
		
		// create customer
		Customer c = mstate.cf.makeCustomer(this.queueTime);
		this.customer = c;
				
		// if market is open and not full
		if(mstate.isOpen() && mstate.isSpace()) {
			mstate.customer.add(c);
			queue.addEvent(new GoodsPickedEvent(this.queueTime + mstate.uniP.next(), c));
			queue.addEvent(new CustomerArrivalEvent(this.queueTime + mstate.expR.next() ));
		}
		
		else if(mstate.isOpen() && mstate.isSpace() == false) {
			mstate.incMissedCustomer();
			queue.addEvent(new CustomerArrivalEvent(this.queueTime + mstate.expR.next()));
		}
		
		// set for difference calculations
		mstate.lastN = mstate.getN();
		mstate.lastRegisterQueueSize = mstate.registerQueue.size();
		
		// notify state
		mstate.recivedChange();
	}
}
