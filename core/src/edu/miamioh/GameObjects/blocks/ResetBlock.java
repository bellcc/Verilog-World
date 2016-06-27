package edu.miamioh.GameObjects.blocks;

import edu.miamioh.GameObjects.SpecialBlock;
import edu.miamioh.GameObjects.SpecialBlockType;

public class ResetBlock extends SpecialBlock {
	
	public ResetBlock(int row, int column) {
		super(SpecialBlockType.Reset, row, column);
	}
}
