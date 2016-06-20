package edu.miamioh.worldSimulator.ChangeListeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorld;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class BackChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Back Change Listener");

		VerilogWorldMain.getVerilogWorldMain().setVerilogWorldScreen(VerilogWorld.WORLD_EDITOR);
		VerilogWorldMain.getVerilogWorldMain().updateScreen();
	}

}
