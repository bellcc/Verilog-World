package edu.miamioh.verilogWorld;

import com.badlogic.gdx.Game;

import edu.miamioh.Level.Level;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorScreen;
import edu.miamioh.worldSimulator.WorldSimulatorController;
import edu.miamioh.worldSimulator.WorldSimulatorScreen;

public class VerilogWorldMain extends Game {

	private static VerilogWorldMain verilogWorldMain;
	
	private VerilogWorldController verilogWorldController;
	private WorldEditorController worldEditorController;
	private WorldSimulatorController worldSimulatorController;
	
	private WorldEditorScreen worldEditorScreen;
	private WorldSimulatorScreen worldSimulatorScreen;
	
	private VerilogWorld verilogWorldScreen;
	
	public VerilogWorldMain() {
		verilogWorldMain = this;
		verilogWorldScreen = VerilogWorld.WORLD_EDITOR;

		verilogWorldController = new VerilogWorldController();
		worldEditorController = new WorldEditorController(verilogWorldController.getDefaultConfig(), new Level());
		worldSimulatorController = new WorldSimulatorController(verilogWorldController.getDefaultConfig());
		
		worldEditorScreen = new WorldEditorScreen(worldEditorController);
		worldSimulatorScreen = new WorldSimulatorScreen(worldSimulatorController);
		
		
	}
	
	@Override
	public void create() {
		//Set the default screen
		
		//this.setScreen(worldEditorScreen);
		this.setScreen(worldSimulatorScreen);
		//updateScreen();
	}
	
	public void updateScreen() {
		
		switch(verilogWorldScreen) {
		case WORLD_EDITOR:
			this.setScreen(worldEditorScreen);
			break;
			
		case WORLD_SIMULATOR:
			break;
		}
	}
	
	public static VerilogWorldMain getVerilogWorldMain() {
		return verilogWorldMain;
	}
	
	public VerilogWorld getVerilogWorldScreen() {
		return verilogWorldScreen;
	}
	
	public void setVerilogWorldScreen(VerilogWorld verilogWorldScreen) {
		this.verilogWorldScreen = verilogWorldScreen;
	}

}
