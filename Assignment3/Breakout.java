/*
 * File: Breakout.java
 * -------------------
 * Name:Mintu
 * Section Leader:Bandhav
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 400;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private GRect currentRect;
	
	private GRect brick;
	
	private GOval ball;
	
	private Double vx, vy;
	
	private RandomGenerator rgen = new RandomGenerator();
	
	private int TOTAL_BRICKS = NBRICKS_PER_ROW*NBRICK_ROWS;
	
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au"); 

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setup();
		int TURNS_REMAINING = NTURNS;
		while (TURNS_REMAINING > 0) {
			createBall();
			playGame();
			if (TOTAL_BRICKS == 0) {
				break;
			}
			TURNS_REMAINING--;
		}
		//add(new GLabel("GAME OVER!!!!", 200, 400));
	}
	
	private void playGame() {
		intiateBall();
		rebounceBall();
	}
	
	private void rebounceBall() {
		waitForClick();
		while (true) {
			ball.move(vx, vy);
			//GLine Dot = new GLine(ball.getX()+BALL_RADIUS, ball.getY(),ball.getX()+BALL_RADIUS,ball.getY());
			//add(Dot);
			//println(vx+"before reflection");
			pause(1);
			//currentRect.move(ball.getX(), getHeight() - (PADDLE_WIDTH + PADDLE_Y_OFFSET));
			//add(currentRect);
			if (ball.getX() + 2*BALL_RADIUS >= getWidth()) {
				vx = - vx;
			}if (ball.getX() <= 0) {
				vx = -vx;
			}if (ball.getY() <= 0) {
				vy = -vy;
			}
			if ((getElementAt(ball.getX(), ball.getY()+2*BALL_RADIUS) == currentRect) || (getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+2*BALL_RADIUS) == currentRect)) {
				bounceClip.play(); 
				vy = -vy;
				//println(ball.getX() + 2*BALL_RADIUS);
				if (ball.getX() + 2*BALL_RADIUS >= getWidth() || (ball.getX() <= 0)){
					vx = -vx;
					//println(vx+"afterdfghjkl;lkjhgfghjk");
				}
			}else if ((getElementAt(ball.getX(), ball.getY()) == currentRect) || (getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()) == currentRect)) {
				//bounceClip.play(); 
				//vy = -vy;
				break;
			
			}else if (getElementAt(ball.getX(), ball.getY()) != null) {
				//println(getElementAt(ball.getX(), ball.getY()));
				if (getElementAt(ball.getX(), ball.getY()) != currentRect) {
					remove(getElementAt(ball.getX(), ball.getY()));
					TOTAL_BRICKS--;
					if (TOTAL_BRICKS == 0) {
						add(new GLabel ("YOU WON!!!", 200, 400));
						break;
					}
					vy = -vy;
					bounceClip.play(); 
				}
			}else if (getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()) != null) {
				//println(getElementAt(ball.getX(), ball.getY()));
				if (getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()) != currentRect) {
					remove(getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()));
				}TOTAL_BRICKS--;
				if (TOTAL_BRICKS == 0) {
					add(new GLabel ("YOU WON!!!", 200, 400));
					break;
				}
				vy = -vy;
				bounceClip.play(); 
			}else if (ball.getY()+2*BALL_RADIUS >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT) {
				break;
			}
		}
	}		
	
	private void intiateBall() {
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx; 
		}
	}
	
	private void setup() {
		setUpBricks();
		createPaddle();
	}
	
	private void createBall() {
		ball = new GOval(getWidth()/2 - BALL_RADIUS, getHeight()/2, 2*BALL_RADIUS, 2*BALL_RADIUS);
		//ball = new GOval(getWidth() - 2*BALL_RADIUS, getHeight()/2, 2*BALL_RADIUS, 2*BALL_RADIUS);
		ball.setFilled(true);
		//add(new GLabel("ghjk",300, 200));
		add(ball);
	}
	
	private void createPaddle() {
		currentRect = new GRect(0, getHeight() - (PADDLE_HEIGHT + PADDLE_Y_OFFSET), PADDLE_WIDTH, PADDLE_HEIGHT);
		currentRect.setFilled(true);
		add(currentRect);
		addMouseListeners();
	}

	public void mouseMoved(MouseEvent e) {
		if (e.getX()+PADDLE_WIDTH/2<= getWidth() && e.getX()- PADDLE_WIDTH/2 > 0) {
			//add( new GLabel("("+e.getX()+","+e.getY()+")",0, 300));
			currentRect.setBounds(e.getX() - currentRect.getWidth()/2 , getHeight() - (PADDLE_WIDTH + PADDLE_Y_OFFSET), PADDLE_WIDTH, PADDLE_HEIGHT);
		}
	}
	private void setUpBricks() {
		int y = BRICK_Y_OFFSET;
		for (int i = 1; i <= NBRICK_ROWS; i++) {
			int x = 0;
			for (int j = 1; j <= NBRICKS_PER_ROW; j++) {
				brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setColor(color(i));
				add(brick);
				x += (BRICK_WIDTH + BRICK_SEP);
			}
		y += (BRICK_HEIGHT+ BRICK_SEP);
		}
	}
	private Color color(int i) {
		if (i == 1 || i == 2) return Color.red;
		if (i == 3 || i == 4) return Color.orange;
		if (i == 5 || i == 6) return Color.yellow;
		if (i == 7 || i == 8) {
			return Color.green;
		}else{
			return Color.cyan;
		}
	}
	
}
