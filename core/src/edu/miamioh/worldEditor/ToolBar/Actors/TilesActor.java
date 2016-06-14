
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info
 */

package edu.miamioh.worldEditor.ToolBar.Actors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.ChangeListeners.TilesChangeListener;

public class TilesActor extends AbstractToolBarActor {

	/**
	 * The constructor call creates the text button. 
	 */
	public TilesActor() {
		
		buttonText = "TILES";
		color = new Color(Color.PURPLE);
		
		create();
		addChangeListener();
	}

	public void addChangeListener() {
		
		button.addListener(new TilesChangeListener());
	}
	
}
