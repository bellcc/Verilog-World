package edu.miamioh.worldEditor.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.ChangeListeners.BlankBlockChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.ClockBlockChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.ResetBlockChangeListener;
import edu.miamioh.worldEditor.ChangeListeners.WallBlockChangeListener;

public class BlockStage {

	private Stage stage;
	
	private Actor blankActor;
	private Actor clockActor;
	private Actor resetActor;
	private Actor wallActor;
	
	public BlockStage() {
		
		stage = new Stage();
		
		// Construct the text button actors. This can be 
		// configured to add pictures to the buttons instead 
		// of a color.
		blankActor = new TextButtonActor().createTextButton(Color.RED, "BLANK\nBLOCK");
		clockActor = new TextButtonActor().createTextButton(Color.PINK, "CLOCK\nBLOCK");
		resetActor = new TextButtonActor().createTextButton(Color.YELLOW, "RESET\nBLOCK");
		wallActor = new TextButtonActor().createTextButton(Color.ORANGE, "WALL\nBLOCK");
		
		// Add the appropriate change listener to the actor which 
		// is located at edu.miamioh.worldEditor.ChangeListeners.
		blankActor.addListener(new BlankBlockChangeListener());
		clockActor.addListener(new ClockBlockChangeListener());
		resetActor.addListener(new ResetBlockChangeListener());
		wallActor.addListener(new WallBlockChangeListener());
		
		int windowHeight = WorldEditorController.getCurrentController().getWindowHeight();
		
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
		
		table.add(blankActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(clockActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(resetActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(wallActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
}
