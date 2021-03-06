
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   The WorldController class contains all the 
 *         game logic to initialize and modify the game world.
 * 
 */

package edu.miamioh.worldEditor;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.Configuration.ConfigurationParser;
import edu.miamioh.GameObjects.blocks.BlankBlock;
import edu.miamioh.GameObjects.blocks.ControllerBlock;
import edu.miamioh.GameObjects.blocks.LedBlock;
import edu.miamioh.GameObjects.blocks.ScooterBlock;
import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.blocks.WallBlock;
import edu.miamioh.Level.Level;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.simulator.WireRoleType;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.worldSimulator.InvalidModulePortException;
import edu.miamioh.worldSimulator.ModulePort;

public class WorldEditorController {
	
	private static WorldEditorController currentController;
	
	private WorldEditorInputProcessor inputProcessor;
	private InputMultiplexer multiplexer;	
	
	private Level currentLevel;
	
	private ToolBarSelectionType selection;
	private int selectedRow;
	private int selectedColumn;
	
	private BlockSelectionType blockSelection;

	private int worldWidth;
	private int worldHeight;

	private int bufferWidth;
	private int bufferHeight;
	
	private int gridWidth;
	private int gridHeight;
	
	private int stepWidth;
	private int stepHeight;
	
	private int blockID;
	
	private boolean paused;
	
	private NormalBlock selectedBlock;
	private NormalBlock targetBlock;
	
	public WorldEditorController() {
				
		currentController = this;
		paused = false;

		inputProcessor = new WorldEditorInputProcessor();
		multiplexer = new InputMultiplexer();
		
		selection = ToolBarSelectionType.NONE;
		blockSelection = BlockSelectionType.NONE;
		blockID = 0;
	}
	
	public WorldEditorController(Level level) {

		this();
				
		updateParameters(level.getConfig());		
		this.currentLevel = level;
			
	}
	
	private void updateParameters(Configuration config) {

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

		worldWidth = config.getWorldWidth();
		worldHeight = config.getWorldHeight();
		
		gridWidth = config.getGridWidth();
		gridHeight = config.getGridHeight();
		
		stepWidth = config.getStepWidth();
		stepHeight = config.getStepHeight();
		
		bufferWidth = config.getBufferWidth();
		bufferHeight= config.getBufferHeight();
		
	}
	
	public void updateWorld(Level level) {
		
		this.currentLevel = level;
		
		Configuration config = level.getConfig();
		
		setWorldWidth(config.getWorldWidth());
		setWorldHeight(config.getWorldHeight());

		worldWidth = config.getWorldWidth();
		worldHeight = config.getWorldHeight();
		
		gridWidth = config.getGridWidth();
		gridHeight = config.getGridHeight();
		
		stepWidth = config.getStepWidth();
		stepHeight = config.getStepHeight();
		
		bufferWidth = config.getBufferWidth();
		bufferHeight= config.getBufferHeight();

	}

	public void updateConnectionMode() {
		Stage connectionStage = WorldEditorScreen.getScreen().getConnectionStage();
		Gdx.input.setInputProcessor(connectionStage);
	}
	
	public void updateInputMultiplexer() {		
		
		Stage optionStage = WorldEditorScreen.getScreen().getOptionStage();
		Stage homeStage = WorldEditorScreen.getScreen().getHomeStage();
		Stage blockStage = WorldEditorScreen.getScreen().getBlockStage();
		Stage blockSelectedStage = WorldEditorScreen.getScreen().getBlockSelectedStage();
		Stage toolStage = WorldEditorScreen.getScreen().getToolStage();
		
		resetMultiplexer();
		
		multiplexer.addProcessor(optionStage);
		
		switch (selection) {
		
			case HOME:
				multiplexer.addProcessor(homeStage);
				break;
				
			case BLOCK:
				multiplexer.addProcessor(blockStage);
				break;
				
			case TOOLS:
				multiplexer.addProcessor(toolStage);
				break;
				
			case BLOCK_SELECTED:
				multiplexer.addProcessor(blockSelectedStage);
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
		Stage toolStage = WorldEditorScreen.getScreen().getToolStage();
		Stage connectionStage = WorldEditorScreen.getScreen().getConnectionStage();
		
		multiplexer.removeProcessor(inputProcessor);
		multiplexer.removeProcessor(optionStage);
		multiplexer.removeProcessor(homeStage);
		multiplexer.removeProcessor(blockStage);
		multiplexer.removeProcessor(blockSelectedStage);
		multiplexer.removeProcessor(toolStage);
		multiplexer.removeProcessor(connectionStage);
	}

	/**
	 * This method discerns which action should be taken after a user touches down in the world. 
	 * @param row
	 * @param column
	 */
	public void gridPressed(int row, int column) {
				
		boolean isBlock = currentLevel.isBlock(row, column);
		boolean connectMode = WorldEditorScreen.getScreen().getConnectMode();
		
		if(isBlock) {
			
			if(connectMode) {
				
				System.out.println("Connect block at (" + selectedRow + ", " + selectedColumn + ") with (" + row + ", " + column + ").");
				
				selectedBlock = (NormalBlock)currentLevel.getBlock(selectedRow, selectedColumn);
				targetBlock = (NormalBlock)currentLevel.getBlock(row, column);
				
				VerilogWorldController.getController().getSim().getRootModuleSimulator().updateTargetBlock(selectedBlock);
				
				VerilogWorldController.getController().getSim().getRootModuleSimulator().updateTargetBlock(targetBlock);
				
				WorldEditorScreen.getScreen().setConnectModeWire(true, selectedBlock, targetBlock);
				this.updateConnectionMode();				

			}

			if(selection == ToolBarSelectionType.BLOCK_SELECTED) {
				selection = ToolBarSelectionType.NONE;
			}else {
				selection = ToolBarSelectionType.BLOCK_SELECTED;
				resetMultiplexer();
				updateInputMultiplexer();
				
				selectedRow = row;
				selectedColumn = column;
			}
			
			return;
		}
		
		if (selection == ToolBarSelectionType.BLOCK) {
						
			switch(blockSelection) {
						
				case Block_Blank:
					int id = new edu.miamioh.util.UniqueNumber().generateID(WorldEditorController.getCurrentController().getCurrentLevel().getBlockList(), 0, WorldEditorController.getCurrentController().getWorldWidth() * WorldEditorController.getCurrentController().getWorldHeight());
					currentLevel.addBlock(new BlankBlock(row, column, id));
					break;
				case Block_Wall:
					id = new edu.miamioh.util.UniqueNumber().generateID(WorldEditorController.getCurrentController().getCurrentLevel().getBlockList(), 0, WorldEditorController.getCurrentController().getWorldWidth() * WorldEditorController.getCurrentController().getWorldHeight());
					currentLevel.addBlock(new WallBlock(row, column, id));
					break;
				case Block_Controller:
					id = new edu.miamioh.util.UniqueNumber().generateID(WorldEditorController.getCurrentController().getCurrentLevel().getBlockList(), 0, WorldEditorController.getCurrentController().getWorldWidth() * WorldEditorController.getCurrentController().getWorldHeight());
					currentLevel.addBlock(new ControllerBlock(row, column, id));
					break;
				case Block_Scooter:
					id = new edu.miamioh.util.UniqueNumber().generateID(WorldEditorController.getCurrentController().getCurrentLevel().getBlockList(), 0, WorldEditorController.getCurrentController().getWorldWidth() * WorldEditorController.getCurrentController().getWorldHeight());
					currentLevel.addBlock(new ScooterBlock(row, column, id));
					break;
				case Block_Led:
					id = new edu.miamioh.util.UniqueNumber().generateID(WorldEditorController.getCurrentController().getCurrentLevel().getBlockList(), 0, WorldEditorController.getCurrentController().getWorldWidth() * WorldEditorController.getCurrentController().getWorldHeight());
					currentLevel.addBlock(new LedBlock(row, column, id));
					break;
				default:
					break;
			}
			
			//System.out.println(currentLevel.getBlockList().get(currentLevel.getBlockList().size() - 1).getID());
			
			++blockID;
		}
		
	}
	
	public void connectBlocks(NormalBlock selectedBlock, NormalBlock targetBlock, 
							  String selectedWireName, String targetWireName) throws InvalidModulePortException {
		
		/*
		 * Grab the wires
		 */
		ParseRegWire selectedWire = selectedBlock.getModuleWrapper().getModule().getHash_vars().get(selectedWireName);
		ParseRegWire targetWire = targetBlock.getModuleWrapper().getModule().getHash_vars().get(targetWireName);
		
		/*
		 * Find t
		 */
		boolean selectedIsInput = selectedWire.getRole() == WireRoleType.INPUT ? true : false;
		boolean targetIsInput = targetWire.getRole() == WireRoleType.INPUT ? true : false;
		
		if(!(selectedIsInput ^ targetIsInput)) throw new InvalidModulePortException("Selected ports are incompatible. (Both inputs or both outputs)");
		
		ModulePort selectedPort = new ModulePort(selectedBlock, selectedWireName, selectedWire, selectedIsInput);
		selectedBlock.getModuleWrapper().addPort(selectedPort);
		targetBlock.getModuleWrapper().addPort(new ModulePort(targetBlock, targetWireName, selectedPort, targetWire, targetIsInput));
	}
	
	public boolean changesMade() {
		
		if(currentLevel.getProject() == null) {
			return false;
		}
				
		Level otherLevel = new ConfigurationParser().getConfiguration(new File(currentLevel.getProject() + "/world.xml"));

		if(currentLevel.compareTo(otherLevel) != 0) {
			return true;
		}
		
		return false;
		
	}
	
	public void saveLevel() {
		
		File file = WorldEditorController.getCurrentController().getCurrentLevel().getProject();
		Level level = currentLevel;
		
		File tempFile = new File(file.getPath() + "/modules/");
		tempFile.mkdirs();
		
		ConfigurationParser parser = new ConfigurationParser();
		parser.createWorld(level, new File(file.getPath() + "/world.xml"));
		
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
	
	public ToolBarSelectionType getToolBarSelection() {
		return selection;
	}
	
	public void setToolBarSelection(ToolBarSelectionType selection) {
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
	
	public void setCurrentLevel(Level level) {
		this.currentLevel = level;
	}

	public NormalBlock getSelectedBlock() {
		return selectedBlock;
	}

	public void setSelectedBlock(NormalBlock selectedBlock) {
		this.selectedBlock = selectedBlock;
	}

	public NormalBlock getTargetBlock() {
		return targetBlock;
	}

	public void setTargetBlock(NormalBlock targetBlock) {
		this.targetBlock = targetBlock;
	}

}
