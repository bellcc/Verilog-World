package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.worldSimulator.ChangeListeners.BackChangeListener;

class BackStage{
	
	private Stage stage;
	private Actor butt;
	private SchematicRendererController controller;
	
	BackStage(){
		
		controller = SchematicRendererController.getCurrentController();
		
		stage = new Stage();
		
		butt = new TextButtonActor().createTextButton(Color.BLUE, "Back");
		
		butt.addListener(new BackChangeListener());
		
		int buttonWidth = 100;
        int buttonHeight = 40;
        int w = controller.getWindowWidth();
        int h = controller.getWindowHeight();
        butt.setPosition(w - buttonWidth, h - buttonHeight);
        butt.setSize(buttonWidth, buttonHeight);
        
        stage.addActor(butt);
        
	}
	
	Stage getStage(){
		return this.stage;
	}

}
