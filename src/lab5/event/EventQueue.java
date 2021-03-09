package lab5.event;

import java.util.ArrayList;

/**
 * Contains all events for given simulation
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class EventQueue {
    private ArrayList<Event> queue = new ArrayList<Event>();

    /**
     * Get the first event in queue
     * @return first event
     */
    public Event getFirstEvent() {
        if (this.queue.size() == 0) {
            return null;
        } else {
            return this.queue.get(0);
        }
    }

    /**
     * Add an event to queue and sort by (simulated) execution time
     * @param item Event to be added
     */
    public void addEvent(Event item) {
        if (this.queue.size() == 0) {
            this.queue.add(item);
        } else {
            double eventTime = item.getQueueTime();

            for (int i = 0; i < this.queue.size(); i++) {
                if (eventTime < this.queue.get(i).getQueueTime()) {
                    this.queue.add(i, item);
                    return;
                }
            }
            this.queue.add(item);
        }
    }

    /**
     * Remove the element at index 0 in queue
     */
    public void removeFirstEvent() {
        if(this.queue.size() == 0) {
            return;
        }
        else {
            this.queue.remove(0);
        }
    }

    /**
     * Return the number of elements in queue
     * @return queue size
     */
    public int getLength() {
        return this.queue.size();
    }
}
