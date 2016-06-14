
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.GameObjects;

import com.badlogic.gdx.graphics.Color;
import edu.miamioh.simulator.ModuleInstance;

public class Block extends AbstractGameObject {

	private static final int DEFAULT_ROW = 0;
	private static final int DEFAULT_COLUMN = 0;
	private static final Color DEFAULT_COLOR = Color.BLACK;
	
	//Modifiable variables.
	private Color color;
	private int row;
	private int column;
	
	private String source;
	private ModuleInstance module;
	
	public Block() {

		this(DEFAULT_COLOR, DEFAULT_ROW, DEFAULT_COLUMN);
	}
	
	public Block(Color color, int row, int column) {

		setColor(color);
		setRow(row);
		setColumn(column);
	}
	
	public void compile() {
		
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

}
