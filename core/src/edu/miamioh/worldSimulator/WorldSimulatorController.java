
/**
 * @author Chris Bell
 * @date   6-14-2016
 * @info   
 */

package edu.miamioh.worldSimulator;

import edu.miamioh.Level.Level;

public class WorldSimulatorController {
	
	private static WorldSimulatorController currentController;
	
	private Level currentLevel;
	
	public WorldSimulatorController() {
		
		currentController = this;
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public static WorldSimulatorController getController() {
		return currentController;
	}

}
