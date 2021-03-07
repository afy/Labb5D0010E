package lab5.event;
import lab5.State;
public abstract class StartEvent extends Event{
	
	public StartEvent(int time) {
		super(time);
	}


	public void runEvent(State state, EventQueue eventQueue) {
		state.startSim();
		postRunEvent();
	}
	
	
	protected abstract void postRunEvent();
	
}
