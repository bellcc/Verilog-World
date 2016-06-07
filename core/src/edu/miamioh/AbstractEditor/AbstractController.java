
/**
 * @author Chris Bell
 * @date   6-4-2016
 * @info   
 */

package edu.miamioh.AbstractEditor;

import edu.miamioh.Configuration.Configuration;

public abstract class AbstractController {
	
	protected Configuration defaultConfig;

	public abstract void init();
	public abstract void update();
		
}
