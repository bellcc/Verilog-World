
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   VerilogWorldMain is the starter class of the game.
 */

package edu.miamioh.verilogWorld;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.badlogic.gdx.Game;

import edu.miamioh.verilogEditor.RunEditor;
import edu.miamioh.worldEditor.WorldEditorScreen;
	
public class VerilogWorldMain extends Game {
	
	private static VerilogWorldMain currentVerilogWorldMain;	
	private VerilogWorldController verilogWorldController;

	private String	VERILOG_WORLD_DEVELOPMENT	= "VERILOG_WORLD_DEVELOPMENT";

	public VerilogWorldMain() {
	
		create();
	}

	/**
	 * This method is called when an application is first started.
	 */
	@Override
	public void create() {
		
		currentVerilogWorldMain = this;
		verilogWorldController = new VerilogWorldController();
		
		//Set the default screen.
		currentVerilogWorldMain.setScreen(new WorldEditorScreen(verilogWorldController.getDefaultConfig()));
	}
	
	public static VerilogWorldMain getCurrentVerilogWorldMain() {
		return currentVerilogWorldMain;
	}
	
	public void launchVerilogEditor(String fileName){

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

}
