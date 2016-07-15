
/**
 * @author Clark Bell
 * @date   7-11-2016
 * @info   The back change listener for the world configuration 
 *         screen that takes you back to the play screen.
 */

package edu.miamioh.worldConfiguration;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class BackChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {

		System.out.println("Back Change Listener");
				
		Level level = VerilogWorldController.getController().getLevel();
		VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(level);
	}

}
