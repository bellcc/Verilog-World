package edu.miamioh.projectDirectory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import edu.miamioh.Buttons.TextButtonActor;

public class ProjectDirectoryScreen implements Screen {

	private Stage startStage;
	
	@Override
	public void show() {
		
		constuctStartStage();
		Gdx.input.setInputProcessor(startStage);
	}

	@Override
	public void render(float arg0) {
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		startStage.act(Gdx.graphics.getDeltaTime());
		startStage.draw();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}
	
	private void constuctStartStage() {
		
		startStage = new Stage();
		
		TextButton newButton = new TextButtonActor().createTextButton(Color.RED, "New Project");
		TextButton oldButton = new TextButtonActor().createTextButton(Color.BLUE, "Import Project");
		
		Table table = new Table();
		table.pad(0, 5, 0, 5);
		table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.center();
		
		table.add(newButton).width(150).height(50);
		table.add(oldButton).width(150).height(50);
		
		startStage.addActor(table);
		
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
