package edu.miamioh.worldSimulator.OptionStage.Actors.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldType;

public class BackChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Back Change Listener");
		
		VerilogWorldType state = VerilogWorldType.WORLD_EDITOR;
		VerilogWorldController.getController().setState(state);
		
		VerilogWorldController.getController().updateInputMultiplexer();
		
	}

}
