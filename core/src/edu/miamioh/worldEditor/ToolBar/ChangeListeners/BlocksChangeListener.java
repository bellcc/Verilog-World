
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorRenderer;
import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractChangeListener;

public class BlocksChangeListener extends AbstractChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blocks Change Listener");
		
		//Set the current selection option to view the blocks sub menu.
		WorldEditorRenderer.getWorldRenderer().setHomeActor(false);
		WorldEditorRenderer.getWorldRenderer().setBlocksActor(true);
		WorldEditorRenderer.getWorldRenderer().setTilesActor(false);
		
		//Update the input multiplexer so that only the tool bar stage, 
		//the blocks sub menu stage, and the input process processors 
		//are attached to the input multiplexer.
		WorldEditorController.getCurrentWorldController().getMultiplexer().updateMultiplexer();
	
		//TODO Figure out what this does.
		WorldEditorRenderer.getWorldRenderer().resetSelectedItems();
		//Set the states of the tiles to false so that they are no longer selected.
		//WorldEditorRenderer.getWorldRenderer().resetStates();

	}
	
}
