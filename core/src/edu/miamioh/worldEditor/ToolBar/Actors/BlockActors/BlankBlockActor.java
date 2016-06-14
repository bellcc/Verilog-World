
/**
 * @author Chris Bell
 * @date   05-31-2016
 * @info   The generic block object.
 */

package edu.miamioh.worldEditor.ToolBar.Actors.BlockActors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners.BlankBlockChangeListener;

public class BlankBlockActor extends AbstractToolBarActor{

	public BlankBlockActor() {
		
		buttonText = "Blank\nBlock";
		color = new Color(Color.RED);
		
		create();
		addChangeListener();
	}

	public void addChangeListener() {
		
		button.addListener(new BlankBlockChangeListener());
	}
}
