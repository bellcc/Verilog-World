
/**
 * @author Chris Bell
 * @date   6-14-2016
 * @info   
 */

package edu.miamioh.worldSimulator;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class WorldSimulatorController {
	
	private static WorldSimulatorController currentController;
	private static WorldSimulatorMultiplexer multiplexer;
	
	private Level currentLevel;
	
	private int worldWidth;
	private int worldHeight;
	
	private int windowWidth;
	private int windowHeight;
	
	private int bufferWidth;
	private int bufferHeight;
	
	private int gridWidth;
	private int gridHeight;
	
	private int stepWidth;
	private int stepHeight;

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

	public WorldSimulatorController() {
		
		currentController = this;
		initWorld();
	}
	
	public void initWorld() {
		
		Configuration config = VerilogWorldController.getController().getDefaultConfig();
		
		setWorldWidth(config.getWorldWidth());
		setWorldHeight(config.getWorldHeight());
		
		setWindowWidth(config.getWindowWidth());
		setWindowHeight(config.getWindowHeight());
		
		setBufferWidth(config.getBufferWidth());
		setBufferHeight(config.getBufferHeight());
		
		setGridWidth(config.getGridWidth());
		setGridHeight(config.getGridHeight());
		
		setStepWidth(config.getStepWidth());
		setStepHeight(config.getStepHeight());
	}
	
	public void initMultiplexer() {
		
		multiplexer = new WorldSimulatorMultiplexer();
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public static WorldSimulatorController getController() {
		return currentController;
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

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
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

	public static WorldSimulatorMultiplexer getMultiplexer() {
		return multiplexer;
	}

}
