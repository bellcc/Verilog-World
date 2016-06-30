package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

import static edu.miamioh.schematicRenderer.GateType.*;

/**
 * This class describes all gates, including ports.
 * Created by shaffebd.
 */
class Gate {

    private SchematicRendererController controller = SchematicRendererController.getCurrentController();
    private GateType type;
    private String id = "";
    private ArrayList<String> inputs = new ArrayList<>();
    private int level = 0;
    private int cx = 0, cy = 0, rx = 0, ry = 0;
//    private float r = 0, g = 0, b = 0, a = 0;
    private ArrayList<Integer> inputYCoords = new ArrayList<>();
    private ArrayList<Integer> inputXCoords = new ArrayList<>();
    private float scaledGS = controller.gateSize * controller.scaleFactor;
    private Color color = Color.BLACK;

    /**
     * Gate constructor. Requires a GateType (enumerated class), a number of
     * numOfInputs, a unique ID, and a level (distance
     * from the numOfInputs).
     *
     * @param type  The type of gate to be created. Types can be found
     *              in {@link GateType}.
     * @param id    Unique identifier of the Gate.
     * @param level The level (right from 0) of the Gate. Levels are two
     *              scaled gate widths, center to center.
     */
    Gate(GateType type, String id, int level) {

        switch (type) {

            case AND:
                this.type = AND;
                this.id = id;
                this.level = level;
//                r = 255;
//                g = 0;
//                b = 0;
                setColor(Color.RED);
                break;

            case NAND:
                this.type = NAND;
                this.id = id;
                this.level = level;
//                r = 125;
//                g = 0;
//                b = 0;
                setColor(Color.PINK);
                break;

            case OR:
                this.type = OR;
                this.id = id;
                this.level = level;
//                r = 0;
//                g = 255;
//                b = 0;
                setColor(Color.GREEN);
                break;

            case NOR:
                this.type = NOR;
                this.id = id;
                this.level = level;
//                r = 0;
//                g = 125;
//                b = 0;
                setColor(Color.CYAN);
                break;

            case XOR:
                this.type = XOR;
                this.id = id;
                this.level = level;
//                r = 0;
//                g = 0;
//                b = 255;
                setColor(Color.BLUE);
                break;

            case XNOR:
                this.type = XNOR;
                this.id = id;
                this.level = level;
//                r = 0;
//                g = 0;
//                b = 125;
                setColor(Color.MAGENTA);
                break;

            case NOT:
                this.type = NOT;
                this.id = id;
                this.level = level;
//                r = 255;
//                g = 255;
//                b = 0;
                setColor(Color.ORANGE);
                break;

            case WIRE:
                this.type = WIRE;
                this.id = id;
                this.level = level;
//                r = 255;
//                g = 0;
//                b = 255;
                setColor(Color.DARK_GRAY);
                break;

            case REG:
                this.type = REG;
                this.id = id;
                this.level = level;
//                r = 0;
//                g = 255;
//                b = 255;
                setColor(Color.LIGHT_GRAY);
                break;

            case BLANK:
                this.type = BLANK;
                this.id = id;
                this.level = level;
//                r = 0;
//                g = 0;
//                b = 0;
                setColor(Color.BLACK);
                break;

            default:
                this.type = BLANK;
                this.level = 0;
                setColor(Color.BLACK);
                break;

        }
    }

    private void setInputXCoords() {

        switch (this.getType()) {

            case AND:
            case NAND:
                for (int i = 0; i < this.getNumOfInputs(); i++) {
                    inputXCoords.add((int)(this.cx - scaledGS / 2));
                }
                break;

            case NOT:
                inputXCoords.add((int)(this.cx - scaledGS / 2));
                break;

            case OR:
            case NOR:
            case XOR:
            case XNOR:
                for (int i = 0; i < this.getNumOfInputs(); i++) {
                    inputXCoords.add((int)(this.cx - scaledGS / 2));
                }
                break;

            case WIRE:
            case REG:
                inputXCoords.add((int)(this.cx - scaledGS / 2));
                break;

            default:
                inputXCoords.add((int)(this.cx - scaledGS / 2));
                break;

        }

    }

    private void setInputYCoords() {

        float y;
        float space;
        float inputs;

        switch (this.getType()) {

            case AND:
            case NAND:
                for (int i = 1; i <= this.getNumOfInputs(); i++) {

                    inputs = this.getNumOfInputs();
                    space = scaledGS / inputs;
                    y = this.cy - scaledGS / 2 - space / 2; //Start at the bottom corner minus half a space (to offset)
                    y += i * space; //Add height depending on the input
                    inputYCoords.add((int)y); //Add the coordinate to the list of heights

                }
                break;

            case NOT:
                inputYCoords.add(this.cy);
                break;

            case OR:
            case NOR:
            case XOR:
            case XNOR:
                for (int i = 1; i <= this.getNumOfInputs(); i++) {

                    inputs = this.getNumOfInputs();
                    space = scaledGS / inputs;
                    y = this.cy - scaledGS / 2 - space / 2; //Start at the bottom corner minus half a space (to offset)
                    y += i * space; //Add height depending on the input
                    inputYCoords.add((int)y); //Add the coordinate to the list of heights

                }
                break;

            case WIRE:
            case REG:
                inputYCoords.add(this.cy);
                break;

            default:
                inputYCoords.add(this.cy);
                break;

        }

    }

    private void setColor(Color color) {
//        this.r = r;
//        this.g = g;
//        this.b = b;
//        this.a = a;
        this.color = color;
    }

//    float getR() {
//        return this.r;
//    }
//
//    float getG() {
//        return this.g;
//    }
//
//    float getB() {
//        return this.b;
//    }
//
//    float getA() {
//        return this.a;
//    }

    Color getColor(){
        return this.color;
    }

    /**
     * Gets the type of the Gate.
     *
     * @return The instance type of the gate.
     */
    public GateType getType() {

        return this.type;

    }

    /**
     * Gets the unique ID of the Gate.
     *
     * @return The unique ID of this instance.
     */
    String getID() {

        return this.id;

    }

    /**
     * Gets the Center X coordinate of this Gate instance.
     *
     * @return The Center X coordinate of this Gate instance.
     */
    int getCX() {

        return this.cx;

    }

    /**
     * Sets the Center X coordinate of this Gate instance.
     *
     * @param cx An integer location of the Center X coordinate of this Gate
     *           instance. Relative to the bottom left corner of the window.
     */
    void setCX(int cx) {

        this.cx = cx;
        setInputXCoords();
        setRX();

    }

    /**
     * Gets the Reference x coord of this gate.
     * @return
     */
    int getRX(){return this.rx;}

    private void setRX(){
        switch (this.getType()) {
            case XOR:
            case XNOR:
                this.rx = (int)(cx - scaledGS / 4);

            case NOT:
            case AND:
            case NAND:
            case OR:
            case NOR:
            case WIRE:
            case REG:
                this.rx = (int)(cx - scaledGS / 2);
                break;

            default:
                this.rx = cx;
                break;
        }
    }

    /**
     * Gets the Center Y coordinate of this Gate instance.
     *
     * @return The Center Y coordinate of this Gate instance.
     */
    int getCY() {

        return this.cy;

    }

    /**
     * Sets the Center Y coordinate of this Gate instance.
     *
     * @param cy An integer location of the Center Y relative to the bottom
     *           left corner of the screen.
     */
    void setCY(int cy) {

        this.cy = cy;
        setInputYCoords();
        setRY();

    }

    /**
     * Gets the Reference y coord of this gate
     * @return
     */
    int getRY(){
        return this.ry;
    }

    private void setRY(){
        switch (this.getType()){
            case NOT:
            case WIRE:
                this.ry = (int)(cy - scaledGS / 4);
                break;

            case AND:
            case NAND:
            case OR:
            case NOR:
            case REG:
            case XOR:
            case XNOR:
                this.ry = (int)(cy - scaledGS / 2);
                break;

            default:
                this.ry = cy;
                break;

        }
    }

    /**
     * Get the number of numOfInputs contained in this Gate instance.
     *
     * @return The number of numOfInputs containd in this Gate instance.
     */
    int getNumOfInputs() {

        return this.inputs.size();

    }

    /**
     * Gets the X coordinate of a port of this Gate instance. Specified by
     * the gatePort variable. gatePort must be of format "{IN/OUT}~{port#}"
     * Port# go from 0 to numOfInputs - 1.
     *
     * @param gatePort The identifier of the port to get the X coordinate of.
     * @return The X coordinate of the specified port.
     */
    int getPortX(String gatePort) {

        String[] name = gatePort.split("~");
        int portNum = Integer.parseInt(name[1]);

        switch (this.getType()) {

            case AND:
            case NAND:
                if (name[0].equals("IN"))
                    return inputXCoords.get(portNum);
                else
                    return (int)(this.getCX() + scaledGS / 2);

            case OR:
            case NOR:
            case XOR:
            case XNOR:
                if (name[0].equals("IN"))
                    return inputXCoords.get(portNum);
                else
                    return (int)(this.getCX() + scaledGS / 2);

            case NOT:
                if (name[0].equals("IN"))
                    return inputXCoords.get(0);
                else
                    return (int)(this.getCX() + scaledGS / 2);

            case WIRE:
            case REG:
                if (name[0].equals("IN"))
                    return inputXCoords.get(0);
                else
                    return (int)(this.getCX() + scaledGS / 2);

            default:
                if (name[0].equals("IN"))
                    return inputXCoords.get(0);
                else
                    return (int)(this.getCX() + scaledGS / 2);

        }

    }

    /**
     * The same as getPortX, but gets the Y coordinate.
     *
     * @param gatePort The identifier of the port.
     * @return The Y coordinate of the port.
     */
    int getPortY(String gatePort) {

        String[] name = gatePort.split("~");
        int portNum = Integer.parseInt(name[1]);

        switch (this.getType()) {

            case AND:
            case NAND:
                if (name[0].equals("IN"))
                    return inputYCoords.get(portNum);
                else
                    return this.getCY();

            case OR:
            case NOR:
            case XOR:
            case XNOR:
                if (name[0].equals("IN"))
                    return inputYCoords.get(portNum);
                else
                    return this.getCY();

            case NOT:
                return this.getCY();

            case WIRE:
            case REG:
                return this.getCY();

            default:
                return this.getCY();

        }

    }

    /**
     * Gets the level of the Gate. Levels are two scaled gate sizes apart,
     * measured from the center of each gate.
     *
     * @return The level of the Gate.
     */
    public int getLevel() {
        return this.level;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    void addInput(String id) {

        inputs.add(id);

    }

    ArrayList<String> getInputs() {

        return this.inputs;

    }

}
