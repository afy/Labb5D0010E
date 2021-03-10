package lab5.market;
import lab5.State;
import lab5.event.EventQueue;

/**
 * Event called when a customer is done paying
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class GoodsPaidEvent extends MarketEvent{
	
	/**
	 * Constructor
	 * @param time Current simulation time
	 * @param c Customer
	 */
	public GoodsPaidEvent(double time, Customer c) {
		super(time);
		this.customer = c;
	}
	
	/**
	 * Remove customer, set lastN, Open up a register and let another customer pay (if applicable)
	 * if this was the last customer, set the lastCustomerPaid variable. Finally, add 1 to sales
	 * @param state Market state
	 * @param eventQueue EventQueue
	 */
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
		
		}
		mstate.incSales();
		mstate.recivedChange();
	}
}
