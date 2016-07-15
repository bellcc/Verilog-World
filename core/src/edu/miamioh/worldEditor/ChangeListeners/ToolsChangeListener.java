package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.ToolBarSelectionType;
import edu.miamioh.worldEditor.WorldEditorController;

public class ToolsChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
				
		ToolBarSelectionType selection = WorldEditorController.getCurrentController().getToolBarSelection();
		
		if(selection == ToolBarSelectionType.TOOLS) {
			selection = ToolBarSelectionType.NONE;
		}else {
			selection = ToolBarSelectionType.TOOLS;
		}
		
		WorldEditorController.getCurrentController().setToolBarSelection(selection);
		
		WorldEditorController.getCurrentController().updateInputMultiplexer();
	}

}
