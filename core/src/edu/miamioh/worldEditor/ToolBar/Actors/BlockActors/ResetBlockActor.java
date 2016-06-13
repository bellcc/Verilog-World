package edu.miamioh.worldEditor.ToolBar.Actors.BlockActors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners.ResetBlockChangeListener;

public class ResetBlockActor extends AbstractToolBarActor{
	
	public ResetBlockActor() {
		
		buttonText = "Reset\nBlock";
		color = new Color(Color.ORANGE);
		
		create();
		addChangeListener();
	}

	public void addChangeListener() {
		
		button.addListener(new ResetBlockChangeListener());
	}
}
