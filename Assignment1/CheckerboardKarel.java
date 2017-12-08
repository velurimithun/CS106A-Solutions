/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel{
	public void run() {
		while ( leftIsClear() | rightIsClear() | frontIsClear() ) {
			FillRow();
			turnLeft();
			if ( frontIsClear() ){
				BeeperEast();
			}else{
				break;
			}
			FillRow();
			turnRight();
			if ( frontIsClear() ) {
				BeeperWest();
			}else{
				break;
			}
		}
	}
	
	private void FillRow() {
		putBeeper();
		while ( frontIsClear() ) {
			move();
			if ( frontIsClear() ) {
				move();
				putBeeper();
			}
		}
	}
	
	private void BeeperEast() {
		if ( beepersPresent() ) {
			move();
			turnLeft();
			if ( frontIsClear() ){
				move();
			}else{
				turnRight();
				move();
			}
		}else{
			move();
			turnLeft();
		}
	}

	private void BeeperWest()  {
		if ( beepersPresent() ) {
			move();
			turnRight();
			if (frontIsClear() ) {	
				move();
			}else{
				turnRight();
				move();
			}
		}else{
			move();
			turnRight();
		}
	}
}
	
