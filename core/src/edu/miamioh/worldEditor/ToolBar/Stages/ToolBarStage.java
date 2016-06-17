
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
import edu.miamioh.worldEditor.ToolBar.Actors.TilesActor;

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

		//WorldEditorRenderer.getWorldRenderer().setHomeActor(false);
		//WorldEditorRenderer.getWorldRenderer().setBlocksActor(false);
		//WorldEditorRenderer.getWorldRenderer().setTilesActor(false);
				
		Actor homeActor = new HomeActor().getButtonActor();
		homeActor.setPosition(0, windowHeight - toolHeight);
		homeActor.setHeight(toolHeight);
		homeActor.setWidth(toolBarWidth);
				
		Actor blocksActor = new BlocksActor().getButtonActor();
		blocksActor.setPosition(0, windowHeight - (2 * toolHeight));
		blocksActor.setHeight(toolHeight);
		blocksActor.setWidth(toolBarWidth);
		
		Actor tilesActor = new TilesActor().getButtonActor();
		tilesActor.setPosition(0, 0);
		tilesActor.setHeight(windowHeight - (2 * toolHeight));
		tilesActor.setWidth(toolBarWidth);
		
		stage.addActor(homeActor);
		stage.addActor(blocksActor);
		stage.addActor(tilesActor);
	}

	public Stage getStage() {
		
		return stage;
	}
	
}
