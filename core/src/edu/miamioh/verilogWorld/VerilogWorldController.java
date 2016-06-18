
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

import edu.miamioh.Configuration.Configuration;
import edu.miamioh.Configuration.ConfigurationParser;
import edu.miamioh.worldEditor.WorldEditorScreen;

public class VerilogWorldController {

	private Configuration defaultConfig;
	
	public VerilogWorldController() {

		ConfigurationParser parser = new ConfigurationParser();
		defaultConfig = parser.getDefaultConfiguration();
		
	}

	public void update() {

	}
	
	public void updateInputMultiplexer() {
				
	}

	public Configuration getDefaultConfig() {	
		return this.defaultConfig;
	}
	
	public static String getRootPath() {
		return System.getProperty("user.dir");
	}
	
}
