
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.GameObjects;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.worldEditor.WorldEditorController;

public abstract class Block {

	private static RootModuleSimulator rootSim;
	
	//Modifiable variables.
	private Color color;
	private int row;
	private int column;
	private int id;
	
	public Block() {

		this(0, 0);
	}
	
	public Block(int row, int column) {

		setRow(row);
		setColumn(column);
	}
	
	public Block(int id) {
		this.id = id;
	}
	
	public Block(int row, int column, Color color) {
		
		setRow(row);
		setColumn(column);
		setColor(color);
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
		
	public static void setRootSim(RootModuleSimulator sim) {rootSim = sim;}
	public static RootModuleSimulator getRootSim() {return rootSim;}

	@Override
	public String toString() {
		return "Block [color=" + color + ", row=" + row + ", column=" + column + "]";
	}

}
