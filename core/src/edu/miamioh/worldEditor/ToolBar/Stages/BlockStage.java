
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.ToolBar.Actors.BlockActors.BlankBlockActor;
import edu.miamioh.worldEditor.ToolBar.Actors.BlockActors.ClockBlockActor;
import edu.miamioh.worldEditor.ToolBar.Actors.BlockActors.ResetBlockActor;
import edu.miamioh.worldEditor.ToolBar.Actors.BlockActors.WallBlockActor;

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
		
		Actor wallBlock = new WallBlockActor().getButtonActor();
		wallBlock.setPosition(50, 400);
		wallBlock.setHeight(100);
		wallBlock.setWidth(100);
		
		Actor clockBlock = new ClockBlockActor().getButtonActor();
		clockBlock.setPosition(50, 300);
		clockBlock.setHeight(100);
		clockBlock.setWidth(100);
		
		Actor resetBlock = new ResetBlockActor().getButtonActor();
		resetBlock.setPosition(50, 200);
		resetBlock.setHeight(100);
		resetBlock.setWidth(100);
		
		stage.addActor(blankBlock);
		stage.addActor(wallBlock);
		stage.addActor(clockBlock);
		stage.addActor(resetBlock);
		
	}

	public Stage getStage() {
		
		return stage;
	}
	
}
