
/**
 * @author Chris Bell
 * @date   6-2-2016
 * @info   
 */

package edu.miamioh.Level;

import java.io.File;
import java.util.ArrayList;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.GameObjects.Block;

public class Level {
	
	private File project;
	
	private ArrayList<Block> blockList;
	private Configuration config;

	public Level() {
		
		blockList = new ArrayList<Block>();
		config = new Configuration();
	}
	
	public Level(Configuration config) {
		this();
		this.config = config;
	}
	
	public Level(ArrayList<Block> blockList) {
		
		this.blockList = blockList;

	}
	
	public void addBlock(Block block) {
		
		blockList.add(block);
	}
	
	public void removeBlock(int row, int column) {
		
		for(int i=0;i<blockList.size();i++) {
			
			int blockRow = blockList.get(i).getRow();
			int blockColumn = blockList.get(i).getColumn();
			
			if(blockRow == row && blockColumn == column) {
				blockList.remove(i);
				return;
			}
		}

	}
	
	public boolean isBlock(int row, int column) {
		
		for(int i=0;i<blockList.size();i++) {
			
			int tempRow = blockList.get(i).getRow();
			int tempColumn = blockList.get(i).getColumn();
			
			if(row == tempRow && column == tempColumn) {
				return true;
			}
		}
		
		return false;
	}

	public Block getBlock(int row, int column) {
		
		for(int i=0;i<blockList.size();i++) {
			
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
	
	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	public Configuration getConfig() {
		return this.config;
	}
	
	public void setProject(File file) {
		this.project = file;
	}
	
	public File getProject() {
		return this.project;
	}
	
}
