
/**
 * @author Chris Bell
 * @date   05-27-2016
 * @info   
 */

package edu.miamioh.verilogworld.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.miamioh.SchematicRenderer.SchematicRendererMain;
import edu.miamioh.util.Constants;

public class DesktopLauncher {

	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Verilog World";
		config.width = Constants.WINDOW_WIDTH;
		config.height = Constants.WINDOW_HEIGHT;
		
//		new LwjglApplication(new VerilogWorldMain(), config);
		new LwjglApplication(new SchematicRendererMain(), config);
	
	}

}
