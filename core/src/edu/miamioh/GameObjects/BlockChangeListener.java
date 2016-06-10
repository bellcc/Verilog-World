
/**
 * @author Chris Bell
 * @date   6-9-2016
 * @info   
 */

package edu.miamioh.GameObjects;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BlockChangeListener extends ChangeListener {

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			
			System.out.println("World Block Change Listener: " + actor.getName());
			
			//TODO The actors name is a unique identifier which will indicate
			String blockID = actor.getName();
		}
		
	}
