
/**
 * @author Clark Bell
 * @date   06-21-2016
 * @info   
 */

package edu.miamioh.verilogWorld;

import com.badlogic.gdx.Game;
import edu.miamioh.Screens.ChallengesScreen;
import edu.miamioh.Screens.MainMenuScreen;
import edu.miamioh.Screens.PlayScreen;
import edu.miamioh.schematicRenderer.SchematicRendererScreen;
import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogEditor.RunEditor;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorScreen;
import edu.miamioh.worldSimulator.WorldSimulatorController;
import edu.miamioh.worldSimulator.WorldSimulatorScreen;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class VerilogWorldMain extends Game {

	private static VerilogWorldMain verilogWorldMain;
	
	private VerilogWorldController verilogWorldController;
	private WorldEditorController worldEditorController;
	private WorldSimulatorController worldSimulatorController;
	
	private WorldEditorScreen worldEditorScreen;
	private WorldSimulatorScreen worldSimulatorScreen;

	private MainMenuScreen mainMenuScreen;
	private PlayScreen playScreen;
	private ChallengesScreen challengesScreen;
	private SchematicRendererScreen schematicRendererScreen;
		
	private String	VERILOG_WORLD_DEVELOPMENT	= "VERILOG_WORLD_DEVELOPMENT";
	
	private RootModuleSimulator sim;
	private Parse compiler;
	
	public VerilogWorldMain() {
		
		verilogWorldMain = this;
	}
	
	@Override
	public void create() {
		
		// Make controller and update the sim and compiler variables
		verilogWorldController = new VerilogWorldController();
		this.sim = verilogWorldController.getSim().getRootModuleSimulator();
		this.compiler = verilogWorldController.getCompiler();

		worldEditorController = new WorldEditorController(verilogWorldController.getDefaultConfig(), 
														  verilogWorldController.getCurrentLevel());
		worldSimulatorController = new WorldSimulatorController(verilogWorldController.getDefaultConfig());
		
		worldEditorScreen = new WorldEditorScreen(worldEditorController);
		worldSimulatorScreen = new WorldSimulatorScreen(worldSimulatorController);

		schematicRendererScreen = new SchematicRendererScreen();
				
		mainMenuScreen = new MainMenuScreen(getVerilogWorldMain());
		this.setScreen(mainMenuScreen);
		
		//this.setScreen(worldEditorScreen);
	}
		
	public void setWorldEditorScreen() {
		this.setScreen(worldEditorScreen);
	}
	
	public void setWorldSimulatorScreen() {
		this.setScreen(worldSimulatorScreen);
	}

	public void setSchematicRendererScreen() {
		this.setScreen(schematicRendererScreen);
	}
	
	public void setMainMenuScreen() {
		mainMenuScreen = new MainMenuScreen(getVerilogWorldMain());
		this.setScreen(mainMenuScreen);
	}
	
	public void setPlayScreen() {
		playScreen = new PlayScreen(getVerilogWorldMain());
		this.setScreen(playScreen);
	}
	
	public void setChallengesScreen() {
		challengesScreen = new ChallengesScreen(getVerilogWorldMain());
		this.setScreen(challengesScreen);
	}

	public void launchVerilogEditor(String fileName){
		/*String pathToJar = getRootPath() + "/VerilogEditor.jar";
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", pathToJar, getRootPath(), fileName);

		try {
			Process p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		Thread thread = new Thread(new RunEditor(sim, compiler, fileName));
		thread.start();
	}

	public String getRootPath()
	{
		String path = null;
		try
		{
			path = VerilogWorldMain.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			// Path can be a filename when executing a jar file. (filename/../)
			// doesn't work.
			path = new File(path).getParent() + "/../";
			// Development environment has different directory structure than
			// that when releasing
			if (isDevelopment())
				path += "../";
			/* getCanonicalPath() returns a path containing "\", which doesn't
			 * work (even on Windows) when passing the path as a command line
			 * argument. Thus, regular expression <code>\\\b</code> is used to
			 * substitute '\' to '/'. */
			path = new File(path).getCanonicalPath().replaceAll("\\\\\\b", "/");
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return path;
	}
	public boolean isDevelopment()
	{
		String env = System.getenv(VERILOG_WORLD_DEVELOPMENT);
		return env != null && !env.equals("0");
	}

	public void setSimulator(RootModuleSimulator sim) {this.sim = sim;}
	public void setCompiler(Parse compiler) {this.compiler = compiler;}
	
	public static VerilogWorldMain getVerilogWorldMain() {
		return verilogWorldMain;
	}

	public SchematicRendererScreen getSchematicRendererScreen(){
		return schematicRendererScreen;
	}
}
