package edu.miamioh.simulator;

import org.antlr.v4.runtime.tree.ParseTree;

import edu.miamioh.GameObjects.NormalBlock;

public class RootModuleSimulator {
	
	private Parse 				compiler;
	private ParseTree 			root_tree;
	private RootModuleInstance 	root_module;
	
	public RootModuleSimulator(Parse compiler) {
		this.compiler = compiler;
	}
	
	public void displayResults() {
		compiler.getErrorText().setText("Simulation Results:");
		
		for(int i = 0; i < root_module.getVars_list().size(); ++i) {
			
			String name = root_module.getVars_list().get(i).getName();
			int index = root_module.getVisitor().getNewIndex();
			int value = root_module.getVars_list().get(i).getValue(index);
			
			if (!name.equals("clk") && !name.equals("rst")) {
				compiler.reportParseInfo(name + ": " + value);
			}
		}
	}

	public void sim_cycle(int mode)
	{
		if (compiler.isCompiled())
		{
			this.simComb();
			this.simSequ();
			root_module.getVisitor().clean_sim_cycle();
			
			this.displayResults();
		}
	}
	
	public void simComb() {
		
		SimVisitor visitor = root_module.getVisitor();
		
		do {
			
			/*
			 * For debugging
			 */
//			System.out.println("*********** Comb Cycle ************");
//			System.out.println(">>> Top Module");
//			DebugUtils.printModuleVars(visitor, this.root_module);
//			for(ModuleInstance sub : root_module.getSubModulesList()) {
//				System.out.println(">>> " + sub.getName());
//				DebugUtils.printModuleVars(sub.getVisitor(), sub);
//			}
			
			// Assume the circuit is steady at the start. 
			// Simulate it and let it change it's own steady or not steady state.
			visitor.setState(SimVisitor.STEADY);
			for(ModuleInstance module : root_module.getSubModulesList()) {
				module.getVisitor().setState(SimVisitor.STEADY);
			}
			
			// Run one simulation cycle for combinational circuit
			visitor.next_sim_cycle();
			visitor.visit(root_tree);
			updateSequWires();

		} while(visitor.getState() == SimVisitor.NOT_STEADY);
	}
	
	public void simSequ() {
		/*
		 * For debugging
		 */
//		System.out.print("************************\n" +
//						 "*        Clock!        *\n" +
//						 "************************\n");
		
		// Toggle sequ clock and simulate
		root_module.getVisitor().toggleSequClock();
		simComb();
		root_module.getVisitor().toggleSequClock();

		// Reset sequential wire update flags
		root_module.getVisitor().resetSequUpdateFlag();
	}
	
	public void updateSequWires() {
		
		SimVisitor visitor = root_module.getVisitor();
		
		for(ParseRegWire wire : root_module.getVars_list()) {
			if (wire.getType() == RegWireType.SEQUENTIAL) {
				wire.setValue(visitor.getOldIndex(), 
							  wire.getValue(visitor.getNewIndex()), 
							  false);
			}
		}
		
		for(ModuleInstance module : root_module.getSubModulesList()) {
			for(ParseRegWire wire : module.getVars_list()) {
				if (wire.getType() == RegWireType.SEQUENTIAL) {
					wire.setValue(visitor.getOldIndex(), 
							  wire.getValue(visitor.getNewIndex()), 
							  false);
				}
			} 
		}
	}
	
	// Bring reset line high and simulate circuit until steady
	public void resetSimulation() {
		
		root_module.getVisitor().toggleResetLine(); // Turn on
		simComb(); // Simulate until steady
		root_module.getVisitor().resetSequUpdateFlag();
		root_module.getVisitor().toggleResetLine(); // Turn off
		this.displayResults();
	}
	
	public void updateTargetBlock(NormalBlock block) {
		setRootModule(block.getModuleWrapper().getModule());
		setRootTree(block.getModuleWrapper().getModule().getParseTree());
	}
	
	public void setCompiler(Parse compiler)					{this.compiler = compiler;}
	public RootModuleInstance getRootModuleInstance() 		{return this.root_module;}
	public ParseTree getRootModuleTree()					{return this.root_tree;}
	public void setRootModule(RootModuleInstance module) 	{this.root_module = module;}
	public void setRootTree(ParseTree tree)					{this.root_tree = tree;}
	public ParseTree getRootTree() 							{return this.root_tree;}
}
