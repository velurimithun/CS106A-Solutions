/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	private GLabel label;
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		GLine scaffold = new GLine(getWidth()/4.0, getHeight()/4.0-50, getWidth()/4.0, getHeight()/4.0-50+SCAFFOLD_HEIGHT);
		GLine beam = new GLine(getWidth()/4.0, getHeight()/4.0-50, getWidth()/4.0+BEAM_LENGTH, getHeight()/4.0-50);
		GLine rope = new GLine(getWidth()/4.0+BEAM_LENGTH, getHeight()/4.0-50, getWidth()/4.0+BEAM_LENGTH, getHeight()/4.0-50+ROPE_LENGTH);
		add(scaffold); 
		add(beam);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String str) {
		/* You fill this in */
		label = new GLabel(str,getWidth()/4.0+30, getHeight()/4.0-80+SCAFFOLD_HEIGHT);
		label.setFont("SansSerif-bold-18");
		if (getElementAt(getWidth()/4.0+33, getHeight()/4.0-80+SCAFFOLD_HEIGHT) != null) {
			remove( getElementAt(getWidth()/4.0+33, getHeight()/4.0-80+SCAFFOLD_HEIGHT) );
			//GObject obj = getElementAt(getWidth()/4.0+33, getHeight()/4.0-80+SCAFFOLD_HEIGHT);
			//add(obj,getWidth()/4.0+33, getHeight()/4.0+SCAFFOLD_HEIGHT);
		}
		//add(obj,getWidth()/4.0+33, getHeight()/4.0+SCAFFOLD_HEIGHT);
		add(label);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String letter) {
		/* You fill this in */
		GLabel incorrect =new GLabel(letter, getWidth()/4.0+30, getHeight()/4.0-50+SCAFFOLD_HEIGHT);
		incorrect.setFont("SansSerif-bold-18");
		
		if (getElementAt(getWidth()/4.0+30, getHeight()/4.0-50+SCAFFOLD_HEIGHT) != null) {
			remove(getElementAt(getWidth()/4.0+30, getHeight()/4.0-50+SCAFFOLD_HEIGHT));
		}
		
		add(incorrect);
		double bodySX = getWidth()/4.0+BEAM_LENGTH;
		double bodySY = getHeight()/4.0-50+ROPE_LENGTH+2*HEAD_RADIUS;
		double bodyEX = getWidth()/4.0+BEAM_LENGTH;
		double bodyEY = getHeight()/4.0-50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH;
		
		GOval face = new GOval(getWidth()/4.0+BEAM_LENGTH-HEAD_RADIUS, getHeight()/4.0-50+ROPE_LENGTH, 2*HEAD_RADIUS, 2*HEAD_RADIUS);
		GLine body = new GLine(getWidth()/4.0+BEAM_LENGTH, getHeight()/4.0-50+ROPE_LENGTH+2*HEAD_RADIUS, getWidth()/4.0+BEAM_LENGTH, getHeight()/4.0-50+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
		GLine leftArm = new GLine(bodySX,bodySY+ARM_OFFSET_FROM_HEAD,bodySX+UPPER_ARM_LENGTH,bodySY+ARM_OFFSET_FROM_HEAD);
		GLine leftWrist = new GLine(bodySX+UPPER_ARM_LENGTH,bodySY+ARM_OFFSET_FROM_HEAD,bodySX+UPPER_ARM_LENGTH,bodySY+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		GLine rightArm = new GLine(bodySX,bodySY+ARM_OFFSET_FROM_HEAD,bodySX-UPPER_ARM_LENGTH,bodySY+ARM_OFFSET_FROM_HEAD);
		GLine rightWrist = new GLine(bodySX-UPPER_ARM_LENGTH,bodySY+ARM_OFFSET_FROM_HEAD,bodySX-UPPER_ARM_LENGTH,bodySY+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		GLine hip = new GLine(bodyEX-HIP_WIDTH/2, bodyEY,bodyEX+HIP_WIDTH/2, bodyEY);
		GLine rightLeg = new GLine(bodyEX-HIP_WIDTH/2, bodyEY,bodyEX-HIP_WIDTH/2, bodyEY+LEG_LENGTH);
		GLine rightFoot = new GLine(bodyEX-HIP_WIDTH/2, bodyEY+LEG_LENGTH,bodyEX-HIP_WIDTH/2-FOOT_LENGTH, bodyEY+LEG_LENGTH);
		GLine leftLeg = new GLine(bodyEX+HIP_WIDTH/2, bodyEY,bodyEX+HIP_WIDTH/2, bodyEY+LEG_LENGTH);
		GLine leftFoot = new GLine(bodyEX+HIP_WIDTH/2, bodyEY+LEG_LENGTH, bodyEX+HIP_WIDTH/2+FOOT_LENGTH, bodyEY+LEG_LENGTH);
		
		switch(letter.length()) {
			case 1:
				add(face);
				break;
			case 2:
				add(body);
				break;
			case 3:
				add(leftArm);
				add(leftWrist);
				break;
			case 4:
				add(rightArm);
				add(rightWrist);
				break;
			case 5:
				add(hip);
				add(leftLeg);
				break;
			case 6:
				add(rightLeg);
				break;
			case 7:
				add(leftFoot);
				break;
			case 8:
				add(rightFoot);
				break;
		}
		
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
