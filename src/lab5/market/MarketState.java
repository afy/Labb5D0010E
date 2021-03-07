package lab5.market;
import java.util.ArrayList;

import lab5.State;

public class MarketState extends State{
	public int N = 0;
	public int Ntot = 0;
	public int M = 0;
	public int customerCounter = 0;
	public int f = 0;
	public int sales = 0;
	public int missedCustomers;
	
	public double kMin = 0;
	public double kMax = 0;
	public double pMin = 0;
	public double pMax = 0;
	public double S = 0;
	public double downTimeCheckout = 0;
	public double totalQueueTime;	
	public double[] arrival; 
	
	public String latestEvent;
	
	public FIFO registreQueue;
	public ArrayList<Customer> customer = new ArrayList<Customer>(); 
	
	public MarketState() {
		
	}
	public void recivedEvent() {
	//	e.runEvent();
		setN(3);
		setChanged();
		notifyObservers();
	}
	/* -------------------------- Creation methods ------------------------------ */

	/**
	* Method that sets the amount of N (checkouts) 
	**/
	public void setN(int x) {
		Ntot++;
		N = x;
	}
	
	/**
	* Method that sets the max amount of customers allowed
	**/
	public void setM(int x) {
		M = x;
	}
	
	/**
	* Method that sets the seed value (Frö punkt 7 sid 5)
	**/
	public void setF(int x) {
		f = x;
	}
	
	/**
	* Method that sets the amount of sales done 
	**/
	public void setSales() {
		sales++;
	}
	
	/**
	* Method that sets amount of missed customers
	**/
	public void setMissedCustomers() {
		missedCustomers++;
	}
	
	/**
	* Creates a new Customer with different id 
	**/
	public Customer makeCustomer() {
		customerCounter++;
		return new Customer(customerCounter);
	}
	
	/* -------------------------- Get methods ------------------------------ */
	
	/**
	* Gets the amount of N (checkouts) 
	**/
	public int getN() {
		return N;
	}
	
	/**
	* Gets the total amount of N (checkouts) 
	**/
	public int getNtot() {
		return Ntot;
	}
	
	/**
	* Gets the max amount of customers limit 
	**/
	public int getM() {
		return M;
	}
	
	/**
	* Gets the seed value (Frö punkt 7 sid 5)
	**/
	public int getF() {
		return f;
	}
	
	/**
	* Gets the amount of sales done
	**/
	public int getSales() {
		return sales;
	}
	/**
	* Gets the amount of missed customers 
	**/
	public int getMissedCustomers() {
		return missedCustomers;
	}
}

