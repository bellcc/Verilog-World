
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   
 */

package edu.miamioh.verilogworld.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.miamioh.util.Constants;
import edu.miamioh.verilogWorld.VerilogWorldMain;

public class DesktopLauncher {

	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Verilog World";
		config.width = Constants.WINDOW_WIDTH;
		config.height = Constants.WINDOW_HEIGHT;
		
		config.resizable = false;
		config.backgroundFPS = 30;

		new LwjglApplication(new VerilogWorldMain(), config);
	
	}

}
