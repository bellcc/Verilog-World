package edu.miamioh.util;


import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.*;
import edu.miamioh.simulator.SimVisitor;
import edu.miamioh.simulator.ModuleInstance;
import edu.miamioh.simulator.ParseRegWire;

public class DebugUtils {
	
	public static void printModuleVars(SimVisitor visitor, ModuleInstance module) {
		for(ParseRegWire var : module.getVars_list()) {
			System.out.println(var.getName() + ": " + var.getValue(visitor.getOldIndex()));
		}
	}
	
	/*
	 * Prints a nicely formatted parse tree
	 */
	public static void printParseTree(ParseTree root_tree, Parser parser) {
		
		String printedTree = root_tree.toStringTree(parser);
		int depthCount = 0; // Tab depth count for current line
		
		for(int i = 0; i < printedTree.length(); ++i) {
			char character = printedTree.charAt(i);
			
			// If a parenthesis is encountered, we must add a line break and 
			// the correct number of tab indents
			if (character == '(') {
				++depthCount;
				
				// Split parse tree string along the parenthesis just found
				String start = printedTree.substring(0, i);
				String end = printedTree.substring(i);
				printedTree = start + "\n";
				
				// Insert correct number of tabs
				for(int j = 0; j < depthCount; ++j) {
					printedTree = printedTree + "\t";
					++i;
				}
				
				printedTree = printedTree + end;
				++i;
			}
			// Decrease tab depth count
			else if (character == ')') {
				--depthCount;
			}
		}
		
		System.out.println(printedTree);
	}
}
