/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	//private HangmanLexicon word;
	private RandomGenerator rgen = new RandomGenerator();  //Initiating RandomGenerator rgen variable
	private int gusess;
	private String incorrectGusess = "";
	private String word;
	private String newWord = "";
	private String returnStr;
	private String inp;
	private HangmanCanvas canvas;
	private String secWord = "";
	private String old = "";
	
	public void init() {
		 canvas = new HangmanCanvas();
		 add(canvas);
		}

	
    public void run() {
		/* You fill this in */
    	HangmanLexicon word1 = new HangmanLexicon();
    	int count = word1.getWordCount();
    	println(count+" words loaded");
    	word = word1.getWord(rgen.nextInt(0, count-1));
    	canvas.reset();
    	//word = "FUZZY";
    	//println(word);
    	gusess = 8;
    	displayStatments(word);
    	while(gusess > 0) {
    		userInput();
    		if (secWord.equals(partialWord(word, newWord))) {
    			println("You won!!!!");
    			break;
    		}
    		println("You left with "+gusess+" gussess");
    	}
    	if (newWord.length() != word.length()) println("You looooooooose\n"+"The word was "+word);
	}
    
    private void displayStatments(String str) {
    	println("Welcome to Hangman!");
    	println("The word looks like this: "+dashes(str));
    	println("you left with "+gusess+" guesess");
    }
    
    private String dashes(String str1) {
    	returnStr = "";
    	//println(str1);
    	for( int i = 0;i < str1.length(); i++) {
    		returnStr += "- ";
    		secWord += str1.substring(i,i+1)+" ";
    	}
    	return returnStr;
    }
    
    private void userInput() {
    	inp = readLine("Your Guess: ");
    	if ("qwertyuiopasdfghjklzxcvbnm".contains(inp.toLowerCase()) && (inp.length() == 1)) {
    		
    		old = partialWord(word, newWord);
    		if (word.contains(inp.toUpperCase())) {
    			newWord += inp.toUpperCase();
    		}else{
    			gusess--;
    			incorrectGusess += inp.toUpperCase();
    			println("There are no "+inp+"'s in the word");}
    		canvas.displayWord(partialWord(word, newWord));
    		canvas.noteIncorrectGuess(incorrectGusess);
    		println("The word now looks like this "+partialWord(word, newWord));
    	}else{println("Enter valid input.");}
    }
    
    private String partialWord(String str1, String str2) {
    	String result = "";
    	for (int i = 0; i < str1.length(); i++) {
    		//println(str1.substring(i,i+1));
    		if (str2.contains(str1.substring(i,i+1))){
    			result += str1.substring(i,i+1)+" ";
    		}else {
    			result += "- ";
    		}
    	}
    	return result;
    }
}
