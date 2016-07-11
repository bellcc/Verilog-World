package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.Stack;

import static edu.miamioh.schematicRenderer.GateType.INPUT;
import static edu.miamioh.schematicRenderer.GateType.OUTPUT;
import static edu.miamioh.schematicRenderer.GateType.REG;
import static edu.miamioh.schematicRenderer.SchematicRendererController.*;

/**
 * Created by bdshaffer73.
 */
class SchematicRenderer implements Disposable {

    private ArrayList<Gate> gates = new ArrayList<>();
    private ArrayList<Port> ports = new ArrayList<>();
    private SchematicRendererController controller;
    private ShapeRenderer renderer;
    private Stage schematicStage;
//    private SchematicRendererScreen schematicScreen;
    private ParseTree root_tree;

    private int maxLevel = 0;
    private int l1Gates = 0;//inCount = 0
    private boolean compiled = false;

    /**
     * This constructor expects the root_tree and renderer to be set before rendering.
     */
    SchematicRenderer() {

        controller = SchematicRendererController.getCurrentController();
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

//    void setSchematicScreen(SchematicRendererScreen screen){
//        this.schematicScreen = screen;
//    }
//

    /**
     * Gets the status of the schematic.
     * @return True if the root_tree has been visited and the schematic has built the gates and
     * ports lists.
     */
    boolean is_Compiled(){
        return this.compiled;
    }

    /**
     * Sets the compilation status.
     * @param status Whether or not the schematic has been compiled yet.
     */
    private void is_Compiled(boolean status){
        this.compiled = status;
    }

    //Methods for setting up the render

    /**
     * Runs the SchematicVisitor to collect Ports, Gates, and relationships from the root_tree.
     */
    void getData(){
        if(root_tree != null) {
            SchematicVisitor visitor = new SchematicVisitor(this);
            visitor.visit(root_tree);
            this.is_Compiled(true);
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
    public void render(Stage schematicStage) {

        //Set the background color to white.
//        Gdx.gl.glClearColor(255, 255, 255, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer = new ShapeRenderer();

        int cxAxis = SchematicRendererController.getCurrentController().getWindowHeight() / 2; /* Axis of the window
        where y = total height / 2 */
        int cyAxis = SchematicRendererController.getCurrentController().getWindowWidth() / 2; /* Axis of the window
        where
        x = total width / 2 */

//        frame = true;

        //Template
        if (frame) {
        	renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(Color.BLUE);
            renderer.line(leftEdge, cxAxis, rightEdge, cxAxis);
            renderer.line(cyAxis, bottomEdge, cyAxis, topEdge);
            renderer.rect(leftEdge, bottomEdge, rightEdge - leftEdge, topEdge - bottomEdge);//Draw a box to show the edges of the schematic.
            renderer.end();
        }

        //Actual drawing
        this.schematicStage = schematicStage;
        renderHelper();

//        schematicStage.act(Gdx.graphics.getDeltaTime());
//        schematicStage.draw();
    }

    // Private methods to get things done

    private void renderHelper() {

        Color color;

        //Render Gates and Ports
        {
            GateRenderer grenderer = new GateRenderer(this.renderer);

            int totalOuts = 0;
            int totalIns = 0;
            int skips = 0;
            l1Gates = 0;
            int numOfGatesHoriz = 0;
            int numOfGatesVert = 0;

            Port tempP;
            Gate tempG;

            /*Determine a proper scalingFactor by the greatest number of gates in either
            direction, divided by the gateSize and the number of gates in that direction.*/
            //Count the number of INPUTs vertically
            for(Port port : ports){
                if(port.getType().equals(INPUT)) {
                    numOfGatesHoriz = 1;
                    numOfGatesVert++;
                }
            }

            //Count the number of Gates vertically on the first level. The first level should set
            // the vertical height because every other level must fit within it.
            numOfGatesHoriz = (maxLevel + 1 > numOfGatesHoriz)? maxLevel + 1 : numOfGatesHoriz;
            for (int i = 0; i < gates.size(); i++){
                tempG = gates.get(i);
                if(tempG.getLevel() == 1) {
                    numOfGatesVert = (i - skips > numOfGatesVert) ? i - skips : numOfGatesVert;
                    l1Gates++;
                } else
                    skips++;
            }

            //Count the number of OUTPUTs vertically.
            for(int i = 0; i < ports.size(); i++){
                tempP = ports.get(i);
                if(tempP.getType().equals(OUTPUT))
                    numOfGatesVert = (i - totalIns > numOfGatesVert)? i - totalIns : numOfGatesVert;
                else
                    totalIns++;
            }

            float height = controller.getWindowHeight();
            float width = controller.getWindowWidth();

            //Assume 1 extra gate horizontally as the OUTPUT level.
            if(numOfGatesVert > numOfGatesHoriz + 1){
                scaleFactor = (int)(height / gateSize / numOfGatesVert);
//                System.out.println(height + " h, " + scaleFactor);
//                scaleFactor = 40;
            } else {
                scaleFactor = (int)(width / gateSize / (numOfGatesHoriz * 2 + 1));
//                System.out.println(width + " w, " + scaleFactor);
//                scaleFactor = 40;
            }

            //If the calculated scalefactor is too big or too small, limit it.
            scaleFactor = (scaleFactor > 100)? 100 : scaleFactor;
            scaleFactor = (scaleFactor < 20)? 20 : scaleFactor;

            Label nametag;
            BitmapFont bfont = new BitmapFont();
            Label.LabelStyle style = new Label.LabelStyle(bfont, Color.BLACK);
            int nx, ny, inTopY = 0;
//                    , l1TopY = 0;

            //Place the gates by their level, scaled gateSize and level multiplicity.
            //Set coordinates of INPUT Ports.
            for (int i = 0; i < ports.size(); i++) {

                tempP = ports.get(i);
                if (tempP.getType().equals(INPUT)) {

                    tempP.setCX(getXCenter(0));
                    tempP.setCY(getYCenter(i - totalOuts));
//                    inCount++;
                    inTopY = (tempP.getCY() > inTopY)? tempP.getCY() : inTopY;

                    //Add a nametag to the gate
                    nx = tempP.getRX();
                    ny = tempP.getRY();
                    nametag = new Label(tempP.getID(), style);
                    nametag.setPosition(nx, ny);
                    schematicStage.addActor(nametag);

                } else
                   totalOuts++;
            }

            //Set coordinates of Gates.
//            boolean attempt;
//            do {
//                l1Gates = 0;
//                attempt = false;
                skips = 0;
                for (int p = 1; p <= maxLevel; p++) {
                    for (int i = 0; i < gates.size(); i++) {

                        tempG = gates.get(i);
                        if (tempG.getLevel() == p) {

                            tempG.setCX(getXCenter(p));

                            //Change the algorithm for setting the cy of Gates so that the first
                            // Level spreads out the following ones.
                            if (p == 1) {
//                                if(!attempt) {
//                                    //If an attempt to place the first level hasn't been made...
//                                    //Place the first Level stacked evenly
//                                    tempG.setCY(getYCenter(i - skips));
//                                    l1TopY = (tempG.getCY() > l1TopY) ? tempG.getCY() : l1TopY; //Get the top l1 y
//                                    l1Gates++; //Count the number of gates in l1
//                                    attempt = true; //Mark that an attempt has been made
//                                } else {
//                                    //If stacking evenly wasn't spread out enough, spread them as high as the inputs.
//                                    tempG.setCY(getYAdj(i - skips, inTopY));
//                                    l1TopY = (tempG.getCY() > l1TopY)? tempG.getCY() : l1TopY;
//                                }
                                //Spread out l1 across the whole height
                                tempG.setCY(getYAdj(i - skips));
                            } else //Place following levels by the average height of their inputs.
                                tempG.setCY(getYCenter(tempG.getInputs()));

                            //Add a nametag to the gate
                            nx = tempG.getRX();
                            ny = tempG.getRY();
                            nametag = new Label(tempG.getID(), style);
                            nametag.setPosition(nx, ny);
                            schematicStage.addActor(nametag);

                        } else
                            skips++;
                    }
                    skips = 0;
                }
//            } while ((l1TopY < inTopY || !attempt) && l1Gates > 1);

            //Set coordinates of OUTPUT Ports.
            for (Port port : ports) {

                tempP = port;
                if (tempP.getType().equals(OUTPUT)) {

                    tempP.setCX(getXCenter(maxLevel + 1));

                    //OUTUPUTs are always placed based on the average of their inputs' heights.
                    tempP.setCY(getYCenter(tempP.getInputs()));

                    //Add a nametag to the gate
                    nx = tempP.getRX();
                    ny = tempP.getRY();
                    nametag = new Label(tempP.getID(), style);
                    nametag.setPosition(nx, ny);
                    schematicStage.addActor(nametag);

                }
            }

            //Render all the ports.
            for(Port port : ports){
                grenderer.render(port.getType(), port.getCX(), port.getCY(), port.getColor());
            }

            //Render all the gates.
            for(Gate gate : gates){
                grenderer.render(gate.getType(), gate.getCX(), gate.getCY(), gate.getColor());
            }
        }

        //Render Gate port connections
        {
            Port tempPl, tempPr;
            Gate tempGl, tempGr;
            int x1, x2, y1, y2;
            String gatePort;

            //Connect all OUTPUT Ports to their inputs.
            for(Port port : ports){
                tempPr = port;
                for(String id : tempPr.getInputs()){
                    x2 = tempPr.getPortX();
                    y2 = tempPr.getPortY();

                    //Check if the left-hand ID is a gate; if a gate, get
                    // its output port coordinates.
                    if(gateLookup(id) != null){
                        tempGl = gateLookup(id);
                        gatePort = "OUT~0";
                        assert tempGl != null;
                        color = tempGl.getColor();
                        x1 = tempGl.getPortX(gatePort);
                        y1 = tempGl.getPortY(gatePort);
                        drawSquareLine(x1, y1, x2, y2, color);

                        //Check if the left-hand ID is a port; if a port,
                        // get its output coordinates.
                    } else if(portLookup(id) != null){
                        tempPl = portLookup(id);
                        assert tempPl != null;
                        color = tempPl.getColor();
                        x1 = tempPl.getPortX();
                        y1 = tempPl.getPortY();
                        drawSquareLine(x1, y1, x2, y2, color);
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
                                j = copyInputs.size();
                            }
                        } else if (portLookup(i) != null) {
                            tempP = portLookup(i);
                            assert tempP != null;
                            if (tempP.getCY() == highestY) {
                                sortedInputs.push(i);
                                j = copyInputs.size();
                            }
                        }
                    }
                    copyInputs.remove(sortedInputs.peek());
                }

                String id;

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
                        color = tempGl.getColor();
                        x1 = tempGl.getPortX(gatePort);
                        y1 = tempGl.getPortY(gatePort);
                        drawSquareLine(x1, y1, x2, y2, color);

                        //Check if the left-hand ID is a port; if a port, get
                        // its output coordinates.
                    } else if(portLookup(id) != null){
                        tempPl = portLookup(id);
                        assert tempPl != null;
                        color = tempPl.getColor();
                        x1 = tempPl.getPortX();
                        y1 = tempPl.getPortY();
                        drawSquareLine(x1, y1, x2, y2, color);
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

    private void drawSquareLine(int x1, int y1, int x2, int y2, Color color){

        int xm = (x1 + x2) / 2;

        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(color);
        renderer.rectLine(x1, y1, xm, y1, scaleFactor / 20);
        renderer.rectLine(xm, y1, xm, y2, scaleFactor / 20);
        renderer.rectLine(xm, y2, x2, y2, scaleFactor / 20);
        this.renderer.end();

    }

    private void drawClkLine(Port clk, int cx, int cy){

        int clkx = clk.getPortX();
        int clky = clk.getPortY();

        int dy = (int)(cy - gateSize * scaleFactor / 2);

        drawSquareLine(clkx, clky, cx, dy, Color.BLUE);
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
        return (int)(leftEdge + gateSize * scaleFactor / 2 + level *
                gateSize * scaleFactor * 2);
    }

    private int getYCenter(int row) {

        return  (int)(bottomEdge + gateSize * scaleFactor / 2 + row *
                gateSize * scaleFactor * 2);

    }

    /**
     * All inputs must have coordinates or else this will error out.
     *
     * @param inputs Of the gate.
     * @return New y coordinate of the gate.
     */
    private int getYCenter(ArrayList<String> inputs){

        int avgY = 0, numOfInputs = inputs.size();

        for(String id : inputs){
            if(gateLookup(id) != null)
                avgY += gateLookup(id).getCY();
            if(portLookup(id) != null)
                avgY += portLookup(id).getCY();
        }

        if(numOfInputs != 0)
            avgY /= numOfInputs;

        return avgY;
    }

    private int getYAdj(int row){
        float ydiff = topEdge - bottomEdge;
        return (int)(row * (ydiff / l1Gates) + bottomEdge);
    }

    /**
     * Clears the lists so that new data can be collected.
     */
    void clearData(){
        gates.clear();
        ports.clear();
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        renderer.dispose();
        schematicStage.dispose();
    }
}
