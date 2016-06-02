package edu.miamioh.worldEditor.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldController;
import edu.miamioh.worldEditor.WorldRenderer;

public class TilesChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
			
		System.out.println("New Tiles Change Listener");
		WorldRenderer.getWorldRenderer().setHomeActor(false);
		WorldRenderer.getWorldRenderer().setBlocksActor(false);
		WorldRenderer.getWorldRenderer().setTilesActor(true);
	
		WorldController.getCurrentWorldController().updateInputMultiplexer();
		
	}
	
}
