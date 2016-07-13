package edu.miamioh.worldSimulator.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldSimulator.ChangeListeners.BackChangeListener;

public class HomeStage {

	private Stage stage;
	private Actor backActor;
	
	public HomeStage() {
		
		stage = new Stage();
		
		backActor = new TextButtonActor().createTextButton(Color.ORANGE, "BACK");

		backActor.addListener(new BackChangeListener());
		
		int windowHeight = VerilogWorldController.WINDOW_HEIGHT;
		
		int actorHeight = 50;
		int actorWidth = 100;

		Table table = new Table();
		table.setSize(actorWidth, windowHeight);
		table.setPosition(50, 0);
		table.right().top();
		
		table.add(backActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
}
