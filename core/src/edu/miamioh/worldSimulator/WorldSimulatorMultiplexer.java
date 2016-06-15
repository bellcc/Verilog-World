
/**
 * @author Chris Bell
 * @date   6-14-2016
 * @info   
 */

package edu.miamioh.worldSimulator;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WorldSimulatorMultiplexer {

	private WorldSimulatorInputProcessor inputProcess;
	private InputMultiplexer multiplexer;
	
	public WorldSimulatorMultiplexer() {
	
		init();
	}
	
	public void init() {
		
		inputProcess = new WorldSimulatorInputProcessor();
		multiplexer = new InputMultiplexer();
		
		initMultiplexer();
	}
	
	public void initMultiplexer() {
		
		Stage optionStage = WorldSimulatorRenderer.getSimulatorRenderer().getOptionStage();
		
		multiplexer.addProcessor(optionStage);
		multiplexer.addProcessor(inputProcess);
	}
	
	public InputMultiplexer getMultiplexer() {
		return multiplexer;
	}
}
