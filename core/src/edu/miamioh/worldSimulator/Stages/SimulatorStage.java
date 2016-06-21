package edu.miamioh.worldSimulator.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.worldSimulator.WorldSimulatorController;
import edu.miamioh.worldSimulator.ChangeListeners.BackChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.ResetChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.SimulateChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.VerifyChangeListener;

public class SimulatorStage {

	private Stage stage;
	
	private Actor simulateActor;
	private Actor verifyActor;
	private Actor resetActor;
	
	public SimulatorStage() {
		
		stage = new Stage();
		
		// Construct the text button actors. This can be 
		// configured to add pictures to the buttons instead 
		// of a color.
		simulateActor = new TextButtonActor().createTextButton(Color.RED, "SIMULATE");
		verifyActor = new TextButtonActor().createTextButton(Color.PINK, "VERIFY");
		resetActor = new TextButtonActor().createTextButton(Color.YELLOW, "RESET");
		
		// Add the appropriate change listener to the actor which 
		// is located at edu.miamioh.worldEditor.ChangeListeners.
		simulateActor.addListener(new SimulateChangeListener());
		verifyActor.addListener(new VerifyChangeListener());
		resetActor.addListener(new ResetChangeListener());
		
		int windowHeight = WorldSimulatorController.getController().getWindowHeight();
		
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
		
		table.add(simulateActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(verifyActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(resetActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
}
