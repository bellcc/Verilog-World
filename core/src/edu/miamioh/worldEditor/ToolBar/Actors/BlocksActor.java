
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info
 */

package edu.miamioh.worldEditor.ToolBar.Actors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.ChangeListeners.BlocksChangeListener;

public class BlocksActor extends AbstractToolBarActor{

	public BlocksActor() {

		buttonText = "BLOCK";
		color = new Color(Color.NAVY);
		
		create();
		addChangeListener();
	}

	public void addChangeListener() {
		
		button.addListener(new BlocksChangeListener());
	}
	
}
