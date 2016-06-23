package edu.miamioh.worldEditor.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import edu.miamioh.schematicRenderer.SchematicRendererScreen;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class SchematicChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		System.out.println("Schmematic Change Listener");

//		SchematicRendererScreen srs = VerilogWorldMain.getVerilogWorldMain()
//				.getSchematicRendererScreen();
		VerilogWorldMain.getVerilogWorldMain().setSchematicRendererScreen();
	}

	
}
