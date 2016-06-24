package edu.miamioh.GameObjects.blocks;

import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;

public class WallBlock extends NormalBlock {
	
	public WallBlock(int row, int column) {
		super(NormalBlockType.Wall, row, column);
	}

	@Override
	public void updateProperties() {
		// TODO Auto-generated method stub
		
	}
}
