package edu.miamioh.worldSimulator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleInstance;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class WorldSimulator {
	
	private Parse compiler;
	private RootModuleSimulator sim;
	private ArrayList<Block> blocks;
	private ArrayList<ModuleWrapper> modules;
	
	private int freq;
	private boolean shouldRun;
	
	private ModulePort clock;
	private ModulePort reset;
	
	public WorldSimulator(RootModuleSimulator sim) {
		this.modules = new ArrayList<>();
		this.blocks = new ArrayList<>();
		this.compiler = VerilogWorldController.getController().getCompiler();
		this.sim = sim;
		this.freq = 1;
		this.shouldRun = false;
		
		this.clock = new ModulePort();
		this.reset = new ModulePort();
		
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
	
	public void addTestPorts() {
		
		for (ModuleWrapper wrapper : modules) {
			RootModuleInstance module = wrapper.getModule();
			ArrayList<ModulePort> ports = wrapper.getPorts();
			
			if (module.getPorts_list().contains("clk")) {
				wrapper.addPort(new ModulePort("clk", clock, true));
			}
			
			if (module.getPorts_list().contains("rst")) {
				wrapper.addPort(new ModulePort("rst", clock, true));
			}
		}
	}
	
	public void executeCycle() {
		
		System.out.printf("Cycle\n");
		
		// Update clock
		int value = (clock.getValue() == 0) ? 1 : 0;
		clock.setValue(value);
		
		// Simulate block communication
		for(Block block : blocks) {
			
			if (block instanceof NormalBlock) {
				NormalBlock normBlock = (NormalBlock)block;
				
				normBlock.updatePortValues();
			}
		}
		
		// Simulate blocks and update properties
		for(Block block : blocks) {
			
			if (block instanceof NormalBlock) {
				NormalBlock normBlock = (NormalBlock)block;
				
				//Simulate
				sim.updateTargetBlock(normBlock);
				sim.sim_cycle();
				
				// Update
				normBlock.updateProperties();
			}
		}
	}
	
	public void updateModules() {
		
		// Clear module list so it can be remade with new modules
		modules.clear();
		
		// Fill with new modules
		for(Block block : blocks) {
			if (block instanceof NormalBlock) {
				NormalBlock normBlock = (NormalBlock)block;
				
				ModuleWrapper module = normBlock.compile();
				normBlock.addDefaultPorts(clock, reset);
				modules.add(module);
			}
		}
	}
	
	public void setBlockList(ArrayList<Block> blocks) {this.blocks = blocks;}
	public Parse getCompiler() {return this.compiler;}
	public RootModuleSimulator getRootModuleSimulator () {return this.sim;}
	public void setShouldRun(boolean value) {this.shouldRun = value;}
}
