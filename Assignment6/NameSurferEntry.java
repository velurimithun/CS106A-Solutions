/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import acm.program.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	
	private String Line;
	private String Name;
	private String RankStr;
	private int endOfName;
	private String ReturnString;
	
	public NameSurferEntry(String line) {
		// You fill this in //
		Line = line;
		//println(toString());
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		endOfName = Line.indexOf(" ");
		Name = Line.substring(0,endOfName); 
		return Name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		int end = Line.indexOf(" ");
		for (int i = 0; i < decade; i++) {
			end = Line.indexOf(" ", end+1);
		}
		if (decade == 10) {
			RankStr = Line.substring(end+1);
		}else {
			RankStr = Line.substring(end + 1, Line.indexOf(" ",end+1));
		}
		//println(RankStr);
		return Integer.parseInt(RankStr);
		//return end + "--" +RankStr;
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		ReturnString = getName() + " [";
		for (int i = 0; i < 11; i++){
			ReturnString += getRank(i) + " ";
		}
		//ReturnString.replace(ReturnString.charAt(ReturnString.length()-1), ']');
		return ReturnString.substring(0,ReturnString.length()-1) + "]";
	}
}

