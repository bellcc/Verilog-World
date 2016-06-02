package edu.miamioh.SchematicRenderer;

import com.badlogic.gdx.utils.Disposable;
import edu.miamioh.worldEditor.WorldController;
import edu.miamioh.util.Constants;

/**
 * @author bdshaffer73
 */

public class SchematicRendererTest implements Disposable {

    private WorldController worldController;
    private SchematicRenderer schematic;

    /**
     * Default constructor.
     *
     * @param worldController
     */
    public SchematicRendererTest(WorldController worldController) {
        this.worldController = worldController;
        this.schematic = new SchematicRenderer(this.worldController);
    }

    /**
     * This method draws a new window with the schematic design determined by
     * the Verilog logic.
     */
    public void render() {

        schematic.addInput("A");
        schematic.addInput("B");
        schematic.addGate("AND", 2, "AND0", 1);
        schematic.connectPorts("A", "OUT", 0, "AND", "IN", 0);
        schematic.addOutput("O");
        schematic.connectPorts("AND", "OUT", 0, "O", "IN", 0);
        schematic.render();

    }

    public SchematicRendererTest() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {

    }
}
