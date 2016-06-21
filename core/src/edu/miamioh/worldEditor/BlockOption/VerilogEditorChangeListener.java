package edu.miamioh.worldEditor.BlockOption;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorRenderer;
import edu.miamioh.worldSimulator.WorldSimulator;

public class VerilogEditorChangeListener extends ChangeListener{
	
	RootModuleSimulator sim;
	
	public VerilogEditorChangeListener(WorldSimulator sim) {
		this.sim = sim.getRootModuleSimulator();
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Verilog Editor Change Listener");
		
		Block targetBlock = WorldEditorRenderer.getWorldRenderer().getSelectedBlock();
		
		if (targetBlock instanceof NormalBlock) {
			NormalBlock block = (NormalBlock)targetBlock;
			
			if (block.getModuleWrapper() == null) {
				block.compile();
			}
			
			sim.updateTargetBlock(block);
			VerilogWorldMain.getVerilogWorld().launchVerilogEditor(block.getSourceFile());
		}
		
		/*
		 * Not used
		 */
		//WorldEditorRenderer.getWorldRenderer().setBlockOption(false);
	}

}
