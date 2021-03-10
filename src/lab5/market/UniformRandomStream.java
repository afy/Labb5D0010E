package lab5.market;

import java.util.Random;

/**
 * Unform random stream (given)
 */
public class UniformRandomStream {

	private Random rand;
	private double lower, width;
	
	/**
	 * Constructor
	 * @param lower Lower
	 * @param upper Upper
	 * @param seed Seed
	 */
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper-lower;
	}
	
	/**
	 * Constructor
	 * @param lower Lower
	 * @param upper Upper
	 */
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
	    this.lower = lower;
	    this.width = upper-lower;
	}
	
	/**
	 * Next value
	 * @return psuedorandom number
	 */
	public double next() {
	    return lower+rand.nextDouble()*width;
	}
}
