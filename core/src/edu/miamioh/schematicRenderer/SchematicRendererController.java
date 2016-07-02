package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.miamioh.verilogWorld.VerilogWorldController;

/**
 * Created by shaffebd.
 */
public class SchematicRendererController {

    private static SchematicRendererController currentController;

    private int worldWidth;
    private int worldHeight;
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
        worldWidth = VerilogWorldController.getController().getDefaultConfig().getWindowWidth();
        worldHeight = VerilogWorldController.getController().getDefaultConfig().getWindowHeight();
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();
        leftEdge = (int)((float)worldWidth / 20f);
        rightEdge = worldWidth - leftEdge;
        bottomEdge = leftEdge;
        topEdge = worldHeight - bottomEdge;
    }

    public static SchematicRendererController getCurrentController() {

        return currentController;
    }

    public int getWorldWidth() {
        return this.worldWidth;
    }

    public int getWindowWidth(){
        return this.windowWidth;
    }

//    public void setWorldWidth(int windowWidth) {
//        this.worldWidth = windowWidth;
//    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getWindowHeight(){
        return this.windowHeight;
    }

//    public void setWorldHeight(int windowHeight) {
//        this.worldHeight = windowHeight;
//    }
}
