package edu.miamioh.simulator;

import edu.miamioh.GameObjects.Block;
import edu.miamioh.simulator.AntlrGen.Verilog2001Lexer;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Parse {
	
//	public int RESET = 0;
//	public int RUN = 1;
	private String					rootPath;
	private JTextPane 				errorText;
	private boolean 				is_compiled;
	private boolean 				is_no_parse_errors;
	private RootModuleSimulator 	sim;
	
	public Parse() throws Exception {
		this(null, null);
	}
	public Parse(JTextPane errorText, String rootPath) {
		
		this.errorText = errorText;
		this.rootPath = rootPath;
		this.sim = new RootModuleSimulator(this);
		SimVisitor.setSim(this.sim);

		Block.setRootSim(sim);
	}
	
	public RootModuleInstance compileFileForGame(String fileName) throws IOException {
		
		//errorText.setText("Compiling " + fileName + "...");

		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(rootPath + "/core/assets/modules/" + fileName));
		Verilog2001Lexer lexer = new Verilog2001Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Verilog2001Parser parser = new Verilog2001Parser(tokens);
//		parser.removeErrorListeners();
//		parser.addErrorListener(new VerboseListenerE());

		is_no_parse_errors = true;

		ParseTree root_tree = parser.module_declaration();
		String name = fileName.substring(0, fileName.length() - 2); // Removes .v file ending
		RootModuleInstance root_module = new RootModuleInstance(parser, this, sim, root_tree, name);

		is_compiled = is_no_parse_errors;
		
		return root_module;
	}
	
	public RootModuleInstance compileFileForEditor(String fileName) throws IOException {
		
		errorText.setText("Compiling " + fileName + "...");

		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(rootPath + "core/assets/modules/" + fileName));
		Verilog2001Lexer lexer = new Verilog2001Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Verilog2001Parser parser = new Verilog2001Parser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new VerboseListenerE());

		is_no_parse_errors = true;

		
		ParseTree root_tree = parser.module_declaration();
		String name = fileName.substring(0, fileName.length() - 2); // Removes .v file ending
		RootModuleInstance root_module = new RootModuleInstance(parser, this, sim, root_tree, name);

		is_compiled = is_no_parse_errors;
		
		return root_module;
	}
	
	void reportParseError(String message) {
		
		String old_text = errorText.getText();
		errorText.setText(old_text + "\n" + message);
		
		this.setIs_no_parse_errors(false);
	}
	
	void reportParseInfo(String message) {
		
		String old_text = errorText.getText();
		errorText.setText(old_text + "\n" + message);
	}
	
	ParseTree loadParseTreeFromFile(String moduleName) {
		
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
		
		/*
		 * For debugging
		 */
		//DebugUtils.printParseTree(result_tree, parser);
		
		return parser.module_declaration();
	}
	
	JTextPane getErrorText() 					{ return this.errorText;}
	private void setIs_no_parse_errors(Boolean value) 	{this.is_no_parse_errors = value;}
	public Boolean isCompiled() 						{ return is_compiled;}
	
	public RootModuleSimulator getRootModuleSimulator() {return sim;}
	
	private class VerboseListenerE extends BaseErrorListener
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
