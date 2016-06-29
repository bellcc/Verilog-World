
/**
 * @author Clark Bell
 * @date   06-16-2016
 * @info   The change listener for the main menu button 
 *         of the home option in the tool bar stage.
 */

package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorldMain;

public class MainMenuChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Main Menu Change Listener");
		VerilogWorldMain.getVerilogWorldMain().setMainMenuScreen();

	}

}
