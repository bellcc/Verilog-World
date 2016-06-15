
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   The WorldController class contains all the 
 *         game logic to initialize and modify the game world.
 * 
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.AbstractEditor.AbstractController;
import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;
import edu.miamioh.GameObjects.SpecialBlock;
import edu.miamioh.GameObjects.SpecialBlockType;
import edu.miamioh.GameObjects.Tile;
import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class WorldEditorController extends AbstractController{
	
	private static WorldEditorController currentWorldController;
	private static WorldEditorInputMultiplexer worldEditorMultiplexer;
		
	private static Level currentLevel;

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
	
	public WorldEditorController() {
		
		currentWorldController = this;
		init();

	}

	/**
	 * This method is used to initialize any of the 
	 */
	public void init() {
			
		initWorld();
		initPlayer();
		blockID = 0;
	}

	public void initWorld() {
		
		currentLevel = new Level();

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

	public void initPlayer() {
		
	}
	
	public void initMultiplexer() {
	
		worldEditorMultiplexer = new WorldEditorInputMultiplexer();

	}
	
	public void update() {
		
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
			
			WorldEditorController.getCurrentWorldController().getMultiplexer().updateMultiplexer();
			
			Block selectedBlock = currentLevel.getBlock(row, column);
			WorldEditorRenderer.getWorldRenderer().setSelectedBlock(selectedBlock);
			
			return;
		}
		
		boolean blocksActor = WorldEditorRenderer.getWorldRenderer().getBlocksActor();
		boolean tileActor = WorldEditorRenderer.getWorldRenderer().getTilesActor();
		
		SelectionType type = WorldEditorRenderer.getWorldRenderer().getSelectionType();
		
		if (blocksActor) {
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
			}
		}
		
		//Procedures for all other block states go here in the form of else if statements.
		
		if(tileActor) {
			
		}
		
	}
	
	public void addNewBlock(Block aBlock) {
		
		++blockID;
		currentLevel.addBlock(aBlock);
	}
	
	public void addNewTile(Tile tile) {
		
		currentLevel.addTile(tile);
	}
	
	public static WorldEditorController getCurrentWorldController() {
		return currentWorldController;
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
	
	public WorldEditorInputMultiplexer getMultiplexer() {
		
		return worldEditorMultiplexer;
	}
	
	public int getUniqueBlockID() {
		return this.blockID;
	}
}
