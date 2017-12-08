/*
 * File: FindRange.java
 * Name: Mintu	
 * Section Leader: Bandhav
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0; 
	public void run() {
		println("This program finds smallest and largest Integers.");
		int smallest;
		int largest;
		int value = readInt("? ");
		if (value != 0) {
			smallest = value;
			largest = value;
			while ( value != SENTINEL ) {
				if (value <= smallest) smallest = value;
				if (value >= largest) largest = value;
				value = readInt("? ");
			}
			println("Smallest: "+smallest);
			println("Largest: "+largest);
		}else{
			println("No vlaue is enterd.");
		}
	}
}
