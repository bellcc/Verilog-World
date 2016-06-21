
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorRenderer;
import edu.miamioh.worldEditor.ToolBar.Actors.BlocksActor;
import edu.miamioh.worldEditor.ToolBar.Actors.HomeActor;

public class ToolBarStage {

	private Stage stage;
	
	private int toolBarWidth;
	private int toolHeight;
	
	public ToolBarStage() {
		
		toolBarWidth = 50;
		toolHeight = (WorldEditorController.getCurrentWorldController().getWindowHeight()) / 3;
		
		stage = new Stage();
	}
	
	public void init() {
		
		createStage();
	}
	
	private void createStage() {
		
		int windowHeight = WorldEditorController.getCurrentWorldController().getWindowHeight();

		WorldEditorRenderer.getWorldRenderer().setHomeActor(false);
		WorldEditorRenderer.getWorldRenderer().setBlocksActor(false);
				
		Actor homeActor = new HomeActor().getButtonActor();
		homeActor.setPosition(0, windowHeight - toolHeight);
		homeActor.setHeight(toolHeight);
		homeActor.setWidth(toolBarWidth);
				
		Actor blocksActor = new BlocksActor().getButtonActor();
		blocksActor.setPosition(0, windowHeight - (2 * toolHeight));
		blocksActor.setHeight(toolHeight);
		blocksActor.setWidth(toolBarWidth);
		
		stage.addActor(homeActor);
		stage.addActor(blocksActor);
	}

	public Stage getStage() {
		
		return stage;
	}
	
}
