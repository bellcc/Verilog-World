
/**
 * @author Clark Bell
 * @date   7-11-2016
 * @info   The next change listener that takes the user from 
 *         the world configuration screen to the world editor screen.
 */

package edu.miamioh.worldConfiguration;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class NextChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {

		System.out.println("Next Change Listener");

		Level level = ConfigurationScreen.getCurrentScreen().getLevel();
		
		VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(level);
		
	}

}
