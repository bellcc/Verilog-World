
/**
 * @author Clark Bell
 * @date   06-16-2016
 * @info   The change listener for the main menu button 
 *         of the home option in the tool bar stage.
 */

package edu.miamioh.worldEditor.ChangeListeners;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;

public class MainMenuChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) { 
				
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

		VerilogWorldMain.getVerilogWorldMain().setMainMenuScreen();

	}

}
