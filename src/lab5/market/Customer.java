package lab5.market;
/**
 * 
 * @author Wiklund
 *
 */
public class Customer {
	public int id;
	public double pickingTime;
	public double arrivalTime;
	public double queueTime = 0;
	public double scanTime = 0;
	public double payTime = 0;
	
	/**
	* A constructor that make Customer objects with an id
	**/
	public Customer(int id) {
		this.id = id; 
//		this.arrivalTime = arrivalTime;
//		this.pickingTime = pickingTime;
//		this.queueTime = queueTime;
	}
}
