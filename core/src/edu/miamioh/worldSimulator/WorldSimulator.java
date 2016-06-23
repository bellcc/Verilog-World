package edu.miamioh.worldSimulator;

import java.util.ArrayList;

import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class WorldSimulator {
	
	private Parse compiler;
	private RootModuleSimulator sim;
	private ArrayList<ModuleWrapper> modules;
	
	public WorldSimulator(RootModuleSimulator sim) {
		modules = new ArrayList<>();
		compiler = VerilogWorldController.getController().getCompiler();
		this.sim = sim;
	}
	
	public void executeCycle() {
		
	}
	
	public void addModule(ModuleWrapper module) {
		modules.add(module);
	}
	
	public void removeModule(ModuleWrapper module) {
		modules.remove(module);
	}
	
	public Parse getCompiler() {return this.compiler;}
	public RootModuleSimulator getRootModuleSimulator () {return this.sim;}
}
