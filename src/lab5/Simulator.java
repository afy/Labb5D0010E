package lab5;
import lab5.event.*;

/**
 * Class that runs the simulation by calling the events in eventQueue
*/
public class Simulator {
    private EventQueue eventQueue = new EventQueue();
    private State state;
    private View view;
    private double simulationTime;
    /**
     * loops through all events, calling them and removing them one by one
    */
    public void run() {
        while(eventQueue.getLength() > 0) {
        	
            eventQueue.getFirstEvent().runEvent(state, eventQueue);
            eventQueue.removeFirstEvent();
            if(state._stopSim) {
            	break;
            }
        }
    }
}