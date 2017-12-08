/*
 * File: Hailstone.java
 * Name: Mintu
 * Section Leader: Bandhav
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int count = 0;
		int value = readInt("Enter an Positive Integer: ");
		while ( value != 1 ) {
			if ( value%2 == 0 ) {
				println(value+"   is even,so I take half:   "+value/2);
				value = value/2;
			}else{
				println(value+"   is odd,so I make 3n+1:    "+(3*value + 1)); 
				value = (3*value + 1);
			}
		count += 1;
		}
		println("The process took "+count+" steps to reach 1.");
	}
}

