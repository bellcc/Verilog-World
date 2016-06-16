package edu.miamioh.worldSimulator;

import java.util.ArrayList;

public class WorldSimulator {
	
	ArrayList<ModuleWrapper> modules;
	
	public WorldSimulator() {
		
	}
	
	public void addModule(ModuleWrapper module) {
		modules.add(module);
	}
	
	public void removeModule(ModuleWrapper module) {
		modules.remove(module);
	}
}
