package edu.miamioh.worldEditor.ChangeListeners;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.miamioh.Configuration.ConfigurationParser;
import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorScreen;
import edu.miamioh.worldSimulator.WorldSimulator;

public class ImportProjectChangeListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		
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
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select Directory Location");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			
			Level level = new Level();
			level.setProject(new File(chooser.getSelectedFile().getAbsolutePath() + "/"));
			WorldEditorController.getCurrentController().setCurrentLevel(level);
			
			ConfigurationParser parser = new ConfigurationParser();
			level = parser.getConfiguration(new File(chooser.getSelectedFile().getAbsolutePath() + "/world.xml"));
			level.setProject(new File(chooser.getSelectedFile().getAbsolutePath() + "/"));
			
			String rootPath = level.getProject().getAbsolutePath();
			VerilogWorldController.getController().setRootPath(rootPath);
			
			WorldEditorController.getCurrentController().setCurrentLevel(level);
			WorldEditorController.getCurrentController().updateWorld(level);
			WorldEditorScreen.getScreen().updateWorldParameters();
						
			WorldSimulator sim = VerilogWorldController.getController().getSim();
			sim.setBlocks(level.getBlockList());
			sim.addDefaultPorts();
		}
	}

}
