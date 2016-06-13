
/**
 * @author Chris Bell
 * @date   6-13-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.miamioh.worldEditor.WorldEditorRenderer;
import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractChangeListener;

public class ResetBlockChangeListener  extends AbstractChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Reset Block Change Listener");
		
		boolean resetBlockState = WorldEditorRenderer.getWorldRenderer().getResetBlockState();
		
		if(resetBlockState) {
			
			WorldEditorRenderer.getWorldRenderer().setResetBlockState(false);
			return;
		}
		
		WorldEditorRenderer.getWorldRenderer().setResetBlockState(true);
		
	}
}
