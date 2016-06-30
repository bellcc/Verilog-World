package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by shaffebd.
 */
public class SchematicRendererController {

    private static SchematicRendererController currentController;

    private int worldWidth;
    private int worldHeight;

    //Constants
    final int leftEdge = worldWidth / 20;
    final int rightEdge = worldWidth - leftEdge;
    final int bottomEdge = leftEdge;
    final int topEdge = worldHeight - bottomEdge;
    static final int gateSize = 1;
    static boolean frame = false;
    static int scaleFactor = 80;

    public SchematicRendererController() {

        currentController = this;
//        paused = false;
//
//        inputProcessor = new WorldEditorInputProcessor();
//        multiplexer = new InputMultiplexer();
//
//        selection = ToolBarSelection.NONE;
//        blockSelection = BlockSelectionType.NONE;
//        blockID = 0;
    }

    void updateInputProcessor() {

        Stage schematicStage = SchematicRendererScreen.getScreen().getSchematicStage();

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

    public static SchematicRendererController getCurrentController() {

        return currentController;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public void setWorldWidth(int windowWidth) {
        this.worldWidth = windowWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public void setWorldHeight(int windowHeight) {
        this.worldHeight = windowHeight;
    }
}
