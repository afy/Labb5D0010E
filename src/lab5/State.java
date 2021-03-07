package lab5;

import java.util.*;

public class State extends Observable{
	boolean _stopSim = false;
	
	public void stopSim() {
		_stopSim = true;
	}
	public void startSim() {
		_stopSim = false;
	}
}
