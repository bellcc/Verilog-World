
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Actors.TileChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldEditorRenderer;

public class BlankTileChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blank Tile Change Listener");
		
		boolean blankTileState = WorldEditorRenderer.getWorldRenderer().getBlankTileState();
		
		if(blankTileState) {
			
			WorldEditorRenderer.getWorldRenderer().setBlankTileState(false);
			return;	
		}
		
		WorldEditorRenderer.getWorldRenderer().setBlankTileState(true);
		
	}
	
}
