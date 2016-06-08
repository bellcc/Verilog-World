
/**
 * @author Chris Bell
 * @date   6-4-2016
 * @info   
 */

package edu.miamioh.AbstractEditor;

import com.badlogic.gdx.utils.Disposable;

public abstract class AbstractRenderer implements Disposable{

	public abstract void init();
	public abstract void render();
	public abstract void resize(int width, int height);
	public abstract void dispose();
	
}
