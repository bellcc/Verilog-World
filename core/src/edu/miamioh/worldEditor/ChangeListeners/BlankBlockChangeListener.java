package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.BlockSelectionType;
import edu.miamioh.worldEditor.WorldEditorController;

public class BlankBlockChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
				
		WorldEditorController.getCurrentController().toggleBlockSelection(BlockSelectionType.Block_Blank);
	}
	
}
