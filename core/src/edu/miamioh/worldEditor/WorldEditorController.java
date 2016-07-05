
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

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.GameObjects.blocks.BlankBlock;
import edu.miamioh.GameObjects.blocks.ControllerBlock;
import edu.miamioh.GameObjects.blocks.LedBlock;
import edu.miamioh.GameObjects.blocks.ScooterBlock;
import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.blocks.WallBlock;
import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class WorldEditorController {
	
	private static WorldEditorController currentController;
	
	private WorldEditorInputProcessor inputProcessor;
	private InputMultiplexer multiplexer;	
	
	private Level currentLevel;
	
	private ToolBarSelection selection;
	private int selectedRow;
	private int selectedColumn;
	
	private BlockSelectionType blockSelection;

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

		inputProcessor = new WorldEditorInputProcessor();
		multiplexer = new InputMultiplexer();
		
		selection = ToolBarSelection.NONE;
		blockSelection = BlockSelectionType.NONE;
		blockID = 0;
	}
	
	public WorldEditorController(Configuration config, Level currentLevel) {

		this();
		
		updateParameters(config);
		this.currentLevel = currentLevel;
	}
	
	private void updateParameters(Configuration config) {
		
		windowWidth = config.getWindowWidth();
		windowHeight = config.getWindowHeight();
		
		worldWidth = config.getWorldWidth();
		worldHeight = config.getWorldHeight();
		
		gridWidth = config.getGridWidth();
		gridHeight = config.getGridHeight();
		
		stepWidth = config.getStepWidth();
		stepHeight = config.getStepHeight();
		
		bufferWidth = config.getBufferWidth();
		bufferHeight = config.getBufferHeight();
	}
	
	public void initWorld() {
		
		//currentLevel = VerilogWorldController.getController().getLevel();

		Configuration config = VerilogWorldController.getController().getDefaultConfig();
		
		setWorldWidth(config.getWorldWidth());
		setWorldHeight(config.getWorldHeight());
		
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
		Stage blockSelectedStage = WorldEditorScreen.getScreen().getBlockSelectedStage();
		//Stage toolStage = WorldEditorScreen.getScreen().getToolStage();
		Stage simulatorStage = WorldEditorScreen.getScreen().getSimulatorStage();

		resetMultiplexer();
		
		multiplexer.addProcessor(optionStage);
		
		switch (selection) {
		
			case HOME:
				multiplexer.addProcessor(homeStage);
				break;
				
			case BLOCK:
				multiplexer.addProcessor(blockStage);
				break;
				
			case BLOCK_SELECTED:
				multiplexer.addProcessor(blockSelectedStage);
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
	
	public void resetMultiplexer() {
		
		Stage optionStage = WorldEditorScreen.getScreen().getOptionStage();
		Stage homeStage = WorldEditorScreen.getScreen().getHomeStage();
		Stage blockStage = WorldEditorScreen.getScreen().getBlockStage();
		Stage blockSelectedStage = WorldEditorScreen.getScreen().getBlockSelectedStage();
		//Stage toolStage = WorldEditorScreen.getScreen().getToolStage();
		Stage simulatorStage = WorldEditorScreen.getScreen().getSimulatorStage();
		
		multiplexer.removeProcessor(inputProcessor);
		multiplexer.removeProcessor(optionStage);
		multiplexer.removeProcessor(homeStage);
		multiplexer.removeProcessor(blockStage);
		multiplexer.removeProcessor(blockSelectedStage);
		//multiplexer.removeProcessor(toolStage);
		multiplexer.removeProcessor(simulatorStage);
		
	}

	/**
	 * This method discerns which action should be taken after a user touches down in the world. 
	 * @param row
	 * @param column
	 */
	public void gridPressed(int row, int column) {
				
		boolean isBlock = currentLevel.isBlock(row, column);
		
		if(isBlock) {

			if(selection == ToolBarSelection.BLOCK_SELECTED) {
				selection = ToolBarSelection.NONE;
			}else {
				selection = ToolBarSelection.BLOCK_SELECTED;
				resetMultiplexer();
				updateInputMultiplexer();
				
				selectedRow = row;
				selectedColumn = column;
			}
			
			return;
		}
		
		if (selection == ToolBarSelection.BLOCK) {
						
			switch(blockSelection) {
			
				case Block_Blank:
					currentLevel.addBlock(new BlankBlock(row, column));
					break;
				case Block_Wall:
					currentLevel.addBlock(new WallBlock(row, column));
					break;
				case Block_Controller:
					currentLevel.addBlock(new ControllerBlock(row, column));
					break;
				case Block_Scooter:
					currentLevel.addBlock(new ScooterBlock(row, column));
					break;
				case Block_Led:
					currentLevel.addBlock(new LedBlock(row, column));
					break;
				default:
					break;
			}
			
			++blockID;
		}
		
	}
	
	public void addNewBlock(Block aBlock) {
		
		++blockID;
		currentLevel.addBlock(aBlock);
	}
	
	public void toggleBlockSelection(BlockSelectionType selectionType) {
				
		if(blockSelection == selectionType) {
			blockSelection = BlockSelectionType.NONE;
		}else {
			blockSelection = selectionType;
		}
				
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
	
	public BlockSelectionType getBlockSelection() {
		return blockSelection;
	}
	
	public void setBlockSelection(BlockSelectionType blockSelection) {
		this.blockSelection = blockSelection;
	}
	
	public int getSelectedRow() {
		return selectedRow;
	}
	
	public int getSelectedColumn() {
		return selectedColumn;
	}

}
