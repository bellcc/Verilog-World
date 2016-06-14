package edu.miamioh.simulator;

import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import edu.miamioh.simulator.AntlrGen.Verilog2001Lexer;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;
import edu.miamioh.util.DebugUtils;

import javax.swing.JTextPane;

import org.antlr.v4.runtime.*;

public class Parse {
	
	private String				rootPath;
	private ParseTree 			root_tree;
	private RootModuleInstance 	root_module;
	
	private boolean 	is_compiled;
	private boolean 	is_no_parse_errors;
	
	private JTextPane 	errorText;
	
	public int RESET = 0;
	public int RUN = 1;
	
	public Parse() throws Exception {
		this(null, null);
	}
	public Parse(JTextPane errorText, String rootPath) {
		
		this.errorText = errorText;
		this.rootPath = rootPath;
	}
	
	public ModuleInstance getRootModule() {return this.root_module;}
	
	public void compileFileForGame(String fileName) throws IOException {
		
		//errorText.setText("Compiling " + fileName + "...");

		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(rootPath + "core/assets/modules/" + fileName));
		Verilog2001Lexer lexer = new Verilog2001Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Verilog2001Parser parser = new Verilog2001Parser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new VerboseListenerE());

		is_no_parse_errors = true;

		root_tree = parser.module_declaration();
		root_module = new RootModuleInstance(parser, this, root_tree, "root_module");

		if (is_no_parse_errors)
		{
			is_compiled = true;
		}
		else
		{
			is_compiled = false;
		}
	}
	
	public void compileFileForEditor(String fileName) throws IOException {
		
		errorText.setText("Compiling " + fileName + "...");

		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(rootPath + "core/assets/modules/" + fileName));
		Verilog2001Lexer lexer = new Verilog2001Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Verilog2001Parser parser = new Verilog2001Parser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new VerboseListenerE());

		is_no_parse_errors = true;

		
		root_tree = parser.module_declaration();
		root_module = new RootModuleInstance(parser, this, root_tree, "root_module");

		if (is_no_parse_errors)
		{
			is_compiled = true;
		}
		else
		{
			is_compiled = false;
		}
	}
	
	public void displayResults() {
		errorText.setText("Simulation Results:");
		
		for(int i = 0; i < root_module.getVars_list().size(); ++i) {
			
			String name = root_module.getVars_list().get(i).getName();
			int index = root_module.getVisitor().getNewIndex();
			int value = root_module.getVars_list().get(i).getValue(index);
			
			if (!name.equals("clk") && !name.equals("rst")) {
				this.reportParseInfo(name + ": " + value);
			}
		}
	}

	public void sim_cycle(int mode)
	{
		if (is_compiled)
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
	
	public void reportParseError(String message) {
		
		String old_text = errorText.getText();
		errorText.setText(old_text + "\n" + message);
		
		this.setIs_no_parse_errors(false);
	}
	
	public void reportParseInfo(String message) {
		
		String old_text = errorText.getText();
		errorText.setText(old_text + "\n" + message);
	}
	
	public ParseTree loadParseTreeFromFile(String moduleName) {
		
		if (!is_no_parse_errors) {
			this.reportParseInfo("\n");
		}
		this.reportParseInfo("Compiling " + moduleName + ".v...");
		
		ANTLRInputStream input = null;
		try {
			String newModulePath = rootPath + "core/bin/modules/" + moduleName + ".v";
			input = new ANTLRInputStream(new FileInputStream(newModulePath));
		} catch (FileNotFoundException e) {
			this.reportParseError("Source file '" + moduleName + ".v' not found in modules directory.");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Verilog2001Lexer lexer = new Verilog2001Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Verilog2001Parser parser = new Verilog2001Parser(tokens);

		is_no_parse_errors = true;

		parser.removeErrorListeners();
		parser.addErrorListener(new VerboseListenerE());
		ParseTree result_tree = parser.module_declaration();
		
		/*
		 * For debugging
		 */
		//DebugUtils.printParseTree(result_tree, parser);
		
		return result_tree;
	}
	
	public void updateRootModule(RootModuleInstance module) {this.root_module = module;}
	
	public JTextPane getErrorText() 					{ return this.errorText;}
	public void setIs_no_parse_errors(Boolean value) 	{this.is_no_parse_errors = value;}
	public Boolean is_compiled_yet() 					{ return is_compiled;}
	
	public ParseTree getRootTree() 									{return this.root_tree;}
	public RootModuleInstance getRootModuleInstance() 				{return this.root_module;}
	
	public class VerboseListenerE extends BaseErrorListener
	{ 
		@Override
		public void syntaxError(
				Recognizer<?, ?> recognizer,
				Object offendingSymbol,
				int line,
				int charPositionInLine,
				String msg,
				RecognitionException e)
		{
			String old_text;
			List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
			Collections.reverse(stack);
			/*
			 * VWorldEdit
			 * Disabled errorText related stuff for testing. Needs to be re-enabled
			 * once this simulator is added to the editor.
			 */
			//System.out.println("Error at line " + line + ":" + charPositionInLine + " at " + offendingSymbol + ": " + msg);
			old_text = errorText.getText();
			errorText.setText(old_text + "\nError at line " + line + ":" + charPositionInLine + " at " + offendingSymbol + ": " + msg);
			/* flag found parse error */
			is_no_parse_errors = false;
		}
	}
}
