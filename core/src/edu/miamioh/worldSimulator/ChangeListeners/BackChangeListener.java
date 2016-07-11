package edu.miamioh.worldSimulator.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.WorldSimulatorController;

public class BackChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Back Change Listener");

		WorldSimulatorController.getController().resetMultiplexer();
		VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(WorldEditorController.getCurrentController().getCurrentLevel());
		
		Level level = VerilogWorldController.getController().getCurrentLevel();
		VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(level);
		
	}

}
