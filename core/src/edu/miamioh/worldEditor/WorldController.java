
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   The WorldController class contains all the 
 *         game logic to initialize and modify the game world.
 * 
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.miamioh.worldEditor.types.Point;

public class WorldController {
	
	private static WorldController currentWorldController;
		
	private MyInputProcessor inputProcess;
	private InputMultiplexer multiplexer;
	
	public WorldController() {
		
		currentWorldController = this;
		init();
	
	}
	
	public void initInputMultiplexer() {
		
		Stage toolsStage = WorldRenderer.getWorldRenderer().getStage();

		inputProcess = new MyInputProcessor();
		multiplexer = new InputMultiplexer();
		
		multiplexer.addProcessor(toolsStage);
		multiplexer.addProcessor(inputProcess);
		
		Gdx.input.setInputProcessor(multiplexer);
		
	}
	
	public void updateInputMultiplexer() {
		
		Stage toolsStage = WorldRenderer.getWorldRenderer().getStage();
		
		Stage homeStage = WorldRenderer.getWorldRenderer().getHomeStage();
		Stage blockStage = WorldRenderer.getWorldRenderer().getBlockStage();
		Stage tileStage = WorldRenderer.getWorldRenderer().getTileStage();
		
		//**
		for(int index=0;index<multiplexer.size();index++) {
			
			multiplexer.removeProcessor(index);
		
		}
		
		//multiplexer = new InputMultiplexer();
		
		multiplexer.removeProcessor(inputProcess);
		
		boolean homeActor = WorldRenderer.getWorldRenderer().getHomeActor();
		boolean blockActor = WorldRenderer.getWorldRenderer().getBlocksActor();
		boolean tileActor = WorldRenderer.getWorldRenderer().getTilesActor();

		multiplexer.addProcessor(toolsStage);
		
		if(homeActor) {
			
			//multiplexer.addProcessor(homeStage);
			
		}else if(blockActor) {
			
			multiplexer.addProcessor(blockStage);
			
		}else if(tileActor) {
			
			multiplexer.addProcessor(tileStage);
			
		}
		
		multiplexer.addProcessor(inputProcess);
		
		Gdx.input.setInputProcessor(multiplexer);
		
	}
	
	/**
	 * This method initializes the world and the player data.
	 */
	public void init() {
	
		initWorld();
		initPlayer();
		
	}
	
	/**
	 * This method initializes the world which contains all of 
	 * the blocks and tiles specified by a specific lab/challenge.
	 */
	public void initWorld() {
		
	}
	
	/**
	 * This method initializes the player(s) which contains the 
	 * objects that make up a player which is specified by a player's 
	 * lab/challenge solution.
	 */
	public void initPlayer() {
		
	}
	
	/**
	 * This method controls all of the changing aspects of the world 
	 * which includes player(s) and other objects that the game contains.
	 */
	public void update() {
		
	}
	
	/**
	 * This method finds the location of the mouse in the window.
	 * @return The point representation of the mouses' location.
	 */
	public Point getMousePoint() {
		
		int xPoint = -1;
		int yPoint = -1;
		
		xPoint = Gdx.input.getX();
		yPoint = Gdx.input.getY();
		
		Point point = new Point(xPoint, yPoint);
		return point;
		
	}
	
	public static WorldController getCurrentWorldController() {
		return currentWorldController;
	}
	
}
