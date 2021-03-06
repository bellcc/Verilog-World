package edu.miamioh.worldSimulator.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldSimulator.ChangeListeners.ResetChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.StartChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.StopChangeListener;
import edu.miamioh.worldSimulator.ChangeListeners.VerifyChangeListener;

public class SimulatorStage {

	private Stage stage;
	
	private Actor startActor;
	private Actor stopActor;
	private Actor verifyActor;
	private Actor resetActor;
	
	public SimulatorStage() {
		
		stage = new Stage();

		startActor = new TextButtonActor().createTextButton(Color.RED, "START");
		stopActor = new TextButtonActor().createTextButton(Color.BLUE, "STOP");
		verifyActor = new TextButtonActor().createTextButton(Color.PINK, "VERIFY");
		resetActor = new TextButtonActor().createTextButton(Color.YELLOW, "RESET");

		startActor.addListener(new StartChangeListener());
		stopActor.addListener(new StopChangeListener());
		verifyActor.addListener(new VerifyChangeListener());
		resetActor.addListener(new ResetChangeListener());
		
		int windowHeight = VerilogWorldController.WINDOW_HEIGHT;
		
		int actorHeight = 50;
		int actorWidth = 100;

		Table table = new Table();
		table.setSize(actorWidth, windowHeight);
		table.setPosition(50, 0);
		table.right().top();

		table.add(startActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(stopActor).width(actorWidth).height(actorHeight);
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
