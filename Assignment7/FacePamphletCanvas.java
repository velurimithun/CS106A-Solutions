/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import acm.program.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
		
	}
	
	
	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		if (getElementAt(APPLICATION_WIDTH/4,getHeight()-BOTTOM_MESSAGE_MARGIN) != null){
			remove(getElementAt(APPLICATION_WIDTH/4,getHeight()-BOTTOM_MESSAGE_MARGIN));
		}
		GLabel message = new GLabel(msg,APPLICATION_WIDTH/4,getHeight()-BOTTOM_MESSAGE_MARGIN);
		message.setFont(MESSAGE_FONT);
		//message.setLocation(APPLICATION_WIDTH/2-msg.length()*18,getHeight()-BOTTOM_MESSAGE_MARGIN);
		add(message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		if (profile != null) {
		if (getElementAt(LEFT_MARGIN,TOP_MARGIN+24) != null) {
			remove(getElementAt(LEFT_MARGIN,TOP_MARGIN+24));
		}
		GLabel name = new GLabel(profile.getName(), LEFT_MARGIN,TOP_MARGIN+24);
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name);
		
		if (getElementAt(LEFT_MARGIN,TOP_MARGIN+24+IMAGE_MARGIN) != null) {
			remove(getElementAt(LEFT_MARGIN,TOP_MARGIN+24+IMAGE_MARGIN));
		}
		if (getElementAt(LEFT_MARGIN+(IMAGE_WIDTH/4),TOP_MARGIN+24+IMAGE_MARGIN+(IMAGE_HEIGHT/2))!=null){
			remove(getElementAt(LEFT_MARGIN+(IMAGE_WIDTH/4),TOP_MARGIN+24+IMAGE_MARGIN+(IMAGE_HEIGHT/2)));
		}
		if (profile.getImage() == null) {
			GRect rectangle = new GRect(LEFT_MARGIN,TOP_MARGIN+24+IMAGE_MARGIN,IMAGE_WIDTH,IMAGE_WIDTH);
			GLabel noImage = new GLabel("No Image",LEFT_MARGIN+(IMAGE_WIDTH/4),TOP_MARGIN+24+IMAGE_MARGIN+(IMAGE_HEIGHT/2));
			noImage.setFont(PROFILE_IMAGE_FONT);
			add(rectangle);
			add(noImage);
		}else {
			GImage image = profile.getImage();
			image.setSize(IMAGE_WIDTH, IMAGE_WIDTH);
			image.setLocation(LEFT_MARGIN,TOP_MARGIN+24+IMAGE_MARGIN);
			add(image);
		}
		
		if (getElementAt(LEFT_MARGIN,TOP_MARGIN+48+IMAGE_MARGIN+IMAGE_WIDTH+STATUS_MARGIN) != null) {
			remove(getElementAt(LEFT_MARGIN,TOP_MARGIN+48+IMAGE_MARGIN+IMAGE_WIDTH+STATUS_MARGIN));
		}
		if (profile.getStatus() != "") {
			GLabel status = new GLabel(profile.getName()+" is "+profile.getStatus(),LEFT_MARGIN,TOP_MARGIN+48+IMAGE_MARGIN+IMAGE_WIDTH+STATUS_MARGIN);
			status.setFont(PROFILE_STATUS_FONT);
			add(status);
		}else {
			GLabel status = new GLabel("No current status",LEFT_MARGIN,TOP_MARGIN+48+IMAGE_MARGIN+IMAGE_WIDTH+STATUS_MARGIN);
			status.setFont(PROFILE_STATUS_FONT);
			add(status);
		}
		
		GLabel frnd = new GLabel("Friends:",getWidth()/2,TOP_MARGIN+36+IMAGE_MARGIN);
		frnd.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(frnd);
		
		for(Double i = TOP_MARGIN+36+IMAGE_MARGIN+(frnd.getHeight()); i < TOP_MARGIN+48+IMAGE_MARGIN+IMAGE_WIDTH+STATUS_MARGIN; i++) {
			if (getElementAt(getWidth()/2,i) != null) {
				remove(getElementAt(getWidth()/2,i));
			}
		}
		
		for (int i = 0; i < profile.returnFrndList().size(); i++) {
			GLabel frndName = new GLabel(profile.returnFrndList().get(i).toString(),getWidth()/2,TOP_MARGIN+36+IMAGE_MARGIN+(frnd.getHeight())*(i+1));
			frndName.setFont(PROFILE_FRIEND_FONT);
			add(frndName);
		}
		}
	}

	
}
