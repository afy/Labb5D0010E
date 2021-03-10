package lab5.market;

/**
* Class that stands for individual customers
* @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
*/
public class Customer {
	public int id;
//	public double pickingTime = 0;
	public double arrivalTime = 0;
//	public double queueArrivalTime = 0;
//	public double scanTime = 0;
//	public double paymentDoneTime = 0;
	public double startedQueue = 0;
	
	/**
	* A constructor that creates a Customer objects with an id and arrivalTime
	*/
	public Customer(int id, double arrivalTime) {
		this.id = id; 
		this.arrivalTime = arrivalTime;
		
	}
	
//	public void setpaymentDoneTime(double t) {
//		paymentDoneTime = t;
//	}
}
