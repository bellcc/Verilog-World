
/**
 * @author Clark Bell
 * @date   6-2-2016
 * @info   This class is the framework for a level which is 
 *         all of the information that is contained within the world.
 */

package edu.miamioh.Level;

import java.io.File;
import java.util.ArrayList;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.GameObjects.Block;

public class Level implements Comparable<Level>{
	
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
	
	public void addBlock(Block block) {
		
		blockList.add(block);
	}
	
	/**
	 * This method will remove a block at a specific row and column. 
	 * If the removal is not successful then nothing will happen.
	 * 
	 * @param row The row of the desired block to be removed.
	 * @param column The column of the desired block to be removed.
	 */
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
	
	/**
	 * This method will determine if the object at a specific location is a block.
	 * @param row The row of the desired object.
	 * @param column The column of the desired object.
	 * @return Returns true if the object is a block and false in every other case.
	 */
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

	/**
	 * This method will retrieve the block object that is 
	 * defined by it's row and column.
	 * 
	 * @param  row The row of the desired block.
	 * @param  column The column of the desired block.
	 * 
	 * @return The block at the row and column specified 
	 *         in the parameters. This method will return 
	 *         null if that block does not exist.
	 */
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
	
	@Override
	/**
	 * This method compares two level objects and determines if they are equivalent.
	 * 
	 * @return Returns 0 if the they are equal and a non-zero integer if not.
	 */
	public int compareTo(Level otherLevel) {

		if(this.getBlockList().size() != otherLevel.getBlockList().size()) {
			return -1;
		}
		
		for(int i=0;i<this.getBlockList().size();i++) {
			
			int id = this.getBlockList().get(i).getID();
			boolean flag = false;
			
			for(int j=0;j<otherLevel.getBlockList().size();j++) {
				
				if(otherLevel.getBlockList().get(i).getID() == id) {
					flag = true;
				}
			}
			
			if(!flag) {
				return -1;
			}
		}
		
		return 0;
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
