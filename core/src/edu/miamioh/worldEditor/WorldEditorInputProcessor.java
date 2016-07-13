 
/**
 * @author Chris Bell
 * @date   05-19-2016
 * @info   This java class deals with user input from 
 * 		   the mouse and keyboard. The input is then redirected 
 *         to modify the work in some manner.
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.InputProcessor;

import edu.miamioh.verilogWorld.VerilogWorldController;

public class WorldEditorInputProcessor implements InputProcessor{

	private boolean clicked = false;
	
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

		int worldX = WorldEditorScreen.getScreen().getWorldX();
		int worldY = WorldEditorScreen.getScreen().getWorldY();
				
		switch (keyCode) {
		
			//Signals when the UP arrow is pressed.
			case 19:
				
				if(canMoveUp(worldY)) {
					
					worldY += WorldEditorController.getCurrentController().getStepHeight();
					WorldEditorScreen.getScreen().setWorldY(worldY);
										
				}
				
				return true;
			
			//Signals when the DOWN arrow is pressed.
			case 20:
				
				if(canMoveDown(worldY)) {
					
					worldY -= WorldEditorController.getCurrentController().getStepHeight();
					WorldEditorScreen.getScreen().setWorldY(worldY);
					
				}

				return true;
				
			//Signals when the LEFT arrow is pressed.
			case 21:
								
				if(canMoveLeft(worldX)) {
					
					worldX -= WorldEditorController.getCurrentController().getStepWidth();
					WorldEditorScreen.getScreen().setWorldX(worldX);
					
				}

				return true;
			
			//Signals when the RIGHT arrow is pressed.
			case 22:
				
				if(canMoveRight(worldX)) {
				
					worldX += WorldEditorController.getCurrentController().getStepWidth();
					WorldEditorScreen.getScreen().setWorldX(worldX);
					
				}

				return true;
			
		}
		
		return false;
	}
	
	public boolean keyTyped(char character) {
		return false;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		int width = WorldEditorController.getCurrentController().getWorldWidth();
		int height = WorldEditorController.getCurrentController().getWorldHeight();
		
		if(width == 0 || height == 0) {
			return true;
		}
		
		if(!clicked) {
			
			clicked = true;
			
			int row = WorldEditorScreen.getScreen().detectRow();
			int column = WorldEditorScreen.getScreen().detectColumn();
			
			int worldWidth = WorldEditorController.getCurrentController().getWorldWidth();
			int worldHeight = WorldEditorController.getCurrentController().getWorldHeight();
			
			if(row >= worldHeight || row < 0 || column >= worldWidth || column < 0) {
				return true;
			}

			WorldEditorController.getCurrentController().gridPressed(row, column);
			
		}
		
		return true;
	}
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		
		clicked = false;
		
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

		return posX > 0;

	}

	private boolean canMoveRight(int posX) {
		
		int worldWidth = WorldEditorController.getCurrentController().getWorldWidth();
		int gridWidth = WorldEditorController.getCurrentController().getGridWidth();
		int worldSize = worldWidth * gridWidth;
		
		int windowWidth = VerilogWorldController.WINDOW_WIDTH;
		int bufferWidth = WorldEditorController.getCurrentController().getBufferWidth();
		
		int toolBarWidth = WorldEditorScreen.getScreen().getToolBarWidth();
		
		if(posX < (worldSize - windowWidth) + (2 * bufferWidth) + toolBarWidth) {
			return true;
		}
		
		return false;

	}

	private boolean canMoveUp(int posY) {
		
		int worldHeight = WorldEditorController.getCurrentController().getWorldHeight();
		int gridHeight = WorldEditorController.getCurrentController().getGridHeight();
		int worldSize = worldHeight * gridHeight;
		
		int windowHeight = VerilogWorldController.WINDOW_HEIGHT;
		int bufferHeight = WorldEditorController.getCurrentController().getBufferHeight();
		
		if(posY < (worldSize - windowHeight) + (2 * bufferHeight)) {
			return true;
		}
		
		return false;

	}

	private boolean canMoveDown(int posY) {

		return posY > 0;

	}
	
}
