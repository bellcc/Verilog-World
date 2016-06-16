package edu.miamioh.simulator;

import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;

public class RootModuleInstance extends ModuleInstance {
	
	private ArrayList<ModuleInstance> 			subModules_list;
	private Hashtable<String, ModuleInstance> 	subModules_hash;
	private ArrayList<ParseTree> 				subTrees;
	private Hashtable<String, ParseTree> 		subTreesHash;
	
	public RootModuleInstance(Verilog2001Parser parser, 
							  Parse Compiler, 
							  RootModuleSimulator sim, 
							  ParseTree tree, 
							  String name) {
		super(parser, null, sim, tree, name); // Pass it null for Compiler so it doesn't generate the symbol table.
										 // We want to generate the table
		
		subModules_list = new ArrayList<>();
		subModules_hash = new Hashtable<>();
		subTrees = new ArrayList<>();
		subTreesHash = new Hashtable<>();
		
		this.vars_list = new ArrayList<>();
		this.hash_vars = new Hashtable<>();
		this.ports_list = new ArrayList<>();
		this.name = name;
		this.tree = tree;
		
		sim.setRootModule(this);
		
		visitor = new SimVisitor(this, this.subModules_hash, this.subModules_list);
		// Generate symbol table and check syntax
		ParseTreeWalker walker = new ParseTreeWalker();
		ParseListener listener = new ParseListener(visitor, parser, sim, Compiler, this);
		walker.walk(listener, this.tree);
	}
	
	public ArrayList<ParseTree> getSubTrees() 						{return this.subTrees;}
	public Hashtable<String, ParseTree> getSubTreesHash() 			{return this.subTreesHash;}
	public Hashtable<String, ModuleInstance> getSubModulesHash() 	{return this.subModules_hash;}
	public ArrayList<ModuleInstance> getSubModulesList() 			{return this.subModules_list;}
}
