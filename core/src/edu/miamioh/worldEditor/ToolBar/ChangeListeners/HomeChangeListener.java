
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorRenderer;

public class HomeChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Home Change Listener");
		
		//Set the current selection option to view the home sub menu.
		WorldEditorRenderer.getWorldRenderer().setHomeActor(true);
		WorldEditorRenderer.getWorldRenderer().setBlocksActor(false);
		WorldEditorRenderer.getWorldRenderer().setTilesActor(false);
		
		//Update the input multiplexer so that only the tool bar stage, 
		//the home sub menu stage, and the input process processors 
		//are attached to the input multiplexer.
		WorldEditorController.getCurrentWorldController().getMultiplexer().updateMultiplexer();
		
		//TODO Figure out what this does.
		WorldEditorRenderer.getWorldRenderer().resetSelectedItems();
		//WorldEditorRenderer.getWorldRenderer().resetStates();

	}

}
