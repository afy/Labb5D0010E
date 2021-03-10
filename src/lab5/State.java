package lab5;

import java.util.*;

/**
 * An object of State represents the state of something that is supposed to be simulated, e.g. a supermarket, a hockey match or something else.
 * The specific state needs to contain its own set of relevant variables.
 *
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 *
 **/
public class State extends Observable{

	
	protected boolean _stopSim = false; //

	/**
	 * Stops the simulation.
	 */
	public void stopSim() {
		_stopSim = true;
	}

	/**
	 * Makes sure that the simulation can run before it is started.
	 */
	public void startSim() {
		_stopSim = false;
	}
}
