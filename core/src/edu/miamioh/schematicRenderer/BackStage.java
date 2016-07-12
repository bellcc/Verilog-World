package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;

import edu.miamioh.Buttons.TextButtonActor;

/**
 * Creates a new stage for the Schematic Render screen for the Back button and
 * any necessary name tags.
 * 
 * @author bdshaffer73
 *
 */
class BackStage {
    
    private Stage			stage;
    private Actor			butt;
    private SchematicRendererController	controller;
    
    /**
     * Creates a new BackStage. Adds a button to the stage, labeled "Back." The
     * button is placed in the top right corner of the stage.
     */
    BackStage() {
	
	controller = SchematicRendererController.getCurrentController();
	
	stage = new Stage();
	
	butt = new TextButtonActor().createTextButton(Color.BLUE, "Back");
	
	butt.addListener(new SchematicBackChangeListener());
	
	int buttonWidth = 100;
	int buttonHeight = 40;
	int w = controller.getWindowWidth();
	int h = controller.getWindowHeight();
	
	butt.setPosition(w - buttonWidth, h - buttonHeight);
	
	butt.setSize(buttonWidth, buttonHeight);
	
	stage.addActor(butt);
	
    }
    
    /**
     * Gets the current instance of the Stage.
     * 
     * @return The stage.
     */
    Stage getStage() {
	return this.stage;
    }
    
}
