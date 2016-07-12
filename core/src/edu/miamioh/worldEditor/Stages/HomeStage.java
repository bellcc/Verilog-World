
/**
 * @author Clark Bell
 * @date   06-17-2016
 * @info   This class is used to construct the options 
 *         part of the home option in the tool bar stage 
 *         which contains the main and editor menu buttons.
 */

package edu.miamioh.worldEditor.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldEditor.ChangeListeners.EditorMenuChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.ImportProjectChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.MainMenuChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.NewProjectChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.SaveChangeListener;

public class HomeStage {

	private Stage stage;
	
	private Actor mainActor;
	private Actor editorActor;
	private Actor importActor;
	private Actor newActor;
	private Actor saveActor;
	
	public HomeStage() {
		
		stage = new Stage();
		
		// Construct the text button actors. This can be 
		// configured to add pictures to the buttons instead 
		// of a color.
		mainActor = new TextButtonActor().createTextButton(Color.RED, "MAIN\nMENU");
		editorActor = new TextButtonActor().createTextButton(Color.PINK, "EDITOR\nMENU");
		newActor = new TextButtonActor().createTextButton(Color.CORAL, "NEW\nPROJECT");
		importActor = new TextButtonActor().createTextButton(Color.GREEN, "IMPORT\nPROJECT");
		saveActor = new TextButtonActor().createTextButton(Color.SCARLET, "SAVE\nPROJECT");
		
		// Add the appropriate change listener to the actor which 
		// is located at edu.miamioh.worldEditor.ChangeListeners.
		mainActor.addListener(new MainMenuChangeListener());
		editorActor.addListener(new EditorMenuChangeListener());
		newActor.addListener(new NewProjectChangeListener());
		importActor.addListener(new ImportProjectChangeListener());
		saveActor.addListener(new SaveChangeListener());
		
		int windowHeight = VerilogWorldController.WINDOW_HEIGHT;
		
		int actorHeight = 50;
		int actorWidth = 100;
		
		// Add the actors to a table which is right justified 
		// and expands along the height of the window. If an 
		// actor needs to be added then follow the format down 
		// below for adding an actor to the table.
		Table table = new Table();
		table.setSize(actorWidth, windowHeight);
		table.setPosition(50, 0);
		table.right().top();
		
		table.add(mainActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(editorActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(newActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(importActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(saveActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
}
