package edu.miamioh.GameObjects.blocks;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;

public class BlankBlock extends NormalBlock {
	
	public static Color COLOR = new Color(0.2f, 0.2f, 0.2f, 1.0f);
	
	public BlankBlock(int row, int column) {
		super(NormalBlockType.Blank, row, column);
	}

	@Override
	public void updateProperties() {
		// TODO Auto-generated method stub
		
	}
}
