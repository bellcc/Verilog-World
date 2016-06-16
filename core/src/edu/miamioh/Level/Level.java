
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.Level;

import java.util.ArrayList;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class Level {
	
	private ArrayList<Block> blockList;
	
	public Level(VerilogWorldController worldController) {
		
		blockList = new ArrayList<Block>();
	}
	
	public Level(ArrayList<Block> blockList) {
		
		this.blockList = blockList;
	}
	
	public void addBlock(Block block) {
		
		blockList.add(block);
	}
	
	public void removeBlock(Block block) {
		
		int index = findBlockIndex(block);
		
		if(index != -1) {
			blockList.remove(index);
		}
	}
	
	private int findBlockIndex(Block block) {
		
		int blockID = block.getId();
		
		for(int i=0;i < blockList.size();i++) {
			
			int tempID = blockList.get(i).getId();
			
			if(blockID == tempID) {		
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean isBlock(int row, int column) {
		
		for(int i=0; i < blockList.size();i++) {
			
			int tempRow = blockList.get(i).getRow();
			int tempColumn = blockList.get(i).getColumn();
			
			if(row == tempRow && column == tempColumn) {
				return true;
			}
		}
		
		return false;
	}

	public Block getBlock(int row, int column) {
		
		for(int i=0; i < blockList.size();i++) {
			
			int blockRow = blockList.get(i).getRow();
			int blockColumn = blockList.get(i).getColumn();
			
			if(row == blockRow && column == blockColumn) {
				
				return blockList.get(i);
			}
		}
		
		return null;
		
	}
	
	public ArrayList<Block> getBlockList() {
		return blockList;
	}
}
