package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import edu.miamioh.Buttons.TextButtonActor;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldSimulator.ChangeListeners.BackChangeListener;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author bdshaffer73
 */
public class SchematicRendererScreen implements Screen {

    private static SchematicRendererScreen schematicRendererScreen;
    private SchematicRenderer schematic;
    private SchematicRendererController controller;
    private Stage schematicStage;
    private TextButton butt;

    /**
     * Constructor for SchematicRendererMain.
     */
    public SchematicRendererScreen(){
        controller = new SchematicRendererController();
        schematic = new SchematicRenderer();
        schematicRendererScreen = this;
//        schematic.setSchematicScreen(schematicRendererScreen);
    }

    public void setRoot_tree(ParseTree root_tree){
        schematic.setRoot_tree(root_tree);
        if(schematic.getRoot_tree() == null) {
            System.out.println("The root_tree has been set to null.");
        } else {
            System.out.println("The root_tree has successfully been set.");
        }
    }

    /**
     * Gets the compilation status of the schematic.
     * @return True if schematic.getData() has run on a valid root_tree;
     */
    public boolean schematic_is_Compiled(){
        return this.schematic.is_Compiled();
    }

    public void compile(){
        if(schematic.getRoot_tree() != null){
            schematic.clearData();
            schematic.getData();
        }
//        schematic.refresh();
//        else {
//            System.out.println("The root_tree has not been set or has not been created.");
//        }
    }

    @Override
    public void show() {
    	
    	controller.updateConfig();
    	
        schematicStage = new BackStage().getStage();
        
    }

    /**
     * This method draws a new window with the schematic design determined by
     * the Verilog logic.
     */
    @Override
    public void render(float arg0) {
    	
    	Gdx.gl.glClearColor(255, 255, 255, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	
        schematic.render(schematicStage);
        
        schematicStage.act(Gdx.graphics.getDeltaTime());
        schematicStage.draw();
    }

    /**
     * Called when the {@link Application} is resized. This can happen at any point during a non-paused state but will never happen
     * before a call to create().
     *
     * @param width  the new width in pixels
     * @param height the new height in pixels
     */
    @Override
    public void resize(int width, int height) {
    	
//    	VerilogWorldController.WINDOW_WIDTH = width;
//    	VerilogWorldController.WINDOW_HEIGHT = height;
    	
    	controller.updateConfig();
    	
        schematicStage = new BackStage().getStage();

        controller.updateInputProcessor(schematicStage);
    	
    }

    static SchematicRendererScreen getScreen(){
        return schematicRendererScreen;
    }

//    Stage getSchematicStage(){
//        return schematic.getSchematicStage();
//    }
//
//    int getWindowWidth(){
//        return this.windowWidth;
//    }
//
//    int getWindowHeight(){
//        return this.windowHeight;
//    }

    /**
     * Called when the {@link Application} is paused, usually when it's not active or visible on screen. An Application is also
     * paused before it is destroyed.
     */
    @Override
    public void pause() {
    }

    /**
     * Called when the {@link Application} is resumed from a paused state, usually when it regains focus.
     */
    @Override
    public void resume() {
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        schematic.dispose();
    }

	@Override
	public void hide() {}

}
