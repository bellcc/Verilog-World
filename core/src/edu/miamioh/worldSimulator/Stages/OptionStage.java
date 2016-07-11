package edu.miamioh.worldSimulator.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldSimulator.ChangeListeners.HomeChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.SimulatorChangeListener;

public class OptionStage {


	private Stage stage;
	
	private Actor homeActor;
	private Actor simulatorActor;
	
	public OptionStage() {
		
		stage = new Stage();
		homeActor = new TextButtonActor().createTextButton(Color.GREEN, "HOME");
		simulatorActor = new TextButtonActor().createTextButton(Color.RED, "SIMULATOR");

		homeActor.addListener(new HomeChangeListener());
		simulatorActor.addListener(new SimulatorChangeListener());
		
		int windowHeight = VerilogWorldController.WINDOW_HEIGHT;
		
		int actorCount = 2;
		int actorHeight = windowHeight / actorCount;
		int actorWidth = 50;

		Table table = new Table();
		table.setSize(actorWidth, windowHeight);
		table.right();
		
		table.add(homeActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(simulatorActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);

	}

	public Stage getStage() {
		return stage;
	}
	
}
