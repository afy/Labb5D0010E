package lab5.event;
import lab5.State;
import lab5.market.ExponentialRandomStream;

/**
 * Abstract class for events indicating simulation start
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public abstract class StartEvent extends Event{
	
	public StartEvent(double time) {
		super(time);
	}

	/**
	 * Method called by simulation; action taken by simulation
	 * Indicate a simulation start, then call abstract method postRunEvent()
	 * @param state Simulation state
	 * @param @param eventQueue FIFO queue of events to be executed
	 */
	public void runEvent(State state, EventQueue eventQueue) {
		state.startSim();
		postRunEvent(state, eventQueue);
	}
	
	/**
	 * Method to handle specific settings at simulation start
	 * @param state Simulation state
	 * @param eventQueue FIFO queue of events to be executed
	 */
	protected abstract void postRunEvent(State state, EventQueue eventQueue);
}