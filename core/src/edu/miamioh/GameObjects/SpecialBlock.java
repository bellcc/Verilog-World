package edu.miamioh.GameObjects;

import com.badlogic.gdx.graphics.Color;

public class SpecialBlock extends Block{
	private static final int DEFAULT_ROW = 0;
	private static final int DEFAULT_COLUMN = 0;
	
	//Modifiable variables.
	private final Color color; // Color for special blocks can't be changed
	private int row;
	private int column;
	
	private SpecialBlockType type;
	
	public SpecialBlock() {

		this(SpecialBlockType.None, DEFAULT_ROW, DEFAULT_COLUMN);
	}
	
	public SpecialBlock(SpecialBlockType type, int row, int column) {

		setRow(row);
		setColumn(column);
		setType(type);
		
		// Set color according to type
		switch(this.type) {
		case Clock:
			color = Color.YELLOW;
			break;
		case Reset:
			color = Color.RED;
			break;
		default:
			color = Color.BLACK;
		}
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
	
	public void setType(SpecialBlockType type) {
		this.type = type;
	}
	
	public SpecialBlockType getType() {
		return type;
	}
}
