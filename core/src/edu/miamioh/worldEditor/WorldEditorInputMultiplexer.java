
/**
 * @author Chris Bell
 * @date   6-5-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WorldEditorInputMultiplexer {

	private WorldEditorInputProcessor inputProcess;
	private InputMultiplexer multiplexer;
	
	public WorldEditorInputMultiplexer() {
		
		init();
	}
	
	/**
	 * This method initializes all of the class members and 
	 * any other features that are needed upon start up of 
	 * the world editor window.
	 */
	public void init() {
		
		inputProcess = new WorldEditorInputProcessor();
		multiplexer = new InputMultiplexer();
		
		initMultiplexer();
	}
	
	/**
	 * This method initializes the default settings of the 
	 * input processor or in other words the state that the 
	 * world editor is in upon start up.
	 */
	private void initMultiplexer() {
		
		//Stage toolBarStage = WorldEditorRenderer.getWorldRenderer().getStage();
		
		//multiplexer.addProcessor(toolBarStage);
		multiplexer.addProcessor(inputProcess);
	}
	
	/**
	 * This method is responsible for changing the input 
	 * processors that are attached to the gdx input process 
	 * using an input multiplexer which enables many input 
	 * processors to be utilized.
	 */
	public void updateMultiplexer() {
				
		//Retrieve all of the stages that are instantiated in 
		//the input processor. Not all of these stages will be 
		//added into the input multiplexer because some of them 
		//will not always be active however the tool bar stage 
		//and the basic input processor will always be added to 
		//this input multiplexer.
		//Stage toolBarStage = WorldEditorRenderer.getWorldRenderer().getStage();
		//Stage homeStage = WorldEditorRenderer.getWorldRenderer().getHomeStage();
		//Stage blockStage = WorldEditorRenderer.getWorldRenderer().getBlockStage();
		//Stage tileStage = WorldEditorRenderer.getWorldRenderer().getTileStage();
		
		//Stage blockOptionStage = WorldEditorRenderer.getWorldRenderer().getBlockOptionStage();
		
		//Remove every input processor from the multiplexer.
		//multiplexer.removeProcessor(toolBarStage);
		//multiplexer.removeProcessor(blockStage);
		//multiplexer.removeProcessor(tileStage);
		multiplexer.removeProcessor(inputProcess);
		
		//Determine which actor of the tool bar stage has been selected.
		//boolean homeActor = WorldEditorRenderer.getWorldRenderer().getHomeActor();
		//boolean blockActor = WorldEditorRenderer.getWorldRenderer().getBlocksActor();
		//boolean tileActor = WorldEditorRenderer.getWorldRenderer().getTilesActor();
		
		//boolean blockOption = WorldEditorRenderer.getWorldRenderer().getBlockOption();
	
		//TODO This is the likely cause of the problem.
		
		//Add all of the appropriate input processors based on the user's input.
		//multiplexer.addProcessor(toolBarStage);
		
		ToolBarSelection selection = WorldEditorController.getCurrentController().getToolBarSelection();
		
		switch(selection) {
		
			case HOME:
				//multiplexer.addProcessor(homeStage);
				break;
				
			case BLOCK:
				//multiplexer.addProcessor(blockStage);
				break;
				
			case TILE:
				//multiplexer.addProcessor(tileStage);
		
			default:
				break;
		
		}
		
		/**
		if(blockOption) {
			
			multiplexer.addProcessor(blockOptionStage);
		}
		*/
		
		multiplexer.addProcessor(inputProcess);
		Gdx.input.setInputProcessor(multiplexer);
		
	}
	
	public InputMultiplexer getMultiplexer() {
		
		return multiplexer;
	}
	
}
