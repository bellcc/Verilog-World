package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.BlockSelectionType;
import edu.miamioh.worldEditor.WorldEditorController;

public class ControllerBlockChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Controller Block Change Listener");
		
		WorldEditorController.getCurrentController().toggleBlockSelection(BlockSelectionType.Block_Controller);
	}

	
}
