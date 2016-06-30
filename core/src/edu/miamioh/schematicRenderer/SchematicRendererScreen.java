package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author bdshaffer73
 */
public class SchematicRendererScreen implements Screen {

    private static SchematicRendererScreen schematicRendererScreen;
    private SchematicRenderer schematic;
    private SchematicRendererController controller = SchematicRendererController.getCurrentController();
//    private Stage schematicStage;
    private int windowWidth;
    private int windowHeight;

    /**
     * Constructor for SchematicRendererMain.
     */
    public SchematicRendererScreen(){
        schematic = new SchematicRenderer();
        schematicRendererScreen = this;
        schematic.setSchematicScreen(schematicRendererScreen);
    }

    public void setRoot_tree(ParseTree root_tree){
        schematic.setRoot_tree(root_tree);
        if(schematic.getRoot_tree() == null) {
            System.out.println("The root_tree has been set to null.");
        } else {
            System.out.println("The root_tree has successfully been set.");
        }
    }

//    public ParseTree getRoot_tree(){
//        return schematic.getRoot_tree();
//    }
//
//    private void setRenderer(){schematic.setRenderer(this.renderer);}
//
//    /**
//     * Gets the active schematic.
//     * @return The active schematic.
//     */
//    public SchematicRenderer getSchematic(){
//        return this.schematic;
//    }

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
        } else {
            System.out.println("The root_tree has not been set or has not been created.");
        }
    }

    @Override
    public void show() {

        if (schematic.getRoot_tree() == null) throw new AssertionError();

        controller.updateInputProcessor();
    }

    /**
     * This method draws a new window with the schematic design determined by
     * the Verilog logic.
     */
    @Override
    public void render(float arg0) {
        schematic.refresh();
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

        controller.setWindowWidth(width);
        controller.setWindowHeight(height);
        windowWidth = controller.getWindowWidth();
        windowHeight = controller.getWindowHeight();

        schematic.refresh();

//        updateWorldParameters();
//
//        controller.resetMultiplexer();

        controller.updateInputProcessor();
    }

    static SchematicRendererScreen getScreen(){
        return schematicRendererScreen;
    }

    Stage getSchematicStage(){
        return schematic.getSchematicStage();
    }

    int getWindowWidth(){
        return this.windowWidth;
    }

    int getWindowHeight(){
        return this.windowHeight;
    }

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
