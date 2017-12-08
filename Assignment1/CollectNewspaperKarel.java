/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run(){
		
	Move_to_Newspaper ();
	Pick_it_up ();
	Return ();
	
	}

	private void Move_to_Newspaper (){
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
	
	private void Pick_it_up (){
		pickBeeper();	
	}
	
	private void Return (){
		turnLeft();
		turnLeft();
		move();
		turnRight();
		move();
		turnLeft();
		move();
		move();
		turnLeft();
		turnLeft();
	}
}
