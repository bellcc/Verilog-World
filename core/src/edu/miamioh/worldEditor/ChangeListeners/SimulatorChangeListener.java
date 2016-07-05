package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.ToolBarSelectionType;
import edu.miamioh.worldEditor.WorldEditorController;

public class SimulatorChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Simulator Change Listener");
		
		WorldEditorController.getCurrentController().resetMultiplexer();
		
		Level currentLevel = WorldEditorController.getCurrentController().getCurrentLevel();
		VerilogWorldController.getController().setLevel(currentLevel);
		
		VerilogWorldMain.getVerilogWorldMain().setWorldSimulatorScreen();
		
		VerilogWorldMain.getVerilogWorldMain().getErrorWindow().display();
	}

}
