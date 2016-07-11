package edu.miamioh.worldSimulator;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.simulator.ParseRegWire;
import edu.miamioh.verilogWorld.VerilogWorldController;

public class ModulePort {
	
	private ModulePort target;
	private ParseRegWire wire;
	private String name;
	private int value;
	private boolean isInput;
	
	public ModulePort() {
		this("", false);
	}
	
	public ModulePort(String name, boolean isInput) {
		this(name, null, null, isInput);
	}
	
	/*
	 * Adds a port for project from a file. The root module simulator must be set to the target block by
	 * using the updateTargetBlock method.
	 */
	public ModulePort(String portName, boolean isInput, String wireName,
					  Block targetBlock, String targetName, boolean targetIsInput, String targetWireName) throws InvalidModulePortException {
		
		if(!(isInput ^ targetIsInput)) {
			throw new InvalidModulePortException("ModulePort usage error: Module ports' inInput values are the same.");
		}
		
		this.name = portName;
		this.isInput = isInput;
		this.value = 0;
		this.wire = VerilogWorldController.getController().getSim().getRootModuleSimulator().getRootModuleInstance().getHash_vars().get("wireName");
		ParseRegWire targetWire = Block.getRootSim().getRootModuleInstance().getHash_vars().get(targetWireName);
		this.target = new ModulePort(targetName, this, targetWire, targetIsInput);
	}
	
	public ModulePort(String name, ModulePort target, ParseRegWire wireTarget, boolean isInput) {
		this.target = target;
		this.wire = wireTarget;
		this.isInput = isInput;
		this.name = name;
		this.value = 0;
		
		if (target != null) target.setTargetPort(this);
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
	
	public String getName()							{return this.name;}
	public ModulePort getTargetPort() 				{return this.target;}
	public void setIsInput(boolean value) 			{this.isInput = value;}
	public int getValue() 							{return this.value;}
}
