package edu.miamioh.worldSimulator;

import edu.miamioh.simulator.ParseRegWire;

public class ModulePort {
	
	private ModulePort target;
	private ParseRegWire wire;
	private String name;
	private int value;
	private boolean isInput;
	
	public ModulePort() {
		this("");
	}
	
	public ModulePort(String name) {
		this(name, null, null, false);
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
