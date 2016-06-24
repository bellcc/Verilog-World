package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import edu.miamioh.simulator.Parse;
import edu.miamioh.util.Constants;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.Stack;

import static edu.miamioh.schematicRenderer.GateType.INPUT;
import static edu.miamioh.schematicRenderer.GateType.OUTPUT;
import static edu.miamioh.schematicRenderer.GateType.REG;

/**
 * Created by bdshaffer73.
 */
class SchematicRenderer implements Disposable {

    private ArrayList<Gate> gates = new ArrayList<>();
    private ArrayList<Port> ports = new ArrayList<>();
    private Constants constants = new Constants();
    private ShapeRenderer renderer;
//    private Screen screen;
//    private Stage stage;
    private ParseTree root_tree;

    private int maxLevel = 0;

    /**
     * The Schematic Renderer recieves a
     */
    SchematicRenderer(Screen screen, ParseTree root_tree, ShapeRenderer renderer){
//        this.screen = screen;
        this.root_tree = root_tree;
//        this.stage = new Stage();
        this.renderer = renderer;
    }

    /**
     * This constructor expects the root_tree and renderer to be set before rendering.
     */
    SchematicRenderer() {

    }

    /**
     * Sets the root_tree to an existing ParseTree.
     * @param root_tree A valid root module tree.
     */
    void setRoot_tree(ParseTree root_tree){
        this.root_tree = root_tree;
    }

    /**
     * Returns the root_tree of the schematic.
     * @return the schematic's root tree.
     */
    ParseTree getRoot_tree(){
        return this.root_tree;
    }

    /**
     * Sets the renderer to a valid ShapeRenderer.
     * @param renderer A valid ShapeRenderer.
     */
    void setRenderer(ShapeRenderer renderer){
        this.renderer = renderer;
    }

    /**
     * Gets the ShapeRenderer in use by this schematic.
     * @return The active ShapeRenderer.
     */
    ShapeRenderer getRenderer(){
        return this.renderer;
    }

    //Methods for setting up the render

    /**
     * Runs the SchematicVisitor to collect Ports, Gates, and relationships from the root_tree.
     */
    void getData(){
        if(root_tree != null) {
            SchematicVisitor visitor = new SchematicVisitor(this);
            visitor.visit(root_tree);
        }
    }

    /**
     * Adds a new Gate to the schematic.
     *
     * @param type   The type of gate, e.g. AND, OR, NOT, etc.
     * @param id     The unique name of the port.
     * @param level  The distance of the gate from the inputs.
     */
    void addGate(GateType type, String id, int level) {

        if (level > maxLevel)
            maxLevel = level;
        Gate temp = new Gate(type, id, level);
        gates.add(temp);

    }

    /**
     * Counts the number of gates of a specified type and returns the total.
     *
     * @param type The type of the Gate being counted.
     * @return The number of gates of type Type.
     */
    int getGateCount(GateType type) {

        int total = 0;

        for(Gate gate : gates){
            if(gate.getType().equals(type))
                total++;
        }
        return total;

    }

    int getLevel(String id){

        Gate tempG = gateLookup(id);
        Port tempP = portLookup(id);

        if(tempG != null){

            return tempG.getLevel();

        } else if(tempP != null){

            return tempP.getLevel();

        } else
            return 0;

    }

    /**
     * Adds a new Input to the schematic.
     *
     * @param id The unique name of the Input. All Inputs have only one port, an output.
     */
    void addInput(String id) {

        Port temp = new Port(INPUT, id, 0);
        ports.add(temp);

    }

    /**
     * Adds a new Output to the schematic.
     *
     * @param id The unique name of the Output. All Outputs have only one port, an input.
     */
    void addOutput(String id) {

        Port temp = new Port(OUTPUT, id, maxLevel + 1);
        ports.add(temp);

    }

    /**
     * Connects two Gates/Ports by adding one to the other's inputs list.
     *
     * @param lValue The gate on the left hand side.
     * @param rValue The gate on the right hand side.
     */
    void connect(String lValue, String rValue){

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

        int cxAxis = Constants.WINDOW_HEIGHT / 2; /* Axis of the window
        where y = total height / 2 */
        int cyAxis = Constants.WINDOW_WIDTH / 2; /* Axis of the window where
        x = total width / 2 */

//        constants.frame = true;

        //Template
        if (Constants.frame) {
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
            GateRenderer grenderer = new GateRenderer(this.renderer);

            int totalOuts = 0;
            int totalIns = 0;
            int skips = 0;

            Port tempP;
            Gate tempG;
//            Label label;
//            Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
//            Point labelPos;

            //Set coordinates of INPUT Ports.
            for (int i = 0; i < ports.size(); i++) {

                tempP = ports.get(i);
                if (tempP.getType().equals(INPUT)) {

                    tempP.setCX(getXCenter(0));
                    tempP.setCY(getYCenter(i - totalOuts));
//                    labelPos =
                            grenderer.render(tempP.getType(), tempP.getCX(), tempP.getCY());
//                    label = new Label(tempP.getID(), style);
//                    label.setPosition(labelPos.x, labelPos.y);
//                    stage.addActor(label);

                } else
                    totalOuts++;
            }

            //Set coordinates of Gates.
            for (int p = 1; p <= maxLevel; p++) {
                for (int j = 0; j < gates.size(); j++) {

                    tempG = gates.get(j);
                    if (tempG.getLevel() == p) {

                        tempG.setCX(getXCenter(p));

                        //Set CY based on the average height of all its inputs
                        tempG.setCY(getYCenter(tempG.getInputs(), j - skips));
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
            Port tempPl, tempPr;
            Gate tempGl, tempGr;
            int x1, x2, y1, y2;
            String gatePort;

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
                        assert tempGl != null;
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
                        assert tempPl != null;
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
                ArrayList<String> copyInputs = new ArrayList<>(tempGr.getInputs());
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
                            assert tempG != null;
                            if (tempG.getCY() > highestY)
                                highestY = tempG.getCY();
                        } else if (portLookup(input) != null) {
                            tempP = portLookup(input);
                            assert tempP != null;
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
                            assert tempG != null;
                            if (tempG.getCY() == highestY) {
                                sortedInputs.push(i);
                            }
                        } else if (portLookup(i) != null) {
                            tempP = portLookup(i);
                            assert tempP != null;
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
                        assert tempGl != null;
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
                        assert tempPl != null;
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
        //This block is just fancy for:
        /**
         * for(Port port : ports){
         * if(port.getID().equalsIgnoreCase("clk"){
         * for(Gate gate : gates){
         * if(gate.getType().equals(REG)){
         * drawClkLine(gate.getCX(), gate.getCY());
         * }}}}
         */
        {
            ports.stream().filter(port -> port.getID().equalsIgnoreCase
                    ("clk")).forEach(port -> gates.stream().filter(gate ->
                    gate.getType().equals(REG)).forEach(gate -> drawClkLine
                    (port, gate.getCX(), gate.getCY())));
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

    private void drawClkLine(Port clk, int cx, int cy){

        int clkx = clk.getPortX();
        int clky = clk.getPortY();

        int dy = cy - Constants.gateSize * Constants.scaleFactor / 2;

        drawSquareLine(clkx, clky, cx, dy, 0, 0, 255, 255);
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

        return constants.leftEdge + Constants.gateSize * Constants.scaleFactor / 2 + level * Constants.gateSize * Constants.scaleFactor * 2;

    }

    private int getYCenter(int row) {

        return  constants.bottomEdge + Constants.gateSize * Constants.scaleFactor / 2 + row * Constants.gateSize * Constants.scaleFactor * 2;

    }

    /**
     * All inputs must have coordinates or else this will error out.
     *
     * @param inputs Of the gate.
     * @param row Of the gate.
     * @return New y coordinate of the gate.
     */
    private int getYCenter(ArrayList<String> inputs, int row){

        int avgY = 0, numOfInputs = inputs.size();

        for(String id : inputs){

            if(gateLookup(id) != null) {
                avgY += gateLookup(id).getCY();
            }
            if(portLookup(id) != null) {
                avgY += portLookup(id).getCY();
            }
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

    private void clearData(){
        gates.clear();
        ports.clear();
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        this.clearData();
        renderer.dispose();
    }
}
