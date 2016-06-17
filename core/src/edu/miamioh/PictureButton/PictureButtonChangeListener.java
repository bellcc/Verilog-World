package edu.miamioh.PictureButton;

import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractChangeListener;

public class PictureButtonChangeListener extends AbstractChangeListener{
		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			
			System.out.println("Picture Button Change Listener");

			WorldEditorController.getCurrentWorldController().getMultiplexer().updateMultiplexer();
		


		}
		
}
