package lab5.market;

import java.util.Random;

/**
 *  Exponential random stream (given)
 */
public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  
	/**
	 * Constructor
	 * @param lambda Lambda
	 * @param seed Seed
	 */
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}
	
	/**
	 * Constructor
	 * @param lambda Lambda
	 */
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}
	  
	/**
	 * Next value
	 * @return Psuedorandom number
	 */
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
}
