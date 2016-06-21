package edu.miamioh.worldSimulator.OptionStage.Actors.ChangeListeners;

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
		
		for(Block block : level.getBlockList()) {
			if (block instanceof NormalBlock) {
				NormalBlock normBlock = (NormalBlock)block;
				
				ModuleWrapper module = normBlock.compile();
				worldController.getSim().addModule(module);
			}
		}
	}

}
