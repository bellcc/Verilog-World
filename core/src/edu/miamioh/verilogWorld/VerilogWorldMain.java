
/**
 * @author Clark Bell
 * @date   06-21-2016
 * @info   
 */

package edu.miamioh.verilogWorld;

import com.badlogic.gdx.Game;

import edu.miamioh.Level.Level;
import edu.miamioh.schematicRenderer.SchematicRendererMain;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorScreen;
import edu.miamioh.worldSimulator.WorldSimulatorController;
import edu.miamioh.worldSimulator.WorldSimulatorScreen;

import static edu.miamioh.verilogWorld.VerilogWorldStage.SCHEMATIC;

public class VerilogWorldMain extends Game {

	private static VerilogWorldMain verilogWorldMain;
	
	private VerilogWorldController verilogWorldController;
	private WorldEditorController worldEditorController;
	private WorldSimulatorController worldSimulatorController;
	
	private WorldEditorScreen worldEditorScreen;
	private WorldSimulatorScreen worldSimulatorScreen;

	private SchematicRendererMain schematicRendererMain;
	
	private VerilogWorldStage verilogWorldScreen;
	
	public VerilogWorldMain() {
		verilogWorldMain = this;
		verilogWorldScreen = VerilogWorldStage.WORLD_EDITOR;

		verilogWorldController = new VerilogWorldController();
		worldEditorController = new WorldEditorController(verilogWorldController.getDefaultConfig(), new Level());
		//worldSimulatorController = new WorldSimulatorController(verilogWorldController.getDefaultConfig());
		
		worldEditorScreen = new WorldEditorScreen(worldEditorController);
		//worldSimulatorScreen = new WorldSimulatorScreen(worldSimulatorController);
		
		//worldSimulatorScreen = new WorldSimulatorScreen(worldSimulatorController);

		//schematicRendererMain = new SchematicRendererMain();
	}
	
	@Override
	public void create() {
		//Set the default screen
		
		this.setScreen(worldEditorScreen);
		//this.setScreen(worldSimulatorScreen);
		//updateScreen();
	}
	
	public void setWorldEditorScreen() {
		this.setScreen(worldEditorScreen);
	}
	
	public void setWorldSimulatorScreen() {
		this.setScreen(worldSimulatorScreen);
	}


	
	public void updateScreen() {
		
		switch(verilogWorldScreen) {
			case WORLD_EDITOR:
				this.setScreen(worldEditorScreen);
				break;

			case WORLD_SIMULATOR:
				this.setScreen(worldSimulatorScreen);
				break;

			case SCHEMATIC:
				this.setScreen(schematicRendererMain);
				break;
		}
	}
	
	public static VerilogWorldMain getVerilogWorldMain() {
		return verilogWorldMain;
	}

	public SchematicRendererMain getSchematicRendererMain(){
		return schematicRendererMain;
	}
	
	public VerilogWorldStage getVerilogWorldScreen() {
		return verilogWorldScreen;
	}
	
	public void setVerilogWorldScreen(VerilogWorldStage verilogWorldScreen) {
		this.verilogWorldScreen = verilogWorldScreen;
	}

}
