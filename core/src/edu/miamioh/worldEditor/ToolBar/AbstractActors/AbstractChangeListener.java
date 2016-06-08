
/**
 * @author Chris Bell
 * @date   6-7-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.AbstractActors;

import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractChangeListener extends ChangeListener{

	public abstract void changed(ChangeEvent event, Actor actor);
	
}
