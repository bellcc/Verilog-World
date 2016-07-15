
package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.ToolBarSelectionType;
import edu.miamioh.worldEditor.WorldEditorController;

public class RemoveChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Remove Change Listener");
		
		int row = WorldEditorController.getCurrentController().getSelectedRow();
		int column = WorldEditorController.getCurrentController().getSelectedColumn();
		
		WorldEditorController.getCurrentController().getCurrentLevel().removeBlock(row, column);
		WorldEditorController.getCurrentController().setToolBarSelection(ToolBarSelectionType.NONE);
	}

}
