package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.ToolBarSelection;
import edu.miamioh.worldEditor.WorldEditorController;

public class SimulatorChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Simulator Change Listener");
		
		ToolBarSelection selection = WorldEditorController.getCurrentController().getToolBarSelection();
		
		if(selection == ToolBarSelection.SIMULATOR) {
			selection = ToolBarSelection.NONE;
		}else {
			selection = ToolBarSelection.SIMULATOR;
		}
		
		WorldEditorController.getCurrentController().setToolBarSelection(selection);
		
		WorldEditorController.getCurrentController().updateInputMultiplexer();

	}

}
