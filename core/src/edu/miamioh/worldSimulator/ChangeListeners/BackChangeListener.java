package edu.miamioh.worldSimulator.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldSimulator.WorldSimulatorController;

public class BackChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Back Change Listener");
		
		WorldSimulatorController.getController().resetMultiplexer();
		//VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen();
		
		//VerilogWorldMain.getVerilogWorldMain()actor;
	}

}
