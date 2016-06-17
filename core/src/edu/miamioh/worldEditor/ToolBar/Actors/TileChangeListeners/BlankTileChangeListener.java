
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Actors.TileChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.worldEditor.SelectionType;

public class BlankTileChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blank Tile Change Listener");
		
		//WorldEditorRenderer.getWorldRenderer().setSelectionType(SelectionType.Tile_Blank);
	}
	
}
