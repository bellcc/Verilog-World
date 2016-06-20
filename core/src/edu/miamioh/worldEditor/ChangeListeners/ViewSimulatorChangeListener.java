package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorld;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class ViewSimulatorChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("View Simulator Change Listener");
		
		VerilogWorldMain.getVerilogWorldMain().setVerilogWorldScreen(VerilogWorld.WORLD_SIMULATOR);
		VerilogWorldMain.getVerilogWorldMain().updateScreen();
	}

}
