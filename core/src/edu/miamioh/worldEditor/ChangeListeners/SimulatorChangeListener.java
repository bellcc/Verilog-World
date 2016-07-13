package edu.miamioh.worldEditor.ChangeListeners;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;

public class SimulatorChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("Simulator Change Listener");
				
		Level currentLevel = WorldEditorController.getCurrentController().getCurrentLevel();
		
		if(currentLevel.getConfig().getWorldWidth() == 0 || currentLevel.getConfig().getWorldHeight() == 0) {
			
			JOptionPane.showMessageDialog(new JFrame(),
				    "You must create a world before you can use the simulator.",
				    null, JOptionPane.ERROR_MESSAGE);
			return;
			
		}
		
		WorldEditorController.getCurrentController().resetMultiplexer();
		VerilogWorldController.getController().setLevel(currentLevel);
		VerilogWorldMain.getVerilogWorldMain().setWorldSimulatorScreen(currentLevel);
	}

}
