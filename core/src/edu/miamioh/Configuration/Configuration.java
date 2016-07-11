
/**
 * @author Clark Bell
 * @date   6-3-2016
 * @info   This class is used to define the default 
 *         or non-default configurations for the Verilog 
 *         World application.
 */

package edu.miamioh.Configuration;

public class Configuration {

	private int worldWidth;
	private int worldHeight;

	private int bufferWidth;
	private int bufferHeight;
	
	private int gridWidth;
	private int gridHeight;
	
	private int stepWidth;
	private int stepHeight;
	
	public Configuration() {
		
		this(0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	public Configuration(int worldWidth, int worldHeight, int bufferWidth, int bufferHeight, 
			int gridWidth, int gridHeight, int stepWidth, int stepHeight) {
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		this.bufferWidth = bufferWidth;
		this.bufferHeight = bufferHeight;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.stepWidth = stepWidth;
		this.stepHeight = stepHeight;
	}

	public int getWorldWidth() {
		return worldWidth;
	}
	
	public void setWorldWidth(int worldWidth) {
		this.worldWidth = worldWidth;
	}
	
	public int getWorldHeight() {
		return worldHeight;
	}
	
	public void setWorldHeight(int worldHeight) {
		this.worldHeight = worldHeight;
	}
	
	public int getBufferWidth() {
		return bufferWidth;
	}
	
	public void setBufferWidth(int bufferWidth) {
		this.bufferWidth = bufferWidth;
	}
	
	public int getBufferHeight() {
		return bufferHeight;
	}
	
	public void setBufferHeight(int bufferHeight) {
		this.bufferHeight = bufferHeight;
	}
	
	public int getStepWidth() {
		return stepWidth;
	}
	
	public void setStepWidth(int stepWidth) {
		this.stepWidth = stepWidth;
	}
	
	public int getStepHeight() {
		return stepHeight;
	}
	
	public void setStepHeight(int stepHeight) {
		this.stepHeight = stepHeight;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}

	@Override
	public String toString() {
		return "Configuration [worldWidth=" + worldWidth + ", worldHeight=" + worldHeight + ", bufferWidth=" + bufferWidth + ", bufferHeight="
				+ bufferHeight + ", gridWidth=" + gridWidth + ", gridHeight=" + gridHeight + ", stepWidth=" + stepWidth
				+ ", stepHeight=" + stepHeight + "]";
	}
	
}
