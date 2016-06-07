
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.ToolBar.Actors.TileActors.BlankTileActor;

public class TileStage {
	
	private Stage stage;

	public TileStage() {

		stage = new Stage();
	}
	
	public void init() {
		
		createStage();
	}
	
	private void createStage() {

		Actor tileBlock = new BlankTileActor().getButtonActor();
		tileBlock.setPosition(50, 500);
		tileBlock.setHeight(100);
		tileBlock.setWidth(100);
		
		stage.addActor(tileBlock);
	}

	public Stage getStage() {
		
		return stage;
	}
}
