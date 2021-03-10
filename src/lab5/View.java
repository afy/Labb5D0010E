package lab5;

import java.util.*;

/**
 * View is supposed to give a visual representation of what is happening in the state during a simulation.
 *
 * @author Simon Engström, Hannes Furhoff, Emil Wiklund, Johannes Sundström
 */
abstract public class View implements Observer{


	abstract public void update(Observable o, Object arg);
	
}
