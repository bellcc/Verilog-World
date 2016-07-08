
/**
 * @author Chris Bell
 * @date   6-4-2016
 * @info   This controller can be seen as the boss of every 
 *         other controller in this application. The other 
 *         controllers will look to this controller for 
 *         information about the default configuration and 
 *         specific information that pertains to the requirements 
 *         of that controller. This controller does not have an 
 *         associated renderer or input processor however because 
 *         this class is meant to be a hub for all of the other 
 *         controllers which will have display output and take 
 *         input from the user.
 */

package edu.miamioh.verilogWorld;


import java.io.File;

import javax.swing.JTextPane;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.Configuration.ConfigurationParser;
import edu.miamioh.Level.Level;
import edu.miamioh.simulator.Parse;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.WorldSimulator;

public class VerilogWorldController {

	private static VerilogWorldController controller;
	
	public static int WINDOW_WIDTH = 600;
	public static int WINDOW_HEIGHT = 600;
	
	private Configuration defaultConfig;
	
	private File levelPath;
	
	private Level currentLevel;
	private WorldSimulator sim;
	private Parse compiler;
	
	private String rootPath;

	public VerilogWorldController() {
		
		init();
		
		ConfigurationParser parser = new ConfigurationParser();
		defaultConfig = parser.getDefaultConfiguration();
	}
	
	/**
	 * This may be used if non-default configuration settings are ever needed 
	 * however by default the variables are defined within the "world.xml" file.
	 * @param config
	 */
	public VerilogWorldController(Configuration config) {
		
		defaultConfig = config;
	}

	public void init() {
		
		this.rootPath = System.getProperty("user.dir");

		//If using IntelliJ...
//		this.rootPath = rootPath.substring(0, rootPath.substring(0, rootPath.lastIndexOf("/")).lastIndexOf("/") ) + "/";

		controller = this;
		currentLevel = new Level();
		try {
			compiler = new Parse(new JTextPane(), rootPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sim = new WorldSimulator(compiler.getRootModuleSimulator());
		sim.setBlockList(currentLevel.getBlockList());
		//TODO This needs to be set to the main menu controller.
		//state = VerilogWorld.WORLD_SIMULATOR;
		
	}
	
	public Configuration getDefaultConfig()              {return this.defaultConfig;}
	public void setDefaultConfig(Configuration config)   {this.defaultConfig = config;}
	public static VerilogWorldController getController() {return controller;}
	public Level getLevel() 		                     {return this.currentLevel;}
	public void setLevel(Level level)                    {this.currentLevel = level;}
	public WorldSimulator getSim() 	                     {return this.sim;}
	public Parse getCompiler()		                     {return this.compiler;}
	public void setRootPath(String rootPath)             {this.rootPath = rootPath;}               
	public String getRootPath() 	                     {return this.rootPath;}
	public void setLevelPath(File path)                  {this.levelPath = path;}
	public File getLevelPath()                           {return this.levelPath;}
	public Level getCurrentLevel() 						 {return this.currentLevel;}
}
