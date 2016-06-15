
/**
 * @author Chris Bell
 * @date   6-14-2016
 * @info   
 */

package edu.miamioh.worldSimulator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.Linked.LinkedList;
import edu.miamioh.worldSimulator.OptionStage.Actors.ResetActor;
import edu.miamioh.worldSimulator.OptionStage.Actors.SimulateActor;
import edu.miamioh.worldSimulator.OptionStage.Actors.VerifyActor;

public class WorldSimulatorRenderer implements Disposable{
	
	private static WorldSimulatorController controller;
	private static WorldSimulatorRenderer simulatorRenderer;
	
	public OrthographicCamera camera;
	public ShapeRenderer renderer;
	
	public Color gridLineColor = Color.LIGHT_GRAY;
	
	public int worldX;
	public int worldY;
	
	private Stage optionStage;
	
	private int optionStageWidth;
	
	public WorldSimulatorRenderer(WorldSimulatorController simulatorController) {
		
		controller = simulatorController;
		init();
	}
	
	public void init() {
		
		simulatorRenderer = this;
		renderer = new ShapeRenderer();
		
		worldX = 0;
		worldY = 0;
		
		initCamera();
		initOptionStage();		
	}
	
	public void initCamera() {
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
	}
	
	public void initOptionStage() {
		
		int windowHeight = controller.getWindowHeight();
		
		optionStageWidth = 50;
		optionStage = new Stage();
		
		Actor resetButton = new ResetActor().getButtonActor();
		resetButton.setPosition(0, windowHeight - 50);
		resetButton.setSize(50, 50);
		
		Actor simulateButton = new SimulateActor().getButtonActor();
		simulateButton.setPosition(0, windowHeight - 100);
		simulateButton.setSize(50, 50);
		
		Actor verifyButton = new VerifyActor().getButtonActor();
		verifyButton.setPosition(0, windowHeight - 150);
		verifyButton.setSize(50, 50);
		
		optionStage.addActor(resetButton);
		optionStage.addActor(simulateButton);
		optionStage.addActor(verifyButton);
	}
	
	public void render() {
		
		camera.update();
		renderer.setProjectionMatrix(camera.combined);
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderBackground();
		renderOptionStage();
		//renderBlocks();
	}
	
	public void renderBackground() {

		int gridWidth = controller.getGridWidth();
		int gridHeight = controller.getGridHeight();
		
		int worldWidth = controller.getWorldWidth();;
		int worldHeight = controller.getWorldHeight();;

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
		
		translateCamera();

	}

	private void renderOptionStage() {
		
		optionStage.act(Gdx.graphics.getDeltaTime());
		optionStage.draw();
	}
	
	public void renderBlocks() {
		
		LinkedList<Block> blockList = controller.getCurrentLevel().getBlockList();
		
		for(int i=1;i<=blockList.getLength();i++) {
			
			int gridWidth = controller.getGridWidth();
			int gridHeight = controller.getGridHeight();
			
			int y = blockList.getEntry(i).getRow() * gridWidth;
			int x = blockList.getEntry(i).getColumn() * gridHeight;
			
			Color blockColor = blockList.getEntry(i).getColor();

			//This causes the blocks to be within the bounds 
			//of the grid's cells so that the blocks do not 
			//overlap with the grid.
			int blockWidth = gridWidth - 1;
			int blockHeight = gridHeight - 1;
			y += 1;

			renderer.begin(ShapeType.Filled);
			renderer.setColor(blockColor);
			renderer.rect(x, y, blockWidth, blockHeight);
			renderer.end();
		}
	}
	
	private void translateCamera() {
		
		int bufferWidth = controller.getBufferWidth();
		int bufferHeight = controller.getBufferHeight();
		
		int windowWidth = controller.getWindowWidth();
		int windowHeight = controller.getWindowHeight();
		
		int gridWidth = controller.getGridWidth();
		int gridHeight = controller.getGridHeight();
		
		int worldWidth = controller.getWorldWidth();
		int worldHeight = controller.getWorldHeight();
		
		int width = gridWidth * worldWidth;
		int height = gridHeight * worldHeight;
		
		int translateX = worldX - bufferWidth;
		int translateY = worldY - bufferHeight;
		
		//This sets the translate width to the amount of 
		//buffer area that is needed on the left of the window.
		if(worldX <	 bufferWidth) {
			translateX = (-1) * (bufferWidth - worldX);
		}
		
		//This sets the translate height to the amount of
		//buffer area that is needed on the bottom of the window.
		if(worldY < bufferHeight) {
			translateY = (-1) * (bufferHeight - worldY);
		}
		
		translateX -= optionStageWidth;
		
		//Irregular world sizes
		
		int windowWidthRange = windowWidth - optionStageWidth;
		
		if(width < windowWidthRange) {
			translateX = optionStageWidth + ((windowWidthRange / 2) - (width / 2));
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

	@Override
	public void dispose() {
		optionStage.dispose();
	}
	
	public Stage getOptionStage() {
		return optionStage;
	}

	public static WorldSimulatorRenderer getSimulatorRenderer() {
		return simulatorRenderer;
	}

}
