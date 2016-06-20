package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorld;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.WorldSimulatorController;

public class ViewSimulatorChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("View Simulator Change Listener");
		
		WorldEditorController.getCurrentController().resetMultiplexer();

		WorldSimulatorController.getController().setCurrentLevel(WorldEditorController.getCurrentController().getCurrentLevel());
		
		VerilogWorldMain.getVerilogWorldMain().setVerilogWorldScreen(VerilogWorld.WORLD_SIMULATOR);
		VerilogWorldMain.getVerilogWorldMain().updateScreen();
	}

}
