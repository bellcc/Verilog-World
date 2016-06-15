
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   VerilogWorldMain is the starter class of the game.
 */

package edu.miamioh.verilogWorld;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import edu.miamioh.verilogEditor.VerilogEditor;
import edu.miamioh.verilogEditor.RunEditor;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorRenderer;
import edu.miamioh.worldSimulator.WorldSimulatorController;
import edu.miamioh.worldSimulator.WorldSimulatorRenderer;

public class VerilogWorldMain implements Screen {
	
	private Game g;
	
	private static VerilogWorldMain currentVerilogWorld;
	
	private String	VERILOG_WORLD_DEVELOPMENT	= "VERILOG_WORLD_DEVELOPMENT";

	private VerilogWorldController verilogWorldController;
	
	private WorldEditorController worldEditorController;
	private WorldEditorRenderer worldRenderer;
	
	private WorldSimulatorController simulatorController;
	private WorldSimulatorRenderer simulatorRenderer;
	
	private boolean paused;
	
	/**
	 * Called when the Application is first created.
	 */
	
	public VerilogWorldMain(Game g){
		this.g = g;
	}
	
	@Override
	public void show () {
		
		currentVerilogWorld = this;

		//The variable is instantiated and initialized and will act 
		//as the central hub for data during execution of the application.
		verilogWorldController = new VerilogWorldController();
		verilogWorldController.init();
				
		worldEditorController = new WorldEditorController();
		worldRenderer = new WorldEditorRenderer(worldEditorController);
		
		worldEditorController.initMultiplexer();

		simulatorController = new WorldSimulatorController();
		simulatorRenderer = new WorldSimulatorRenderer(simulatorController);
		
		simulatorController.initMultiplexer();
		
		//VerilogWorldController.getController().updateInputMultiplexer();
		
		Gdx.input.setInputProcessor(worldEditorController.getCurrentWorldController().getMultiplexer().getMultiplexer());
		//Gdx.input.setInputProcessor(simulatorController.getController().getMultiplexer().getMultiplexer());
		
		paused = false;
		
	}
	
	/**
	 * Called when the Application is destroyed.
	 */
	@Override
	public void dispose() {
		
		//Dispose of all instantiated renderers here.
		
		worldRenderer.dispose();
		simulatorRenderer.dispose();
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
	public void render (float delta) {
		
		//Call the appropriate renderer here.
		
		if(!paused) {
			worldEditorController.update();
		}
		
		worldRenderer.render();
		//simulatorRenderer.render();
		
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
	
	public void launchVerilogEditor(String fileName){
		/*String pathToJar = getRootPath() + "/VerilogEditor.jar";
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", pathToJar, getRootPath(), fileName);

		try {
			Process p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		Thread thread = new Thread(new RunEditor(fileName));
		thread.start();
	}

	public String getRootPath()
	{
		String path = null;
		try
		{
			path = VerilogWorldMain.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			// Path can be a filename when executing a jar file. (filename/../)
			// doesn't work.
			path = new File(path).getParent() + "/../";
			// Development environment has different directory structure than
			// that when releasing
			if (isDevelopment())
				path += "../";
			/* getCanonicalPath() returns a path containing "\", which doesn't
			 * work (even on Windows) when passing the path as a command line
			 * argument. Thus, regular expression <code>\\\b</code> is used to
			 * substitute '\' to '/'. */
			path = new File(path).getCanonicalPath().replaceAll("\\\\\\b", "/");
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return path;
	}
	public boolean isDevelopment()
	{
		String env = System.getenv(VERILOG_WORLD_DEVELOPMENT);
		return env != null && !env.equals("0");
	}
	
	public static VerilogWorldMain getVerilogWorld() {
		return currentVerilogWorld;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}