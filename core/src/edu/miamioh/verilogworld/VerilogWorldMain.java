
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   VerilogWorldMain is the starter class of the game.
 */

package edu.miamioh.verilogworld;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import edu.miamioh.worldEditor.WorldController;
import edu.miamioh.worldEditor.WorldRenderer;
import edu.miamioh.SchematicRenderer.*;

public class VerilogWorldMain implements ApplicationListener {
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private SchematicRendererTest srTest;
	
	private boolean paused;
	
	/**
	 * Called when the Application is first created.
	 */
	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
//		srTest = new SchematicRendererTest(worldController);
		
		paused = false;
		
	}
	
	/**
	 * Called when the Application is destroyed.
	 */
	@Override
	public void dispose() {
		
		worldRenderer.dispose();
		
	}
	
	/**
	 * Called when the Application is paused, usually when 
	 * it's not active or visible on screen.
	 */
	@Override
	public void pause() {
	
		paused = true;
		
	}

	/**
	 * Called when the Application should render itself.
	 */
	@Override
	public void render () {

		if(!paused) {
			worldController.update();
		}
		
//		worldRenderer.render();
//		srTest.render();
		
	}
	
	/**
	 * Called when the application is resized.
	 * @param width The width of the window.
	 * @param height The height of the window.
	 */
	@Override
	public void resize(int width, int height) {
		
		worldRenderer.resize(width, height);
		
	}
	
	/**
	 * Called when the Application is resumed from a paused state, 
	 * usually when it regains focus.
	 */
	@Override
	public void resume() {
		
		paused = false;
		
	}

}