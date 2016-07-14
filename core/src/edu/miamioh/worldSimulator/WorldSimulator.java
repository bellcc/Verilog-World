package edu.miamioh.worldSimulator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;
import edu.miamioh.Level.Level;
import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.simulator.SimVisitor;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class WorldSimulator {
	
	private Parse compiler;
	private RootModuleSimulator sim;
	//TODO
	private ArrayList<Block> blocks;
	
	private int freq;
	private boolean shouldRun;
	
	private ModulePort clock;
	private ModulePort reset;
	
	public WorldSimulator(RootModuleSimulator sim) {
		this.blocks = null;
		this.compiler = VerilogWorldController.getController().getCompiler();
		this.sim = sim;
		this.freq = 1;
		this.shouldRun = false;
		
		this.clock = new ModulePort(null, "Clock", false);
		this.reset = new ModulePort(null, "Reset", false);
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

		if (compiler.isCompiled()) {
			
			System.out.printf("Cycle\n");
			
			// Update master sim clock
			int value = (clock.getValue() == 0) ? 1 : 0;
			clock.setValue(value);
			
			// Toggle the sequential block for every block
			for(Block block : blocks) {
				
				NormalBlock norm = (NormalBlock)block;
				
				sim.updateTargetBlock(norm);
				toggleSequClock();
			}
			
			// Simulate sequenctial blocks and update properties
			for(Block block : blocks) {
				
				if (block instanceof NormalBlock) {
					
					simBlock((NormalBlock)block);
				}
			}
			
			// Update clock
			toggleSequClock();
		}
	}
	
	/*
	 * Does the actual simulation of the block
	 */
	private void simBlock(NormalBlock block) {
		
		// Update input ports
		block.updateInputs();
		
		//Simulate
		sim.updateTargetBlock(block);
		sim.simSequ();
		//sim.clean_sim_cycle();
		
		// Update
		block.updateProperties();
		
		// Update ouput port values
		block.updateOutputs();
	}
	
	/*
	 * Re-simulates a given block if it's input ports have changed
	 */
	public void resimBlock(NormalBlock block) {
		sim.updateTargetBlock(block);
		simBlock(block);
	}
	
	public void toggleResetLine() {
		
		sim.setResetLine(!sim.getResetLine());
		int newValue = reset.getValue() == 1 ? 0 : 1;
		reset.setValue(newValue);
	}
	
	public void toggleSequClock() {
		
		SimVisitor visitor = sim.getRootModuleInstance().getVisitor();
		
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
	
	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}
	
	public void addDefaultPorts() {
		
		for(Block block : blocks) {
			((NormalBlock)block).addDefaultPorts(clock, reset);
		}
	}
	
	public void recompileBlocks() {
		
		for(Block block : blocks) {
			((NormalBlock)block).compile();
		}
	}
	
	public void setBlockList(ArrayList<Block> blocks) {this.blocks = blocks;}
	public Parse getCompiler() {return this.compiler;}
	public RootModuleSimulator getRootModuleSimulator () {return this.sim;}
	public void setShouldRun(boolean value) {this.shouldRun = value;}
}
