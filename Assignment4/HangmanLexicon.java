/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;


public class HangmanLexicon extends ConsoleProgram{
	
	private int i;
	private ArrayList <String> wordList = new ArrayList <String> ();
	
	private BufferedReader openFile(String prompt) { 
		BufferedReader rd = null;
		
		while (rd == null) {
			try {
				String filename = prompt;
				rd = new BufferedReader(new FileReader(filename));
			}catch (IOException ex) {
				println("Error@@@@!!!!");
			}
		}
		return rd;
	}
	public HangmanLexicon() {
		BufferedReader rd = openFile("HangmanLexicon.txt");
		//wordList = new String[0];
		i = 0;
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				//println(i+line);
				//println(wordList);
				wordList.add(line);
				i++;
			}
			rd.close();
		}catch (IOException ex) {
			throw new ErrorException(ex);
		}
		//println(i);
	}


/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		//println(i);
		return i;
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		println(index);
		return wordList.get(index);
	}
}
