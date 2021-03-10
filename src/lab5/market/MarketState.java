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
	
	/// params
	private int N;				// number of registers
	final private int M;		// max customers in store at once
	final private double kMin;	// min scan
	final private double kMax;	// max scan time
	final private double pMin;	// min picking time
	final private double pMax; 	// max picking time
	final double S; 			// lambda
	final int f; 				// seed
	private double closingTime;	
	
	
	/// stats
	private int sales = 0;
	private int missedCustomers = 0;	
	private int hasQueued = 0;
	boolean marketOpen = true;
	double downTimeCheckout = 0;
	double totalQueueTime = 0;	
	
	
	/// helpers / state managers
	/**
	 * Exp random stream
	 */
	public ExponentialRandomStream expR;
	/**
	 * Uniform random stream for P
	 */
	public UniformRandomStream uniP;
	/**
	 * Uniform random stream for K
	 */
	public UniformRandomStream uniK;
	
	/**
	 * The latest event that occured.
	 */
	public Event latestEvent;
	/**
	 * The event before the latest event that occured.
	 */
	public Event lastEvent; 
	double lastcustomerPaid = 0; // set when last customer paid, used for time calcualtions
	
	/**
	 * las register queue size
	 */
	public int lastRegisterQueueSize; // register queue size in last event call, used for time calcualtions
	
	int lastN; // no. last registers in the last event call, used for time calculations
	
	/**
	 * Register queue of type FIFO queue.
	 */
	public FIFO registerQueue = new FIFO(); 
	
	/**
	 * Stores every customer inside the store.
	 */
	public ArrayList<Customer> customer = new ArrayList<Customer>(); 
	
	/**
	 * Factory that creates customers.
	 */
	public CustomerFactory cf = new CustomerFactory(); 
	
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
		// is used so that getQueueTime will not return 999.99 when the StopEvent is called
		double x = latestEvent.getQueueTime();
		if(lastcustomerPaid != 0) {
			x = lastcustomerPaid;
		}
		
		//Increases downTimeCheckout if there are open registers.
		if(lastN > 0 && this._stopSim == false) {
			downTimeCheckout += (x - lastEvent.getQueueTime()) * lastN;
		}
		
		//Increases queue time if there are customers in the register queue.
		if(lastRegisterQueueSize > 0) {
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
}

