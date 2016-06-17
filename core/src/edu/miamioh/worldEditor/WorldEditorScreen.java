
/**
 * @author Clark Bell
 * @date   06-16-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.worldEditor.Stages.OptionStage;

public class WorldEditorScreen implements Screen {

	private static WorldEditorController controller;
	
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
	
	private Stage optionStage;
	private Stage homeStage;
	private Stage blockStage;
	private Stage toolStage;

	public WorldEditorScreen(Configuration config) {

		this.windowWidth = config.getWindowWidth();
		this.windowHeight = config.getWindowHeight();
		
		this.worldWidth = config.getWorldWidth();
		this.worldHeight = config.getWorldHeight();
		
		this.gridWidth = config.getGridWidth();
		this.gridHeight = config.getGridHeight();
		
		this.stepWidth = config.getStepWidth();
		this.stepHeight = config.getStepHeight();
		
		this.bufferWidth = config.getBufferWidth();
		this.bufferHeight = config.getBufferHeight();
	
		//initOptionStage();
		//initHomeStage();
		//initBlockStage();
		//initToolStage();
	}
	
	private void initOptionStage() {
	
		//optionStage = new OptionStage().getStage();
		OptionStage temp = new OptionStage();
		optionStage = temp.getStage();
		
	}
	
	private void initHomeStage() {
		
		homeStage = new Stage();
	}
	
	private void initBlockStage() {
		
		blockStage = new Stage();
	}

	private void initToolStage() {
		
		toolStage = new Stage();
	}

	@Override
	public void dispose() {
		
		optionStage.dispose();
		homeStage.dispose();
		blockStage.dispose();
		toolStage.dispose();
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {

		controller.setPaused(true);
	}

	@Override
	public void render(float arg0) {
		
		renderOptionStage();
	}
	
	private void renderOptionStage() {
		
		optionStage.act(Gdx.graphics.getDeltaTime());
		optionStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
		optionStage.getViewport().update(width, height);
		homeStage.getViewport().update(width, height);
		blockStage.getViewport().update(width, height);
		toolStage.getViewport().update(width, height);
	}

	@Override
	public void resume() {
	
		controller.setPaused(false);
	}

	/**
	 * Called when this screen becomes the current screen for the game.
	 */
	@Override
	public void show() {
		
		updateWorld();
	}
	
	/**
	 * This method is used to update the world conditions that 
	 * specifically relate to the drawing of the grid background 
	 * and the step width/height for moving around the world. 
	 * The world editor controller acts as a hub for this information so 
	 * any time it is changed it is first changed in the controller and 
	 * that update propagates down to the world editor screen.
	 */
	private void updateWorld() {
		
		worldWidth = controller.getWindowWidth();
		worldHeight = controller.getWindowHeight();
		
		worldWidth = controller.getWindowWidth();
		worldHeight = controller.getWindowHeight();
		
		gridWidth = controller.getGridWidth();
		gridHeight = controller.getGridHeight();
		
		stepWidth = controller.getStepWidth();
		stepHeight = controller.getStepHeight();
		
		bufferWidth = controller.getBufferWidth();
		bufferHeight = controller.getBufferHeight();
	
	}

}
