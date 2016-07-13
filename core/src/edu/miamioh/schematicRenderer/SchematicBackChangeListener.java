package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;

public class SchematicBackChangeListener extends ChangeListener {
	
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
		System.out.println("Back Change Listener");
		
		WorldEditorController.getCurrentController().updateInputMultiplexer();;
		
		Level level = VerilogWorldController.getController().getCurrentLevel();
		VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(level);
		
	}
}
