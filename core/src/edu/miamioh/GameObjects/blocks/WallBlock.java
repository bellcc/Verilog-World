package edu.miamioh.GameObjects.blocks;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.simulator.RootModuleInstance;

public class WallBlock extends NormalBlock {
	
	public WallBlock(int row, int column) {
		super(NormalBlockType.Wall, row, column);
	}

	@Override
	public void updateProperties() {
		RootModuleInstance module = this.getModuleWrapper().getModule();
		ParseRegWire colorWire = module.getHash_vars().get("Color");
		int color = colorWire.getValue(Block.getRootSim().getOldSimIndex());
		
		int red = (color & 0xff0000) >> 16;
		int green = (color & 0x00ff00) >> 8;
		int blue = color & 0x0000ff;
		
		this.setColor(new Color((float)red / 256, 
								(float)green / 256, 
								(float)blue / 256, 
								(float)0));
	}
}
