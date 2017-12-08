/*
 * File: Target.java
 * Name: Mithun
 * Section Leader: Bandhav
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	private static final int R1 = 72;
	private static final Double R2 = 46.8;
	private static final Double R3 = 21.6;
	
	public void run() {
	    Double XC1 = getWidth()/2.0;
		Double YC1 = getHeight()/2.0;
		GOval circle1 = new GOval(XC1-R1, YC1-R1, 2*R1, 2*R1);
		GOval circle2 = new GOval(XC1-R2, YC1-R2, 2*R2, 2*R2);
		GOval circle3 = new GOval(XC1-R3, YC1-R3, 2*R3, 2*R3);
		
		add ( circle1 );
		add ( circle2 );
		add ( circle3 );
		
		circle1.setFilled(true);
		circle1.setColor(Color.red);
		circle2.setFilled(true);
		circle2.setColor(Color.white);
		circle3.setFilled(true);
		circle3.setColor(Color.red);
	}
}
