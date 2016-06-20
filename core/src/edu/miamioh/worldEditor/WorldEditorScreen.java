
/**
 * @author Clark Bell
 * @date   06-16-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.worldEditor.Stages.BlockSelectedStage;
import edu.miamioh.worldEditor.Stages.BlockStage;
import edu.miamioh.worldEditor.Stages.HomeStage;
import edu.miamioh.worldEditor.Stages.OptionStage;
import edu.miamioh.worldEditor.Stages.SimulatorStage;
import edu.miamioh.worldEditor.types.Point;

public class WorldEditorScreen implements Screen {

	private static WorldEditorScreen screen;
	
	private WorldEditorController controller;
	
	private int windowWidth;
	private int windowHeight;
	
	private int worldWidth;
	private int worldHeight;
	
	private int gridWidth;
	private int gridHeight;
	
	private int bufferWidth;
	private int bufferHeight;
	
	private OrthographicCamera camera;
	private ShapeRenderer renderer;
	
	private int worldX;
	private int worldY;
	
	private Stage optionStage;
	private Stage homeStage;
	private Stage blockStage;
	private Stage blockSelectedStage;
	private Stage toolStage;
	private Stage simulatorStage;
	
	private final int TOOLBAR_WIDTH = 150;
	
	public WorldEditorScreen() {
		screen = this;
	}
	
	public WorldEditorScreen(WorldEditorController controller) {
		this();
		this.controller = controller;
	}
	
	@Override
	public void show() {
		
		updateWorldParameters();
		
		renderer = new ShapeRenderer();
		
		worldX = 0;
		worldY = 0;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		
		optionStage = new OptionStage().getStage();
		homeStage = new HomeStage().getStage();		
		blockStage = new BlockStage().getStage();
		blockSelectedStage = new BlockSelectedStage().getStage();
		//toolStage = new ToolStage().getStage();
		simulatorStage = new SimulatorStage().getStage();		
	}
	
	private void updateWorldParameters() {
		
		windowWidth = controller.getWindowWidth();
		windowHeight = controller.getWindowHeight();
		
		worldWidth = controller.getWorldWidth();
		worldHeight = controller.getWorldHeight();
		
		gridWidth = controller.getGridWidth();
		gridHeight = controller.getGridHeight();

		bufferWidth = controller.getBufferWidth();
		bufferHeight = controller.getBufferHeight();
		
	}

	@Override
	public void render(float delta) {
		
		camera.update();
		renderer.setProjectionMatrix(camera.combined);
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderWorld();
		renderSelector();
		renderToolBar();

	}
	
	private void renderWorld() {
		
		renderLevel();
		renderGrid();
	}
	
	private void renderGrid() {
		
		int width = worldWidth * gridWidth;
		int height = worldHeight * gridHeight;

		renderer.begin(ShapeType.Line);
		renderer.setColor(Color.LIGHT_GRAY);
				
		for(int i = 0;i <= width;i += gridWidth) {			
			renderer.line(i, 0, i, height);
		}
		
		for(int i = 0;i <= height;i += gridHeight) {
			renderer.line(0, i, width, i);
		}
		
		renderer.end();
		
		translateCamera();
		
	}
	
	private void translateCamera() {
		
		int width = gridWidth * worldWidth;
		int height = gridHeight * worldHeight;
		
		int translateX = worldX - bufferWidth;
		int translateY = worldY - bufferHeight;

		if(worldX <	 bufferWidth) {
			translateX = (-1) * (bufferWidth - worldX);
		}

		if(worldY < bufferHeight) {
			translateY = (-1) * (bufferHeight - worldY);
		}
		
		translateX -= TOOLBAR_WIDTH;
				
		int windowWidthRange = windowWidth - TOOLBAR_WIDTH;
		
		if(width < windowWidthRange) {
			translateX = TOOLBAR_WIDTH + ((windowWidthRange / 2) - (width / 2));
			translateX *= (-1);			
		}
		
		if(height < windowHeight) {
			translateY = ((windowHeight / 2) - (height / 2));
			translateY *= (-1);
		}

		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera.setToOrtho(false, w, h);
		camera.translate(translateX, translateY);
		
	}
	
	public void renderSelector() {

		int row = detectRow();
		int column = detectColumn();

		column = detectColumn();
		row = detectRow();
				
		int x = column * gridWidth;
		int y = row * gridHeight;

		if((column >= 0 && column <= (worldWidth - 1)) && (row >= 0 && row <= (worldHeight - 1))) {
			
			int selectorWidth = gridWidth - 1;
			int selectorHeight = gridHeight - 1;
			
			y += 1;
			
			renderer.begin(ShapeType.Filled);
			renderer.setColor(Color.DARK_GRAY);
			renderer.rect(x, y, selectorWidth, selectorHeight);

			renderer.end();
			
		}
		
	}
	
	private void renderLevel() {
	
		ArrayList<Block> blockList = controller.getCurrentLevel().getBlockList();
		
		for(int i=0;i<blockList.size();i++) {

			int row = blockList.get(i).getRow();
			int column = blockList.get(i).getColumn();
			Color color = blockList.get(i).getColor();
			
			renderer.begin(ShapeType.Filled);
			renderer.setColor(color);
			renderer.rect(column * gridWidth, row * gridHeight, gridWidth, gridHeight);
			renderer.end();
		
		}
	}
		
	private void renderToolBar() {
		
		optionStage.act(Gdx.graphics.getDeltaTime());
		optionStage.draw();
		
		ToolBarSelection selection = controller.getToolBarSelection();
		
		switch (selection) {
		
			case HOME:
				homeStage.act(Gdx.graphics.getDeltaTime());
				homeStage.draw();
				break;
				
			case BLOCK:
				blockStage.act(Gdx.graphics.getDeltaTime());
				blockStage.draw();
				break;
				
			case BLOCK_SELECTED:
				blockSelectedStage.act(Gdx.graphics.getDeltaTime());
				blockSelectedStage.draw();
				break;
				
			case SIMULATOR:
				simulatorStage.act(Gdx.graphics.getDeltaTime());
				simulatorStage.draw();
				break;
				
			default:
				break;
				
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
		
		int worldWidth = controller.getWorldWidth();
		int gridWidth = controller.getGridWidth();
		int bufferWidth = controller.getBufferWidth();
		int windowWidth = controller.getWindowWidth();
		int width = worldWidth * gridWidth;
		
		if(hasIrregularWidth()) {
			
			int currentX = getMousePoint().getX();
			int windowWidthRange = windowWidth - TOOLBAR_WIDTH;
			
			int displayLeftX = (windowWidthRange / 2) - (width / 2);
			int displayRightX = displayLeftX + width;
			
			if(displayLeftX <= (windowWidth - currentX) && displayRightX >= (windowWidth - currentX)) {
				
				column = (windowWidth - currentX - displayLeftX) / gridWidth;
				column = (worldWidth - 1) - column;
			}
			
		}else {
			
			int currentX = getMousePoint().getX() - TOOLBAR_WIDTH;

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
		
		int worldHeight = controller.getWorldHeight();		
		int gridHeight = controller.getGridHeight();
		int bufferHeight = controller.getBufferHeight();
		int windowHeight = controller.getWindowHeight();
		int height = worldHeight * gridHeight;
		
		if(hasIrregularHeight()) {
						
			int displayLowerY = (windowHeight / 2) - (height / 2);
			int displayUpperY = displayLowerY + height;

			if(displayLowerY <= (windowHeight - currentY) && displayUpperY >= (windowHeight - currentY)) {
				
				row = (windowHeight - currentY - displayLowerY) / gridHeight;
				
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
		
		int worldHeight = controller.getWorldHeight();		
		int gridHeight = controller.getGridHeight();
		int windowHeight = controller.getWindowHeight();
		int height = worldHeight * gridHeight;

		return height < windowHeight;

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
		
		int worldWidth = controller.getWorldWidth();		
		int gridWidth = controller.getGridWidth();
		int windowWidth = controller.getWindowWidth();
		int width = worldWidth * gridWidth;

		return width < windowWidth;

	}

	@Override
	public void resize(int width, int height) {

		controller.setWindowWidth(width);
		controller.setWindowHeight(height);
		
		updateWorldParameters();
		
		controller.resetMultiplexer();
		
		optionStage = new OptionStage().getStage();
		homeStage = new HomeStage().getStage();
		blockStage = new BlockStage().getStage();
		//toolStage = new ToolStage().getStage();
		simulatorStage = new SimulatorStage().getStage();

		controller.updateInputMultiplexer();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {

		optionStage.dispose();
		homeStage.dispose();
		blockStage.dispose();
		toolStage.dispose();
		simulatorStage.dispose();
	}
	
	public static WorldEditorScreen getScreen() {
		return screen;
	}
	
	public Stage getOptionStage() {
		return optionStage;
	}
	
	public Stage getHomeStage() {
		return homeStage;
	}
	
	public Stage getBlockStage() {
		return blockStage;
	}
	
	public Stage getBlockSelectedStage() {
		return blockSelectedStage;
	}
	
	public Stage getToolStage() {
		return toolStage;
	}
	
	public Stage getSimulatorStage() {
		return simulatorStage;
	}
	
	public int getWorldX() {
		return worldX;
	}
	
	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}
	
	public int getWorldY() {
		return worldY;
	}
	
	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}
	
	public int getToolBarWidth() {
		return TOOLBAR_WIDTH;
	}
	
}
