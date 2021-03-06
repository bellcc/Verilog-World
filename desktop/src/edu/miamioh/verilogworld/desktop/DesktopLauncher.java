
/**
 * @author Clark Bell
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
		
		//config.addIcon("game-256.png", FileType.Internal);
		//config.addIcon("game-128.png", FileType.Internal);
		//config.addIcon("game-32.png", FileType.Internal);

		config.title = "Verilog World";
		config.width = 800;
		config.height = 600;
		
		config.backgroundFPS = 30;

		new LwjglApplication(new VerilogWorldMain(), config);

	}

}
