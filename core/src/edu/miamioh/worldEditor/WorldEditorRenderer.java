
/**
 * @author Chris Bell
 * @date   05-19-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.AbstractEditor.AbstractRenderer;
import edu.miamioh.GameObjects.Block;
import edu.miamioh.Linked.LinkedList;
import edu.miamioh.worldEditor.ToolBar.Stages.BlockStage;
import edu.miamioh.worldEditor.ToolBar.Stages.HomeStage;
import edu.miamioh.worldEditor.ToolBar.Stages.TileStage;
import edu.miamioh.worldEditor.ToolBar.Stages.ToolBarStage;
import edu.miamioh.worldEditor.types.Point;

public class WorldEditorRenderer extends AbstractRenderer{
	
	private WorldEditorController worldEditorController;
	private static WorldEditorRenderer worldRenderer;
	
	private OrthographicCamera camera;
	private ShapeRenderer renderer;

	private Color gridLineColor = Color.LIGHT_GRAY;
	
	private int worldX;
	private int worldY;
	
	private int toolBarWidth = 50;
	private int subToolBarWidth = 100;
	
	private Stage toolBarStage;
	
	private Stage homeStage;
	private Stage blockStage;
	private Stage tileStage;
	
	private static boolean homeActor;
	private static boolean blocksActor;
	private static boolean tilesActor;
	
	private boolean blankBlockState;

	private boolean blankTileState;
	
	public WorldEditorRenderer() {
		
		init();
	}
	
	public WorldEditorRenderer(WorldEditorController worldEditorController) {
		
		this.worldEditorController = worldEditorController;
		init();
	
	}

	public void init() {

		worldRenderer = this;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);

		renderer = new ShapeRenderer();
				
		blankBlockState = false;
		blankTileState = false;

		resetStates();
		
		initToolBarStages();
	}

	/**
	 * This method initializes the default tool bar 
	 * that sits on the left hand side of the window.
	 */
	public void initToolBarStages() {
		
		toolBarStage = new Stage();
		blockStage = new Stage();		
		tileStage = new Stage();
		
		ToolBarStage tempToolBarStage = new ToolBarStage();
		tempToolBarStage.init();
		toolBarStage = tempToolBarStage.getStage();
		
		BlockStage tempBlockStage = new BlockStage();
		tempBlockStage.init();
		blockStage = tempBlockStage.getStage();
		
		TileStage tempTileStage = new TileStage();
		tempTileStage.init();
		tileStage = tempTileStage.getStage();
		
		HomeStage tempHomeStage = new HomeStage();
		tempHomeStage.init();
		homeStage = tempHomeStage.getStage();
		
	}

	/**
	 * This method renders the necessary components of the 
	 * world and updates the objects that are used to observe 
	 * the world.
	 */
	public void render() {
		
		camera.update();
		renderer.setProjectionMatrix(camera.combined);
		
		//Set the background color to white.
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderBackground();
		renderSelector();
		
		renderLevelBlocks();
		renderToolBar();
		
	}
	
	/**
	 * This method renders the blocks that are defined in the level.
	 */
	public void renderLevelBlocks() {
		
		LinkedList<Block> blockList = worldEditorController.getCurrentLevel().blockList;
		
		for(int i=1;i<=blockList.getLength();i++) {
			
			int gridWidth = worldEditorController.getGridWidth();
			int gridHeight = worldEditorController.getGridHeight();
			
			int y = blockList.getEntry(i).getRow() * gridWidth;
			int x = blockList.getEntry(i).getColumn() * gridHeight;
			
			renderer.begin(ShapeType.Filled);
			renderer.setColor(Color.PINK);
			renderer.rect(x, y, gridWidth, gridHeight);

			renderer.end();			
			
		}
		
	}
	
	/**
	 * This method renders the necessary parts of the 
	 * tool bar that appears on the left hand side.
	 */
	public void renderToolBar() {
		
		toolBarStage.act(Gdx.graphics.getDeltaTime());
		toolBarStage.draw();
		
		if(homeActor) {
			
			homeStage.act(Gdx.graphics.getDeltaTime());
			homeStage.draw();
			
		}else if(blocksActor) {
						
			blockStage.act(Gdx.graphics.getDeltaTime());
			blockStage.draw();
			
		}else if(tilesActor) {
			
			tileStage.act(Gdx.graphics.getDeltaTime());
			tileStage.draw();
			
		}
		
	}

	/**
	 * This method renders the grid background of the world onto the window.
	 */
	public void renderBackground() {

		int gridWidth = worldEditorController.getGridWidth();
		int gridHeight = worldEditorController.getGridHeight();
		
		int worldWidth = worldEditorController.getWorldWidth();;
		int worldHeight = worldEditorController.getWorldHeight();;

		int width = worldWidth * gridWidth;
		int height = worldHeight * gridHeight;
		
		renderer.begin(ShapeType.Line);
		renderer.setColor(gridLineColor);
				
		//Vertical Lines
		for(int i = 0;i <= width;i += gridWidth) {
			
			renderer.line(i, 0, i, height);
			
		}
		
		//Horizontal Lines
		for(int i = 0;i <= height;i += gridHeight) {
			
			renderer.line(0, i, width, i);
			
		}
		
		renderer.end();
		
		int bufferWidth = worldEditorController.getBufferWidth();
		int bufferHeight = worldEditorController.getBufferHeight();
		
		int translateX = worldX - bufferWidth;
		int translateY = worldY - bufferHeight;
		
		if(worldX <	 bufferWidth) {
			
			translateX = (-1) * (bufferWidth - worldX);
			
		}
		
		if(worldY < bufferHeight) {
			
			translateY = (-1) * (bufferHeight - worldY);
			
		}
		
		int windowWidth = worldEditorController.getWindowWidth();
		int windowHeight = worldEditorController.getWindowHeight();
		
		if(width < windowWidth) {
			
			translateX = (-1) * ((windowWidth / 2) - (width / 2));
			
		}
		
		if(height < windowHeight) {
			
			translateY = (-1) * ((windowHeight / 2) - (height / 2));
			
		}
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera.setToOrtho(false, w, h);
		//camera.translate(translateX, translateY);
		//camera.translate(translateX - 50, translateY);	
		camera.translate(translateX - toolBarWidth - subToolBarWidth, translateY);
		
	}
	
	/**
	 * This method is strongly coupled with the renderBackground 
	 * method because the translations of the camera affect the 
	 * row/column detection algorithm and the drawSelector algorithm.
	 */
	public void renderSelector() {

		int row = detectRow();
		int column = detectColumn();

		column = detectColumn();
		row = detectRow();
		
		int gridWidth = worldEditorController.getGridWidth();
		int gridHeight = worldEditorController.getGridHeight();
				
		int x = column * gridWidth;
		int y = row * gridHeight;
		
		int worldWidth = worldEditorController.getWorldWidth();
		int worldHeight = worldEditorController.getWorldHeight();

		if((column >= 0 && column <= (worldWidth - 1)) && (row >= 0 && row <= (worldHeight - 1))) {
			
			renderer.begin(ShapeType.Filled);
			renderer.setColor(Color.DARK_GRAY);
			renderer.rect(x, y, gridWidth, gridHeight);

			renderer.end();
			
		}
		
	}
	
	/**
	 * This method finds the location of the mouse in the window.
	 * @return The point representation of the mouses' location.
	 */
	public Point getMousePoint() {
		
		int xPoint = -1;
		int yPoint = -1;
		
		xPoint = Gdx.input.getX();
		yPoint = Gdx.input.getY();
		
		Point point = new Point(xPoint, yPoint);
		return point;
		
	}
	
	/**
	 * This method determines which column the cursor is current hovering over.
	 * 
	 * @return If the cursor is not within a valid column or is 
	 * outside of the world (the buffer zone) then this method 
	 * returns -1. If the cursor is within the bounds of the world 
	 * then this method returns an integer between 0 and the world width 
	 * (inclusive).
	 */
	public int detectColumn() {
		
		int column = -1;
		int currentX = getMousePoint().getX() - toolBarWidth - subToolBarWidth;
		
		int worldWidth = worldEditorController.getWorldWidth();
		int gridWidth = worldEditorController.getGridWidth();
		int bufferWidth = worldEditorController.getBufferWidth();
		int windowWidth = worldEditorController.getWindowWidth();
		int width = worldWidth * gridWidth;
		
		if(hasIrregularWidth()) {

			int displayLeftX = (windowWidth / 2) - (width / 2);
			int displayRightX = displayLeftX + worldWidth;
			
			if(displayLeftX <= (windowWidth - currentX) && displayRightX >= (windowWidth - currentX)) {
				
				column = (int) (windowWidth - currentX - displayLeftX) / gridWidth;
				column = (worldWidth - 1) - column;
			}
			
		}else {

			column = ((worldX + currentX) - bufferWidth) / gridWidth;

			if(worldX < bufferWidth) {
				
				int displayLowerX = bufferWidth - worldX;

				if(currentX < displayLowerX)  {
	
					column = -1;
					
				}
				
			}
			
		}
				
		return column;
	}
	
	/**
	 * This method determines which row the cursor is current hovering over.
	 * 
	 * @return If the cursor is not within a valid row or is 
	 * outside of the world (the buffer zone) then this method 
	 * returns -1. If the cursor is within the bounds of the world 
	 * then this method returns an integer between 0 and the world height 
	 * (inclusive).
	 */
	public int detectRow() {
		
		int row = -1;
		int currentY = getMousePoint().getY();		
		
		int worldHeight = worldEditorController.getWorldHeight();		
		int gridHeight = worldEditorController.getGridHeight();
		int bufferHeight = worldEditorController.getBufferHeight();
		int windowHeight = worldEditorController.getWindowHeight();
		int height = worldHeight * gridHeight;
		
		if(hasIrregularHeight()) {
						
			int displayLowerY = (windowHeight / 2) - (height / 2);
			int displayUpperY = displayLowerY + height;

			if(displayLowerY <= (windowHeight - currentY) && displayUpperY >= (windowHeight - currentY)) {
				
				row = (int) (windowHeight - currentY - displayLowerY) / gridHeight;
				
			}

		}else {
			
			row = ((worldY + (windowHeight - currentY)) - bufferHeight) / gridHeight;
			
			if(worldY < bufferHeight) {

				int displayLowerY = bufferHeight - worldY;

				if((windowHeight - currentY < displayLowerY))  {
	
					row = -1;
					
				}
				
			}
			
		}
				
		return row;
	}
	
	/**
	 * This method determines if the world has a smaller height
	 * than the height of the window. This causes the world to
	 * need to be centered in the window along the vertical.
	 * 
	 * @return If the world has an irregular height then this
	 * method returns true and false otherwise.
	 */
	private boolean hasIrregularHeight() {
		
		int worldHeight = worldEditorController.getWorldHeight();		
		int gridHeight = worldEditorController.getGridHeight();
		int windowHeight = worldEditorController.getWindowHeight();
		int height = worldHeight * gridHeight;
		
		if(height < windowHeight) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method determines if the world has a smaller width 
	 * than the width of the window. This causes the world to 
	 * need to be centered in the window along the horizontal.
	 * 
	 * @return If the world has an irregular width then 
	 * this method returns true and false otherwise.
	 */
	private boolean hasIrregularWidth() {
		
		int worldWidth = worldEditorController.getWorldWidth();		
		int gridWidth = worldEditorController.getGridWidth();
		int windowWidth = worldEditorController.getWindowWidth();
		int width = worldWidth * gridWidth;
		
		if(width < windowWidth) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method resizes the window to the specified parameters 
	 * and updates the World Controller to the new changes.
	 * @param width The new width of the window.
	 * @param height The new height of the window.
	 */
	public void resize(int width, int height) {
		
		worldEditorController.setWindowWidth(width);
		worldEditorController.setWindowHeight(height);
		
	}
	
	@Override
	public void dispose() {
		
		toolBarStage.dispose();
		homeStage.dispose();
		blockStage.dispose();
		tileStage.dispose();
	}

	public void resetSelectedItems() {
		
		blankBlockState = false;
	}
		
	public void resetStates() {
		
		resetTileStates();
		resetBlockStates();
	}

	public void resetTileStates() {
		
		blankTileState = false;
	}
	
	public void resetBlockStates() {
		
		blankBlockState = false;		
	}
	
	public static WorldEditorRenderer getWorldRenderer() {
		
		return worldRenderer;
	}
	
	public void setWorldX(int x) {
		worldX = x;
	}
	
	public int getWorldX() {
		return worldX;
	}
	
	public void setWorldY(int y) {
		worldY = y;
	}
	
	public int getWorldY() {
		return worldY;
	}
	
	public Stage getStage() {
		return toolBarStage;
	}
	
	public Stage getBlockStage() {
		
		return blockStage;
	}
	
	public Stage getTileStage() {
		
		return tileStage;
	}

	public boolean getHomeActor() {
		return homeActor;
	}

	public void setHomeActor(boolean homeActor) {
		WorldEditorRenderer.homeActor = homeActor;
	}

	public boolean getBlocksActor() {
		return blocksActor;
	}

	public void setBlocksActor(boolean blocksActor) {
		WorldEditorRenderer.blocksActor = blocksActor;
	}

	public boolean getTilesActor() {
		return tilesActor;
	}

	public void setTilesActor(boolean tilesActor) {
		WorldEditorRenderer.tilesActor = tilesActor;
	}

	public boolean getBlankBlockState() {
		return blankBlockState;
	}

	public void setBlankBlockState(boolean blankBlock) {
		this.blankBlockState = blankBlock;
	}

	public boolean getBlankTileState() {
		return blankTileState;
	}

	public void setBlankTileState(boolean blankTile) {
		this.blankTileState = blankTile;
	}

	public Stage getHomeStage() {
		return homeStage;
	}
	
	public int getToolBarWidth() {
		
		return toolBarWidth;
	}
	
	public int getSubToolBarWidth() {
		
		return subToolBarWidth;
	}

}