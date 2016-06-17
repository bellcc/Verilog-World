package edu.miamioh.simulator;

import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;

public class ModuleInstance {
	
	protected SimVisitor visitor;
	protected String name;
	protected ParseTree tree;
	
	protected ArrayList<ParseRegWire> vars_list;
	protected Hashtable<String, ParseRegWire> hash_vars;
	protected ArrayList<String> ports_list;
	
	public ModuleInstance(Verilog2001Parser parser, 
						  Parse compiler, 
						  RootModuleSimulator sim, 
						  ParseTree tree, 
						  String name) {
		
		this.tree = tree;
		this.vars_list = new ArrayList<>();
		this.hash_vars = new Hashtable<>();
		this.ports_list = new ArrayList<>();
		this.name = name;
		
		// Is null in the case that this is called by RootModuleInstance constructor. 
		// That constructor does it's own walking code
		if (compiler != null) {
			visitor = new SimVisitor(this, 
									 sim.getRootModuleInstance().getSubModulesHash(), 
									 sim.getRootModuleInstance().getSubModulesList());
			
			// Generate symbol table and check syntax
			ParseTreeWalker walker = new ParseTreeWalker();
			ParseListener listener = new ParseListener(visitor, parser, sim, compiler, this);
			walker.walk(listener, this.tree);
		}
	}

	public ArrayList<ParseRegWire> getVars_list() 			{return vars_list;}
	public Hashtable<String, ParseRegWire> getHash_vars() 	{return hash_vars;}
	public ArrayList<String> getPorts_list() 				{return ports_list;}
	public SimVisitor getVisitor() 							{return visitor;}
	public ParseTree getParseTree()							{return tree;}
	public String getName()									{return this.name;}
}
