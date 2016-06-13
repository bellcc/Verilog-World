package edu.miamioh.worldEditor.BlockOption;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorRenderer;

public class VerilogEditorChangeListener extends ChangeListener{

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Verilog Editor Change Listener");
		
		VerilogWorldMain.getVerilogWorld().launchVerilogEditor("test.v");
		
		WorldEditorRenderer.getWorldRenderer().setBlockOption(false);
	}

}
