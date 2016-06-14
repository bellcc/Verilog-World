package edu.miamioh.worldEditor.ToolBar.Actors.BlockActors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.Actors.BlockChangeListeners.ClockBlockChangeListener;

public class ClockBlockActor extends AbstractToolBarActor{

	public ClockBlockActor() {
		
		buttonText = "Clock\nBlock";
		color = new Color(Color.YELLOW);
		
		create();
		addChangeListener();
	}

	public void addChangeListener() {
		button.addListener(new ClockBlockChangeListener());
	}
	
}
