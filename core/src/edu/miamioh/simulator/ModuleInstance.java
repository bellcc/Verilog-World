package edu.miamioh.simulator;

import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;

public class ModuleInstance {
	
	private SimVisitor visitor;
	private String name;
	private ParseTree tree;
	private boolean is_sequ;
	
	private ArrayList<ParseRegWire> vars_list;
	private Hashtable<String, ParseRegWire> hash_vars;
	private ArrayList<String> ports_list;
	
	public ModuleInstance(Verilog2001Parser parser, Parse Compiler, ParseTree tree, String name) {
		
		this.tree = tree;
		this.vars_list = new ArrayList<>();
		this.hash_vars = new Hashtable<>();
		this.ports_list = new ArrayList<>();
		this.name = name;
		
		visitor = new SimVisitor(this, 
								 Compiler.getSubModulesHash(), 
								 Compiler.getSubModulesList());
		
		// Generate symbol table and check syntax
		ParseTreeWalker walker = new ParseTreeWalker();
		ParseListener listener = new ParseListener(visitor, parser, Compiler, this);
		walker.walk(listener, this.tree);
		
		// Find out if we use the clock or not
		if (this.hash_vars.containsKey("clk")) {
			this.is_sequ = true;
		}
		else {
			this.is_sequ = false;
		}
	}

	public ArrayList<ParseRegWire> getVars_list() 			{return vars_list;}
	public Hashtable<String, ParseRegWire> getHash_vars() 	{return hash_vars;}
	public ArrayList<String> getPorts_list() 				{return ports_list;}
	public SimVisitor getVisitor() 							{return visitor;}
	public ParseTree getParseTree()							{return tree;}
	public boolean isSequ() 								{return this.is_sequ;}
	public String getName()									{return this.name;}
}
