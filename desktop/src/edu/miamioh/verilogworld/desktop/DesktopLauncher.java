
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   
 */

package edu.miamioh.verilogworld.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.miamioh.verilogWorld.VerilogWorldMain;

public class DesktopLauncher {

	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Verilog World";
		config.width = 600;
		config.height = 600;
		
		config.resizable = false;
		config.backgroundFPS = 30;

		new LwjglApplication(new VerilogWorldMain(), config);
	
	}

}
