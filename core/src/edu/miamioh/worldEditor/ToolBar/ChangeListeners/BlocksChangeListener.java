
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.miamioh.worldEditor.ToolBarSelection;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractChangeListener;

public class BlocksChangeListener extends AbstractChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blocks Change Listener");
		
		//Set the current selection option to view the blocks sub menu.
		//WorldEditorRenderer.getWorldRenderer().setHomeActor(false);
		//WorldEditorRenderer.getWorldRenderer().setBlocksActor(true);
		//WorldEditorRenderer.getWorldRenderer().setTilesActor(false);
		
		ToolBarSelection selection = WorldEditorController.getCurrentController().getToolBarSelection();
		
		switch(selection) {
		
			case BLOCK:
				selection = ToolBarSelection.NONE;
				break;
				
			case HOME:
				selection = ToolBarSelection.BLOCK;
				break;
				
			case TILE:
				selection = ToolBarSelection.BLOCK;
				break;

			case NONE:
				selection = ToolBarSelection.BLOCK;
				break;
				
			default:
				break;
		
		}
		
		WorldEditorController.getCurrentController().setToolBarSelection(selection);
		
		//Update the input multiplexer so that only the tool bar stage, 
		//the blocks sub menu stage, and the input process processors 
		//are attached to the input multiplexer.
		WorldEditorController.getCurrentController().getMultiplexer().updateMultiplexer();
	}
	
}
