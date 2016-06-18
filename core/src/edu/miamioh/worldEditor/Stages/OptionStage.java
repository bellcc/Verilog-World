
/**
 * @author Clark Bell
 * @date   06-17-2016
 * @info   This class is used to construct the options 
 *         part of the tool bar stage which contains the 
 *         home, blocks, and tools options. 
 */

package edu.miamioh.worldEditor.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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
	
	public OptionStage() {
		
		stage = new Stage();
		
		// Construct the text button actors. This can be 
		// configured to add pictures to the buttons instead 
		// of a color.
		homeActor = new TextButtonActor().createTextButton(Color.GREEN, "HOME");
		blockActor = new TextButtonActor().createTextButton(Color.BLUE, "BLOCKS");
		toolActor = new TextButtonActor().createTextButton(Color.PURPLE, "TOOLS");
		
		// Add the appropriate change listener to the actor which 
		// is located at edu.miamioh.worldEditor.ChangeListeners.
		homeActor.addListener(new HomeChangeListener());
		blockActor.addListener(new BlockChangeListener());
		toolActor.addListener(new ToolsChangeListener());
		
		int windowHeight = WorldEditorController.getCurrentController().getWindowHeight();
		
		int actorCount = 3;
		int actorHeight = windowHeight / actorCount;
		int actorWidth = 50;
		
		// Add the actors to a table which is right justified 
		// and expands along the height of the window. If an 
		// actor needs to be added then follow the format down 
		// below for adding an actor to the table and don't 
		// forget to update the actor count variable.
		Table table = new Table();
		table.setSize(actorWidth, windowHeight);
		table.right();
		
		table.add(homeActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(blockActor).width(actorWidth).height(actorHeight);
		table.row();
		table.add(toolActor).width(actorWidth).height(actorHeight);
		
		stage.addActor(table);

	}
	
	/**
	 * @info This method is used to retrieve the options 
	 * stage which is added into the world editor screen.
	 * 
	 * @return The stage of the options part of the tool 
	 * bar on the world editor screen.
	 */
	public Stage getStage() {
		return stage;
	}
	
}
