package lab5.event;
import lab5.State;
public abstract class StopEvent extends Event{
	
	public StopEvent(double time) {
		super(time);
	}


	public void runEvent(State state, EventQueue eventQueue) {
		state.stopSim();
		postRunEvent(state, eventQueue);
	}
	
	
	protected abstract void postRunEvent(State state, EventQueue eventQueue);
	
}
