
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   VerilogWorldMain is the starter class of the game.
 */

package edu.miamioh.verilogWorld;

import com.badlogic.gdx.ApplicationListener;

import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorRenderer;

public class VerilogWorldMain implements ApplicationListener {
	
	private VerilogWorldController verilogWorldController;
	
	private WorldEditorController worldEditorController;
	private WorldEditorRenderer worldRenderer;
	
	private boolean paused;
	
	/**
	 * Called when the Application is first created.
	 */
	@Override
	public void create () {

		//The variable is instantiated and initialized and will act 
		//as the central hub for data during execution of the application.
		verilogWorldController = new VerilogWorldController();
		verilogWorldController.init();
				
		worldEditorController = new WorldEditorController();
		worldRenderer = new WorldEditorRenderer(worldEditorController);
		
		worldEditorController.initMultiplexer();
		
		paused = false;
		
	}
	
	/**
	 * Called when the Application is destroyed.
	 */
	@Override
	public void dispose() {
		
		//Dispose of all instantiated renderers here.
		
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
		
		//Call the appropriate renderer here.
		
		if(!paused) {
			worldEditorController.update();
		}
		
		worldRenderer.render();
		
	}
	
	/**
	 * Called when the application is resized.
	 * @param width The width of the window.
	 * @param height The height of the window.
	 */
	@Override
	public void resize(int width, int height) {
		
		//resize the necessary renderer.
		
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