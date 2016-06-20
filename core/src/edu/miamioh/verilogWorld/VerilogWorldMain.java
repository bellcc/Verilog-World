package edu.miamioh.verilogWorld;

import com.badlogic.gdx.Game;

import edu.miamioh.Level.Level;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorScreen;

public class VerilogWorldMain extends Game {

	private static VerilogWorldMain verilogWorldMain;
	
	private VerilogWorldController verilogWorldController;
	private WorldEditorController worldEditorController;

	private WorldEditorScreen worldEditorScreen;
	
	public VerilogWorldMain() {
		verilogWorldMain = this;

		verilogWorldController = new VerilogWorldController();
		Level newLevel = new Level();
		worldEditorController = new WorldEditorController(verilogWorldController.getDefaultConfig(), newLevel);
		
		worldEditorScreen = new WorldEditorScreen(worldEditorController);
	}
	
	@Override
	public void create() {
		//Set the default screen
		
		this.setScreen(worldEditorScreen);
	}
	
	public static VerilogWorldMain getVerilogWorldMain() {
		return verilogWorldMain;
	}

}
