
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.ToolBar.Actors.BlockActors.BlankBlockActor;

public class BlockStage {
	
	private Stage stage;

	public BlockStage() {

		stage = new Stage();
	}
	
	public void init() {
		
		createStage();
	}
	
	private void createStage() {

		Actor blankBlock = new BlankBlockActor().getButtonActor();
		blankBlock.setPosition(50, 500);
		blankBlock.setHeight(100);
		blankBlock.setWidth(100);
		
		stage.addActor(blankBlock);
		
	}

	public Stage getStage() {
		
		return stage;
	}
	
}
