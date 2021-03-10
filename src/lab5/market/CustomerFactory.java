package lab5.market;

/**
 * A class used to create a new Customer
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class CustomerFactory {
	private int nextCustomerId = 0;
	
	/**
	 * Constructor 
	 * @param arrivalTime The time the Customer arrived. this.queueTime >>> arrivalTime
	 * @return new customer
	 */
	public Customer makeCustomer(double arrivalTime) {
		Customer c = new Customer(nextCustomerId, arrivalTime);
		nextCustomerId++;
		return c;
	}
	
	/**
	 * A get method 
	 * @return nextCustomerId
	 */
	public int getNextId() {
		return nextCustomerId;
	}
}
