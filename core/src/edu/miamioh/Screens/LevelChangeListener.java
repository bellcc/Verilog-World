/**
 * @author Zachary McQuigg
 * @date   06-16-2016
 * @info   
 */

package edu.miamioh.Screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class LevelChangeListener extends ChangeListener {
	private int index;
	
	LevelChangeListener(int index) {
		this.index = index;
	}
	
	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
    	
		ChallengesScreen.listen(index);
	}
	
}
