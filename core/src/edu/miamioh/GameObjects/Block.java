
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.GameObjects;

import com.badlogic.gdx.graphics.Color;

public class Block extends AbstractGameObject {

	private static final int DEFAULT_ROW = 0;
	private static final int DEFAULT_COLUMN = 0;
	
	//Modifiable variables.
	private Color color;
	private int row;
	private int column;
	
	public Block() {

		this(DEFAULT_ROW, DEFAULT_COLUMN);
	}
	
	public Block(int row, int column) {

		setRow(row);
		setColumn(column);
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

	@Override
	public String toString() {
		return "Block [color=" + color + ", row=" + row + ", column=" + column + "]";
	}

}
