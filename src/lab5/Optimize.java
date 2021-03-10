package lab5;

import java.util.Random;

import lab5.event.EventQueue;
import lab5.market.MarketClosedEvent;
import lab5.market.MarketStartEvent;
import lab5.market.MarketState;
import lab5.market.MarketStopEvent;
import lab5.market.UniformRandomStream;

/** A class that finds the least amount of cash registers needed to not miss customers in MarketState.
 *
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
public class Optimize {
	public static void main(String[] args) {
		for(int i = 0; i < 1000; i++) {
			System.out.println(testSeed(i));
		}
	}

	/**Tests for the minimum amount of cash registers needed to not miss a single customer for different seeds.
	 *
	 * @param seed the seed for the random generator.
	 * @return the minimum amount of registers needed to not miss any customers.
	 */
	public static int testSeed(int seed) {
		Random r = new Random(seed);
		int same = 0;
		int maxReg = 0;
		int loopCount = 0;
		int regTot = 0;
		
		
		while(same < 100) {
			int registers = testRegisters(r.nextInt());
			loopCount++;
			regTot += registers;
			
			if(registers > maxReg) {
				same = 0;
				maxReg = registers;
			}
			else {
				same++;
			}
		}
		return maxReg;
	}

	/** Tests for the minimum amount of cash registers needed to not miss a single customer.
	 *
	 * @param seed  the seed for the random generator.
	 * @return the minimum amount of registers needed to not miss any customers.
	 */
	public static int testRegisters(int seed) {
		int minmissed = 999;
		int minReg = 0;
		for(int i = 1; i <= 10; i++) {
			MarketState ms = test(i, seed);
			if(ms.getMissedCustomers() < minmissed) {
				minReg = i;
				minmissed = ms.getMissedCustomers();
			}
		}
		return minReg;
	}

	/** Runs a simulation on MarketState.
	 *
	 * @param reg  the amount of cash registers in the market.
	 * @param seed  the seed for the random generator.
	 * @return the MarketState after the simulation
  	 */
	public static MarketState test(int reg, int seed) {
		EventQueue queue = new EventQueue();
		MarketState state = new MarketState(reg, 10, 1.0, 0.5, 1.0, 2.0, 3.0, seed, 10.0);
		queue.addEvent(new MarketStartEvent(0));
		queue.addEvent(new MarketClosedEvent(state.getClosingTime()));
		queue.addEvent(new MarketStopEvent(999.00));
		
		Simulator sim = new Simulator(queue, state);
		sim.run();
		
		return state;
	}
	
}
