
/**
 * @author Chris Bell
 * @date   05-31-2016
 * @info   The generic block object.
 */

package edu.miamioh.worldEditor.ToolBar.Actors.BlockActors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners.WallBlockChangeListener;

public class WallBlockActor extends AbstractToolBarActor{

	public WallBlockActor() {
		
		buttonText = "Wall\nBlock";
		color = new Color(Color.GRAY);
		
		create();
		addChangeListener();
	}

	public void addChangeListener() {
		
		button.addListener(new WallBlockChangeListener());
	}
}