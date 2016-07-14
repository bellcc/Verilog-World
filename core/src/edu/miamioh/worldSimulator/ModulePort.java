package edu.miamioh.worldSimulator;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.GameObjects.NormalBlock;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class ModulePort {
	
	private NormalBlock block;
	private ModulePort target;
	private ParseRegWire wire;
	private String name;
	private int value;
	private boolean isInput;
	
	public ModulePort(NormalBlock block, String portName, ParseRegWire wire, boolean isInput) {
		this(block, portName, null, wire, isInput);
	}
	
	/*
	 * Adds a port for project from a file. The root module simulator must be set to the target block by
	 * using the updateTargetBlock method.
	 */
	public ModulePort(Block block, String portName, boolean isInput, String wireName,
					  Block targetBlock, String targetName, boolean targetIsInput, String targetWireName) throws InvalidModulePortException {
		
		RootModuleSimulator sim = VerilogWorldController.getController().getSim().getRootModuleSimulator();
		
		// Make sure input and output pair exists
		if(!(isInput ^ targetIsInput)) {
			throw new InvalidModulePortException("ModulePort usage error: Module ports' inInput values are the same.");
		}
		
		this.block = (NormalBlock)block;
		this.name = portName;
		this.isInput = isInput;
		this.value = 0;
		
		// Target the current block and get the wire
		sim.updateTargetBlock((NormalBlock)block);
		this.wire = sim.getRootModuleInstance().getHash_vars().get(wireName);

		// Target the target block and get its wire
		ParseRegWire targetWire = null;
		if (targetBlock != null) {
			sim.updateTargetBlock((NormalBlock)targetBlock);
			targetWire = sim.getRootModuleInstance().getHash_vars().get(targetWireName);
		}
		
		// Connect the ports
		if (targetWire != null) {
			this.target = new ModulePort((NormalBlock)targetBlock, targetName, this, targetWire, targetIsInput);
		}
		else {
			if (targetName.equals("Clock")) {
				this.target = VerilogWorldController.getController().getSim().getClockPort();
			}
			else if (targetName.equals("Reset")) {
				this.target = VerilogWorldController.getController().getSim().getResetPort();
			}
			else {
				System.out.println("Clock or Reset mismatch!");
			}
		}
	}
	
	public ModulePort(NormalBlock block, String name, ModulePort target, ParseRegWire wireTarget, boolean isInput) {
		this.block = block;
		this.target = target;
		this.wire = wireTarget;
		this.isInput = isInput;
		this.name = name;
		this.value = 0;
		
		if (target != null && target.getTargetPort() == null) target.setTargetPort(this);
	}
	
	public void setValue(int value) {
		this.value = value;
		
		// Pass value to target port
		if (!this.isInput && target != null) {
			target.setValue(value);
		}
		// Or pass value to target wire
		else if (this.isInput && wire != null) {
			wire.setValue(0, this.value, true);
			wire.setValue(1, this.value, true);
		}
	}
	
	public void setTargetPort(ModulePort target) {
		this.target = target;
		
		// Pass value from output port to input port
		if(this.isInput) {
			this.value = target.getValue();
		}
		else {
			target.setValue(this.value);
		}
	}
	
	// Removes this port from this block
	public void remove() {
		
		this.block.getModuleWrapper().getPortsList().remove(this);
		this.block.getModuleWrapper().getPortsHash().remove(this.getName());
	}
	
	public ParseRegWire getWire()					{return this.wire;}
	public String getName()							{return this.name;}
	public ModulePort getTargetPort() 				{return this.target;}
	public void setIsInput(boolean value) 			{this.isInput = value;}
	public boolean getIsInput()						{return this.isInput;}
	public int getValue() 							{return this.value;}
	public NormalBlock getBlock()					{return this.block;}
}
