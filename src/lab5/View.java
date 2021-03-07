package lab5;

import java.util.*;

abstract public class View implements Observer{
	
	abstract public void update(Observable o, Object arg);
	
}
