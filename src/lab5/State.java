package lab5;

import java.util.*;

/**
 * 
 * @author Wiklund
 *
 **/

public class State extends Observable{
	
	/** 
	* 
	**/
	
	protected boolean _stopSim = false;
	
	public void stopSim() {
		_stopSim = true;
	}
	public void startSim() {
		_stopSim = false;
	}
}
