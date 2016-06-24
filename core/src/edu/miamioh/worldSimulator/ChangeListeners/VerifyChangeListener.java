package edu.miamioh.worldSimulator.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorldController;

public class VerifyChangeListener  extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Verify Change Listener");
		
		VerilogWorldController.getController().getSim().updateModules();
		
		/*
		 * Testing purposes
		 */
		VerilogWorldController.getController().getSim().addTestPorts();
	}
}
