/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.*;
import acm.program.*;
import java.io.*;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	
	private BufferedReader rd;
	//private String filename;
	private String line;
	private NameSurferEntry entry;
	private NameSurferEntry entryT = new NameSurferEntry("Sam 58 69 99 131 168 236 278 380 467 408 466");
	private HashMap<String, NameSurferEntry> Inventory = new HashMap<String, NameSurferEntry>();
	
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try {
			rd = new BufferedReader(new FileReader(filename));
			while (true) {
				line = rd.readLine();
				if ((line == null)) break;
				entry = new NameSurferEntry(line);
				Inventory.put(entry.getName(), entry);
			}
			rd.close();
		}
		catch (IOException ex) {
			//println("errrroooor");
			throw new ErrorException(ex);
		}
	}

	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		/*   try{
			while (true) {
				//rd = new BufferedReader(new FileReader(filename));
				line = rd.readLine();
				if ((line == null)) break;
				entry = new NameSurferEntry(line);
				Inventory.put(entry.getName(), entry);
			}
			//rd.close();
		}
		catch (IOException ex) {
			//println("errrroooor");
			throw new ErrorException(ex);
		}   *////
		//if (1 == 1) {
		if (Inventory.containsKey(Capitalize(name))) {
			//return entryT;
			return Inventory.get(Capitalize(name));
		}else {
		//if (line == null) return null;
		return null;
		}
	}
	
	
	private String Capitalize(String str) {
		
		String strL = str.toLowerCase();
		String strU = str.toUpperCase();
		//str = strL.replace(strL.charAt(0), strU.charAt(0));
		
		return strU.substring(0,1) + strL.substring(1);
	}
	
}

