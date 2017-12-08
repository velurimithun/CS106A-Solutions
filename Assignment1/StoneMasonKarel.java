/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	private void Check_Stone(){
		/* places a beeper if it is not present
		 * moves forwad if beeper is already present untill it reaches the wall
		 */ 
		while ( frontIsClear() ){
			if ( beepersPresent() ){
				move();
			} 
			else {
				putBeeper();
				move();
			}
		}
	}
	private void Complete_Arch (){
		/*Completes one total arch
		 * Turns up and moves then to right,again turns right and moves down
		 */
		turnLeft();
		Check_Stone();
		turnRight();
		Move4steps();
		turnRight();
		Check_Stone();
		turnLeft();
	}
	private void Move4steps (){
		/*moves 4 steps in forwad direction
		 * 
		 */
		for (int i=0; i<4; i++){
			move();
		}
	}
	public void run(){
		Complete_Arch ();
		/*If front is clear,completes an arch
		 * otherwise rests there itself
		 */ 
		while ( frontIsClear() ){
			Move4steps();
			Complete_Arch();
		}
	}
}
