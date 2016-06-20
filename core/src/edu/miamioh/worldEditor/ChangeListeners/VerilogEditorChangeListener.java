package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;

public class VerilogEditorChangeListener extends ChangeListener {
	
	/**
	RootModuleSimulator sim;
	 
	public VerilogEditorChangeListener(WorldSimulator sim) {
		this.sim = sim.getRootModuleSimulator();
	}
	*/

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Verilog Editor Change Listener");
		
		int row = WorldEditorController.getCurrentController().getSelectedRow();
		int column = WorldEditorController.getCurrentController().getSelectedColumn();
		
		Block targetBlock = WorldEditorController.getCurrentController().getCurrentLevel().getBlock(row, column);
		
		/**
		if(targetBlock instanceof NormalBlock) {
			NormalBlock block = (NormalBlock)targetBlock;
			
			if(block.getModuleWrapper() == null) {
				block.compile();
			}
			
			sim.updateTargetBlock(block);
			VerilogWorldMain.getVerilogWorldMain().launchVerilogEditor(block.getSourceFile());
		}
		*/
	}

}
