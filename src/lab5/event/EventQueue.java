package lab5.event;

import java.util.ArrayList;

public class EventQueue {
    private ArrayList<Event> queue = new ArrayList<Event>();

    public Event getFirstEvent() {
        if (this.queue.size() == 0) {
            return null;
        } else {
            return this.queue.get(0);
        }
    }

    public void addEvent(Event item) {
        if (this.queue.size() == 0) {
            this.queue.add(item);
        } else {
            int eventTime = item.getQueueTime();

            for (int i = 0; i < this.queue.size(); i++) {
                if (eventTime < this.queue.get(i).getQueueTime()) {
                    this.queue.add(i, item);
                    return;
                }
            }
            this.queue.add(item);
        }
    }

    public void removeFirstEvent() {
        if(this.queue.size() == 0) {
            return;
        }
        else {
            this.queue.remove(0);
        }
    }

    public int getLength() {
        return this.queue.size();
    }

    /* //a method that prints out the queueTimes of all the events in the list, mostly for testing
    public void printOutTimes() { // for testing sorting by time
        ArrayList<Integer> times = new ArrayList<Integer>();

        for(int i = 0; i < this.queue.size(); i++) {
            times.add(this.queue.get(i).getQueueTime());
        }
        System.out.println(times);
    }
     */
}
