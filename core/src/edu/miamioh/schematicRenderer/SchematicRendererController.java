package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * This controller contains all the package wide constants and updates them as
 * necessary.
 * 
 * Created by shaffebd.
 */
public class SchematicRendererController {
    
    private static SchematicRendererController currentController;
    
    private int				       windowWidth;
    private int				       windowHeight;
    
    // Constants
    // Set by the world dimensions
    static int				       leftEdge;
    static int				       rightEdge;
    static int				       bottomEdge;
    static int				       topEdge;
    
    // Set by the schematic
    static float			       gateSize	   = 1;
    static float			       scaleFactor = 80;
    static boolean			       frame	   = false;
    
    /**
     * Creates a new controller for the schematic.
     */
    public SchematicRendererController() {
	currentController = this;
	updateConfig();
    }
    
    //Public methods

    public static SchematicRendererController getCurrentController() {
	return currentController;
    }
    
    public int getWindowWidth() {
	return this.windowWidth;
    }
    
    public int getWindowHeight() {
	return this.windowHeight;
    }
    
    //Package methods
    
    void updateInputProcessor(Stage schematicStage) {
	Gdx.input.setInputProcessor(schematicStage);
    }
    
    void updateConfig() {
	windowWidth = Gdx.graphics.getWidth();
	windowHeight = Gdx.graphics.getHeight();
	leftEdge = (int) (windowWidth / 20f);
	rightEdge = windowWidth - leftEdge;
	bottomEdge = leftEdge;
	topEdge = windowHeight - bottomEdge;
    }
}
