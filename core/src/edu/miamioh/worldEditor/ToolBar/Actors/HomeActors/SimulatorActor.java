package edu.miamioh.worldEditor.ToolBar.Actors.HomeActors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.Actors.HomeChangeListeners.SimulatorChangeListener;

public class SimulatorActor extends AbstractToolBarActor{

	public SimulatorActor() {
		
		buttonText = "Simulator";
		color = new Color(Color.ORANGE);
		
		create();
		addChangeListener();
	}
	
	@Override
	public void addChangeListener() {

		button.addListener(new SimulatorChangeListener());
	}

}
