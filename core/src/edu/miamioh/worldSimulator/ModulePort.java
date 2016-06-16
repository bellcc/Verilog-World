package edu.miamioh.worldSimulator;

public class ModulePort {
	
	ModulePort target;
	
	public ModulePort() {
		this(null);
	}
	
	public ModulePort(ModulePort target) {
		this.target = target;
	}
	
	public ModulePort getTargetPort() 				{return this.target;}
	public void setTargetPort(ModulePort target) 	{this.target = target;}
}
