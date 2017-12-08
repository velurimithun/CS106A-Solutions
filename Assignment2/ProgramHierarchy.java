/*
 * File: ProgramHierarchy.java
 * Name: Mithun		
 * Section Leader: Bandhav
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final int WIDTH  = 100;
	private static final int HEIGHT = 40;
	public void run() {
		add( new GRect(275, 100, WIDTH, HEIGHT));//Rectangles
		add( new GRect(275, 190, WIDTH, HEIGHT));
		add( new GRect(150, 190, WIDTH, HEIGHT));
		add( new GRect(400, 190, WIDTH, HEIGHT));
		
		add( new GLine(200, 190, 325, 140));//Lines
		add( new GLine(325, 190, 325, 140));
		add( new GLine(450, 190, 325, 140));
		
		add( new GLabel("Graphics Program", 150, 210));//texts
		add( new GLabel("Consol Program", 280, 210));
		add( new GLabel("Dialog Program", 405, 210));
		add( new GLabel("Program", 300, 120));
	}
}

