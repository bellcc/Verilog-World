package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import edu.miamioh.schematicRenderer.SchematicRendererScreen;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class SchematicChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		SchematicRendererScreen srs = VerilogWorldMain.getVerilogWorldMain()
				.getSchematicRendererScreen();
		if(srs.schematic_is_Compiled()) {
			VerilogWorldMain.getVerilogWorldMain().setSchematicRendererScreen();
		} else {
			System.out.println("Please compile the verilog module for this block before " +
					"attempting to view its schematic design.");
		}
	}

	
}
