package lab5.event;
import lab5.State;

/** 
 * Base class for all types of events
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
*/
public abstract class Event {
    protected double queueTime; // determines when the event will occur (in simulation time)
    
    /**
     * Constructor 
     * @param time Execution time
     */
    public Event(double time) {
        this.queueTime = time;
    }

    /**
     * Return time of execution (in simulation)
     * @return queueTime
     */
    public double getQueueTime() {
        return this.queueTime;
    }

    /**
     * Method called by simulator; action taken by event
     * @param state Simulation state
     * @param eventQueue queue of events to be executed
     */
    abstract public void runEvent(State state, EventQueue eventQueue);
}