package edu.miamioh.schematicRenderer;

import edu.miamioh.util.Constants;

import static edu.miamioh.schematicRenderer.GateType.BLANK;

/**
 * Created by shaffebd.
 */
class Port {

    private GateType type;
    private String id = "";
    private int level = 0;
    private int cx = 0, cy = 0;

    /**
     * Port constructor. Takes a Port Type, Port ID, and Port Level and
     * creates a new Port. Port types can be found in
     * {@link edu.miamioh.simulator.WireRoleType}.
     *
     * @param type  The type of the Port.
     * @param id    The unique ID of the Port.
     * @param level The level of the Port, usually 0 or max Gate level + 1.
     */
    public Port(GateType type, String id, int level) {

        switch (type) {

            case INPUT:
                this.type = type;
                this.id = id;
                break;

            case OUTPUT:
                this.type = type;
                this.id = id;
                this.level = level;
                break;

            default:
                this.type = BLANK;
                this.id = "INVALID_ASSIGNMENT";
                break;

        }

    }

    /**
     * Gets the Type of the Port.
     *
     * @return The Type of the Port.
     */
    public GateType getType() {

        return this.type;

    }

    /**
     * Gets the ID of the Port.
     *
     * @return The ID of the Port.
     */
    public String getID() {

        return this.id;

    }

    /**
     * Gets the Level of the Port.
     *
     * @return The Level of the Port.
     */
    public int getLevel() {

        return this.level;

    }

    /**
     * Gets the Center X coordinate of the Port.
     *
     * @return The Center X coordinate of the Port.
     */
    public int getCX() {

        return this.cx;

    }

    /**
     * Sets the Center X coordinate of the Port.
     *
     * @param cx An integer of the Center X coordinate of the Port.
     */
    public void setCX(int cx) {

        this.cx = cx;

    }

    /**
     * Gets the Center Y coordinate of the Port.
     *
     * @return The Center Y coordinate of the Port.
     */
    public int getCY() {

        return this.cy;

    }

    /**
     * Sets the Center Y coordinate of the Port.
     *
     * @param cy An integer of the Center Y coordinate of the Port.
     */
    public void setCY(int cy) {

        this.cy = cy;

    }

    /**
     * Gets the X coordinate of the Port input/output port.
     *
     * @return The X coordinate of the Port port.
     */
    public int getPortX() {

        Constants constants = new Constants();

        switch (this.getType()) {

            case INPUT:
                return this.getCX() + constants.gateSize * constants.scaleFactor / 2;

            case OUTPUT:
                return this.getCX() - constants.gateSize * constants.scaleFactor / 2;

            default:
                return 0;

        }

    }

    /**
     * Gets the Y coordinate of the Port input/output port.
     *
     * @return The Y coordinate of the Port port.
     */
    public int getPortY() {

        return this.getCY();

    }
}
