package lab5.market;
import lab5.event.Event;
import java.util.ArrayList;
import lab5.State;
//import lab5.marketEvent.MarketEvent;

public class MarketState extends State{
	private int N;
	final private int M;
	final private double kMin;
	final private double kMax;
	final private double pMin;
	final private double pMax;
	final double S; //lambda
	final int f;
	
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
	
	public Event lastEvent;
	public Event latestEvent;
	double lastcustomerPaid = 0;
	
	public int lastRegisterQueueSize;
	
	int lastN;
	
	public FIFO registerQueue = new FIFO();
	public ArrayList<Customer> customer = new ArrayList<Customer>(); 
	
	public CustomerFactory cf = new CustomerFactory();
	
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
	
	public void setLatestEvent(Event e) {
		lastEvent = latestEvent;
		latestEvent = e;
	}
	
	public void recivedChange() {
	//	e.runEvent();
		double x = latestEvent.getQueueTime();
		
		if(lastcustomerPaid != 0) {
			x = lastcustomerPaid;
		}
		if(lastN > 0 && this._stopSim == false) {
			//System.out.println(latestEvent.getQueueTime() + "---" + lastEvent.getQueueTime());
			downTimeCheckout += (x - lastEvent.getQueueTime()) * lastN;
		}
		if(lastRegisterQueueSize > 0) {
			totalQueueTime += (x - lastEvent.getQueueTime()) * lastRegisterQueueSize;
		}
		setChanged();
		notifyObservers();
	}

	public void incN() {N++;}
	public void decN() {N--;}
	public void incMissedCustomer() {missedCustomers++;}
	public void incSales() {sales++;}
	public void incHasQueued() {hasQueued++;}

	public int getN() {return N;}
	public int getM() {return M;}
	public int getMissedCustomers() {return missedCustomers;}
	public int getSales() {return sales;}
	public int getHasQueued() {return hasQueued;}
	public double getClosingTime() {return closingTime;}
	public double getPMin() {return pMin;}
	public double getPMax() {return pMax;}
	public double getKMin() {return kMin;}
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
	* Creates a new Customer with different id 
	**/
	
	public boolean isSpace() {
		if(customer.size() >= M) {
			return false;
		}
		return true;
	}
	
	public boolean isOpen() { 
		if(marketOpen) {
			return true;
		}
		return false;
	}
	
	public void setMarketOpen() {
		marketOpen = true;
	}
	
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

