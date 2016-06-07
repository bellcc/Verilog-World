package edu.miamioh.schematicRenderer;

import edu.miamioh.util.Constants;

import java.util.ArrayList;

import static edu.miamioh.schematicRenderer.GateType.*;

/**
 * This class describes all gates, including ports.
 * Created by shaffebd.
 */
class Gate {

    Constants constants = new Constants();

    private GateType type;
    private String id = "";
    private int level = 0;
    private int cx = 0, cy = 0;
    private int inputs = 0;
    private ArrayList<Integer> inputYCoords = new ArrayList<>();
    private ArrayList<Integer> inputXCoords = new ArrayList<>();
    private int scaledGS = constants.gateSize * constants.scaleFactor;

    /**
     * Gate default constructor. WILL ERROR OUT IF ATTEMPTED TO RENDER.
     */
    public Gate() {
        this(null, 0, null, 0);
    }

    /**
     * Gate constructor. Requires a GateType (enumerated class), a number of
     * inputs, a unique ID, and a level (distance
     * from the inputs).
     *
     * @param type        The type of gate to be created. Types can be found
     *                    in {@link GateType}.
     * @param numOfInputs The number of Inputs the gate should have. May be
     *                    changed later.
     * @param id
     * @param level
     */
    public Gate(GateType type, int numOfInputs, String id, int level) {

        switch (type) {

            case AND:
                this.type = AND;
                this.setNumOfInputs(numOfInputs);
                this.id = id;
                this.level = level;
                break;

            case OR:
                this.type = OR;
                this.setNumOfInputs(numOfInputs);
                this.id = id;
                this.level = level;
                break;

            case XOR:
                this.type = XOR;
                this.setNumOfInputs(numOfInputs);
                this.id = id;
                this.level = level;
                break;

            case NOT:
                this.type = NOT;
                this.setNumOfInputs(1);
                this.id = id;
                this.level = level;
                break;

            case WIRE:
                this.type = WIRE;
                this.setNumOfInputs(1);
                this.id = id;
                this.level = level;
                break;

            case REG:
                this.type = REG;
                this.setNumOfInputs(1);
                this.id = id;
                this.level = level;
                break;

            case BLANK:
                this.type = BLANK;
                this.setNumOfInputs(1);
                this.id = id;
                this.level = level;
                break;

//            case MODULE:
//                this.type = type;
//                this.setNumOfInputs(numOfInputs);
//                this.setNumOfOutputs(numOfOutputs);

            default:
                this.type = BLANK;
                this.setNumOfInputs(0);
                this.id = "INVALID_ENTRY";
                this.level = 0;
                break;

        }

    }

    private void setInputXCoords() {

        switch (this.getType()) {

            case AND:

                for (int i = 0; i < this.getNumOfInputs(); i++) {
                    inputXCoords.add(this.cx - scaledGS / 2);
                }
                break;

            case NOT:
                inputXCoords.add(this.cx - scaledGS / 2);
                break;

            case OR:
            case XOR:
                for (int i = 0; i < this.getNumOfInputs(); i++) {
                    inputXCoords.add(this.cx - scaledGS / 2);
                }
                break;

        }

    }

    private void setInputYCoords() {

        int y;

        switch (this.getType()) {

            case AND:
                for (int i = 1; i <= this.getNumOfInputs(); i++) {

                    y = this.cy - scaledGS / 2 - scaledGS / (this.getNumOfInputs() * 2) + (int) ((i / (float) this.getNumOfInputs()) * (scaledGS)); //casting inspired by Connor
                    inputYCoords.add(y);

                }
                break;

            case NOT:
                inputYCoords.add(this.cy);
                break;

            case OR:
            case XOR:
                for (int i = 1; i <= this.getNumOfInputs(); i++) {

                    y = this.cy - scaledGS / 2 - scaledGS / (this.getNumOfInputs() * 2) + (int) ((i / (float) this.getNumOfInputs()) * (scaledGS)); //casting inspired by Connor
                    inputYCoords.add(y);

                }
                break;

        }

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
    public String getID() {

        return this.id;

    }

    /**
     * Gets the Center X coordinate of this Gate instance.
     *
     * @return The Center X coordinate of this Gate instance.
     */
    public int getCX() {

        return this.cx;

    }

    /**
     * Sets the Center X coordinate of this Gate instance.
     *
     * @param cx An integer location of the Center X coordinate of this Gate
     *           instance. Relative to the bottom left corner of the window.
     */
    public void setCX(int cx) {

        this.cx = cx;
        setInputXCoords();

    }

    /**
     * Gets the Center Y coordinate of this Gate instance.
     *
     * @return The Center Y coordinate of this Gate instance.
     */
    public int getCY() {

        return this.cy;

    }

    /**
     * Sets the Center Y coordinate of this Gate instance.
     *
     * @param cy An integer location of the Center Y relative to the bottom
     *           left corner of the screen.
     */
    public void setCY(int cy) {

        this.cy = cy;
        setInputYCoords();

    }

    /**
     * Get the number of inputs contained in this Gate instance.
     *
     * @return The number of inputs containd in this Gate instance.
     */
    public int getNumOfInputs() {

        return this.inputs;

    }

    /**
     * Sets the number of inputs this Gate instance has.
     *
     * @param numOfInputs An integer number of inputs this gate should contain.
     */
    public void setNumOfInputs(int numOfInputs) {

        this.inputs = numOfInputs;

    }

    /**
     * Gets the X coordinate of a port of this Gate instance. Specified by
     * the gatePort variable. gatePort must be of format "{IN/OUT}~{port#}"
     * Port# go from 0 to numOfInputs - 1.
     *
     * @param gatePort The identifier of the port to get the X coordinate of.
     * @return The X coordinate of the specified port.
     */
    public int getPortX(String gatePort) {

        String[] name = gatePort.split("~");
        int portNum = Integer.parseInt(name[1]);

        switch (this.getType()) {

            case AND:
                if (name[0].equals("IN"))
                    return inputXCoords.get(portNum);
                else
                    return this.getCX() + scaledGS / 2;

            case OR:
            case XOR:
                if (name[0].equals("IN"))
                    return inputXCoords.get(portNum);
                else
                    return this.getCX() + scaledGS / 2;

            case NOT:
                if (name[0].equals("IN"))
                    return inputXCoords.get(0);
                else
                    return this.getCX() + scaledGS / 2;

            default:
                return 0;

        }

    }

    /**
     * The same as getPortX, but gets the Y coordinate.
     *
     * @param gatePort The identifier of the port.
     * @return The Y coordinate of the port.
     */
    public int getPortY(String gatePort) {

        String[] name = gatePort.split("~");
        int portNum = Integer.parseInt(name[1]);

        switch (this.getType()) {

            case AND:
                if (name[0].equals("IN"))
                    return inputYCoords.get(portNum);
                else
                    return this.getCY();

            case OR:
            case XOR:
                if (name[0].equals("IN"))
                    return inputYCoords.get(portNum);
                else
                    return this.getCY();

            case NOT:
                return this.getCY();

            default:
                return 0;

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

}
