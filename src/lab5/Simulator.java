package lab5;
import lab5.event.*;
import lab5.market.MarketStartEvent;
import lab5.market.MarketState;
import lab5.market.MarketView;

/**
 * Class that runs the simulation by calling the events in eventQueue
*/
public class Simulator {

    private EventQueue eventQueue;
    private State state;
    private double simulationTime;
    
	public Simulator(EventQueue eventQueue, State state) {
	    this.eventQueue = eventQueue;
	    this.state = state;
	}
	public State getState() {
		return state;
	}
    /**
     * loops through all events, calling them and removing them one by one
    */
    public void run() {
    	
    	
        while(eventQueue.getLength() > 0) {
        	((MarketState)state).setLatestEvent(eventQueue.getFirstEvent());
            eventQueue.getFirstEvent().runEvent(state, eventQueue);
            eventQueue.removeFirstEvent();
            if(state._stopSim) {
            	break;
            }
        }
    }
}