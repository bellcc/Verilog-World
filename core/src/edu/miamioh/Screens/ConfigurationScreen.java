package edu.miamioh.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.miamioh.actors.LabelActor;

public class ConfigurationScreen implements Screen {
	
	private Stage stage;

	@Override
	public void show() {
		
		stage = new Stage();
		
		Label worldWidthLabel = new LabelActor().createLabel("World Width: ");
		
		stage.addActor(worldWidthLabel);
		
	}
	
	@Override
	public void render(float arg0) {
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}
	
	@Override
	public void resize(int wdith, int height) {
		
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

}
