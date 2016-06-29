package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.ToolBarSelection;
import edu.miamioh.worldEditor.WorldEditorController;

public class BlockChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		
		System.out.println("Block Change Listener");
		
		ToolBarSelection selection = WorldEditorController.getCurrentController().getToolBarSelection();
		
		if(selection == ToolBarSelection.BLOCK) {
			selection = ToolBarSelection.NONE;
		}else {
			selection = ToolBarSelection.BLOCK;
		}
		
		WorldEditorController.getCurrentController().setToolBarSelection(selection);
		
		WorldEditorController.getCurrentController().updateInputMultiplexer();

	}
	
}
