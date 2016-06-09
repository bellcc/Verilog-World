package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.*;
import edu.miamioh.simulator.ModuleInstance;
import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.simulator.WireRoleType;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

/**
 * @author bdshaffer73
 */

public class SchematicRendererMain implements ApplicationListener {

    private SchematicRenderer schematic;
    private Parse compiler;
    private ModuleInstance root_module;
    private ParseTree root_tree;

    /**
     * Constructor for SchematicRendererMain. Requires a Parse to have already been created.
     * @param compiler Contains information on the design to be rendered.
     */
    public SchematicRendererMain(Parse compiler){

        this.compiler = compiler;
        this.root_module = compiler.getRootModule();
        this.root_tree = compiler.getRootTree();

    }

    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create() {
        schematic = new SchematicRenderer();
        getData();
    }

    private void getData(){

        SchematicVisitor visitor = new SchematicVisitor<Gate>(schematic);
        visitor.visit(root_tree);

    }

    /**
     * Called when the {@link Application} is resized. This can happen at any point during a non-paused state but will never happen
     * before a call to {@link #create()}.
     *
     * @param width  the new width in pixels
     * @param height the new height in pixels
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * This method draws a new window with the schematic design determined by
     * the Verilog logic.
     */
    public void render() {schematic.render();}

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
     * Default constructor.
     */
    public SchematicRendererMain() {}

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {

    }
}
