
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.ToolBarSelection;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorRenderer;

public class TilesChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
	
		System.out.println("Tile Change Listener");
		
		//Set the current selection option to view the tiles sub menu.
		//WorldEditorRenderer.getWorldRenderer().setHomeActor(false);
		//WorldEditorRenderer.getWorldRenderer().setBlocksActor(false);
		//WorldEditorRenderer.getWorldRenderer().setTilesActor(true);
		
		ToolBarSelection selection = WorldEditorController.getCurrentWorldController().getToolBarSelection();
		
		switch(selection) {

			case TILE:
				selection = ToolBarSelection.NONE;
				break;
			
			case BLOCK:
				selection = ToolBarSelection.TILE;
				break;
				
			case HOME:
				selection = ToolBarSelection.TILE;
				break;
	
			case NONE:
				selection = ToolBarSelection.TILE;
				break;
				
			default:
				break;
	
		}
		
		WorldEditorController.getCurrentWorldController().setToolBarSelection(selection);
		
		//Update the input multiplexer so that only the tool bar stage, 
		//the tiles sub menu stage, and the input process processors 
		//are attached to the input multiplexer.
		WorldEditorController.getCurrentWorldController().getMultiplexer().updateMultiplexer();
	}
	
}
