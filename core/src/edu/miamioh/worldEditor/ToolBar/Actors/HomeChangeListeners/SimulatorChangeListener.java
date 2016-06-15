package edu.miamioh.worldEditor.ToolBar.Actors.HomeChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorld;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class SimulatorChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Simulator Change Listener");

		VerilogWorldController.getController().setState(VerilogWorld.WORLD_SIMULATOR);
		VerilogWorldController.getController().updateInputMultiplexer();
	
	}

}
