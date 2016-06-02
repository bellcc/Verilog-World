
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.worldEditor.tileActors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldRenderer;

public class BlankTileChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blank Tile Change Listener");

		boolean blankTileState = WorldRenderer.getWorldRenderer().getBlankTileState();
		
		if(blankTileState) {
			
			WorldRenderer.getWorldRenderer().setBlankTileState(false);
			return;	
		}
		
		WorldRenderer.getWorldRenderer().setBlankTileState(true);
				
	}
	
}
