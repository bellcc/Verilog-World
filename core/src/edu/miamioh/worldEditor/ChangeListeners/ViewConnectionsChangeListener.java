package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldEditorScreen;

public class ViewConnectionsChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		
		boolean flag = WorldEditorScreen.getScreen().getBlockConnection();
		WorldEditorScreen.getScreen().setBlockConnection(!flag);
		
		
	}

}
