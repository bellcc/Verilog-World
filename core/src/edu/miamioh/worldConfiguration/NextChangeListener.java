package edu.miamioh.worldConfiguration;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class NextChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {

		System.out.println("Next Change Listener");
		
		Configuration config = ConfigurationScreen.getCurrentScreen().getConfiguration();
		VerilogWorldController.getController().setDefaultConfig(config);
		VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen();
		
	}

}
