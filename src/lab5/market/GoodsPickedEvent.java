package lab5.market;
import lab5.State;
import lab5.event.EventQueue;

/**
 * Event called when a customer is done picking out their goods
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class GoodsPickedEvent extends MarketEvent{
	
	/**
	 * Constructor
	 * @param time Current simulation time
	 * @param c Constumer done picking
	 */
	public GoodsPickedEvent(double time, Customer c) {
		super(time);
		this.customer = c;
	}
	
	/**
	 * Set lastQueueSize and lastN, decide wheter to enter queue or to start payment
	 * @param state Market state
	 * @param queue EventQueue
	 */
	public void runEvent(State state, EventQueue queue) {
		MarketState mstate = ((MarketState)state);
		
		// set for difference calculations
		mstate.lastRegisterQueueSize = mstate.registerQueue.size();
		mstate.lastN = mstate.getN();
		
		// if a register is empty
		if(mstate.getN() > 0 ) {
			mstate.decN();
			queue.addEvent(new GoodsPaidEvent(this.queueTime + mstate.uniK.next(), this.customer));
		}
		
		// queue customer
		else {
			mstate.registerQueue.enqueue(this.customer);
			mstate.incHasQueued();
		}
		
		// notify state
		mstate.recivedChange();
		
	}
	
}
