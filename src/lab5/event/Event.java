package lab5.event;
import lab5.State;
public abstract class Event {
    private int queueTime;

    public Event(int time) {
        this.queueTime = time;
    }

    int getQueueTime() {
        return this.queueTime;
    }

    abstract public void runEvent(State state, EventQueue eventQueue);
}
