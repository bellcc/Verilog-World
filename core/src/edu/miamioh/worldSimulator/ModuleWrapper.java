package edu.miamioh.worldSimulator;

import java.util.ArrayList;
import java.util.Hashtable;

import edu.miamioh.simulator.RootModuleInstance;

public class ModuleWrapper {

	private RootModuleInstance module;
	private ArrayList<ModulePort> portsList;
	private Hashtable<String, ModulePort> portsHash;
	
	public ModuleWrapper(RootModuleInstance module) {
		this.module = module;
		portsList = new ArrayList<>();
		portsHash = new Hashtable<>();
	}
	
	public void addPort(ModulePort port) {
		this.portsList.add(port);
		this.portsHash.put(port.getName(), port);
	}
	
	public ArrayList<ModulePort> getPortsList() 			{return this.portsList;}
	public Hashtable<String, ModulePort> getPortsHash() 	{return this.portsHash;}
	public RootModuleInstance getModule() 					{return this.module;}
	public void setRootModuleInstance(RootModuleInstance module) {this.module = module;}
}
