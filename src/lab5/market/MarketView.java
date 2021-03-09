package lab5.market;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import lab5.View;

/**
 * Display simulation passage of events and results
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class MarketView extends View{
	
	/**
	 * State to observe
	 */
	MarketState MS;
	
	
	/**
	 * Constructor
	 * @param s State to observe
	 */
	public MarketView(MarketState s){
		this.MS = s;
		MS.addObserver(this);
		printParameters();
		printCourse();
	}
	
	/**
	 * Default update method from Observer pattern
	 * @param o 
	 * @param arg
	 */
	public void update(Observable o, Object arg) {
		printCourseValue();
	}
	
	/**
	 * Display simulation result
	 */
	public void printResult() {
		System.out.println("\nRESULTAT \n "
				+ "======== \n"
				+ "1) Av " + (this.MS.cf.getNextId() - 1) + " kunder handlade " + this.MS.getSales() + " medans " + this.MS.getMissedCustomers() + " missades. \n\n"
				+ "2) Total tid " + this.MS.getN() + " kassor varit lediga: " + new DecimalFormat("0.00").format(this.MS.downTimeCheckout) + " te.\r\n"
				+ " Genomsnittlig ledig kassatid: " + (new DecimalFormat("0.00").format(this.MS.downTimeCheckout / this.MS.getN())) + " te ("+ new DecimalFormat("0.00").format(((this.MS.downTimeCheckout / this.MS.getN()) / (this.MS.lastcustomerPaid)) * 100) +"% av tiden från öppning tills sista kunden\r\n"
				+ "betalat). \n\n"
				+ "3) Total tid " + this.MS.getHasQueued() +" kunder tvingats köa: " + new DecimalFormat("0.00").format(this.MS.totalQueueTime) + " te.\r\n"
				+ " Genomsnittlig kötid: " + new DecimalFormat("0.00").format(this.MS.totalQueueTime / this.MS.getHasQueued())+ " te. ");
	}

	/**
	 * Display simulation parameters
	 */
	private void printParameters() {
		System.out.println(
				  "PARAMETRAR \n"
				+ "========== \n"
				+ "Antal Kassor: "     + this.MS.getN() + "\n"
				+ "Max som ryms: " 	   + this.MS.getM()    + "\n"
				+ "Ankomsthastighet: " + this.MS.S    + "\n"
				+ "Plocktider: "	   + "[P_min , Pmax]: [" + this.MS.getPMin() + " , " + this.MS.getPMax() + "]\n"
				+ "Betaltider: "	   + "[K_min , Kmax]: [" + this.MS.getKMin() + " , " + this.MS.getKMax() + "]\n"
				+ "Frö: "   	       + this.MS.f	 + "\n"
				
						);
	}
	
	/**
	 * Display title for the course of events
	 */
	private void printCourse() {
		System.out.println(
				  "FÖRLOPP \n"
				+ "========== \n"
				+ "Tid \t" + "Händelse \t" + "Kund \t " + "? \t" + "led \t" + "ledT \t" + "I \t" + "$ \t" + ":-( \t" + "köat \t" + "köT \t" + "köar \t" + "[KassaKö]"
				);
	}
	
	/**
	 * Display a line from course of events; display a standard row with no restrictions
	 */
	public void printOther() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("CustomerArrivalEvent", "Arrival");
		map.put("GoodsPaidEvent", "Payment");
		map.put("GoodsPickedEvent", "Picked");
		map.put("MarketClosedEvent", "Closed");
		map.put("MarketStartEvent", "Start");
		map.put("MarketStopEvent", "Stop");
		
		ArrayList<Integer> printList = new ArrayList<Integer>();
		for(int i = 0; i < this.MS.registerQueue.size(); i++) { 
			
			printList.add(((Customer)this.MS.registerQueue.getQueue().get(i)).id);
			
		}
		
		System.out.println(new DecimalFormat("0.00").format(this.MS.latestEvent.getQueueTime()) +"\t"
				+ map.get(this.MS.latestEvent.getClass().getName().replace("lab5.market.", "")) + "\t\t" 
				+ ((this.MS.latestEvent instanceof MarketEvent && ((MarketEvent)this.MS.latestEvent).customer != null)  ? ((MarketEvent)this.MS.latestEvent).customer.id : "") + "\t"
				+ (this.MS.isOpen() ? "Ö" : "S") + "\t"
				+ this.MS.getN() + "\t"
				+ new DecimalFormat("0.00").format(this.MS.downTimeCheckout) + "\t"
				+ this.MS.customer.size() + "\t"
				+ this.MS.getSales() + "\t" 
				+ this.MS.getMissedCustomers() + "\t"
				+ this.MS.getHasQueued() + "\t"
				+ new DecimalFormat("0.00").format(this.MS.totalQueueTime) + "\t"
				+ printList.size() + "\t"
				+ printList);
	}
	
	/**
	 * Display a line from course of events; display a line for starting event
	 */
	public void printStart() {
		System.out.println(new DecimalFormat("0.00").format(this.MS.latestEvent.getQueueTime()) +"\t"
				+ "Start");
	}
	
	/**
	 * Display a line from course of events; display a line for stopping event and then print results
	 */
	public void printStop() {
		System.out.println( new DecimalFormat("0.00").format(this.MS.latestEvent.getQueueTime()) +"\t"
				+ "Stop");
		printResult();
	}
	
	/**
	 * Display course of events (runs during simulation on observer update)
	 */
	private void printCourseValue() {		
			switch((this.MS.latestEvent.getClass().getName().replace("lab5.market.", ""))) {
				case "MarketStartEvent":
					printStart();
					break;
					
				case "MarketStopEvent":
					printStop();
					break;	
					
				default:
					printOther();
					break;
			}
	}
}
