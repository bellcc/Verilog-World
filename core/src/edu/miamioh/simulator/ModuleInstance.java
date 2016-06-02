package edu.miamioh.simulator;

import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ModuleInstance {
	
	private SimVisitor visitor;
	private ParseTree tree;
	
	private ArrayList<ParseRegWire> vars_list;
	private Hashtable<String, ParseRegWire> hash_vars;
	private ArrayList<String> ports_list;
	
	public ModuleInstance(Verilog2001Parser parser, Parse Compiler, ParseTree tree) {
		
		this.tree = tree;
		this.vars_list = new ArrayList<>();
		this.hash_vars = new Hashtable<>();
		this.ports_list = new ArrayList<>();
		
		visitor = new SimVisitor(this, Compiler.getSubModulesHash());
		
		// Generate symbol table and check syntax
		ParseTreeWalker walker = new ParseTreeWalker();
		ParseListener listener = new ParseListener(visitor, parser, Compiler, this);
		walker.walk(listener, this.tree);
	}

	public ArrayList<ParseRegWire> getVars_list() 			{return vars_list;}
	public Hashtable<String, ParseRegWire> getHash_vars() 	{return hash_vars;}
	public ArrayList<String> getPorts_list() 				{return ports_list;}
	public SimVisitor getVisitor() 							{return visitor;}
	public ParseTree getParseTree()							{return tree;}
}
