package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class VerifyChangeListener extends ChangeListener{
	
	private VerilogWorldController worldController;
	
	public VerifyChangeListener() {
		worldController = VerilogWorldController.getController();
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Verify Change Listener");
		
		worldController.getSim().updateModules();
	}

}
