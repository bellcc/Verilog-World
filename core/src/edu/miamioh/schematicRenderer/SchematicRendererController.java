package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.miamioh.verilogWorld.VerilogWorldController;

/**
 * Created by shaffebd.
 */
public class SchematicRendererController {

    private static SchematicRendererController currentController;

//    private int worldWidth;
//    private int worldHeight;
    private int windowWidth;
    private int windowHeight;

    //Constants
    //Set by the world dimensions
    static int leftEdge;
    static int rightEdge;
    static int bottomEdge;
    static int topEdge;

    //Set by the schematic
    static float gateSize = 1;
    static float scaleFactor = 80;
    static boolean frame = false;

    public SchematicRendererController() {
        currentController = this;
        updateConfig();
    }

    void updateInputProcessor(Stage schematicStage) {

//        resetMultiplexer();
//
//        multiplexer.addProcessor(optionStage);
//
//        switch (selection) {
//
//            case HOME:
//                multiplexer.addProcessor(homeStage);
//                break;
//
//            case BLOCK:
//                multiplexer.addProcessor(blockStage);
//                break;
//
//            case BLOCK_SELECTED:
//                multiplexer.addProcessor(blockSelectedStage);
//                break;
//
//            case SIMULATOR:
//                multiplexer.addProcessor(simulatorStage);
//                break;
//
//            default:
//                break;
//
//        }
//
//        multiplexer.addProcessor(inputProcessor);
//
        Gdx.input.setInputProcessor(schematicStage);

    }

    void updateConfig(){
//        windowWidth = VerilogWorldController.WINDOW_WIDTH;
//        windowHeight = VerilogWorldController.WINDOW_HEIGHT;
    	windowWidth = Gdx.graphics.getWidth();
    	windowHeight = Gdx.graphics.getHeight();
//        worldWidth = Gdx.graphics.getWidth();
//        worldHeight = Gdx.graphics.getHeight();
        leftEdge = (int) (windowWidth / 20f);
        rightEdge = windowWidth - leftEdge;
        bottomEdge = leftEdge;
        topEdge = windowHeight - bottomEdge;
    }

    public static SchematicRendererController getCurrentController() {

        return currentController;
    }

//    public int getWorldWidth() {
//        return this.worldWidth;
//    }

    public int getWindowWidth(){
        return this.windowWidth;
    }

//    public void setWorldWidth(int windowWidth) {
//        this.worldWidth = windowWidth;
//    }

//    public int getWorldHeight() {
//        return worldHeight;
//    }

    public int getWindowHeight(){
        return this.windowHeight;
    }

//    public void setWorldHeight(int windowHeight) {
//        this.worldHeight = windowHeight;
//    }
}
