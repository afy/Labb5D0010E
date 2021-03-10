package lab5;
import lab5.event.EventQueue;
import lab5.market.ExponentialRandomStream;
import lab5.market.MarketClosedEvent;
import lab5.market.MarketStartEvent;
import lab5.market.MarketState;
import lab5.market.MarketStopEvent;
import lab5.market.MarketView;

/**
 * Runs the Market simulation.
 *
 *@author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 *
 */
public class RunSim{
	/**
	 * Main
	 * @param args Arguments
	 */
	public static void main(String[] args) {
//			MarketState marketState = new MarketState();
//			
//			MarketView view = new MarketView(marketState);
//			marketState.recivedEvent();
//			marketState.recivedEvent();
		
		EventQueue queue = new EventQueue();
	//	MarketState state = new MarketState(2, 5, 1.0, 0.5, 1.0, 2.0, 3.0, 1234, 10.0);
		MarketState state = new MarketState(2, 7, 3.0, 0.6, 0.9, 0.35, 0.6, 13, 8.0);
		queue.addEvent(new MarketStartEvent(0));
		queue.addEvent(new MarketClosedEvent(state.getClosingTime()));
		queue.addEvent(new MarketStopEvent(999.00));
		
		Simulator sim = new Simulator(queue, state);
	    MarketView view = new MarketView((MarketState)sim.getState());
		sim.run();
	}
}
