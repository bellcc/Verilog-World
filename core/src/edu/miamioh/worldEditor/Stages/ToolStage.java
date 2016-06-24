package edu.miamioh.worldEditor.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.actors.TextButtonActor;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.ChangeListeners.VerifyChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.ViewSimulatorChangeListener;

public class ToolStage {

	private Stage stage;
	
	private Label worldWidthLabel;
	private Label worldHeightLabel;
	
	private Label gridWidthLabel;
	private Label gridHeightLabel;
	
	private Label bufferWidthLabel;
	private Label bufferHeightLabel;
	
	private Label stepWidthLabel;
	private Label stepHeightLabel;

	private Actor updateActor;
	
	public ToolStage() {
		
		stage = new Stage();
		
		// Construct the text button actors. This can be 
		// configured to add pictures to the buttons instead 
		// of a color.
		//viewSimulatorActor = new TextButtonActor().createTextButton(Color.PURPLE, "VIEW\nSIMULATOR");
		//verifyActor = new TextButtonActor().createTextButton(Color.RED, "VERIFY");
		
		// Add the appropriate change listener to the actor which 
		// is located at edu.miamioh.worldEditor.ChangeListeners.
		//viewSimulatorActor.addListener(new ViewSimulatorChangeListener());
		//verifyActor.addListener(new VerifyChangeListener());
		
		int windowHeight = WorldEditorController.getCurrentController().getWindowHeight();
		
		int actorHeight = 50;
		int actorWidth = 100;
		
		// Add the actors to a table which is right justified 
		// and expands along the height of the window. If an 
		// actor needs to be added then follow the format down 
		// below for adding an actor to the table and don't 
		// forget to update the actor count variable.
		Table table = new Table();
		table.setSize(actorWidth, windowHeight);
		table.setPosition(50, 0);
		table.right().top();
		
		//table.add(viewSimulatorActor).width(actorWidth).height(actorHeight);
		table.row();
		//table.add(verifyActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
}
