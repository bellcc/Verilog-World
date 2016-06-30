package edu.miamioh.worldSimulator.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorldController;

public class StartChangeListener  extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		VerilogWorldController.getController().getSim().setShouldRun(true);
	}
	
}
