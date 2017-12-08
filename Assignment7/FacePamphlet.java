/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	private FacePamphletProfile trailProfile;
	private FacePamphletDatabase trailDatabase;
	private FacePamphletCanvas canvas;
	private JTextField name;
	private JTextField changeStatus;
	private JTextField changePicture;
	private JTextField addFriend;
	private JButton Add = new JButton("Add");
	private JButton Delete = new JButton("Delete");
	private JButton Look_up = new JButton("Look_up");
	private JButton Change_Status = new JButton("Change Status");
	private JButton Change_Picture = new JButton("Change Picture");
	private JButton Add_Friend = new JButton("Add Friend");
	
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		graphics();
		trailDatabase = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		//canvas.showMessage("Mithun");
		add(canvas);
    }
    
	public void graphics() {
		name = new JTextField(TEXT_FIELD_SIZE);
		add(name, NORTH);
		add(new JLabel("Name "), NORTH);
		add(name, NORTH);
		add(Add, NORTH);
		add(Delete, NORTH);
		add(Look_up, NORTH);
		
		//WEST
		changeStatus = new JTextField(TEXT_FIELD_SIZE);
		add(changeStatus, WEST);
		add(Change_Status, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		changePicture = new JTextField(TEXT_FIELD_SIZE);
		add(changePicture, WEST);
		add(Change_Picture, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		addFriend = new JTextField(TEXT_FIELD_SIZE);
		add(addFriend, WEST);
		add(Add_Friend, WEST);
		
		changeStatus.addActionListener(this);
		changePicture.addActionListener(this);
		addFriend.addActionListener(this);
		addActionListeners();
	}
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	
    	if (e.getSource() == Add ) {
    		if (trailDatabase.containsProfile(name.getText())) {
    			canvas.showMessage( "A profile with the name "+name.getText()+" already exists");
    			println("Add: Profile for "+name.getText()+" already exists "+trailDatabase.getProfile(name.getText()).toString());
    		}else {
    			trailProfile = new FacePamphletProfile(name.getText());
    			trailDatabase.addProfile(trailProfile);
    			canvas.showMessage("New profile created");
    			println("Add: "+"new profile "+trailDatabase.getProfile(name.getText()).toString());
    		}
    		//removeAll();
    		//add(canvas);
    		canvas.displayProfile(trailDatabase.getProfile(name.getText()));
    		
    	}
    	if (e.getSource() == Delete ) {
    		canvas.removeAll();
    		if (trailDatabase.containsProfile(name.getText())){
    			trailDatabase.deleteProfile(name.getText());
    			canvas.showMessage( "Profile of "+name.getText()+" deleted");
    			println("Delete: Profile with name "+name.getText()+" was deleted");
    		}else {
    			canvas.showMessage( "A profile with the name "+name.getText()+" does not exist");
    			println("Delete: Profile with the name "+name.getText()+" doesn't exists");
    		}
    		
    		//canvas.displayProfile(trailDatabase.getProfile(name.getText()));
    	}
    	if (e.getSource() == Look_up ) {
    		if (trailDatabase.containsProfile(name.getText())){
    			canvas.showMessage("Displaying "+name.getText());
    			println("Look up: "+ trailDatabase.getProfile(name.getText()).toString());
    		}else {
    			canvas.showMessage("A profile with the name "+name.getText()+" does not exist");
    			println("Look up: profile with the name "+name.getText()+" doesn't exists");
    		}
    		//removeAll();
    		canvas.displayProfile(trailDatabase.getProfile(name.getText()));
    	}
    	
    	if ((e.getSource() == changeStatus & changeStatus.getText().length() != 0)|| (e.getSource() == Change_Status & changeStatus.getText().length() != 0)){
    		if (trailDatabase.containsProfile(name.getText())){
    			trailDatabase.getProfile(name.getText()).setStatus(changeStatus.getText());
    			println("Status updated to "+changeStatus.getText());
    			canvas.showMessage( "Status updated to "+changeStatus.getText());
    			println("--> Current profile: "+trailDatabase.getProfile(name.getText()).toString());
    		}else {
    			println("--> No current profile");
    			canvas.showMessage( "Please select a profile to change status");
    			println("please select a profile to change the status");
    		}
    		//removeAll();
    		canvas.displayProfile(trailDatabase.getProfile(name.getText()));
    	}
    	//if (e.getSource() == Change_Status & changeStatus.getText().length() != 0) println("Change Status "+changeStatus.getText());
    	
    	if ((e.getSource() == changePicture & changePicture.getText().length() != 0) || (e.getSource() == Change_Picture & changePicture.getText().length() != 0)) {
    		if (trailDatabase.containsProfile(name.getText())){
    			GImage image = null;
    			//image = new GImage(name.getText());
    			try {
    				image = new GImage(changePicture.getText());
    			} catch (ErrorException ex) {
    				// Code that is executed if the filename cannot be opened.
    				image = null;
    				canvas.showMessage("Unable to open image file: "+changePicture.getText());
    				//throw new ErrorException(ex);
    			}
    			trailDatabase.getProfile(name.getText()).setImage(image);
    			canvas.showMessage( "Picture updated");
    			println("Picture updated");
    			println("--> Current profile: "+trailDatabase.getProfile(name.getText()).toString());
    		}else {
    			println("--> No current profile");
    			canvas.showMessage( "Please select a profile to change picture");
    			println("please select a profile to change the status");
    		}
    		//removeAll();
    		canvas.displayProfile(trailDatabase.getProfile(name.getText()));
    	}
    	//if (e.getSource() == Change_Picture & changePicture.getText().length() != 0) println("Change Picture "+changePicture.getText());
    	
    	if ((e.getSource() == addFriend & addFriend.getText().length() != 0) || (e.getSource() == Add_Friend & addFriend.getText().length() != 0)) {
    		if (trailDatabase.containsProfile(name.getText())){
    			if (trailDatabase.containsProfile(addFriend.getText())){
    				if (!(trailDatabase.getProfile(name.getText()).addFriend(addFriend.getText()))){
    					canvas.showMessage(addFriend.getText()+" already has "+name.getText()+" as a friend");
    					println("Friend already exists!!");
    				} else{canvas.showMessage(addFriend.getText()+" added as a friend");}
    				if (trailDatabase.getProfile(addFriend.getText()).addFriend(name.getText())){}
    				println("--> Current profile: "+trailDatabase.getProfile(name.getText()).toString());
    			}else {
    				canvas.showMessage(addFriend.getText()+" does not exist.");
    				println("Friend's profile does not exist");
    			}
    		}else {
    			println("--> No current profile");
    			canvas.showMessage( "Please select a profile to add friend");
    			println("please select a profile to change the status");
    		}
    		//removeAll();
    		canvas.displayProfile(trailDatabase.getProfile(name.getText()));
    	}
    	/*if (e.getSource() == Add_Friend & addFriend.getText().length() != 0) {
    		println("Add Friend "+addFriend.getText());
    		//println(trailProfile.toString().toUpperCase());
    	}*/
    }
}
