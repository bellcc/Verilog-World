
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.AbstractActors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public abstract class AbstractToolBarActor {

	public TextButton button;
	public String buttonText;
	public Color color;

	public abstract void create();
	public abstract TextButton getButtonActor();
	
}
