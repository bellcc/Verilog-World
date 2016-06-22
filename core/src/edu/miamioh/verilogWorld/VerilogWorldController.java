
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


import javax.swing.JTextPane;

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.Configuration.ConfigurationParser;

import edu.miamioh.Level.Level;
import edu.miamioh.simulator.Parse;
import edu.miamioh.worldSimulator.WorldSimulator;

public class VerilogWorldController {

	private Configuration defaultConfig;

	private static VerilogWorldController controller;

	private static VerilogWorldType state;
	
	private Level currentLevel;
	private WorldSimulator sim;
	private Parse compiler;
	
	private String rootPath;

	public VerilogWorldController() {
		
		ConfigurationParser parser = new ConfigurationParser();
		defaultConfig = parser.getDefaultConfiguration();
		init();
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
				
		this.rootPath = System.getProperty("user.dir") + "/../";

		controller = this;
		currentLevel = new Level();
		try {
			compiler = new Parse(new JTextPane(), rootPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sim = new WorldSimulator(compiler.getRootModuleSimulator());
		//TODO This needs to be set to the main menu controller.
		//state = VerilogWorld.WORLD_SIMULATOR;
		state = VerilogWorldType.WORLD_EDITOR;
		
	}

	public void setState(VerilogWorldType newState)      {state = newState;}
	
	public Configuration getDefaultConfig()              {return this.defaultConfig;}
	public VerilogWorldType getState()                   {return state;}
	public static VerilogWorldController getController() {return controller;}
	public Level getLevel() 		                     {return this.currentLevel;}
	public WorldSimulator getSim() 	                     {return this.sim;}
	public Parse getCompiler()		                     {return this.compiler;}
	public String getRootPath() 	                     {return this.rootPath;}
}
