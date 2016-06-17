package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BlockChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		
		System.out.println("Block Change Listener");
	}
}
