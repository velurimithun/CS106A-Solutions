/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	
	private JTextField JText;
	private JButton Clear;
	private JButton Graph;
	private NameSurferDataBase DataBase;
	private NameSurferEntry Entry ;
	private NameSurferGraph canvas;
	//private NameSurferEntry Entry = new NameSurferEntry("Sam 58 69 99 131 168 236 278 380 467 408 466");
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		JText = new JTextField(30);
		Clear = new JButton("Clear");
		Graph = new JButton("Graph");
		
		add(new JLabel("Name "),SOUTH);
		add(JText,SOUTH);
		add(Graph,SOUTH);
		add(Clear,SOUTH);
		
		JText.addActionListener(this);
		addActionListeners();
		
		DataBase = new NameSurferDataBase(NAMES_DATA_FILE);
		canvas = new NameSurferGraph();
		add(canvas);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getSource() == JText ||  e.getSource() == Graph){
			Entry = DataBase.findEntry(JText.getText());
			canvas.addEntry(Entry);
			canvas.update();
			//if (Entry != null) println("Graph: " + Entry.toString());
			//println("Graph: " + JText.getText() );
		}
		if (e.getSource() == Clear) {
			canvas.clear();
			canvas.update();
			//println("Clear");
		}
	}
}
