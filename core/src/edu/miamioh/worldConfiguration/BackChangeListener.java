package edu.miamioh.worldConfiguration;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorldMain;

public class BackChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {

		System.out.println("Back Change Listener");
		
		VerilogWorldMain.getVerilogWorldMain().setPlayScreen();
	}

}
