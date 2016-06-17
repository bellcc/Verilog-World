package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import edu.miamioh.util.Constants;

import java.util.ArrayList;
import java.util.Stack;

import static edu.miamioh.schematicRenderer.GateType.INPUT;
import static edu.miamioh.schematicRenderer.GateType.OUTPUT;
import static edu.miamioh.schematicRenderer.GateType.REG;

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
     * @param id     The unique name of the port.
     * @param level  The distance of the gate from the inputs.
     */
    public void addGate(GateType type, String id, int level) {

        if (level > maxLevel)
            maxLevel = level;
        Gate temp = new Gate(type, id, level);
        gates.add(temp);

    }

    /**
     * Counts the number of gates of a specified type and returns the total.
     *
     * @param type
     * @return
     */
    public int getGateCount(GateType type) {

        int total = 0;

        for(Gate gate : gates){
            if(gate.getType().equals(type))
                total++;
        }
        return total;

    }

    public int getLevel(String id){

        Gate tempG = gateLookup(id);
        Port tempP = portLookup(id);

        if(tempG != null){

            return tempG.getLevel();

        } else if(tempP != null){

            return tempP.getLevel();

        } else
            return 0;

    }

    public void setLevel(String id, int level){
        Gate tempG = gateLookup(id);
        Port tempP = portLookup(id);

        if(tempG != null)
            tempG.setLevel(level);
        else if (tempP != null)
            tempP.setLevel(level);
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
     * Connects two Gates/Ports by adding one to the other's inputs list.
     *
     * @param lValue
     * @param rValue
     */
    public void connect(String lValue, String rValue){

        Gate tempGl = null, tempGr = null;
        Port tempPl = null, tempPr = null;

        //Get the left-hand object
        if(gateLookup(lValue) != null)
            tempGl = gateLookup(lValue);
        if(portLookup(lValue) != null)
            tempPl = portLookup(lValue);

        //Get the right-hand object
        if(gateLookup(rValue) != null)
            tempGr = gateLookup(rValue);
        if(portLookup(rValue) != null)
            tempPr = portLookup(rValue);

        if(tempGl != null && tempGr != null)
            tempGr.addInput(tempGl.getID());
        if(tempGl != null && tempPr != null)
            tempPr.addInput(tempGl.getID());
        if(tempPl != null && tempGr != null)
            tempGr.addInput(tempPl.getID());
        if(tempPl != null && tempPr != null)
            tempPr.addInput(tempPl.getID());
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

        //Template
        if (constants.frame) {
            renderer.setColor(Color.BLUE);
            renderer.line(constants.leftEdge, cxAxis, constants.rightEdge, cxAxis);
            renderer.line(cyAxis, constants.bottomEdge, cyAxis, constants.topEdge);
            renderer.rect(constants.leftEdge, constants.bottomEdge, constants.rightEdge - constants.leftEdge,
                    constants.topEdge - constants.bottomEdge);      //Draw a box to show the edges of the schematic.
        }

        //Actual drawing
        renderHelper();
    }

    // Private methods to get things done

    private void renderHelper() {

        //Render Gates and Ports
        {
            this.renderer.begin(ShapeRenderer.ShapeType.Line);
            this.renderer.setColor(Color.BLACK);
            GateRenderer grenderer = new GateRenderer(this.renderer, constants);

            int totalOuts = 0;
            int totalIns = 0;
            int skips = 0;

            Port tempP;
            Gate tempG;

            //Set coordinates of INPUT Ports.
            for (int i = 0; i < ports.size(); i++) {

                tempP = ports.get(i);
                if (tempP.getType().equals(INPUT)) {

                    tempP.setCX(getXCenter(0));
                    tempP.setCY(getYCenter(i - totalOuts));
                    grenderer.render(tempP.getType(), tempP.getCX(), tempP.getCY());

                } else
                    totalOuts++;
            }

            //Set coordinates of Gates.
            for (int p = 1; p <= maxLevel; p++) {
                int rowY, avgY;
                for (int j = 0; j < gates.size(); j++) {

                    tempG = gates.get(j);
                    if (tempG.getLevel() == p) {

                        tempG.setCX(getXCenter(p));
                        tempG.setCY(getYCenter(tempG.getInputs(), j - skips));
                        //Set CY
                        // based on the average height of all its inputs
                        if(tempG.getInputs().size() > 0)
                            grenderer.render(tempG.getType(), tempG.getCX(), tempG.getCY());
                    } else
                        skips++;
                }
                skips = 0;
            }

            //Set coordinates of OUTPUT Ports.
            for (int k = 0; k < ports.size(); k++) {

                tempP = ports.get(k);
                if (tempP.getType().equals(OUTPUT)) {
                    tempP.setCX(getXCenter(maxLevel + 1));
                    tempP.setCY(getYCenter(tempP.getInputs(), k - totalIns));
                    grenderer.render(tempP.getType(), tempP.getCX(), tempP.getCY());

                } else
                    totalIns++;
            }
        }

        this.renderer.end();

        //Render Gate port connections
        {
            Port tempPl = null, tempPr = null;
            Gate tempGl = null, tempGr = null;
            int x1 = 0, x2 = 0, xm = 0, y1 = 0, y2 = 0;
            String gatePort = "";

            //Connect all OUTPUT Ports to their inputs.
            for(Port port : ports){
                tempPr = port;
                float r, g, b, a;
                for(String id : tempPr.getInputs()){
                    x2 = tempPr.getPortX();
                    y2 = tempPr.getPortY();

                    //Check if the left-hand ID is a gate; if a gate, get
                    // its output port coordinates.
                    if(gateLookup(id) != null){
                        tempGl = gateLookup(id);
                        gatePort = "OUT~0";
                        r = tempGl.getR();
                        g = tempGl.getG();
                        b = tempGl.getB();
                        a = tempGl.getA();
                        x1 = tempGl.getPortX(gatePort);
                        y1 = tempGl.getPortY(gatePort);
                        drawSquareLine(x1, y1, x2, y2, r, g, b, a);

                        //Check if the left-hand ID is a port; if a port,
                        // get its output coordinates.
                    } else if(portLookup(id) != null){
                        tempPl = portLookup(id);
                        r = tempPl.getR();
                        g = tempPl.getG();
                        b = tempPl.getB();
                        a = tempPl.getA();
                        x1 = tempPl.getPortX();
                        y1 = tempPl.getPortY();
                        drawSquareLine(x1, y1, x2, y2, r, g, b, a);
                    }
                }
            }

            //Connect all Gates to their inputs.
            for(Gate gate : gates){
                tempGr = gate;

                //Sort inputs of tempGr
                Stack<String> sortedInputs = new Stack<>();
                ArrayList<String> copyInputs = new ArrayList<String>(tempGr.getInputs());
                int totalInputs = copyInputs.size();
                int highestY;
                Gate tempG;
                Port tempP;

                for(int s = 0; s < totalInputs; s++){

                    highestY = 0;

                    //For each input, check if it's the highest. If so, save the y.
                    for (String input : copyInputs) {
                        if (gateLookup(input) != null) {
                            tempG = gateLookup(input);
                            if (tempG.getCY() > highestY)
                                highestY = tempG.getCY();
                        } else if (portLookup(input) != null) {
                            tempP = portLookup(input);
                            if (tempP.getCY() > highestY)
                                highestY = tempP.getCY();
                        }
                    }

                    //Find the gate/port with the highest y value and move it
                    // to the sorted Stack.
                    int j;
                    String i;
                    for (j = 0; j < copyInputs.size(); j++) {
                        i = copyInputs.get(j);
                        if (gateLookup(i) != null) {
                            tempG = gateLookup(i);
                            if (tempG.getCY() == highestY) {
                                sortedInputs.push(i);
                            }
                        } else if (portLookup(i) != null) {
                            tempP = portLookup(i);
                            if (tempP.getCY() == highestY) {
                                sortedInputs.push(i);
                            }
                        }
                    }
                    copyInputs.remove(sortedInputs.peek());
                }

                String id;
                float r, g, b, a;

                for(int i = 0; i < tempGr.getNumOfInputs(); i++){

                    gatePort = "IN~" + i;
                    id = sortedInputs.pop();
                    x2 = tempGr.getPortX(gatePort);
                    y2 = tempGr.getPortY(gatePort);

                    //Check if the left-hand ID is a gate; if a gate, get its
                    // output port coordinates
                    if(gateLookup(id) != null){
                        tempGl = gateLookup(id);
                        gatePort = "OUT~0";
                        r = tempGl.getR();
                        g = tempGl.getG();
                        b = tempGl.getB();
                        a = tempGl.getA();
                        x1 = tempGl.getPortX(gatePort);
                        y1 = tempGl.getPortY(gatePort);
                        drawSquareLine(x1, y1, x2, y2, r, g, b, a);

                        //Check if the left-hand ID is a port; if a port, get
                        // its output coordinates.
                    } else if(portLookup(id) != null){
                        tempPl = portLookup(id);
                        r = tempPl.getR();
                        g = tempPl.getG();
                        b = tempPl.getB();
                        a = tempPl.getA();
                        x1 = tempPl.getPortX();
                        y1 = tempPl.getPortY();
                        drawSquareLine(x1, y1, x2, y2, r, g, b, a);
                    }
                }
            }
        }

        //Render wires from the clk to all Reg blocks
        {
            for(Port port : ports) {
                if (port.getID().equalsIgnoreCase("clk")) {
                    for (Gate gate : gates) {
                        if (gate.getType().equals(REG)) {
                            drawClkLine(gate.getCX(), gate.getCY());
                        }
                    }
                }
            }
        }
    }

    private void drawSquareLine(int x1, int y1, int x2, int y2, float r,
                                float g, float b, float a){

        int xm = (x1 + x2) / 2;

        this.renderer.begin(ShapeRenderer.ShapeType.Line);
        this.renderer.setColor(r, g, b, a);
        renderer.line(x1, y1, xm, y1);
        renderer.line(xm, y1, xm, y2);
        renderer.line(xm, y2, x2, y2);
        this.renderer.end();

    }

    private void drawClkLine(int cx, int cy){

        Port clk = portLookup("clk");
        int clkx = clk.getPortX();
        int clky = clk.getPortY();

        int dx = cx;
        int dy = cy - constants.gateSize * constants.scaleFactor / 2;

        drawSquareLine(clkx, clky, dx, dy, 0, 0, 255, 255);
    }

    private Gate gateLookup(String id){

        for(Gate gate : gates){

            if(gate.getID().equals(id))
                return gate;

        }

        return null;

    }

    private Port portLookup(String id){

        for(Port port : ports){

            if(port.getID().equals(id))
                return port;

        }

        return null;

    }

    private int getXCenter(int level) {

        xCenter = constants.leftEdge + constants.gateSize * constants.scaleFactor / 2 + level * constants.gateSize * constants.scaleFactor * 2;

        return xCenter;

    }

    private int getYCenter(int row) {

        yCenter = constants.bottomEdge + constants.gateSize * constants.scaleFactor / 2 + row * constants.gateSize * constants.scaleFactor * 2;

        return this.yCenter;

    }

    private int getYCenter(ArrayList<String> inputs, int row){

        int avgY = 0, numOfInputs = inputs.size();

        for(String id : inputs){

            if(gateLookup(id) != null)
                avgY += gateLookup(id).getCY();
            if(portLookup(id) != null)
                avgY += portLookup(id).getCY();
        }

        if(numOfInputs != 0)
            avgY /= numOfInputs;

//        int gsFactor = constants.gateSize + constants.scaleFactor;
//        Gate gate;
//        boolean moved;
//        do{
//            moved = false;
//            for(int i = 0; i < gates.size(); i++) {
//                gate = gates.get(i);
//                if()
//                if (gate.getCY() < avgY + gsFactor & gate.getCY() > avgY - gsFactor) {
//                    avgY += gsFactor;
//                    moved = true;
//                }
//
//            }
//        }while(moved);

        return avgY;

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
