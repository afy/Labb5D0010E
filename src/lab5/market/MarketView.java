package lab5.market;
import java.util.Observable;

import lab5.View;

public class MarketView extends View{
	
	MarketState MS;
	public MarketView(MarketState s){
		this.MS = s;
		MS.addObserver(this);
		printParameters();
		printCourse();
	}
	public void update(Observable o, Object arg) {
		printCourseValue();
	}
	
//	public void printState() {
//		printParameters();
//		printCourse();
//		//printCourseValue();
//	}
	private void printParameters() {
		System.out.println(
				  "PARAMETRAR \n"
				+ "========== \n"
				+ "Antal Kassor: "     + this.MS.Ntot    + "\n"
				+ "Max som ryms: " 	   + this.MS.M    + "\n"
				+ "Ankomsthastighet: " + this.MS.S    + "\n"
				+ "Plocktider: "	   + "..."   + "\n"
				+ "Betaltider: "	   + "..."   + "\n"
				+ "Fr�: "   	           + this.MS.f	 + "\n"
				
						);
	}
	private void printCourse() {
		System.out.println(
				  "F�RLOPP \n"
				+ "========== \n"
				+ "Tid \t" + "H�ndelse \t" + "Kund \t" + "? \t" + "led \t" + "ledT \t" + "I \t" + "$ \t" + ":-( \t" + "k�at \t" + "k�T \t" + "k�ar \t" + "[KassaK�]"
				);
	}
	private void printCourseValue() {
			System.out.println( 0 +"\t" + this.MS.latestEvent + "\t" + "\t" + 10000 + "\t" + 0 + "\t" + 100090 + "\t" + 0 + "\t" + 0 + "\t" + 0 + "\t" + 0 + "\t" + 0 + "\t" + 0 + "\t" + 0 + "\t" + 0);
	}
}
