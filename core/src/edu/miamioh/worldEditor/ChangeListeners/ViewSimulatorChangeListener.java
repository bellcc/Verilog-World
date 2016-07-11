package edu.miamioh.worldEditor.ChangeListeners;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;

public class ViewSimulatorChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		System.out.println("View Simulator Change Listener");
		
		boolean changes = WorldEditorController.getCurrentController().changesMade();
		
		if(changes && WorldEditorController.getCurrentController().getCurrentLevel().getBlockList().size() != 0) {
			System.out.println("You have unsaved changes");
						
			Object[] options = {"Yes", "No", "Cancel"};
			int n = JOptionPane.showOptionDialog(new JFrame(),
				    "You have unsaved work. Would you like to save it?",
				    "Unsaved Work",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[2]);
			
			if(n == 0) {
				WorldEditorController.getCurrentController().saveLevel();
			}else if(n == 2) {
				return;
			}
			
		}

		WorldEditorController.getCurrentController().resetMultiplexer();
		
		Level currentLevel = WorldEditorController.getCurrentController().getCurrentLevel();
		VerilogWorldController.getController().setLevel(currentLevel);
		
		Level level = WorldEditorController.getCurrentController().getCurrentLevel();
		VerilogWorldMain.getVerilogWorldMain().setWorldSimulatorScreen(level);

	}

}
