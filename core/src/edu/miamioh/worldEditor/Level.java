
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import java.util.Random;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.Tile;
import edu.miamioh.Linked.LinkedList;
import edu.miamioh.util.Constants;

public class Level {
	
	LinkedList<Block> blockList;
	LinkedList<Tile> tileList;
	
	public Level() {
		
		blockList = new LinkedList<Block>();
		tileList = new LinkedList<Tile>();
		
	}
	
	public Level(LinkedList<Block> blockList, LinkedList<Tile> tileList) {
		
		this.blockList = blockList;
		this.tileList = tileList;
		
	}
	
	public void addBlock(Block block) {
		
		blockList.add(block);
	}
	
	public void addTile(Tile tile) {
		
		tileList.add(tile);
	}
	
	public void removeBlock(Block block) {
		
		int index = findBlockIndex(block);
		
		if(index != -1) {
			blockList.remove(index);
		}
		
	}
	
	public void removeTile(Tile tile) {
		
		int index = findTileIndex(tile);
		
		if(index != -1) {
			tileList.remove(index);
		}
		
	}
	
	private int findTileIndex(Tile tile) {
		
		int tileID = tile.getId();
		
		for(int i=1;i<=tileList.getLength();i++) {
			
			int tempID = tileList.getEntry(i).getId();
			
			if(tileID == tempID) {
				return i;
			}
		}
		
		return -1;
		
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
	
	public boolean isTile(int row, int column) {
		
		for(int i=1;i<=tileList.getLength();i++) {
			
			int tempRow = tileList.getEntry(i).getRow();
			int tempColumn = tileList.getEntry(i).getColumn();
			
			if(row == tempRow && column == tempColumn) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public int generateBlockID(Block block) {

		int maxNum = Constants.MAX_ID;
		
		Random rand = new Random();
		
		while(true) {

			int randNum = rand.nextInt(maxNum);
			boolean flag = false;
			
			for(int i=1;i<=blockList.getLength();i++) {
			
				if(blockList.getEntry(i).getId() == randNum) {
					flag = true;
				}
				
				if(blockList.getEntry(i).getId() != randNum && i == blockList.getLength() && flag == false) {
					return randNum;
				}
				
			}
			
		}
		
	}

}
