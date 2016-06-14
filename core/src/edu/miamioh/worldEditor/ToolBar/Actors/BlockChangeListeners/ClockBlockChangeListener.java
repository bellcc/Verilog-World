
/**
 * @author Chris Bell
 * @date   6-13-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.miamioh.worldEditor.WorldEditorRenderer;
import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractChangeListener;

public class ClockBlockChangeListener extends AbstractChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Clock Block Change Listener");
		
		boolean clockBlockState = WorldEditorRenderer.getWorldRenderer().getClockBlockState();
		
		if(clockBlockState) {
			
			WorldEditorRenderer.getWorldRenderer().setClockBlockState(false);
			return;
		}

		WorldEditorRenderer.getWorldRenderer().setClockBlockState(true);
	}
	
}
