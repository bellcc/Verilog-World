package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.BlockSelectionType;
import edu.miamioh.worldEditor.WorldEditorController;

public class WallBlockChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Wall Block Change Listener");
		
		BlockSelectionType selection = WorldEditorController.getCurrentController().getBlockSelection();
		
		if(selection == BlockSelectionType.Block_Wall) {
			selection = BlockSelectionType.NONE;
		}else {
			selection = BlockSelectionType.Block_Wall;
		}
		
		WorldEditorController.getCurrentController().setBlockSelection(selection);
	}

	
}
