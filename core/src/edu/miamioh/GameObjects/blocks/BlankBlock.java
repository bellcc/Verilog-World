package edu.miamioh.GameObjects.blocks;

import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;

public class BlankBlock extends NormalBlock {
	
	public BlankBlock(int row, int column) {
		super(NormalBlockType.Blank, row, column);
	}

	@Override
	public void updateProperties() {
		// TODO Auto-generated method stub
		
	}
}
