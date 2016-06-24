
/**
 * @author Chris Bell
 * @date   6-14-2016
 * @info   
 */

package edu.miamioh.worldSimulator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.Level.Level;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.ToolBarSelection;

public class WorldSimulatorController {
	
	private static WorldSimulatorController currentController;
	
	private WorldSimulatorInputProcessor inputProcessor;
	private InputMultiplexer multiplexer;
	
	private Level currentLevel;
	
	private ToolBarSelection selection;
	
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

	public WorldSimulatorController() {
		
		currentController = this;
		selection = ToolBarSelection.NONE;
		
		inputProcessor = new WorldSimulatorInputProcessor();
		multiplexer = new InputMultiplexer();
		
		currentLevel = WorldEditorController.getCurrentController().getCurrentLevel();
	}
	
	public WorldSimulatorController(Configuration config) {

		this();
		
		windowWidth = config.getWindowWidth();
		windowHeight = config.getWindowHeight();
		
		worldWidth = config.getWorldWidth();
		worldHeight = config.getWorldHeight();
		
		gridWidth = config.getGridWidth();
		gridHeight = config.getGridHeight();
		
		stepWidth = config.getStepWidth();
		stepHeight = config.getStepHeight();
		
		bufferWidth = config.getBufferWidth();
		bufferHeight= config.getBufferHeight();		

	}
	
	public void resetMultiplexer() {
		
		Stage optionStage = WorldSimulatorScreen.getScreen().getOptionStage();
		Stage homeStage = WorldSimulatorScreen.getScreen().getHomeStage();
		Stage simulatorStage = WorldSimulatorScreen.getScreen().getSimulatorStage();
		
		multiplexer.removeProcessor(inputProcessor);
		multiplexer.removeProcessor(optionStage);
		multiplexer.removeProcessor(homeStage);
		multiplexer.removeProcessor(simulatorStage);
	}
	
	public void updateInputMultiplexer() {
		
		Stage optionStage = WorldSimulatorScreen.getScreen().getOptionStage();
		Stage homeStage = WorldSimulatorScreen.getScreen().getHomeStage();
		Stage simulatorStage = WorldSimulatorScreen.getScreen().getSimulatorStage();
		
		resetMultiplexer();
		
		multiplexer.addProcessor(optionStage);
		
		switch (selection) {
		
			case HOME:
				multiplexer.addProcessor(homeStage);
				break;

			case SIMULATOR:
				multiplexer.addProcessor(simulatorStage);
				break;
				
			default:
				break;
			
		}
		
		multiplexer.addProcessor(inputProcessor);
		
		Gdx.input.setInputProcessor(multiplexer);
		
	}
	
	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
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
	
	public void setSelection(ToolBarSelection selection) {
		this.selection = selection;
	}
	
	public ToolBarSelection getSelection() {
		return selection;
	}

}
