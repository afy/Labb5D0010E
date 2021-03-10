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
	* A constructor that creates a Customer objects with an id and arrivalTime
	* @param id Customer id
	* @param arrivalTime arrival time 
	*/
	public Customer(int id, double arrivalTime) {
		this.id = id; 
		this.arrivalTime = arrivalTime;
		
	}
}
