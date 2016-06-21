package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.BlockSelectionType;
import edu.miamioh.worldEditor.WorldEditorController;

public class ResetBlockChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		
		System.out.println("Reset Block Change Listener");
		
//		BlockSelectionType selection = WorldEditorController.getCurrentController().getBlockSelection();
//		
//		if(selection == BlockSelectionType.Block_Reset) {
//			selection = BlockSelectionType.NONE;
//		}else {
//			selection = BlockSelectionType.Block_Reset;
//		}
//		
//		WorldEditorController.getCurrentController().setBlockSelection(selection);
	}
	
}
