package edu.miamioh.worldEditor.ChangeListeners;

import java.io.File;

import javax.swing.JFileChooser;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Configuration.ConfigurationParser;
import edu.miamioh.Level.Level;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorScreen;

public class ImportProjectChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {

		System.out.println("Import Project Change Listener");
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select Directory Location");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			
			System.out.println(chooser.getSelectedFile());
			
			ConfigurationParser parser = new ConfigurationParser();
			Level level = parser.getConfiguration(new File(chooser.getSelectedFile().getAbsolutePath() + "/world.xml"));
			
			WorldEditorController.getCurrentController().setCurrentLevel(level);
			WorldEditorController.getCurrentController().updateWorld(level);
			WorldEditorScreen.getScreen().updateWorldParameters();
						
		}
	}

}
