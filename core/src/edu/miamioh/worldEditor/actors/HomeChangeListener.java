package edu.miamioh.worldEditor.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldController;
import edu.miamioh.worldEditor.WorldRenderer;

public class HomeChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
				
		WorldRenderer.getWorldRenderer().setHomeActor(true);
		WorldRenderer.getWorldRenderer().setBlocksActor(false);
		WorldRenderer.getWorldRenderer().setTilesActor(false);
		
		WorldController.getCurrentWorldController().updateInputMultiplexer();
	
		WorldRenderer.getWorldRenderer().resetSelectedItems();
		WorldRenderer.getWorldRenderer().resetStates();

	}

}
