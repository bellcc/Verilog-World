
/**
 * @author Chris Bell
 * @date   6-14-2016
 * @info   
 */

package edu.miamioh.worldSimulator;

import com.badlogic.gdx.InputProcessor;

import edu.miamioh.worldSimulator.WorldSimulatorController;
import edu.miamioh.worldSimulator.WorldSimulatorScreen;

public class WorldSimulatorInputProcessor implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		int worldX = WorldSimulatorScreen.getScreen().getWorldX();
		int worldY = WorldSimulatorScreen.getScreen().getWorldY();
				
		switch (keycode) {
		
			//Signals when the UP arrow is pressed.
			case 19:
				
				if(canMoveUp(worldY)) {
					
					worldY += WorldSimulatorController.getController().getStepHeight();
					WorldSimulatorScreen.getScreen().setWorldY(worldY);
										
				}
				
				return true;
			
			//Signals when the DOWN arrow is pressed.
			case 20:
				
				if(canMoveDown(worldY)) {
					
					worldY -= WorldSimulatorController.getController().getStepHeight();
					WorldSimulatorScreen.getScreen().setWorldY(worldY);
					
				}

				return true;
				
			//Signals when the LEFT arrow is pressed.
			case 21:
								
				if(canMoveLeft(worldX)) {
					
					worldX -= WorldSimulatorController.getController().getStepWidth();
					WorldSimulatorScreen.getScreen().setWorldX(worldX);
					
				}

				return true;
			
			//Signals when the RIGHT arrow is pressed.
			case 22:
				
				if(canMoveRight(worldX)) {
				
					worldX += WorldSimulatorController.getController().getStepWidth();
					WorldSimulatorScreen.getScreen().setWorldX(worldX);
					
				}

				return true;
			
		}
		
		return false;
		
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
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
		
		int worldWidth = WorldSimulatorController.getController().getWorldWidth();
		int gridWidth = WorldSimulatorController.getController().getGridWidth();
		int worldSize = worldWidth * gridWidth;
		
		int windowWidth = WorldSimulatorController.getController().getWindowWidth();
		int bufferWidth = WorldSimulatorController.getController().getBufferWidth();
		
		int toolBarWidth = WorldSimulatorScreen.getScreen().getToolBarWidth();
		
		if(posX < (worldSize - windowWidth) + (2 * bufferWidth) + toolBarWidth) {
			return true;
		}
		
		return false;
	}

	private boolean canMoveUp(int posY) {
		
		int worldHeight = WorldSimulatorController.getController().getWorldHeight();
		int gridHeight = WorldSimulatorController.getController().getGridHeight();
		int worldSize = worldHeight * gridHeight;
		
		int windowHeight = WorldSimulatorController.getController().getWindowHeight();
		int bufferHeight = WorldSimulatorController.getController().getBufferHeight();
		
		if(posY < (worldSize - windowHeight) + (2 * bufferHeight)) {
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
