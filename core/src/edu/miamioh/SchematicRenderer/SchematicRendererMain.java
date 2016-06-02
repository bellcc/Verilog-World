package edu.miamioh.SchematicRenderer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.*;
import edu.miamioh.simulator.Parse;
import edu.miamioh.worldEditor.WorldController;

/**
 * @author bdshaffer73
 */

public class SchematicRendererMain implements ApplicationListener {

    private WorldController worldController;
    private SchematicRenderer schematic;
    private Parse parse;

    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create() {

        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        worldController = new WorldController();
        parse = new Parse(null, "/home/pheonix/USS/Verilog-World/core/assets/modules");
        schematic = new SchematicRenderer(parse);

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
    public void render() {

        schematic.addInput("A");
        schematic.addInput("B");
        schematic.addGate("AND", 2, "AND0", 1);
        schematic.addInput("C");
        schematic.addGate("OR", 2, "OR0", 2);
        schematic.connectPorts("A", "OUT", 0, "AND0", "IN", 0);
        schematic.connectPorts("B", "OUT", 0, "AND0", "IN", 1);
        schematic.connectPorts("AND0", "OUT", 0, "OR0", "IN", 0);
        schematic.connectPorts("C", "OUT", 0, "OR0", "IN", 1);
        schematic.addOutput("O");
        schematic.connectPorts("OR0", "OUT", 0, "O", "IN", 0);

        schematic.render();

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

    public SchematicRendererMain() {
        super();
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {

    }
}
