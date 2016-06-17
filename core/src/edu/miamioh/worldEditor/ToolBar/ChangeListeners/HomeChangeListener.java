
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

public class HomeChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Home Change Listener");
		
		//Set the current selection option to view the home sub menu.
		//WorldEditorRenderer.getWorldRenderer().setHomeActor(true);
		//WorldEditorRenderer.getWorldRenderer().setBlocksActor(false);
		//WorldEditorRenderer.getWorldRenderer().setTilesActor(false);
		
		ToolBarSelection selection = WorldEditorController.getCurrentWorldController().getToolBarSelection();
		
		switch(selection) {
		
			case HOME:
				selection = ToolBarSelection.NONE;
				break;
			
			case BLOCK:
				selection = ToolBarSelection.HOME;
				break;
				
			case TILE:
				selection = ToolBarSelection.HOME;
				break;
	
			case NONE:
				selection = ToolBarSelection.HOME;
				break;
				
			default:
				break;
	
		}
		
		WorldEditorController.getCurrentWorldController().setToolBarSelection(selection);
		
		//Update the input multiplexer so that only the tool bar stage, 
		//the home sub menu stage, and the input process processors 
		//are attached to the input multiplexer.
		WorldEditorController.getCurrentWorldController().getMultiplexer().updateMultiplexer();
	}

}
