
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.Level;

import java.util.Random;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.Linked.LinkedList;

public class Level {
	
	LinkedList<Block> blockList;
	
	public Level() {
		
		blockList = new LinkedList<Block>();
	}
	
	public Level(LinkedList<Block> blockList) {
		
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
