package edu.miamioh.worldEditor.ToolBar.Actors.TileActors;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.worldEditor.ToolBar.AbstractActors.AbstractToolBarActor;
import edu.miamioh.worldEditor.ToolBar.Actors.TileChangeListeners.BlankTileChangeListener;

public class BlankTileActor extends AbstractToolBarActor{

	public BlankTileActor() {
		
		buttonText = "Blank\nTile";
		color = new Color(Color.ORANGE);
		
		create();
		addChangeListener();
	}

	public void addChangeListener() {
		button.addListener(new BlankTileChangeListener());
	}
}
