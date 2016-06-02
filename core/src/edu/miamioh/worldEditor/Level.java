
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import edu.miamioh.gameObjects.Block;
import edu.miamioh.gameObjects.Tile;
import edu.miamioh.linked.LinkedList;

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
		
		int tileID = tile.getID();
		
		for(int i=1;i<=tileList.getLength();i++) {
			
			int tempID = tileList.getEntry(i).getID();
			
			if(tileID == tempID) {
				return i;
			}
		}
		
		return -1;
		
	}
	
	private int findBlockIndex(Block block) {
		
		int blockID = block.getID();
		
		for(int i=1;i<=blockList.getLength();i++) {
			
			int tempID = blockList.getEntry(i).getID();
			
			if(blockID == tempID) {		
				return i;
			}
		}
		
		return -1;
	}

}
