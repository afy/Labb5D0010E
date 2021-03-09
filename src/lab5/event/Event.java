package lab5.event;
import lab5.State;
public abstract class Event {
    protected double queueTime;
    
    public Event(double time) {
        this.queueTime = time;
    }

    public double getQueueTime() {
        return this.queueTime;
    }

    abstract public void runEvent(State state, EventQueue eventQueue);
}
