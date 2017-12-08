/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	
	private NameSurferEntry lastEntry;
	private GLine VerticalLine;
	private Color c;
	private ArrayList<NameSurferEntry> EntryList = new ArrayList<NameSurferEntry>();
	
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
		lastEntry = null;
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		EntryList = new ArrayList<NameSurferEntry>();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		lastEntry = entry;
		if (lastEntry != null ) EntryList.add(lastEntry);
	}
	
	public void displayInventory(ArrayList<NameSurferEntry> EntryList) {
		removeAll();
		//lastEntry = Entry;
		Integer start = 1900;
		int Xline = 0;
		int Yline = 0;
		for (int i = 1; i <= 10; i ++ ) {
			VerticalLine = new GLine(Xline + getWidth()*i/11,0, Xline + getWidth()*i/11,Yline + getHeight()); 
			add(new GLabel(Integer.toString(start+(10*(i-1))),Xline + ((getWidth()*(i-1))/11 )+ 2,getHeight()-GRAPH_MARGIN_SIZE+15));
			add(VerticalLine);
		}
		add(new GLabel("2000",Xline + getWidth()*10/11+2,getHeight()-GRAPH_MARGIN_SIZE+15));
		add(new GLine(0,GRAPH_MARGIN_SIZE, getWidth(),GRAPH_MARGIN_SIZE));
		add(new GLine(0,getHeight()-GRAPH_MARGIN_SIZE, getWidth(),getHeight()-GRAPH_MARGIN_SIZE));
		for (int j = 0; j < EntryList.size(); j++) {
			setValuec(j+1);
			drawLinesForEntry(EntryList.get(j));
		}
	}
	
	public void setValuec(int i) {
		while (i > 4) {i -= 4;}
		if (i == 1) c = Color.BLACK;
		if (i == 2) c = Color.RED;
		if (i == 3) c = Color.BLUE;
		if (i == 4) c = Color.MAGENTA;
	}
	public void drawLinesForEntry(NameSurferEntry Entry) {
		for(int i = 0; i < 10; i++) {
			Integer Rank = Entry.getRank(i);
			String RankStr = Integer.toString(Rank);
			if (Rank == 0) RankStr = "*";
			int Y1 = getHeight()*Entry.getRank(i)/1000;
			int Y2 = getHeight()*Entry.getRank(i+1)/1000;
			if (Y1 == 0 ) Y1 = getHeight()-GRAPH_MARGIN_SIZE;
			if (Y2 == 0 ) Y2 = getHeight()-GRAPH_MARGIN_SIZE;
			if (Y1 < GRAPH_MARGIN_SIZE) Y1 = GRAPH_MARGIN_SIZE;
			if (Y2 < GRAPH_MARGIN_SIZE) Y2 = GRAPH_MARGIN_SIZE;
			GLine line = new GLine(getWidth()*(i)/11,Y1,getWidth()*(i+1)/11,Y2);
			line.setColor(c);
			add(line);
			
			GLabel label = new GLabel(Entry.getName()+" "+RankStr,getWidth()*(i)/11 + 2,Y1-5);
			label.setColor(c);
			add(label);
		}
		Integer Rank = Entry.getRank(10);
		String RankStr = Integer.toString(Rank);
		if (Rank == 0) RankStr = "*";
		GLabel label = new GLabel(Entry.getName()+" "+RankStr,getWidth()*(10)/11 + 2,getHeight()*Entry.getRank(10)/1000-5);
		label.setColor(c);
		add(label);
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		displayInventory(EntryList);
	}
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
