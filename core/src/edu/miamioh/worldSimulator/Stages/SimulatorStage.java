package edu.miamioh.worldSimulator.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldSimulator.WorldSimulatorController;
import edu.miamioh.worldSimulator.ChangeListeners.BackChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.ResetChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.StartChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.StopChangeListener;

public class SimulatorStage {

	private Stage stage;
	
	private Actor startActor;
	private Actor stopActor;
	private Actor resetActor;
	
	public SimulatorStage() {
		
		stage = new Stage();
		
		// Construct the text button actors. This can be 
		// configured to add pictures to the buttons instead 
		// of a color.
		startActor = new TextButtonActor().createTextButton(Color.RED, "START");
		stopActor = new TextButtonActor().createTextButton(Color.BLUE, "STOP");
		resetActor = new TextButtonActor().createTextButton(Color.YELLOW, "RESET");
		
		// Add the appropriate change listener to the actor which 
		// is located at edu.miamioh.worldEditor.ChangeListeners.
		startActor.addListener(new StartChangeListener());
		stopActor.addListener(new StopChangeListener());
		resetActor.addListener(new ResetChangeListener());
		
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

		table.add(startActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(stopActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(resetActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
}
