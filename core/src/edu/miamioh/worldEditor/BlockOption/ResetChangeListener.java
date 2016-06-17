package edu.miamioh.worldEditor.BlockOption;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ResetChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Reset Change Listener");
		
		/*
		 * Not used
		 */
		//WorldEditorRenderer.getWorldRenderer().setBlockOption(false);
	}

}
