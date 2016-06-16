
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.Level;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.Linked.LinkedList;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class Level {
	
	private VerilogWorldController worldController;
	
	private LinkedList<Block> blockList;
	
	public Level(VerilogWorldController worldController) {
		
		this.worldController = worldController;
		blockList = new LinkedList<Block>();
	}
	
	public Level(LinkedList<Block> blockList) {
		
		this.blockList = blockList;
	}
	
	public void addBlock(Block block) {
		
		blockList.add(block);
		
		if (block instanceof NormalBlock) {
			worldController.getSim().addModule(((NormalBlock)block).getModuleWrapper());
		}
	}
	
	public void removeBlock(Block block) {
		
		int index = findBlockIndex(block);
		
		if(index != -1) {
			blockList.remove(index);
		}
		
		if (block instanceof NormalBlock) {
			worldController.getSim().removeModule(((NormalBlock)block).getModuleWrapper());
		}
	}
	
	private int findBlockIndex(Block block) {
		
		int blockID = block.getId();
		
		for(int i=1;i<=blockList.getLength();i++) {
			
			int tempID = blockList.getEntry(i).getId();
			
			if(blockID == tempID) {		
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean isBlock(int row, int column) {
		
		for(int i=1;i<=blockList.getLength();i++) {
			
			int tempRow = blockList.getEntry(i).getRow();
			int tempColumn = blockList.getEntry(i).getColumn();
			
			if(row == tempRow && column == tempColumn) {
				return true;
			}
		}
		
		return false;
	}

	public Block getBlock(int row, int column) {
		
		for(int i=1;i<=blockList.getLength();i++) {
			
			int blockRow = blockList.getEntry(i).getRow();
			int blockColumn = blockList.getEntry(i).getColumn();
			
			if(row == blockRow && column == blockColumn) {
				
				return blockList.getEntry(i);
			}
		}
		
		return null;
		
	}
	
	public LinkedList<Block> getBlockList() {
		return blockList;
	}
}
