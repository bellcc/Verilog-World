package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.*;
import edu.miamioh.simulator.RootModuleSimulator;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author bdshaffer73
 */
public class SchematicRendererMain implements Screen {

    private SchematicRenderer schematic;
    private ParseTree root_tree;

    /**
     * Constructor for SchematicRendererMain.
     */
    public SchematicRendererMain(){
        schematic = new SchematicRenderer(this, root_tree);
    }

    public void setSim(RootModuleSimulator sim){
        this.root_tree = sim.getRootModuleTree();
    }

    @Override
    public void show() {
        schematic.getData();
    }

    /**
     * This method draws a new window with the schematic design determined by
     * the Verilog logic.
     */
    @Override
    public void render(float arg0) {
        schematic.render();
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
