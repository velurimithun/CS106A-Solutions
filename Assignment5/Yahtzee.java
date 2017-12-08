/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;

import acm.io.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players (<5)");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		/* You fill this in */
		intializeTotalsList();
		intializeCategorysList();
		for (int j = 0; j < N_SCORING_CATEGORIES ; j++) {
			for (int i = 0; i < nPlayers; i++){
				display.printMessage(playerNames[i] + "'s turn! Click \"Roll Dice\" button to roll the dice");
				random5();
				display.waitForPlayerToClickRoll(i+1);
				display.displayDice(random5IntArray);
				display.printMessage("Select the dice you wish to re-roll and click \"Roll again\".");
				display.waitForPlayerToSelectDice();
				rerollSelectedDice();
				display.displayDice(random5IntArray);
				display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
				display.waitForPlayerToSelectDice();
				rerollSelectedDice();
				display.displayDice(random5IntArray);
				display.printMessage("Select a category for this roll.");
				while (true) {
					category = display.waitForPlayerToSelectCategory();
					if (categorys[i][category] == 0) {
						//bounceClip.play();
						totals[i][category] += categoryScore(category);
						display.updateScorecard(category, i+1, categoryScore(category));

						categorys[i][category] = 1;
						//categorysInt++;	
						break;
					}
					display.printMessage("Select a category for this roll and pl. don't select category already used");
				}
				//totals[i][category] += categoryScore(category);
				//display.updateScorecard(category, i+1, categoryScore(category));
				display.updateScorecard(17, i+1,sumOfElements(totals[i]));
			}
		}
		int maxScore = 0;
		String winner = "";
		for (int k = 0; k < nPlayers;  k++) {
			display.updateScorecard(UPPER_SCORE, k+1, upperScore(totals[k]));
			if (upperScore(totals[k]) > 63) {
				display.updateScorecard(8, k+1, 35);
				totals[k][8] = 35;
			}
			if (sumOfElements(totals[k]) > maxScore ) {
				maxScore = sumOfElements(totals[k]);
				winner = playerNames[k];
			}
		}
		display.printMessage("Congratulations, "+winner+" ,you are the winner with the total score of "+maxScore);
	}
	
	private int upperScore(int[] array) {
		int sum = 0;
		for (int i = 1; i <= 6; i++)  sum += array[i];
		return sum;
	}
	
	private int sumOfElements(int[] array ) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) sum += array[i];
		//println(sum);
		return sum;
	}
	
	private void intializeTotalsList() {
		totals = new int[nPlayers][17];
		for (int i = 0; i < nPlayers; i++) {
			for (int j = 0; j < 17; j++) {
				totals[i][j] = 0;
			}
		}
	}
	
	private void intializeCategorysList() {
		categorys = new int[nPlayers][17];
		for (int i = 0; i < nPlayers; i++) {
			for (int j = 0; j < 17; j++) {
				categorys[i][j] = 0;
			}
		}
	}
	
	private void random5() {
		for (int i = 0; i < 5; i ++) {
			random5IntArray[i] = rgen.nextInt(1, 6);
		}
		//return random5IntArray;
	}
	
	private void rerollSelectedDice() {
		for (int i = 0; i < 5; i++) {
			if (display.isDieSelected(i)) random5IntArray[i] = rgen.nextInt(1,6);
		}
	}
	
	private int categoryScore(int category) {

		if (YahtzeeMagicStub.checkCategory(random5IntArray, category)) {
			createCList();
			score = 0;
			if (cList.contains(category)) {
				for (int i = 0; i < 5; i++) {
					if (random5IntArray[i] == category) score += category;
				} 
			}else if ((category == 9) || (category == 10) || (category == 15)) {
				for (int k = 0; k < 5; k++) {
					score += random5IntArray[k];
				}
			}else if (category == 11) score = 25;
			else if (category == 14) score = 50;
			else if (category == 12) score = 30;
			else if (category == 13) score = 40;

			//categorys.add(category);
			//categorysInt++;

		}else return 0;

		return score;
		
	}
	
	private void createCList() {
		for (int i = 1; i <= 6; i++) {
			cList.add(i);
		}
	}
	
/* Private instance variables */
	//private int categorysInt = 0;
	private int[][] categorys;
	private int[][] totals;
	private ArrayList<Integer> cList = new ArrayList <Integer> ();
	private int category;
	private int nPlayers;
	private int score;
	private int[] random5IntArray = new int[5];
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au"); 
}
