package edu.miamioh.worldSimulator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.simulator.ModuleInstance;

import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.simulator.RootModuleInstance;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.simulator.SimVisitor;
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
		this.freq = 100;
		this.shouldRun = false;
		
		this.clock = new ModulePort("Clock");
		this.reset = new ModulePort("Reset");
		reset.setValue(1); // Active low reset line
		
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
	
	public void resetWorldSim() {
		
		if (compiler.isCompiled()) {
			
			System.out.println("Reset!");
			
			// Update reset line
			toggleResetLine();
			
			// Pass new reset line value to blocks and simulate block communication
//			for(Block block : blocks) {
//				
//				if (block instanceof NormalBlock) {
//					NormalBlock normBlock = (NormalBlock)block;
//					
//					normBlock.updatePortValues();
//				}
//			}
			
			// Simulate sequenctial blocks and update properties
			for(Block block : blocks) {
				
				if (block instanceof NormalBlock) {
					NormalBlock normBlock = (NormalBlock)block;
					
					//Simulate
					sim.updateTargetBlock(normBlock);
					sim.resetSimulation();
					
					// Update
					normBlock.updateProperties();
				}
			}
			
			// Update clock
			toggleResetLine();
		}
	}
	
	public void executeCycle() {
		System.out.printf("Cycle\n");
		
		if (compiler.isCompiled()) {
			
			System.out.printf("Cycle\n");
			
			// Simulate combination logic blocks
//			for(Block block : blocks) {
//				
//				if (block instanceof NormalBlock) {
//					NormalBlock normBlock = (NormalBlock)block;
//					
//					//Simulate
//					sim.updateTargetBlock(normBlock);
//					sim.simComb();
//				}
//			}
			
			// Update clock
			toggleSequClock();
			
			// Simulate block communication
//			for(Block block : blocks) {
//				
//				if (block instanceof NormalBlock) {
//					NormalBlock normBlock = (NormalBlock)block;
//					
//					normBlock.updatePortValues();
//				}
//			}
			
			// Simulate sequenctial blocks and update properties
			for(Block block : blocks) {
				
				if (block instanceof NormalBlock) {
					NormalBlock normBlock = (NormalBlock)block;
					
					//Simulate
					sim.updateTargetBlock(normBlock);
					sim.simSequ();
					//sim.clean_sim_cycle();
					
					// Update
					normBlock.updateProperties();
				}
			}
			
			// Update clock
			toggleSequClock();
		}
	}
	
	public void toggleResetLine() {
		
		sim.setResetLine(!sim.getResetLine());
		int newValue = reset.getValue() == 1 ? 0 : 1;
		reset.setValue(newValue);
	}
	
	public void toggleSequClock() {
		
		SimVisitor visitor = sim.getRootModuleInstance().getVisitor();
		
		// Update clock
		int value = (clock.getValue() == 0) ? 1 : 0;
		clock.setValue(value);
		
		/* toggle between sequential sims and combinational sims */
		sim.setIsSequCycle(!sim.isSequCycle());
		visitor.setCondProcess(false); // Reset conditional processed flag
	}
	
//	public void sendClockSignal() {
//		
//		SimVisitor visitor = sim.getRootModuleInstance().getVisitor();
//		int clockValue = clock.getValue();
//		
//		// Update root module clock
//		ParseRegWire wire = sim.getRootModuleInstance().getHash_vars().get("clk");
//		if (wire != null) {
//			wire.setValue(visitor.getNewIndex(), 
//						  clockValue, visitor.isInSequ() || !sim.getResetLine());
//			wire.setValue(visitor.getOldIndex(), 
//						  clockValue, visitor.isInSequ() || !sim.getResetLine());
//		}
//		
//		// Update clock in all other modules
//		for (ModuleInstance sub : sim.getRootModuleInstance().getSubModulesList()) {
//			wire = sub.getHash_vars().get("clk");
//			
//			if (wire != null) {
//				wire.setValue(visitor.getNewIndex(), 
//							  clockValue, visitor.isInSequ() || !sim.getResetLine());
//				wire.setValue(visitor.getOldIndex(), 
//							  clockValue, visitor.isInSequ() || !sim.getResetLine());
//			}
//		}
//	}
	
	public void updateBlocks(ArrayList<Block> blocks) {
		
		for(Block block : blocks) {
			this.blocks.add(block);
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
