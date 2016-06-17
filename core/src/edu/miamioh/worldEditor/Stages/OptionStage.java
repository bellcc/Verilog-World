package edu.miamioh.worldEditor.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.ChangeListeners.BlockChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.HomeChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.ToolsChangeListener;

public class OptionStage {

	private Stage stage;
	
	private Actor homeActor;
	private Actor blockActor;
	private Actor toolActor;
	
	private final int ACTOR_COUNT = 3;
	private final int ACTOR_WIDTH = 50;
	
	public OptionStage() {
		
		stage = new Stage();
		
		homeActor = new TextButtonActor().createTextButton(Color.GREEN, "HOME");
		blockActor = new TextButtonActor().createTextButton(Color.BLUE, "BLOCKS");
		toolActor = new TextButtonActor().createTextButton(Color.PURPLE, "TOOLS");
		
		homeActor.addListener(new HomeChangeListener());
		blockActor.addListener(new BlockChangeListener());
		toolActor.addListener(new ToolsChangeListener());
		
		int windowHeight = WorldEditorController.getCurrentWorldController().getWindowHeight();
		int actorHeight = windowHeight / ACTOR_COUNT;
		
		int x = 0;
		int y = WorldEditorController.getCurrentWorldController().getWindowHeight();
		
		homeActor.setPosition(x, y - (1 * actorHeight));
		blockActor.setPosition(x, y - (2 * actorHeight));
		toolActor.setPosition(x, y - (3 * actorHeight));
		
		homeActor.setSize(ACTOR_WIDTH, actorHeight);
		blockActor.setSize(ACTOR_WIDTH, actorHeight);
		toolActor.setSize(ACTOR_WIDTH, actorHeight);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
}
