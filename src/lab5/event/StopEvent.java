package lab5.event;
import lab5.State;
public abstract class StopEvent extends Event{
	
	public StopEvent(int time) {
		super(time);
	}


	public void runEvent(State state, EventQueue eventQueue) {
		state.stopSim();
		postRunEvent();
	}
	
	
	protected abstract void postRunEvent();
	
}
