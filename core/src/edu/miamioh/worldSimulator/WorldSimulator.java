package edu.miamioh.worldSimulator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.GameObjects.NormalBlockType;
import edu.miamioh.GameObjects.blocks.NullBlock;
import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.ParseRegWire;
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
		this.freq = 2;
		this.shouldRun = false;
		
		this.clock = new ModulePort(new NullBlock(), "Clock", new ParseRegWire(null), false);
		this.reset = new ModulePort(new NullBlock(), "Reset", new ParseRegWire(null), false);
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
		
		// Update master sim clock
		int value = (reset.getValue() == 0) ? 1 : 0;
		reset.setValue(value);
		
		// Bring reset line high in all blocks
		for(Block block : blocks) {
			
			NormalBlock norm = (NormalBlock)block;
			
			sim.updateTargetBlock(norm);
			toggleResetLine();
		}
			
		// Bring simulate the blocks to reset them
		for(Block block : blocks) {
			
			if (block instanceof NormalBlock) {
				NormalBlock normBlock = (NormalBlock)block;
				
				//Simulate
				sim.updateTargetBlock(normBlock);
				simBlock((NormalBlock)normBlock);
				
				// Update
				normBlock.updateProperties();
			}
		}
	}
	
	public void executeCycle() {

		if (compiler.isCompiled()) {
			
			// Update master sim clock
			int value = (clock.getValue() == 0) ? 1 : 0;
			clock.setValue(value);
			
			// toggle the sequential flag in the simulator
			sim.setIsSequCycle(!sim.isSequCycle());
			
			// Reset sequential conditionals for all blocks
			for(Block block : blocks) {
				
				NormalBlock norm = (NormalBlock)block;
				
				sim.updateTargetBlock(norm);
				resetSequentialConditionals();
			}
			
			// Simulate sequenctial blocks and update properties
			for(Block block : blocks) {
				
				if (block instanceof NormalBlock) {
					
					simBlock((NormalBlock)block);
				}
			}
			
//			// Update clock
//			toggleSequClock();
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
		
		SimVisitor visitor = sim.getRootModuleInstance().getVisitor();
		
		sim.setResetLine(!sim.getResetLine());
	}
	
	public void resetSequentialConditionals() {
		
		SimVisitor visitor = sim.getRootModuleInstance().getVisitor();
		
		visitor.setCondProcess(false); // Reset conditional processed flag
	}
	
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
	public ModulePort getClockPort() 		{return this.clock;}
	public ModulePort getResetPort()		{return this.reset;}
}
