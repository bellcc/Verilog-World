
/**
 * @author Clark Bell
 * @date   06-16-2016
 * @info   The change listener for the editor menu button 
 *         of the home option in the tool bar stage.
 */

package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class EditorMenuChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Editor Menu Change Listener");
	}

}
