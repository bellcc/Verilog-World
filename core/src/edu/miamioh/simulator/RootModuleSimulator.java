package edu.miamioh.simulator;

import org.antlr.v4.runtime.tree.ParseTree;

import edu.miamioh.GameObjects.NormalBlock;

public class RootModuleSimulator {
	
	private Parse 				compiler;
	private SimVisitor			visitor;
	private ParseTree 			root_tree;
	private RootModuleInstance 	root_module;
	
	private boolean				is_sequential_sim_cycle;
	private boolean				resetLineHigh;
	
	public RootModuleSimulator(Parse compiler) {
		this.compiler = compiler;
		
		this.resetLineHigh = true; // Active-low reset line
		this.is_sequential_sim_cycle = false;
	}
	
	public void clean_sim_cycle()
	{
		if (is_sequential_sim_cycle)
		{
			/* Makes sure the sequential registers keep value and catches
			 * inferred latches */
			for (int i = 0; i < root_module.getVars_list().size(); i++)
			{
				root_module.getVars_list().get(i).seqUpdate(0, visitor.getNewIndex(), visitor.getOldIndex());
			}
		}
		this.is_sequential_sim_cycle = false;
	}
	
	public void toggleResetLine() {
		
		resetLineHigh = !resetLineHigh;
		
		int resetValue = 0;
		if(resetLineHigh) resetValue = 1;
		
		
		// Update root module clock
		ParseRegWire wire = root_module.getHash_vars().get("rst");
		if (wire != null) {
			wire.setValue(visitor.getNewIndex(), resetValue, visitor.isInSequ() || !resetLineHigh);
			wire.setValue(visitor.getOldIndex(), resetValue, visitor.isInSequ() || !resetLineHigh);
		}
		
		// Update clock in all other modules
		for (ModuleInstance sub : root_module.getSubModulesList()) {
			wire = sub.getHash_vars().get("rst");
			
			if (wire != null) {
				wire.setValue(visitor.getNewIndex(), resetValue, visitor.isInSequ() || !resetLineHigh);
				wire.setValue(visitor.getOldIndex(), resetValue, visitor.isInSequ() || !resetLineHigh);
			}
		}
	}
	
	// The update flag tells the simulator if the given
	// wire has already been updated for a sequential clock cycle.
	public void resetSequUpdateFlag() {
		
		// Reset wires in root module
		for(ParseRegWire wire : root_module.getVars_list()) {
			wire.resetUpdateFlag();
		}
		
		// Reset wires in all other modules
		for (ModuleInstance sub : root_module.getSubModulesList()) {
			for(ParseRegWire wire : sub.getVars_list()) {
				wire.resetUpdateFlag();
			}
		}
	}
	
	// Updates sim information from parent module into child module
	public void syncSimInfo(SimVisitor visitor) {
		
		SimVisitor targetVisitor = root_module.getVisitor();
		targetVisitor.setNewIndex(visitor.getNewIndex());
		targetVisitor.setOldIndex(visitor.getOldIndex());
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

	public void sim_cycle()
	{
		if (compiler.isCompiled())
		{
			simComb();
			simSequ();
			clean_sim_cycle();
			
			this.displayResults();
		}
	}
	
	public void simComb() {
		
		SimVisitor visitor = root_module.getVisitor();
		
		do {
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
		
		// Toggle sequ clock and simulate
		simComb();

		// Reset sequential wire update flags
		resetSequUpdateFlag();
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
		
		toggleResetLine(); // Turn on
		simComb(); // Simulate until steady
		resetSequUpdateFlag();
		toggleResetLine(); // Turn off

		displayResults();
	}
	
	public void updateTargetBlock(NormalBlock block) {
		setRootModule(block.getModuleWrapper().getModule());
		setRootTree(block.getModuleWrapper().getModule().getParseTree());
		setSimVisitor(this.root_module.getVisitor());
	}
	
	public int getOldSimIndex() 							{return this.root_module.getVisitor().getOldIndex();}
	public void setCompiler(Parse compiler)					{this.compiler = compiler;}
	public void setSimVisitor(SimVisitor visitor)			{this.visitor = visitor;}
	public RootModuleInstance getRootModuleInstance() 		{return this.root_module;}
	public ParseTree getRootModuleTree()					{return this.root_tree;}
	public void setRootModule(RootModuleInstance module) 	{this.root_module = module;}
	public void setRootTree(ParseTree tree)					{this.root_tree = tree;}
	public boolean isSequCycle()							{return this.is_sequential_sim_cycle;}
	public void setIsSequCycle(boolean value)				{this.is_sequential_sim_cycle = value;}
	public boolean getResetLine()							{return this.resetLineHigh;}
	public void setResetLine(boolean value)					{this.resetLineHigh = value;}
	public ParseTree getRootTree() 							{return this.root_tree;}
}
