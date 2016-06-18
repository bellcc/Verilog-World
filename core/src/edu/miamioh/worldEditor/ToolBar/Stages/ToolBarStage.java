
/**
 * @author Chris Bell
 * @date   6-6-2016
 * @info   
 */

package edu.miamioh.worldEditor.ToolBar.Stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.WorldEditorController;

public class ToolBarStage {

	private Stage stage;
	
	private int toolBarWidth;
	private int toolHeight;
	
	public ToolBarStage() {
		
		toolBarWidth = 50;
		toolHeight = (WorldEditorController.getCurrentController().getWindowHeight()) / 3;
		
		stage = new Stage();
	}
	
	public void init() {
		
	}

	public Stage getStage() {
		
		return stage;
	}
	
}
