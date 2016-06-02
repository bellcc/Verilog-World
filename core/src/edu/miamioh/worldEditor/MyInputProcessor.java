 
/**
 * @author Chris Bell
 * @date   05-19-2016
 * @info   This java class deals with user input from 
 * 		   the mouse and keyboard. The input is then redirected 
 *         to modify the work in some manner.
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.InputProcessor;

import edu.miamioh.util.Constants;

public class MyInputProcessor implements InputProcessor{

	public boolean keyDown(int keyCode) {
		return false;
	}

	/**
	 * This method is used to detect user input from the keyboard. 
	 * It reports a keyCode from the user's keyboard that determines 
	 * which action to take in the world/player creator and the simulator.
	 * @return This method returns true if an action was taken by user input 
	 * and false if the action taken by the user had no subsequent response.
	 */
	public boolean keyUp(int keyCode) {

		System.out.println(keyCode);
		
		int worldX = WorldRenderer.getWorldRenderer().getWorldX();
		int worldY = WorldRenderer.getWorldRenderer().getWorldY();

		switch (keyCode) {
		
			//Signals when the UP arrow is pressed.
			case 19:
				
				if(canMoveUp(worldY)) {
					
					worldY += Constants.STEP_HEIGHT;
					WorldRenderer.getWorldRenderer().setWorldY(worldY);
					
				}

				return true;
			
			//Signals when the DOWN arrow is pressed.
			case 20:
				
				if(canMoveDown(worldY)) {
					
					worldY -= Constants.STEP_HEIGHT;
					WorldRenderer.getWorldRenderer().setWorldY(worldY);
					
				}

				return true;
				
			//Signals when the LEFT arrow is pressed.
			case 21:
								
				if(canMoveLeft(worldX)) {
					
					worldX -= Constants.STEP_WIDTH;
					WorldRenderer.getWorldRenderer().setWorldX(worldX);
					
				}

				return true;
			
			//Signals when the RIGHT arrow is pressed.
			case 22:
				
				if(canMoveRight(worldX)) {
				
					worldX += Constants.STEP_WIDTH;
					WorldRenderer.getWorldRenderer().setWorldX(worldX);
					
				}

				return true;
			
		}
		
		return false;
	}
	
	public boolean keyTyped(char character) {
		return false;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		int row = WorldRenderer.getWorldRenderer().detectRow();
		int column = WorldRenderer.getWorldRenderer().detectColumn();

		int worldWidth = Constants.WORLD_WIDTH;
		int worldHeight = Constants.WORLD_HEIGHT;

		if(row >= 0 && row <= (worldHeight -1) && column >= 0 && column <= (worldWidth - 1)) {
			
			WorldController.getCurrentWorldController().gridPressed(row, column);
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}
	
	public boolean touchDragged(int x, int y, int position) {
		return false;
	}
	
	public boolean mouseMoved(int x, int y) {
		return false;
	}
	
	public boolean scrolled(int amount) {
		return false;
	}

	private boolean canMoveLeft(int posX) {

		if(posX > 0) {
			return true;
		}
		
		return false;
	}

	private boolean canMoveRight(int posX) {
		
		int worldWidth = Constants.WORLD_WIDTH * Constants.GRID_WIDTH;
		
		if(posX < (worldWidth - Constants.WINDOW_WIDTH) + (2 * Constants.BUFFER_WIDTH)) {
			return true;
		}
		
		return false;
	}

	private boolean canMoveUp(int posY) {

		int worldHeight = Constants.WORLD_HEIGHT * Constants.GRID_HEIGHT;
		
		if(posY < (worldHeight - Constants.WINDOW_HEIGHT) + (2 * Constants.BUFFER_HEIGHT)) {
			return true;
		}
		
		return false;
	}

	private boolean canMoveDown(int posY) {

		if(posY > 0) {
			return true;
		}
		
		return false;
	}
	
}
