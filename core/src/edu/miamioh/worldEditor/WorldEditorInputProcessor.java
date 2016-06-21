 
/**
 * @author Chris Bell
 * @date   05-19-2016
 * @info   This java class deals with user input from 
 * 		   the mouse and keyboard. The input is then redirected 
 *         to modify the work in some manner.
 */

package edu.miamioh.worldEditor;

import edu.miamioh.AbstractEditor.AbstractInputProcessor;
import edu.miamioh.GameObjects.Block;
import edu.miamioh.Linked.LinkedList;

public class WorldEditorInputProcessor extends AbstractInputProcessor{

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

		int worldX = WorldEditorRenderer.getWorldRenderer().getWorldX();
		int worldY = WorldEditorRenderer.getWorldRenderer().getWorldY();
				
		switch (keyCode) {
		
			//Signals when the UP arrow is pressed.
			case 19:
				
				if(canMoveUp(worldY)) {
					
					worldY += WorldEditorController.getCurrentWorldController().getStepHeight();
					WorldEditorRenderer.getWorldRenderer().setWorldY(worldY);
										
				}
				
				return true;
			
			//Signals when the DOWN arrow is pressed.
			case 20:
				
				if(canMoveDown(worldY)) {
					
					worldY -= WorldEditorController.getCurrentWorldController().getStepHeight();
					WorldEditorRenderer.getWorldRenderer().setWorldY(worldY);
					
				}

				return true;
				
			//Signals when the LEFT arrow is pressed.
			case 21:
								
				if(canMoveLeft(worldX)) {
					
					worldX -= WorldEditorController.getCurrentWorldController().getStepWidth();
					WorldEditorRenderer.getWorldRenderer().setWorldX(worldX);
					
				}

				return true;
			
			//Signals when the RIGHT arrow is pressed.
			case 22:
				
				if(canMoveRight(worldX)) {
				
					worldX += WorldEditorController.getCurrentWorldController().getStepWidth();
					WorldEditorRenderer.getWorldRenderer().setWorldX(worldX);
					
				}

				return true;
			
		}
		
		return false;
	}
	
	public boolean keyTyped(char character) {
		return false;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		int row = WorldEditorRenderer.getWorldRenderer().detectRow();
		int column = WorldEditorRenderer.getWorldRenderer().detectColumn();
		
		WorldEditorController.getCurrentWorldController().gridPressed(row, column);
		
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

		return posX > 0;

	}

	private boolean canMoveRight(int posX) {
		
		int worldWidth = WorldEditorController.getCurrentWorldController().getWorldWidth();
		int gridWidth = WorldEditorController.getCurrentWorldController().getGridWidth();
		int worldSize = worldWidth * gridWidth;
		
		int windowWidth = WorldEditorController.getCurrentWorldController().getWindowWidth();
		int bufferWidth = WorldEditorController.getCurrentWorldController().getBufferWidth();
		
		int toolBarWidth = WorldEditorRenderer.getWorldRenderer().getToolBarWidth();
		//int subToolBarWidth = WorldEditorRenderer.getWorldRenderer().getSubToolBarWidth();
		int subToolBarWidth = WorldEditorRenderer.getWorldRenderer().getToolBarOptionsWidth();

		return posX < (worldSize - windowWidth) + (2 * bufferWidth) + toolBarWidth + subToolBarWidth;

	}

	private boolean canMoveUp(int posY) {
		
		int worldHeight = WorldEditorController.getCurrentWorldController().getWorldHeight();
		int gridHeight = WorldEditorController.getCurrentWorldController().getGridHeight();
		int worldSize = worldHeight * gridHeight;
		
		int windowHeight = WorldEditorController.getCurrentWorldController().getWindowHeight();
		int bufferHeight = WorldEditorController.getCurrentWorldController().getBufferHeight();

		return posY < (worldSize - windowHeight) + (2 * bufferHeight);

	}

	private boolean canMoveDown(int posY) {

		return posY > 0;

	}
	
}
