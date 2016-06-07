
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Stages;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class HomeStage {
	
	private Stage stage;

	public HomeStage() {

		stage = new Stage();
	}
	
	public void init() {
		
		createStage();
	}
	
	private void createStage() {

		
	}

	public Stage getStage() {
		
		return stage;
	}
}
