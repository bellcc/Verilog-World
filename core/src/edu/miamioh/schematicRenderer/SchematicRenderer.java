package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import edu.miamioh.util.Constants;

import java.util.ArrayList;

import static edu.miamioh.schematicRenderer.GateType.INPUT;
import static edu.miamioh.schematicRenderer.GateType.OUTPUT;

/**
 * Created by shaffebd.
 */
public class SchematicRenderer implements Disposable {

    private ArrayList<Gate> gates = new ArrayList<>();
    private ArrayList<Port> ports = new ArrayList<>();
    private ArrayList<String> conPorts = new ArrayList<>();
    private Constants constants = new Constants();
    private ShapeRenderer renderer = new ShapeRenderer();

    private int xCenter = 0;
    private int yCenter = 0;
    private int maxLevel = 0;

    public SchematicRenderer(){}

    //Public methods for setting up the render

    /**
     * Adds a new Gate to the schematic.
     *
     * @param type   The type of gate, e.g. AND, OR, NOT, etc.
     * @param inputs The number of inputs the gate has. All NOT gates have one input, so this doesn't affect them.
     * @param id     The unique name of the port.
     * @param level  The distance of the gate from the inputs.
     */
    public void addGate(GateType type, int inputs, String id, int level) {

        if (level > maxLevel)
            maxLevel = level;
        Gate temp = new Gate(type, inputs, id, level);
        gates.add(temp);

    }

    /**
     * Adds a new Input to the schematic.
     *
     * @param id The unique name of the Input. All Inputs have only one port, an output.
     */
    public void addInput(String id) {

        Port temp = new Port(INPUT, id, 0);
        ports.add(temp);

    }

    /**
     * Adds a new Output to the schematic.
     *
     * @param id The unique name of the Output. All Outputs have only one port, an input.
     */
    public void addOutput(String id) {

        Port temp = new Port(OUTPUT, id, maxLevel + 1);
        ports.add(temp);

    }

    /**
     * Connects two ports together.
     *
     * @param id1       The ID of the first Gate/Port being connected
     * @param portType1 The Type of the first Gate/Port
     * @param portNum1  The Number of the port. If the Type is "OUT", this is overwritten to "0"
     * @param id2       The ID of the second Gate/Port being connected
     * @param portType2 The Type of the second Gate/Port
     * @param portNum2  The Number of the port. If the Type is "OUT", this is overwritten to "0"
     */
    public void connectPorts(String id1, String portType1, int portNum1, String id2, String portType2, int portNum2) {

        String portA = id1 + "/" + portType1 + "~" + portNum1;
        String portB = id2 + "/" + portType2 + "~" + portNum2;

        conPorts.add(portA);
        conPorts.add(portB);

    }

    /**
     * Begins the actual rendering process.
     */
    public void render() {

        //Set the background color to white.
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int cxAxis = constants.WINDOW_HEIGHT / 2; /* Axis of the window where
        y = total height / 2 */
        int cyAxis = constants.WINDOW_WIDTH / 2; /* Axis of the window where
        x = total width / 2 */

//        constants.frame = true;

        this.renderer.begin(ShapeRenderer.ShapeType.Line);

        //Template
        if (constants.frame) {
            renderer.setColor(Color.BLUE);
            renderer.line(constants.leftEdge, cxAxis, constants.rightEdge, cxAxis);
            renderer.line(cyAxis, constants.bottomEdge, cyAxis, constants.topEdge);
            renderer.rect(constants.leftEdge, constants.bottomEdge, constants.rightEdge - constants.leftEdge,
                    constants.topEdge - constants.bottomEdge);      //Draw a box to show the edges of the schematic.
        }

        //Actual drawing
        this.renderer.setColor(Color.BLACK);
        render(this.renderer);
        this.renderer.end();
    }

    // Private methods to get things done

    private void render(ShapeRenderer renderer) {

        //Render Gates and Ports
        {
            GateRenderer grenderer = new GateRenderer(renderer, constants);

            int totalOuts = 0;
            int totalIns = 0;
            int skips = 0;

            Port tempP;
            Gate tempG;

            for (int i = 0; i < ports.size(); i++) {

                tempP = ports.get(i);
                if (tempP.getType().equals(INPUT)) {

                    tempP.setCX(getXCenter(0));
                    tempP.setCY(getYCenter(i - totalOuts));
                    grenderer.render(tempP.getType(), tempP.getCX(), tempP.getCY());

                } else
                    totalOuts++;
            }

            for (int p = 1; p <= maxLevel; p++) {
                for (int j = 0; j < gates.size(); j++) {

                    tempG = gates.get(j);
                    if (tempG.getLevel() == p) {

                        tempG.setCX(getXCenter(p));
                        tempG.setCY(getYCenter(j - skips));
                        grenderer.render(tempG.getType(), tempG.getCX(), tempG.getCY());

                    } else
                        skips++;
                }
                skips = 0;
            }

            for (int k = 0; k < ports.size(); k++) {

                tempP = ports.get(k);
                if (tempP.getType().equals(OUTPUT)) {

                    tempP.setCX(getXCenter(maxLevel + 1));
                    tempP.setCY(getYCenter(k - totalIns));
                    grenderer.render(tempP.getType(), tempP.getCX(), tempP.getCY());
                } else
                    totalIns++;
            }
        }

        //Render Gate port connections
        {

            String portName;
            String portDetails[];

            Gate tempG;
            Port tempP;

            int x1, y1, x2, y2, j;
            x1 = y1 = x2 = y2 = 0;

            for (int i = 0; i < conPorts.size(); i++) {

                portName = conPorts.get(i);
                portDetails = portName.split("/"); //0 : ID ; 1 : gatePort

                tempP = null;
                tempG = null;

                for (j = 0; j < ports.size(); j++) {
                    if (ports.get(j).getID().equals(portDetails[0])) {
                        tempP = ports.get(j);
                        x1 = tempP.getPortX();
                        y1 = tempP.getPortY();
                        j = ports.size();
                    }
                }

                if (tempP == null) {
                    for (j = 0; j < gates.size(); j++) {
                        if (gates.get(j).getID().equals(portDetails[0])) {
                            tempG = gates.get(j);
                            x1 = tempG.getPortX(portDetails[1]);
                            y1 = tempG.getPortY(portDetails[1]);
                            j = gates.size();
                        }
                    }
                }

                i++;

                portName = conPorts.get(i);
                portDetails = portName.split("/"); //0 : ID ; 1 : gatePort

                tempP = null;
                tempG = null;

                for (j = 0; j < ports.size(); j++) {
                    if (ports.get(j).getID().equals(portDetails[0])) {
                        tempP = ports.get(j);
                        x2 = tempP.getPortX();
                        y2 = tempP.getPortY();
                        j = ports.size();
                    }
                }

                if (tempP == null) {
                    for (j = 0; j < gates.size(); j++) {
                        if (gates.get(j).getID().equals(portDetails[0])) {
                            tempG = gates.get(j);
                            x2 = tempG.getPortX(portDetails[1]);
                            y2 = tempG.getPortY(portDetails[1]);
                            j = gates.size();
                        }
                    }
                }

                if (x1 != 0 & y1 != 0 & x2 != 0 & y2 != 0) {

                    int xm = (x2 + x1) / 2;

                    renderer.line(x1, y1, xm, y1);
                    renderer.line(xm, y1, xm, y2);
                    renderer.line(xm, y2, x2, y2);

                }
            }
        }
    }

    private int getXCenter(int level) {

        xCenter = constants.leftEdge + constants.gateSize * constants.scaleFactor / 2 + level * constants.gateSize * constants.scaleFactor * 2;

        return xCenter;

    }

    private int getYCenter(int row) {

        yCenter = constants.bottomEdge + constants.gateSize * constants.scaleFactor / 2 + row * constants.gateSize * constants.scaleFactor * 2;

        return this.yCenter;

    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {

    }

    public void resize(int width, int height) {

    }
}
