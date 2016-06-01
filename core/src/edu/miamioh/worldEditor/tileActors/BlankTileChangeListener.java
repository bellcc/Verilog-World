package edu.miamioh.worldEditor.tileActors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BlankTileChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blank Tiles Change Listener");
				
	}
	
}
