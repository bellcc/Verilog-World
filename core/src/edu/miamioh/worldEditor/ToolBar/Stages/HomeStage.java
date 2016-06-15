
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.ToolBar.Actors.HomeActors.SimulatorActor;

public class HomeStage {
	
	private Stage stage;

	public HomeStage() {

		stage = new Stage();
	}
	
	public void init() {
		
		createStage();
	}
	
	private void createStage() {
		
		int windowHeight = WorldEditorController.getCurrentWorldController().getWindowHeight();

		Actor simulator = new SimulatorActor().getButtonActor();
		simulator.setPosition(50, windowHeight - 100);
		simulator.setSize(100, 100);
		
		stage.addActor(simulator);
	}

	public Stage getStage() {
		
		return stage;
	}
}
