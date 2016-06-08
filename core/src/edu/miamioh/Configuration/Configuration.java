
/**
 * @author Chris Bell
 * @date   6-3-2016
 * @info   This class is used to define the default 
 *         or non-default configurations for the Verilog 
 *         World application.
 */

package edu.miamioh.Configuration;

public class Configuration {

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
	
	/**
	 * This method is used to initialize the default configuration 
	 * values that is defined within the "world.xml" file.
	 */
	public void init() {
		
		Configuration config = retrieveConfiguration();
		
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
	
	/**
	 * This method is used to call the parse method that is 
	 * defined in the ConfigurationParser. This data is return 
	 * to this method and then that data is then returned to 
	 * the calling method.
	 * 
	 * @return This is the configuration of the default settings 
	 * as defined by the "world.xml" file.
	 */
	private Configuration retrieveConfiguration() {
		
		ConfigurationParser parser = new ConfigurationParser();
		Configuration config = parser.getDefaultConfiguration();
		
		return config;
		
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
		return "Configuration [worldWidth=" + worldWidth + ", worldHeight=" + worldHeight + ", windowWidth="
				+ windowWidth + ", windowHeight=" + windowHeight + ", bufferWidth=" + bufferWidth + ", bufferHeight="
				+ bufferHeight + ", gridWidth=" + gridWidth + ", gridHeight=" + gridHeight + ", stepWidth=" + stepWidth
				+ ", stepHeight=" + stepHeight + "]";
	}
	
	
	
}
