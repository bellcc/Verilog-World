package edu.miamioh.PictureButton;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PictureButtonChangeListener extends ChangeListener{
		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			
			System.out.println("Picture Button Change Listener");		
		}
		
}
