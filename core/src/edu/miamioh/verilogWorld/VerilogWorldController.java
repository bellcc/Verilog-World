
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
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldSimulator.WorldSimulatorController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import edu.miamioh.AbstractEditor.AbstractController;

public class VerilogWorldController extends AbstractController{

	private static VerilogWorldController controller;

	private static InputMultiplexer multiplexer;
	private static VerilogWorld state;
	
	private static String rootPath;

	public VerilogWorldController() {
		
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
	
	/**
	 * This method is used to initialize any class members that need 
	 * to be instantiated and/or populated upon startup of the application. 
	 * This is where any default settings will be instantiated as well.
	 */
	@Override
	public void init() {

		controller = this;
		//TODO This needs to be set to the main menu controller.
		//state = VerilogWorld.WORLD_SIMULATOR;
		state = VerilogWorld.WORLD_EDITOR;
		
		//The default configurations only need to be set once which 
		//is why there is not a public setter for this field.
		this.defaultConfig = new Configuration();
		this.defaultConfig.init();
		
	}
	
	/**
	 * This method is used to update all class member data fields during 
	 * execution of the application. This will likely be used to update 
	 * information about the lab/challenge that the user is modifying as 
	 * well as any other data that is necessary to use during execution.
	 */
	@Override
	public void update() {
		
	}
	
	public void updateInputMultiplexer() {
		
		multiplexer = new InputMultiplexer();
		
		switch(state) {
		
			case WORLD_EDITOR:
				Gdx.input.setInputProcessor(WorldEditorController.getCurrentWorldController().getMultiplexer().getMultiplexer());
				System.out.println(state);
				return;				
			
			case WORLD_SIMULATOR:
				Gdx.input.setInputProcessor(WorldSimulatorController.getMultiplexer().getMultiplexer());
				System.out.println(state);
				return;

			
		}
				
	}
	
	/**
	 * This method is used to modify the non-static member variables
	 * of this class from outside of the class. 
	 * @return The static reference to this class.
	 */
	public static VerilogWorldController getController() {
		
		return controller;
	}
	
	/**
	 * This method is used by every controller in this project to 
	 * retrieve the default configurations of the application.
	 * @return The default configurations as defined by "world.xml"
	 */
	public Configuration getDefaultConfig() {
		
		return this.defaultConfig;
	}
	
	public static String getRootPath() {
		return System.getProperty("user.dir");
	}
	
	public void setState(VerilogWorld newState) {
		state = newState;
	}
	
	public VerilogWorld getState() {
		return state;
	}
	
}
