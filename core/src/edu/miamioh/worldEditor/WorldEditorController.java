
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   The WorldController class contains all the 
 *         game logic to initialize and modify the game world.
 * 
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.AbstractEditor.AbstractController;
import edu.miamioh.Configuration.Configuration;
import edu.miamioh.GameObjects.Block;

import edu.miamioh.GameObjects.Tile;
import edu.miamioh.Level.Level;

public class WorldEditorController extends AbstractController{
	
	private static WorldEditorController currentController;
	private InputMultiplexer multiplexer;	
	
	private static Level currentLevel;
	
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
	
	private int blockID;
	
	private boolean paused;
	
	public WorldEditorController() {
		
		currentController = this;
		paused = false;

		multiplexer = new InputMultiplexer();
		
		selection = ToolBarSelection.NONE;
		blockID = 0;
	}
	
	public WorldEditorController(Configuration config) {

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
	
	public void updateInputMultiplexer() {		
		
		Stage optionStage = WorldEditorScreen.getScreen().getOptionStage();
		Stage homeStage = WorldEditorScreen.getScreen().getHomeStage();
		Stage blockStage = WorldEditorScreen.getScreen().getBlockStage();
		//Stage toolStage = WorldEditorScreen.getScreen().getToolStage();
		Stage simulatorStage = WorldEditorScreen.getScreen().getSimulatorStage();
		
		multiplexer.removeProcessor(optionStage);
		multiplexer.removeProcessor(homeStage);
		multiplexer.removeProcessor(blockStage);
		//multiplexer.removeProcessor(toolStage);
		multiplexer.removeProcessor(simulatorStage);
		
		multiplexer.addProcessor(optionStage);
				
		switch (selection) {
		
			case HOME:
				multiplexer.addProcessor(homeStage);
				break;
				
			case BLOCK:
				multiplexer.addProcessor(blockStage);
				break;
				
			case SIMULATOR:
				multiplexer.addProcessor(simulatorStage);
				break;
				
			default:
				break;
			
		}
		
		Gdx.input.setInputProcessor(multiplexer);
		
	}
	
	public void resetMultiplexer() {
		
		Stage optionStage = WorldEditorScreen.getScreen().getOptionStage();
		Stage homeStage = WorldEditorScreen.getScreen().getHomeStage();
		Stage blockStage = WorldEditorScreen.getScreen().getBlockStage();
		//Stage toolStage = WorldEditorScreen.getScreen().getToolStage();
		Stage simulatorStage = WorldEditorScreen.getScreen().getSimulatorStage();
		
		multiplexer.removeProcessor(optionStage);
		multiplexer.removeProcessor(homeStage);
		multiplexer.removeProcessor(blockStage);
		//multiplexer.removeProcessor(toolStage);
		multiplexer.removeProcessor(simulatorStage);
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method discerns which action should be taken after a user touches down in the world. 
	 * @param row
	 * @param column
	 */
	public void gridPressed(int row, int column) {
				
		boolean isBlock = currentLevel.isBlock(row, column);
		boolean isTile = currentLevel.isTile(row, column);
		
		if(isBlock || isTile) {
		
			/*
			 * Not used
			 */
//			boolean blockOption = WorldEditorRenderer.getWorldRenderer().getBlockOption();
//			
//			if(blockOption) {
//				WorldEditorRenderer.getWorldRenderer().setBlockOption(false);
//			}else {
//				WorldEditorRenderer.getWorldRenderer().setBlockOption(true);
//			}
			
			//WorldEditorController.getCurrentWorldController().getMultiplexer().updateMultiplexer();
			
			//Block selectedBlock = currentLevel.getBlock(row, column);
			//WorldEditorRenderer.getWorldRenderer().setSelectedBlock(selectedBlock);
			
			return;
		}

		/**
		SelectionType type = WorldEditorRenderer.getWorldRenderer().getSelectionType();
		
		if (selection == ToolBarSelection.BLOCK) {
			
			switch(type) {
			
				case Block_Blank:
					currentLevel.addBlock(new NormalBlock(NormalBlockType.Blank, row, column));
					break;
				case Block_Clock:
					currentLevel.addBlock(new SpecialBlock(SpecialBlockType.Clock, row, column));
					break;
				case Block_Reset:
					currentLevel.addBlock(new SpecialBlock(SpecialBlockType.Reset, row, column));
					break;
				case Block_Wall:
					currentLevel.addBlock(new NormalBlock(NormalBlockType.Wall, row, column));
					break;
				default:
					break;
			}
		
		}

		if(selection == ToolBarSelection.TILE) {
			
		}
		*/
		
	}
	
	public void addNewBlock(Block aBlock) {
		
		++blockID;
		currentLevel.addBlock(aBlock);
	}
	
	public void addNewTile(Tile tile) {
		
		currentLevel.addTile(tile);
	}
	
	public static WorldEditorController getCurrentController() {
		return currentController;
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
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

	public int getUniqueBlockID() {
		return this.blockID;
	}
	
	public ToolBarSelection getToolBarSelection() {
		return selection;
	}
	
	public void setToolBarSelection(ToolBarSelection selection) {
		this.selection = selection;
	}
	
	public boolean getPaused() {
		return paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}
