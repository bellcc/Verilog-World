package edu.miamioh.worldSimulator;

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
import edu.miamioh.worldSimulator.Stages.HomeStage;
import edu.miamioh.worldSimulator.Stages.OptionStage;
import edu.miamioh.worldSimulator.Stages.SimulatorStage;

public class WorldSimulatorScreen implements Screen {

	private static WorldSimulatorScreen screen;
	
	private WorldSimulatorController controller;
	
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
	private Stage simulatorStage;
	
	private final int TOOLBAR_WIDTH = 150;
	
	public WorldSimulatorScreen() {
		screen = this;
	}
	
	public WorldSimulatorScreen(WorldSimulatorController controller) {
		this();
		this.controller = controller;
	}
	
	@Override
	public void show() {
		
		updateWorldParameters();
		controller.setSelection(ToolBarSelection.NONE);
		controller.setKeyPressed(-1);
		
		renderer = new ShapeRenderer();
		
		worldX = 0;
		worldY = 0;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
				
		optionStage = new OptionStage().getStage();
		homeStage = new HomeStage().getStage();		
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
		
		ToolBarSelection selection = controller.getSelection();
		
		switch (selection) {
		
			case HOME:
				homeStage.act(Gdx.graphics.getDeltaTime());
				homeStage.draw();
				break;
	
			case SIMULATOR:
				simulatorStage.act(Gdx.graphics.getDeltaTime());
				simulatorStage.draw();
				break;
				
			default:
				break;
				
		}
	
	}

	@Override
	public void resize(int width, int height) {

		controller.setWindowWidth(width);
		controller.setWindowHeight(height);
		
		updateWorldParameters();
		
		controller.resetMultiplexer();
		
		optionStage = new OptionStage().getStage();
		//homeStage = new HomeStage().getStage();
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
		simulatorStage.dispose();
	}
	
	public static WorldSimulatorScreen getScreen() {
		return screen;
	}
	
	public Stage getOptionStage() {
		return optionStage;
	}
	
	public Stage getHomeStage() {
		return homeStage;
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
