package edu.miamioh.GameObjects.blocks;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;

public class BlankBlock extends NormalBlock {
	
	public static Color COLOR = new Color(0.2f, 0.2f, 0.2f, 1.0f);
	
	public BlankBlock(int row, int column, int id) {
		super(NormalBlockType.Blank, row, column, id);
	}

	@Override
	public void updateProperties() {
		
	}
}
