package edu.miamioh.GameObjects.blocks;

import com.badlogic.gdx.graphics.Color;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.simulator.RootModuleInstance;

public class WallBlock extends NormalBlock {
	
	public static Color COLOR = new Color(0.6f, 0.6f, 0.6f, 1.0f);
	
	public WallBlock(int row, int column, int id) {
		super(NormalBlockType.Wall, row, column, id);
	}

	@Override
	public void updateProperties() {
//		RootModuleInstance module = this.getModuleWrapper().getModule();
//		ParseRegWire colorWire = module.getHash_vars().get("color");
//
//		int color = colorWire.getValue(Block.getRootSim().getOldSimIndex());
//		
//		int red = (color & 0xff0000) >> 16;
//		int green = (color & 0x00ff00) >> 8;
//		int blue = color & 0x0000ff;
//		
//		this.setColor(new Color((float)red / 256, 
//								(float)green / 256, 
//								(float)blue / 256, 
//								(float)0));
	}
}
