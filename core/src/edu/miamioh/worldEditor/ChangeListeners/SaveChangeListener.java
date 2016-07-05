package edu.miamioh.worldEditor.ChangeListeners;

import java.io.File;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Configuration.ConfigurationParser;
import edu.miamioh.Level.Level;
import edu.miamioh.worldEditor.WorldEditorController;

public class SaveChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {

		System.out.println("Save Change Listener");
		
		File file = WorldEditorController.getCurrentController().getCurrentLevel().getProject();
		Level level = WorldEditorController.getCurrentController().getCurrentLevel();
		
		File tempFile = new File(file.getPath() + "/modules/");
		tempFile.mkdirs();
		
		ConfigurationParser parser = new ConfigurationParser();
		parser.createWorld(level, new File(file.getPath() + "/world.xml"));

		
	}

}
