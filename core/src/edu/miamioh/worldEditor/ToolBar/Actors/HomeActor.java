
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   This class is responsible for the home button actor. 
 *         This button will take the user back to a main menu of 
 *         their choosing based on a selection to another window 
 *         that the application prompts the user with.
 */

package edu.miamioh.worldEditor.ToolBar.Actors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.ChangeListeners.HomeChangeListener;

public class HomeActor extends AbstractToolBarActor {

	public HomeActor() {
		
		buttonText = "HOME";
		color = new Color(Color.GREEN);
		
		create();
		addChangeListener();
	}

	public void addChangeListener() {
		
		button.addListener(new HomeChangeListener());
	}
}
