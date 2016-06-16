package edu.miamioh.worldSimulator;

import java.util.ArrayList;

import edu.miamioh.simulator.RootModuleInstance;

public class ModuleWrapper {

	private RootModuleInstance module;
	private ArrayList<ModulePort> ports;
	
	public ModuleWrapper(RootModuleInstance module) {
		this.module = module;
	}
	
	public void addPort(ModulePort port) {
		this.ports.add(port);
	}
	
	public ArrayList<ModulePort> getPorts() {return this.ports;}
}
