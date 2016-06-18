
/**
 * @author Clark Bell
 * @date   06-16-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.Stages.BlockStage;
import edu.miamioh.worldEditor.Stages.HomeStage;
import edu.miamioh.worldEditor.Stages.OptionStage;

public class WorldEditorScreen implements Screen {

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

	private Stage optionStage;
	private Stage homeStage;
	private Stage blockStage;
			
	public WorldEditorScreen(WorldEditorController controller) {
		this.controller = controller;
	}
	
	@Override
	public void show() {
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		
		optionStage = new OptionStage().getStage();
		homeStage = new HomeStage().getStage();		
		blockStage = new BlockStage().getStage();
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderToolBar();

	}
		
	public void renderToolBar() {
		
		optionStage.act(Gdx.graphics.getDeltaTime());
		optionStage.draw();

		//homeStage.act(Gdx.graphics.getDeltaTime());
		//homeStage.draw();
		
		blockStage.act(Gdx.graphics.getDeltaTime());
		blockStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
		controller.setWindowWidth(width);
		controller.setWindowHeight(height);
		
		optionStage = new OptionStage().getStage();
		homeStage = new HomeStage().getStage();
		blockStage = new BlockStage().getStage();
		
		Gdx.input.setInputProcessor(optionStage);
		Gdx.input.setInputProcessor(homeStage);
		Gdx.input.setInputProcessor(blockStage);
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
		// TODO Auto-generated method stub
		
	}
	
}
