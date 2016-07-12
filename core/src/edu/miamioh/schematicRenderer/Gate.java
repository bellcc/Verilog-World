package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

import static edu.miamioh.schematicRenderer.GateType.*;
import static edu.miamioh.schematicRenderer.SchematicRendererController.gateSize;
import static edu.miamioh.schematicRenderer.SchematicRendererController.scaleFactor;

/**
 * This class describes all gates. Gates have inputs, center (x,y) coordinates,
 * reference (r) coordinates, a GateType, a Level, and a color. Color is set
 * automatically.
 * 
 * Created by bdshaffer73.
 */
class Gate {
	
	private GateType		   type;
	private String			   id			= "";
	private ArrayList<String>  inputs		= new ArrayList<>();
	private int				   level		= 0;
	private int				   cx			= 0, cy = 0, rx = 0, ry = 0;
	private ArrayList<Integer> inputYCoords	= new ArrayList<>();
	private ArrayList<Integer> inputXCoords	= new ArrayList<>();
	private float			   scaledGS;
	private Color			   color		= Color.BLACK;
	
	/**
	 * Creates a new Gate. Requires a {@link GateType}, a unique ID, and a level
	 * (distance from the inputs).
	 *
	 * @param type
	 *            The type of gate to be created. Types can be found in
	 *            {@link GateType}.
	 * @param id
	 *            Unique identifier of the Gate.
	 * @param level
	 *            The level (right from 0) of the Gate. Levels are two scaled
	 *            gate widths, center to center.
	 */
	Gate(GateType type, String id, int level) {
		
		updateSGS();
		
		switch (type) {
			
			case AND:
				this.type = AND;
				this.id = id;
				this.level = level;
				setColor(Color.RED);
				break;
			
			case NAND:
				this.type = NAND;
				this.id = id;
				this.level = level;
				setColor(Color.RED);
				break;
			
			case OR:
				this.type = OR;
				this.id = id;
				this.level = level;
				setColor(Color.PURPLE);
				break;
			
			case NOR:
				this.type = NOR;
				this.id = id;
				this.level = level;
				setColor(Color.PURPLE);
				break;
			
			case XOR:
				this.type = XOR;
				this.id = id;
				this.level = level;
				setColor(Color.BLUE);
				break;
			
			case XNOR:
				this.type = XNOR;
				this.id = id;
				this.level = level;
				setColor(Color.BLUE);
				break;
			
			case NOT:
				this.type = NOT;
				this.id = id;
				this.level = level;
				setColor(Color.ORANGE);
				break;
			
			case WIRE:
				this.type = WIRE;
				this.id = id;
				this.level = level;
				setColor(Color.DARK_GRAY);
				break;
			
			case REG:
				this.type = REG;
				this.id = id;
				this.level = level;
				setColor(Color.DARK_GRAY);
				break;
			
			case BLANK:
				this.type = BLANK;
				this.id = id;
				this.level = level;
				setColor(Color.BLACK);
				break;
			
			default:
				this.type = BLANK;
				this.level = 0;
				setColor(Color.BLACK);
				break;
			
		}
	}
	
	// Public methods
	
	/**
	 * Returns a string of all useful information about this gate.
	 * 
	 * @return Info about this gate.
	 */
	public String toString() {
		return String.format(
				"ID: %s (%d,%d) level: %d inX's " + inputXCoords.toString()
						+ ",%ninY's " + inputYCoords.toString() + ", inputs "
						+ inputs.toString(),
				getID(), getCX(), getCY(), getLevel());
	}
	
	// Package methods
	
	/**
	 * Gets the level of the Gate. Levels are two scaled gate sizes apart,
	 * measured from the center of each gate.
	 *
	 * @return The level of the Gate.
	 */
	int getLevel() {
		return this.level;
	}
	
	/**
	 * Gets the type of the Gate.
	 *
	 * @return The instance type of the gate.
	 */
	GateType getType() {
		return this.type;
	}
	
	/**
	 * Sets the level of the Gate. Levels are two scaled gate sizes apart.
	 * 
	 * @param newLevel
	 *            The new level to assign to the gate.
	 */
	void setLevel(int newLevel) {
		this.level = newLevel;
	}
	
	/**
	 * Gets the color of this gate.
	 * 
	 * @return The gate color.
	 */
	Color getColor() {
		return this.color;
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
	 * @param cx
	 *            An integer location of the Center X coordinate of this Gate
	 *            instance. Relative to the bottom left corner of the window.
	 */
	void setCX(int cx) {
		this.cx = cx;
		setInputXCoords();
		setRX();
	}
	
	/**
	 * Gets the Reference x coord of this gate.
	 * 
	 * @return The Reference X.
	 */
	int getRX() {
		return this.rx;
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
	 * @param cy
	 *            An integer location of the Center Y relative to the bottom
	 *            left corner of the screen.
	 */
	void setCY(int cy) {
		this.cy = cy;
		setInputYCoords();
		setRY();
	}
	
	/**
	 * Gets the Reference y coord of this gate
	 * 
	 * @return The Reference Y.
	 */
	int getRY() {
		return this.ry;
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
	 * Gets the X coordinate of a port of this Gate instance. Specified by the
	 * gatePort variable. gatePort must be of format "{IN/OUT}~{port#}" Port# go
	 * from 0 to numOfInputs - 1.
	 *
	 * @param gatePort
	 *            The identifier of the port to get the X coordinate of.
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
					return (int) (this.getCX() + scaledGS / 2);
				
			case OR:
			case NOR:
			case XOR:
			case XNOR:
				if (name[0].equals("IN"))
					return inputXCoords.get(portNum);
				else
					return (int) (this.getCX() + scaledGS / 2);
				
			case NOT:
				if (name[0].equals("IN"))
					return inputXCoords.get(0);
				else
					return (int) (this.getCX() + scaledGS / 2);
				
			case WIRE:
			case REG:
				if (name[0].equals("IN"))
					return inputXCoords.get(0);
				else
					return (int) (this.getCX() + scaledGS / 2);
				
			default:
				if (name[0].equals("IN"))
					return inputXCoords.get(0);
				else
					return (int) (this.getCX() + scaledGS / 2);
				
		}
		
	}
	
	/**
	 * The same as getPortX, but gets the Y coordinate.
	 *
	 * @param gatePort
	 *            The identifier of the port.
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
	 * Adds an input to the gate by name.
	 * 
	 * @param id
	 *            The unique identifier of the input.
	 */
	void addInput(String id) {
		inputs.add(id);
	}
	
	/**
	 * Gets the list of inputs the gate has.
	 * 
	 * @return The list of inputs.
	 */
	ArrayList<String> getInputs() {
		return this.inputs;
	}
	
	// Private methods.
	
	private void setRY() {
		
		switch (this.getType()) {
			case NOT:
			case WIRE:
				this.ry = (int) (cy - scaledGS / 4);
				break;
			
			case AND:
			case NAND:
			case OR:
			case NOR:
			case REG:
			case XOR:
			case XNOR:
				this.ry = (int) (cy - scaledGS / 2);
				break;
			
			default:
				this.ry = cy;
				break;
			
		}
	}
	
	private void setRX() {
		switch (this.getType()) {
			case XOR:
			case XNOR:
				this.rx = (int) (cx - scaledGS / 4);
				
			case NOT:
			case AND:
			case NAND:
			case OR:
			case NOR:
			case WIRE:
			case REG:
				this.rx = (int) (cx - scaledGS / 2);
				break;
			
			default:
				this.rx = cx;
				break;
		}
	}
	
	private void updateSGS() {
		scaledGS = gateSize * scaleFactor;
	}
	
	private void setInputXCoords() {
		
		updateSGS();
		inputXCoords.clear();
		
		switch (this.getType()) {
			
			case AND:
			case NAND:
				for (int i = 0; i < this.getNumOfInputs(); i++) {
					inputXCoords.add((int) (this.cx - scaledGS / 2));
				}
				break;
			
			case NOT:
				inputXCoords.add((int) (this.cx - scaledGS / 2));
				break;
			
			case OR:
			case NOR:
			case XOR:
			case XNOR:
				for (int i = 0; i < this.getNumOfInputs(); i++) {
					inputXCoords.add((int) (this.cx - scaledGS / 2));
				}
				break;
			
			case WIRE:
			case REG:
				inputXCoords.add((int) (this.cx - scaledGS / 2));
				break;
			
			default:
				inputXCoords.add((int) (this.cx - scaledGS / 2));
				break;
			
		}
		
	}
	
	private void setInputYCoords() {
		
		updateSGS();
		inputYCoords.clear();
		
		float y;
		float space;
		float inputs;
		
		switch (this.getType()) {
			
			case AND:
			case NAND:
				for (int i = 1; i <= this.getNumOfInputs(); i++) {
					
					inputs = this.getNumOfInputs();
					space = scaledGS / inputs;
					y = this.cy - scaledGS / 2 - space / 2; // Start at the
					// bottom corner
					// minus half a
					// space (to offset)
					y += i * space; // Add height depending on the input
					inputYCoords.add((int) y); // Add the coordinate to the list
					// of heights
					
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
					y = this.cy - scaledGS / 2 - space / 2; // Start at the
					// bottom corner
					// minus half a
					// space (to offset)
					y += i * space; // Add height depending on the input
					inputYCoords.add((int) y); // Add the coordinate to the list
					// of heights
					
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
		this.color = color;
	}
}
