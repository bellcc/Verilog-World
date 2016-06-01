package edu.miamioh.worldEditor.blockActors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BlankBlockChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Blank Block Change Listener");				
	}
	
}
