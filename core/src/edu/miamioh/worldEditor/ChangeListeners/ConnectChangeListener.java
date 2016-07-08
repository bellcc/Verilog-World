package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldEditorScreen;

public class ConnectChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {

		System.out.println("Connect Change Listener");
		
		boolean connectMode = WorldEditorScreen.getScreen().getConnectMode();
		WorldEditorScreen.getScreen().setConnectMode(!connectMode);
	}

}