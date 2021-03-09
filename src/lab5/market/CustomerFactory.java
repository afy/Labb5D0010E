package lab5.market;

public class CustomerFactory {
	private int nextCustomerId = 0;
	
	public Customer makeCustomer(double arrivalTime) {
		Customer c = new Customer(nextCustomerId, arrivalTime);
		nextCustomerId++;
		return c;
	}
	public int getNextId() {
		return nextCustomerId;
	}
}
