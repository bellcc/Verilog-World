
/**
 * @author Chris Bell
 * @date   6-3-2016
 * @info   
 */

package edu.miamioh.AbstractEditor;

import com.badlogic.gdx.InputProcessor;

public abstract class AbstractInputProcessor implements InputProcessor{

	public abstract boolean keyDown(int keyCode);
	
	public abstract boolean keyUp(int keyCode);
	
	public abstract boolean keyTyped(char character);
	
	public abstract boolean touchDown(int x, int y, int pointer, int button);
	
	public abstract boolean touchUp(int x, int y, int pointer, int button);
	
	public abstract boolean touchDragged(int x, int y, int position);
	
	public abstract boolean mouseMoved(int x, int y);
	
	public abstract boolean scrolled(int amount);
	
}