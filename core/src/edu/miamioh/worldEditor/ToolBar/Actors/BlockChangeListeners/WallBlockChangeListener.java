
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.miamioh.worldEditor.SelectionType;
import edu.miamioh.worldEditor.WorldEditorRenderer;
import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractChangeListener;

public class WallBlockChangeListener extends AbstractChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Wall Block Change Listener");
	
		WorldEditorRenderer.getWorldRenderer().setSelectionType(SelectionType.Block_Wall);
	}
	
}
