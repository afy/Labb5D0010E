package lab5.market;
import lab5.event.Event;
import java.util.ArrayList;
import lab5.State;
//import lab5.marketEvent.MarketEvent;

/**
 * 
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 *
 * Class that stores information and methods controlling the state of a supermarket.
 *
 */
public class MarketState extends State{
	private int N;
	final private int M;
	final private double kMin;
	final private double kMax;
	final private double pMin;
	final private double pMax;
	final double S; //lambda
	final int f; //seed
	
	private int sales = 0;
	private int missedCustomers = 0;
	private int hasQueued = 0;
	boolean marketOpen = true;
	
	private double closingTime;
	double downTimeCheckout = 0;
	double totalQueueTime = 0;	
	
	public ExponentialRandomStream expR;
	public UniformRandomStream uniP;
	public UniformRandomStream uniK;
	
	public Event latestEvent; //The latest event that occured.
	public Event lastEvent; //The event before the latest event that occured.
	double lastcustomerPaid = 0;
	
	public int lastRegisterQueueSize;
	
	int lastN;
	
	public FIFO registerQueue = new FIFO(); //Register queue of type FIFO queue.
	public ArrayList<Customer> customer = new ArrayList<Customer>(); //Stores every customer inside the store.
	
	public CustomerFactory cf = new CustomerFactory(); //Factory that creates customers.
	
	/**
	 * Constructor.
	 * 
	 * @param registers Amount of open registers.
	 * @param maxcustomer Upper limit of customers that can exist inside the supermarket at one point.
	 * @param lambda Constant deciding the rate of customer influx.
	 * @param p1Min Constant deciding Pmin for random stream.
	 * @param p1Max Constant deciding Pmax for random stream.
	 * @param k1Min Constant deciding Kmin for random stream.
	 * @param k1Max Constant deciding Kmax for random stream.
	 * @param seed Seed deciding the pattern for random streams and consequently the simulator.
	 * @param closingTime Closing time for supermarket.
	 */
	public MarketState(int registers, int maxcustomer, double lambda, double p1Min, double p1Max, double k1Min, double k1Max, int seed, double closingTime) {
		this.N = registers;
		this.M = maxcustomer;
		this.S = lambda;
		this.pMin = p1Min;
		this.pMax = p1Max;
		this.kMin = k1Min;
		this.kMax = k1Max;
		this.f = seed;
		this.closingTime = closingTime;
		this.expR = new ExponentialRandomStream(S, f);
		this.uniP = new UniformRandomStream(pMin, pMax, f);
		this.uniK =  new UniformRandomStream(kMin, kMax, f);
		
//		N = 2;
//		M = 5;
//		kMin = 2.0;
//		kMax = 3.0;
//		pMin = 0.5;
//		pMax = 1.0;
//		S = 1 ; //lambda
//		f = 1234;

	}
	
	/**
	 * Sets latest event.
	 * 
	 * @param e Event to be set as latest event.
	 */
	public void setLatestEvent(Event e) {
		lastEvent = latestEvent;
		latestEvent = e;
	}
	
	/**
	 * Method called when a change has been made in the marketstate. 
	 */
	public void recivedChange() {
	//	e.runEvent();
		double x = latestEvent.getQueueTime();
		
		if(lastcustomerPaid != 0) {
			x = lastcustomerPaid;
		}
		if(lastN > 0 && this._stopSim == false) {
			//System.out.println(latestEvent.getQueueTime() + "---" + lastEvent.getQueueTime());
			//Increases downTimeCheckout if there are open registers.
			downTimeCheckout += (x - lastEvent.getQueueTime()) * lastN;
		}
		if(lastRegisterQueueSize > 0) {
			//Increases queue time if there are customers in the register queue.
			totalQueueTime += (x - lastEvent.getQueueTime()) * lastRegisterQueueSize;
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Increases the amount of open registers by 1.
	 */
	public void incN() {N++;}
	
	/**
	 * Decreases the amount of open registers by 1.
	 */
	public void decN() {N--;}
	
	/**
	 * Increases the amount of missed customers by 1.
	 */
	public void incMissedCustomer() {missedCustomers++;}
	
	/**
	 * Increases the amount of sales done by 1.
	 */
	public void incSales() {sales++;}
	
	/**
	 * Increases the amount of people that have queued by 1.
	 */
	public void incHasQueued() {hasQueued++;}

	
	/**
	 * @return Returns amount of open registers.
	 */
	public int getN() {return N;}
	
	/**
	 * @return Returns maximum customer limit.
	 */
	public int getM() {return M;}
	
	/**
	 * @return Returns amount of missed customers.
	 */
	public int getMissedCustomers() {return missedCustomers;}
	
	/**
	 * @return Returns amount of missed sales.
	 */
	public int getSales() {return sales;}
	
	/**
	 * @return Returns the amount of customers that have queued.
	 */
	public int getHasQueued() {return hasQueued;}
	
	/**
	 * @return Returns closing time of supermarket.
	 */
	public double getClosingTime() {return closingTime;}
	
	/**
	 * @return Returns the constant deciding Pmin for random stream.
	 */
	public double getPMin() {return pMin;}
	
	/**
	 * @return Returns the constant deciding Pmax for random stream.
	 */
	public double getPMax() {return pMax;}
	
	/**
	 * @return Returns the constant deciding Kmin for random stream.
	 */
	public double getKMin() {return kMin;}
	
	/**
	 * @return Returns the constant deciding Kmax for random stream.
	 */
	public double getKMax() {return kMax;}
	
//
//	/**
//	* Method that sets the max amount of customers allowed
//	**/
//	public void setM(int x) {
//		M = x;
//	}
//	
//	/**
//	* Method that sets the seed value (Frö punkt 7 sid 5)
//	**/
//	public void setF(int x) {
//		f = x;
//	}
//	
//	/**
//	* Method that sets the amount of sales done 
//	**/
//	public void setSales() {
//		sales++;
//	}
//	
//	/**
//	* Method that sets amount of missed customers
//	**/
//	public void setMissedCustomers() {
//		missedCustomers++;
//	}
	
	
	/**
	 * Checks if there is space inside the store.
	 * 
	 * @return Returns truth value for whether there is space or not.
	 */
	public boolean isSpace() {
		if(customer.size() >= M) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the store is open.
	 * 
	 * @return Returns truth value for whether the store is open or not.
	 */
	public boolean isOpen() { 
		if(marketOpen) {
			return true;
		}
		return false;
	}
	
	/**
	 * Sets market status to open.
	 */
	public void setMarketOpen() {
		marketOpen = true;
	}
	
	/**
	 * Sets market status to closed.
	 */
	public void setMarketClosed() {
		marketOpen = false;
	}
//	
//	/**
//	* Gets the max amount of customers limit 
//	**/
//	public int getM() {
//		return M;
//	}
//	
//	/**
//	* Gets the seed value (Frö punkt 7 sid 5)
//	**/
//	public int getF() {
//		return f;
//	}
//	
//	/**
//	* Gets the amount of sales done
//	**/
//	public int getSales() {
//		return sales;
//	}
//	/**
//	* Gets the amount of missed customers 
//	**/
//	public int getMissedCustomers() {
//		return missedCustomers;
//	}
}

