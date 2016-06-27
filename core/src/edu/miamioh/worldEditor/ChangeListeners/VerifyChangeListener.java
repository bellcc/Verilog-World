package edu.miamioh.worldEditor.ChangeListeners;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.ModuleWrapper;

public class VerifyChangeListener extends ChangeListener{
	
	private VerilogWorldController worldController;
	private Level level;
	
	public VerifyChangeListener() {
		worldController = VerilogWorldController.getController();
		level = worldController.getLevel();
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Verify Change Listener");
		
		worldController.getSim().setBlockList(level.getBlockList());
		worldController.getSim().updateModules();
	}

}
