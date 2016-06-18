
/**
 * @author Clark Bell
 * @date   06-16-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.Stages.BlockSelectedStage;
import edu.miamioh.worldEditor.Stages.BlockStage;
import edu.miamioh.worldEditor.Stages.HomeStage;
import edu.miamioh.worldEditor.Stages.OptionStage;
import edu.miamioh.worldEditor.Stages.SimulatorStage;

public class WorldEditorScreen implements Screen {

	private static WorldEditorScreen screen;
	
	private WorldEditorController controller;
	
	private int windowWidth;
	private int windowHeight;
	
	private int worldWidth;
	private int worldHeight;
	
	private int gridWidth;
	private int gridHeight;
	
	private int stepWidth;
	private int stepHeight;
	
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
		
		stepWidth = controller.getStepWidth();
		stepHeight = controller.getStepHeight();
		
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
		
		renderGrid();
		renderLevel();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
