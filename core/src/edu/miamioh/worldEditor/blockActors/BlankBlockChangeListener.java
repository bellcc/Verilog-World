
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.worldEditor.blockActors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.WorldRenderer;

public class BlankBlockChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
	
		boolean blankBlockState = WorldRenderer.getWorldRenderer().getBlankBlockState();
		
		if(blankBlockState) {
			
			WorldRenderer.getWorldRenderer().setBlankBlockState(false);
			return;	
		}
		
		WorldRenderer.getWorldRenderer().setBlankBlockState(true);
				
	}
	
}
