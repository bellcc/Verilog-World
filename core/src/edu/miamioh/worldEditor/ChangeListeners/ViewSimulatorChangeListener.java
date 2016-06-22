package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.WorldSimulatorController;

public class ViewSimulatorChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("View Simulator Change Listener");

		WorldEditorController.getCurrentController().resetMultiplexer();
		
		Level currentLevel = WorldEditorController.getCurrentController().getCurrentLevel();
		WorldSimulatorController.getController().setCurrentLevel(currentLevel);
		
		VerilogWorldMain.getVerilogWorldMain().setWorldSimulatorScreen();

	}

}
