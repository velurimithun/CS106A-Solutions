/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		double sLength = (getWidth()/2.0) - (BRICKS_IN_BASE/2.0*BRICK_WIDTH);
		double sHeight = (getHeight()) - (BRICK_HEIGHT);
		for (int i = 0; i < 14; i++) {
			sLength = (getWidth()/2.0) - (BRICKS_IN_BASE/2.0*BRICK_WIDTH)+ (i*BRICK_WIDTH/2);
			for (int j = 1; j <= 14 - i; j++) {
				add( new GRect(sLength, sHeight, BRICK_WIDTH, BRICK_HEIGHT) );
				sLength += BRICK_WIDTH;
			}
			sHeight -= BRICK_HEIGHT;
		}
	}
}

