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

        ArrayList<ParseRegWire> vars_list = root_module.getVars_list();

        ParseRegWire temp;

        if(compiler.is_compiled_yet()) {

            for (int i = 0; i < vars_list.size(); i++) {

                temp = vars_list.get(i);

                if (temp.getRole() == WireRoleType.INPUT) {
                    schematic.addInput(temp.getName());
                }

                SchematicVisitor visitor = new SchematicVisitor<Gate>(schematic);
                visitor.visit(root_tree);

                if (temp.getRole() == WireRoleType.OUTPUT) {
                    schematic.addOutput(temp.getName());
                }
            }
        }

//        //Test stuff
//        schematic.addInput("A");
//        schematic.addInput("B");
//        schematic.addGate("AND", 2, "AND0", 1);
//        schematic.addInput("C");
//        schematic.addGate("OR", 2, "OR0", 2);
//        schematic.connectPorts("A", "OUT", 0, "AND0", "IN", 0);
//        schematic.connectPorts("B", "OUT", 0, "AND0", "IN", 1);
//        schematic.connectPorts("AND0", "OUT", 0, "OR0", "IN", 0);
//        schematic.connectPorts("C", "OUT", 0, "OR0", "IN", 1);
//        schematic.addOutput("O");
//        schematic.connectPorts("OR0", "OUT", 0, "O", "IN", 0);

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
