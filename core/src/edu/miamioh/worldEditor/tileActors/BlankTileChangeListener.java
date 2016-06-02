package edu.miamioh.worldEditor.tileActors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldRenderer;

public class BlankTileChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blank Tile Change Listener");
				
		boolean blankTile = WorldRenderer.getWorldRenderer().getBlankTile();
		
		if(blankTile) {
			
			WorldRenderer.getWorldRenderer().setBlankTile(false);
			return;	
		}
		
		WorldRenderer.getWorldRenderer().setBlankTile(true);
				
	}
	
}
