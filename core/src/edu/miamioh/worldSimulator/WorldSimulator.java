package edu.miamioh.worldSimulator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class WorldSimulator {
	
	private Parse compiler;
	private RootModuleSimulator sim;
	private ArrayList<ModuleWrapper> modules;
	
	private int freq;
	private boolean shouldRun;
	
	public WorldSimulator(RootModuleSimulator sim) {
		this.modules = new ArrayList<>();
		this.compiler = VerilogWorldController.getController().getCompiler();
		this.sim = sim;
		this.freq = 1;
		this.shouldRun = false;
		
		// Construct timer to run the simulator at a certain frequency
		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (shouldRun) {
					executeCycle();
				}
			}
		},
								0,
								1000 / freq);
	}
	
	public void executeCycle() {
		System.out.printf("Cycle\n");
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
