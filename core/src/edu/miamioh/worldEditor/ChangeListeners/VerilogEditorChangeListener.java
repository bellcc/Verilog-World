package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;

public class VerilogEditorChangeListener extends ChangeListener {
	
	private RootModuleSimulator sim;

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Verilog Editor Change Listener");
		
		this.sim = VerilogWorldController.getController().getSim().getRootModuleSimulator();
		
		int row = WorldEditorController.getCurrentController().getSelectedRow();
		int column = WorldEditorController.getCurrentController().getSelectedColumn();
		
		Block targetBlock = WorldEditorController.getCurrentController().getCurrentLevel().getBlock(row, column);
		
		if(targetBlock instanceof NormalBlock) {
			NormalBlock block = (NormalBlock)targetBlock;
			
			if(block.getModuleWrapper() == null) {
				block.compile();
			}
			
			sim.updateTargetBlock(block);
			VerilogWorldMain.getVerilogWorldMain().launchVerilogEditor(block.getSourceFile());
		}
		
	}

}
