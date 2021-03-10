package lab5.market;

/**
* Class that stands for individual customers
* @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
*/
public class Customer {
	/**
	 * Costumer id
	 */
	public int id;
	
	/**
	 * Arrival time
	 */
	public double arrivalTime = 0;
	
	/**
	 * Started queue 
	 */
	public double startedQueue = 0;
	
	/**
	* A constructor that creates a Customer objects with an id and arrivalTime
	*/
	public Customer(int id, double arrivalTime) {
		this.id = id; 
		this.arrivalTime = arrivalTime;
		
	}
}
