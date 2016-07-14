package edu.miamioh.GameObjects.blocks;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;

public class NullBlock extends NormalBlock {
	
	public static Color COLOR = new Color(0.2f, 0.2f, 0.2f, 1.0f);
	
	public NullBlock() {
		super(NormalBlockType.NULL);
	}

	@Override
	public void updateProperties() {
		
	}
}
