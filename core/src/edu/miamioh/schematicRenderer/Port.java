package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

import static edu.miamioh.schematicRenderer.GateType.BLANK;
import static edu.miamioh.schematicRenderer.SchematicRendererController.gateSize;
import static edu.miamioh.schematicRenderer.SchematicRendererController.scaleFactor;

/**
 * Creates a new Port, defined by a {@link GateType}, unique identifier and
 * level. Inputs are always level 0. Outputs are always one level higher then
 * their dependents.
 * 
 * Created by bdshaffer73.
 */
class Port {
    
    private GateType	      type;
    private ArrayList<String> inputs = new ArrayList<>();
    private String	      id     = "";
    private int		      level  = 0;
    private int		      cx     = 0, cy = 0, rx = 0, ry = 0;
    private Color	      color;
    
    /**
     * Port constructor. Takes a Port Type, Port ID, and Port Level and creates
     * a new Port. Port types can be found in
     * {@link edu.miamioh.simulator.WireRoleType}.
     *
     * @param type
     *            The type of the Port.
     * @param id
     *            The unique ID of the Port.
     * @param level
     *            The level of the Port, usually 0 or max Gate level + 1.
     */
    Port(GateType type, String id, int level) {
	
	switch (type) {
	    
	    case INPUT:
		this.type = type;
		this.id = id;
		setColor(Color.GRAY);
		break;
	    
	    case OUTPUT:
		this.type = type;
		this.id = id;
		this.level = level;
		setColor(Color.GRAY);
		break;
	    
	    default:
		this.type = BLANK;
		this.id = "INVALID_ASSIGNMENT";
		setColor(Color.GRAY);
		break;
	}
    }
    
    // Public methods
    
    /**
     * Returns a string of all useful data about this port.
     * 
     * @return A string of Port data.
     */
    public String toString() {
	return String.format(
		"ID: %s (%d,%d) level: %d, inputs " + inputs.toString(),
		getID(), getCX(), getCY(), getLevel());
    }
    
    // Package methods
    
    /**
     * Gets the color of this port.
     * 
     * @return The color of this port.
     */
    Color getColor() {
	return this.color;
    }
    
    /**
     * Gets the Type of the Port.
     *
     * @return The Type of the Port.
     */
    GateType getType() {
	return this.type;
    }
    
    /**
     * Gets the ID of the Port.
     *
     * @return The ID of the Port.
     */
    String getID() {
	return this.id;
    }
    
    /**
     * Gets the Level of the Port.
     *
     * @return The Level of the Port.
     */
    int getLevel() {
	return this.level;
    }
    
    /**
     * Sets the level of the port.
     * 
     * @param newLevel
     *            The new level of the port.
     */
    void setLevel(int newLevel) {
	this.level = newLevel;
    }
    
    /**
     * Gets the Center X coordinate of the Port.
     *
     * @return The Center X coordinate of the Port.
     */
    int getCX() {
	return this.cx;
    }
    
    /**
     * Sets the Center X coordinate of the Port.
     *
     * @param cx
     *            An integer of the Center X coordinate of the Port.
     */
    void setCX(int cx) {
	this.cx = cx;
	this.setRX();
    }
    
    /**
     * Gets the Reference (bottom left) x value of this port.
     * 
     * @return
     */
    int getRX() {
	return this.rx;
    }
    
    /**
     * Gets the Center Y coordinate of the Port.
     *
     * @return The Center Y coordinate of the Port.
     */
    int getCY() {
	return this.cy;
    }
    
    /**
     * Sets the Center Y coordinate of the Port.
     *
     * @param cy
     *            An integer of the Center Y coordinate of the Port.
     */
    void setCY(int cy) {
	this.cy = cy;
	this.setRY();
    }
    
    /**
     * Gets the Reference y of this port.
     * 
     * @return
     */
    int getRY() {
	return this.ry;
    }
    
    /**
     * Gets the X coordinate of the Port input/output port.
     *
     * @return The X coordinate of the Port port.
     */
    int getPortX() {
	switch (this.getType()) {
	    
	    case INPUT:
		return (int) (this.getCX() + gateSize * scaleFactor / 2);
	    
	    case OUTPUT:
		return (int) (this.getCX() - gateSize * scaleFactor / 2);
	    
	    default:
		return 0;
	}
    }
    
    /**
     * Gets the Y coordinate of the Port input/output port.
     *
     * @return The Y coordinate of the Port port.
     */
    int getPortY() {
	return this.getCY();
    }
    
    /**
     * Adds an input to the port.
     * 
     * @param id
     *            The unique identifier of the new input.
     */
    void addInput(String id) {
	inputs.add(id);
    }
    
    /**
     * Gets the list of inputs of the port.
     * 
     * @return The inputs list.
     */
    ArrayList<String> getInputs() {
	return this.inputs;
    }
    
    // Private methods
    
    private void setColor(Color color) {
	this.color = color;
    }
    
    private void setRX() {
	
	switch (this.getType()) {
	    
	    case INPUT:
		this.rx = (int) (cx - gateSize * scaleFactor / 2);
		break;
	    
	    case OUTPUT:
		this.rx = (int) (cx - gateSize * scaleFactor / 4);
		break;
	    
	    default:
		this.rx = cx;
		break;
	}
    }
    
    private void setRY() {
	this.ry = (int) (cy - gateSize * scaleFactor / 4);
    }
}
