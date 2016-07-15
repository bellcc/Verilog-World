package edu.miamioh.worldSimulator.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldSimulator.ToolBarSelection;
import edu.miamioh.worldSimulator.WorldSimulatorController;

public class HomeChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		ToolBarSelection selection = WorldSimulatorController.getController().getSelection();
		
		if(selection == ToolBarSelection.HOME) {
			selection = ToolBarSelection.NONE;
		}else {
			selection = ToolBarSelection.HOME;
		}
		
		WorldSimulatorController.getController().setSelection(selection);
		WorldSimulatorController.getController().updateInputMultiplexer();
		
	}

}
