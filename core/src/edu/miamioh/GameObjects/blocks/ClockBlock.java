package edu.miamioh.GameObjects.blocks;

import edu.miamioh.GameObjects.SpecialBlock;
import edu.miamioh.GameObjects.SpecialBlockType;

public class ClockBlock extends SpecialBlock {
	
	public ClockBlock(int row, int column) {
		super(SpecialBlockType.Clock, row, column);
	}
}
