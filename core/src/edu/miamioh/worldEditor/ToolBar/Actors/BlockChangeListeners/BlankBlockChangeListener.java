
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.miamioh.worldEditor.WorldEditorRenderer;
import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractChangeListener;

public class BlankBlockChangeListener extends AbstractChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blank Block Change Listener");
	
		boolean blankBlockState = WorldEditorRenderer.getWorldRenderer().getBlankBlockState();
		
		if(blankBlockState) {
			
			WorldEditorRenderer.getWorldRenderer().setBlankBlockState(false);
			return;	
		}
		
		WorldEditorRenderer.getWorldRenderer().setBlankBlockState(true);
				
	}
	
}
