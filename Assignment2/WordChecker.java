import acm.program.*;
import acm.util.*;
import acm.io.*;

import java.util.*;

public class WordChecker extends ConsoleProgram{
	
	private HangmanLexicon lexicon = new HangmanLexicon(); 
	private String previousWord = "";
	public void run() {
		println("Enter words ending with balnk line");
		while(true){
			String word = readLine();
			if (word.length() == 0){
				break;
			}else{
				
				if (!(checkWord(word))){
					println("Illegal word pl.enter an new word");
					previousWord = "";
				}else if (!(checkPreviousWord(previousWord, word)) ){
					if(previousWord.length()!=0){
					println("Illegal word pl.enter an new word");
					previousWord = "";
					}
				}else if (!(differByOne(previousWord, word))){
					if(previousWord.length()!=0){
						println("Illegal word pl.enter an new word");
						previousWord = "";
					}
				}
			}
			previousWord = word;
		}
		
	}
	
	private boolean checkWord(String word){
		//println(lexicon.isEnglishWord(word));
		return lexicon.isEnglishWord(word);
	}
	
	private boolean checkPreviousWord(String pw, String w){
		for(int i = 0; i < pw.length(); i++){
			if (pw.substring(i, i+1).equalsIgnoreCase(w.substring(i, i+1))){
				return true;
			}
		}
		return false;
	}
	
	private boolean differByOne(String pw, String w){
		int j = 0;
		for(int i = 0; i < pw.length(); i++){
			if (pw.substring(i, i+1).equalsIgnoreCase(w.substring(i, i+1))){
				j++;
			}
			if (j > 1) return true;
		}
		return false;
	}
}
