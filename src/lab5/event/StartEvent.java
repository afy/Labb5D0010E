package lab5.event;
import lab5.State;
import lab5.market.ExponentialRandomStream;
public abstract class StartEvent extends Event{
	
	public StartEvent(double time) {
		super(time);
	}


	public void runEvent(State state, EventQueue eventQueue) {
		state.startSim();
		postRunEvent(state, eventQueue);
	}
	
	
	protected abstract void postRunEvent(State state, EventQueue eventQueue);
	
}
