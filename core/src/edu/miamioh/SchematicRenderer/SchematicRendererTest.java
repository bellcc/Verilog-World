package edu.miamioh.SchematicRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Disposable;
import edu.miamioh.worldEditor.WorldController;

/**
 * @author bdshaffer73
 */

public class SchematicRendererTest implements Disposable {

    private WorldController worldController;
    private Constants constants = new Constants();

    private int SCREEN_WIDTH = constants.DEFAULT_WIDTH;
    private int SCREEN_HEIGHT = constants.DEFAULT_HEIGHT;
    private int leftEdge = constants.leftEdge;
    private int rightEdge = constants.rightEdge;
    private int topEdge = constants.topEdge;
    private int bottomEdge = constants.bottomEdge;

    /**
     * Default constructor.
     *
     * @param worldController
     */
    public SchematicRendererTest(WorldController worldController) {

        this.worldController = worldController;

    }

    /**
     * This method draws a new window with the schematic design determined by
     * the Verilog logic.
     */
    public void render() {

        //Set the background color to white.
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ShapeRenderer renderer = new ShapeRenderer();

        SchematicRenderer schematic = new SchematicRenderer();


        int cxAxis = SCREEN_HEIGHT / 2; /* Axis of the window where
        y = total height / 2 */
        int cyAxis = SCREEN_WIDTH / 2; /* Axis of the window where
        x = total width / 2 */

        int scaleFactor = constants.scaleFactor; //Should be changed later; int or float?;
        //Resizes the schematics to fill more of the window.

//        constants.frame = true;

        renderer.begin(ShapeType.Line);
        //Template
        if (constants.frame) {
            renderer.setColor(Color.BLUE);
            renderer.line(leftEdge, cxAxis, rightEdge, cxAxis);
            renderer.line(cyAxis, bottomEdge, cyAxis, topEdge);
            renderer.rect(leftEdge, bottomEdge, rightEdge - leftEdge, topEdge - bottomEdge);      //Draw a box to show the edges of the schematic.
        }
        //Actual drawing
        renderer.setColor(Color.BLACK);
        schematic.addInput("A");
        schematic.addGate("AND", 3, "AND0", 1);
        schematic.addGate("NOT", 1, "NOT0", 1);
        schematic.addGate("XOR", 2, "XOR0", 2);
        schematic.addGate("AND", 2, "AND1", 3);
        schematic.addGate("NOT", 1, "NOT1", 4);
        schematic.addOutput("O");
        schematic.render(renderer, scaleFactor);
        schematic.connectPorts(renderer, "A", "OUT", "AND0", "IN~0");
        schematic.connectPorts(renderer, "A", "OUT", "AND0", "IN~1");
        schematic.connectPorts(renderer, "A", "OUT", "AND0", "IN~2");
        schematic.connectPorts(renderer, "A", "OUT", "NOT0", "IN~1");
        schematic.connectPorts(renderer, "AND0", "OUT~0", "XOR0", "IN~0");
        schematic.connectPorts(renderer, "NOT0", "OUT~0", "XOR0", "IN~1");
        schematic.connectPorts(renderer, "XOR0", "OUT~0", "AND1", "IN~0");
        schematic.connectPorts(renderer, "XOR0", "OUT~0", "AND1", "IN~1");
        schematic.connectPorts(renderer, "AND1", "OUT~0", "NOT1", "IN~0");
        schematic.connectPorts(renderer, "NOT1", "OUT~0", "O", "IN");
        renderer.end();

        }

    @Override
    public void dispose() {

    }
}
