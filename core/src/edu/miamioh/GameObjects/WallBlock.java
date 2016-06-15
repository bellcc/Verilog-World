package edu.miamioh.GameObjects;

import com.badlogic.gdx.graphics.Color;

public class WallBlock extends NormalBlock {
	
	public WallBlock(Color color, int row, int column) {
		super(NormalBlockType.Wall, row, column);
		
		setColor(color);
		setRow(row);
		setColumn(column);
		setType(NormalBlockType.Wall);
		makeUniqueFile();
	}
}
