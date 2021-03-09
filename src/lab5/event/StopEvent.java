package lab5.event;
import lab5.State;

/**
 * Abstract class for events indicating simulation stop
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public abstract class StopEvent extends Event{
	
	public StopEvent(double time) {
		super(time);
	}

	/**
	 * Method called by simulation; action taken by simulation
	 * Indicate a simulation stop, then call abstract method postRunEvent()
	 * @param state Simulation state
	 * @param @param eventQueue FIFO queue of events to be executed
	 */
	public void runEvent(State state, EventQueue eventQueue) {
		state.stopSim();
		postRunEvent(state, eventQueue);
	}
	
	/**
	 * Method to handle specific settings at end of simulation
	 * @param state Simulation state
	 * @param eventQueue FIFO queue of events to be executed
	 */
	protected abstract void postRunEvent(State state, EventQueue eventQueue);	
}