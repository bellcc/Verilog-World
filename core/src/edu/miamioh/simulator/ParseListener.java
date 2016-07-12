package edu.miamioh.simulator;
/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Peter Jamieson, Naoki Mizuno, and Boyu Zhang
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

import javax.swing.JTextPane;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import edu.miamioh.simulator.AntlrGen.Verilog2001BaseListener;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser.IdentifierContext;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser.Identifier_typesContext;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser.List_of_identifiersContext;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser.List_of_port_connectionsContext;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser.Variable_lvalueContext;

public class ParseListener extends Verilog2001BaseListener {
    
    private Stack<String>		    var_stack;
    private Stack<Integer>		    parameter_value_stack;
    
    private boolean			    is_var_identifier;
    private boolean			    is_number;
    private boolean			    is_var_assign_left;
    private boolean			    isInSequAlways;
    
    private int				    MSB_range;
    private int				    LSB_range;
    Verilog2001Parser			    parser;
    private ArrayList<String>		    ports_list;
    private ArrayList<ParseRegWire>	    vars_list;
    private Hashtable<String, ParseRegWire> hash_vars;
    
    private ArrayList<ParseTree>	    subTrees;
    private Hashtable<String, ParseTree>    subTreesHash;
    private Parse			    Compiler;
    private SimVisitor			    visitor;
    private RootModuleSimulator		    sim;
    
    public ParseListener(SimVisitor visitor, Verilog2001Parser parser,
	    RootModuleSimulator sim, Parse Compiler, ModuleInstance module) {
	this.is_var_identifier = false;
	this.is_number = false;
	this.is_var_assign_left = false;
	this.isInSequAlways = false;
	
	this.parser = parser;
	
	this.ports_list = module.getPorts_list();
	this.vars_list = module.getVars_list();
	this.hash_vars = module.getHash_vars();
	
	this.subTrees = sim.getRootModuleInstance().getSubTrees();
	this.subTreesHash = sim.getRootModuleInstance().getSubTreesHash();
	
	this.var_stack = new Stack<String>();
	this.parameter_value_stack = new Stack<Integer>();
	
	this.sim = sim;
	this.Compiler = Compiler;
	this.visitor = visitor;
    }
    
    /*
     * -------------------------------------------------------------------------
     * - -----------
     * --------------------------------------------------------------
     * -------------------------- These are listeners to add parameters
     * ----------
     * ----------------------------------------------------------------
     * --------------
     * ------------------------------------------------------------
     * --------------------------
     */
    @Override
    public void enterParameter_declaration_(
	    Verilog2001Parser.Parameter_declaration_Context ctx) {
	/* intialize range so that is handled */
	MSB_range = 0;
	LSB_range = 0;
	is_var_identifier = true; /* turn on var stack */
	is_number = true;
    }
    
    @Override
    public void exitParameter_declaration_(
	    Verilog2001Parser.Parameter_declaration_Context ctx) {
	ParseRegWire new_var;
	
	while (!var_stack.empty()) {
	    String name = var_stack.pop();
	    int value = parameter_value_stack.pop();
	    
	    /* create the var */
	    new_var = new ParseRegWire(visitor);
	    new_var.addParameter(name, MSB_range - LSB_range + 1, value);
	    
	    /* add to the hash and list */
	    vars_list.add(new_var);
	    hash_vars.put(name, new_var);
	}
	is_var_identifier = false; /* turn off var stack */
	is_number = false;
    }
    
    /*
     * -------------------------------------------------------------------------
     * - -----------
     * --------------------------------------------------------------
     * -------------------------- These are listeners to add inputs (wires or
     * reg)
     * ----------------------------------------------------------------------
     * ------------------
     * --------------------------------------------------------
     * ------------------------------
     */
    @Override
    public void enterNet_declaration(
	    Verilog2001Parser.Net_declarationContext ctx) {
	/*
	 * This is a wire declaration such as and handles all of these: wire
	 * clk; wire [1:0]a; wire [4:0]a, b;
	 */
	
	/* intialize range so that is handled */
	MSB_range = 0;
	LSB_range = 0;
	is_var_identifier = true; /* turn on var stack */
    }
    
    @Override
    public void exitNet_declaration(
	    Verilog2001Parser.Net_declarationContext ctx) {
	ParseRegWire new_var;
	
	while (!var_stack.empty()) {
	    String name = var_stack.pop();
	    
	    /* create the var */
	    new_var = new ParseRegWire(visitor);
	    new_var.addWire(name, MSB_range - LSB_range + 1);
	    if (isInSequAlways) {
		new_var.setSequential();
	    } else {
		new_var.setCombinational();
	    }
	    
	    /* add to the hash and list */
	    vars_list.add(new_var);
	    hash_vars.put(name, new_var);
	}
	is_var_identifier = false; /* turn off var stack */
    }
    
    @Override
    public void enterReg_declaration(
	    Verilog2001Parser.Reg_declarationContext ctx) {
	/* reg clk; reg [1:0]a; reg [4:0]a, b; */
	
	/* intialize range so that is handled */
	MSB_range = 0;
	LSB_range = 0;
	is_var_identifier = true; /* turn on var stack */
    }
    
    @Override
    public void exitReg_declaration(
	    Verilog2001Parser.Reg_declarationContext ctx) {
	ParseRegWire new_var;
	
	while (!var_stack.empty()) {
	    String name = var_stack.pop();
	    
	    /* create the var */
	    new_var = new ParseRegWire(visitor);
	    new_var.addReg(name, MSB_range - LSB_range + 1);
	    
	    /* add to the hash and list */
	    vars_list.add(new_var);
	    hash_vars.put(name, new_var);
	}
	
	is_var_identifier = false; /* turn off var stack */
    }
    
    /*
     * -------------------------------------------------------------------------
     * - -----------
     * --------------------------------------------------------------
     * -------------------------- These are listeners to add input ports
     * --------
     * ------------------------------------------------------------------
     * --------------
     * ------------------------------------------------------------
     * --------------------------
     */
    @Override
    public void enterInput_declaration(
	    Verilog2001Parser.Input_declarationContext ctx) {
	/* input clk; input [1:0]a; input [4:0]a, b; */
	
	/* intialize range so that is handled */
	MSB_range = 0;
	LSB_range = 0;
	is_var_identifier = true; /* turn on var stack */
    }
    
    @Override
    public void exitInput_declaration(
	    Verilog2001Parser.Input_declarationContext ctx) {
	ParseRegWire new_var;
	
	while (!var_stack.empty()) {
	    String name = var_stack.pop();
	    
	    if (ports_list.contains(name)) {
		/* create the var */
		new_var = new ParseRegWire(visitor);
		new_var.addReg(name, MSB_range - LSB_range + 1);
		new_var.setRole(WireRoleType.INPUT);
		
		/* add to the hash and list */
		vars_list.add(new_var);
		hash_vars.put(name, new_var);
	    } else {
		IdentifierContext identifier = null;
		for (int i = 0; i < ctx.getChildCount(); ++i) {
		    if (ctx.getChild(i) instanceof List_of_identifiersContext) {
			identifier = (IdentifierContext) ctx.getChild(i)
				.getChild(0);
			break;
		    }
		}
		int line = identifier.getStart().getLine();
		int charPos = identifier.getStart().getCharPositionInLine();
		
		Compiler.reportParseError("Variable '" + name + "' <" + line
			+ ", " + charPos
			+ "> has not been declared in the module port connections.");
	    }
	}
	is_var_identifier = false; /* turn off var stack */
    }
    
    /*
     * -------------------------------------------------------------------------
     * - -----------
     * --------------------------------------------------------------
     * -------------------------- Number conversions into the stack
     * --------------
     * ------------------------------------------------------------
     * --------------
     * ------------------------------------------------------------
     * --------------------------
     */
    @Override
    public void enterDecimal_number(
	    Verilog2001Parser.Decimal_numberContext ctx) {
	if (is_number) {
	    if (ctx.Unsigned_number().size() == 1) {
		parameter_value_stack.push(Integer
			.parseInt(ctx.Unsigned_number().get(0).getText()));
	    } else {
		parameter_value_stack.push(Integer
			.parseInt(ctx.Unsigned_number().get(1).getText()));
	    }
	}
    }
    
    @Override
    public void enterHex_number(Verilog2001Parser.Hex_numberContext ctx) {
	if (is_number) {
	    parameter_value_stack.push(Integer
		    .parseInt(ctx.Unsigned_number().get(1).getText(), 16));
	}
    }
    
    @Override
    public void enterOctal_number(Verilog2001Parser.Octal_numberContext ctx) {
	if (is_number) {
	    parameter_value_stack.push(Integer
		    .parseInt(ctx.Unsigned_number().get(1).getText(), 8));
	}
    }
    
    @Override
    public void enterBinary_number(Verilog2001Parser.Binary_numberContext ctx) {
	if (is_number) {
	    parameter_value_stack.push(Integer
		    .parseInt(ctx.Unsigned_number().get(1).getText(), 2));
	}
    }
    
    /*
     * -------------------------------------------------------------------------
     * - -----------
     * --------------------------------------------------------------
     * -------------------------- These are listeners to add output ports
     * --------
     * ------------------------------------------------------------------
     * --------------
     * ------------------------------------------------------------
     * --------------------------
     */
    @Override
    public void enterOUTPUT_DECLARATION_NO_REG(
	    Verilog2001Parser.OUTPUT_DECLARATION_NO_REGContext ctx) {
	/* output clk; output [1:0]a; output [4:0]a, b; */
	
	/* intialize range so that is handled */
	MSB_range = 0;
	LSB_range = 0;
	is_var_identifier = true; /* turn on var stack */
    }
    
    @Override
    public void exitOUTPUT_DECLARATION_NO_REG(
	    Verilog2001Parser.OUTPUT_DECLARATION_NO_REGContext ctx) {
	ParseRegWire new_var;
	
	while (!var_stack.empty()) {
	    String name = var_stack.pop();
	    
	    if (ports_list.contains(name)) {
		/* create the var */
		new_var = new ParseRegWire(visitor);
		new_var.addWire(name, MSB_range - LSB_range + 1);
		new_var.setRole(WireRoleType.OUTPUT);
		
		/* add to the hash and list */
		vars_list.add(new_var);
		hash_vars.put(name, new_var);
	    } else {
		IdentifierContext identifier = null;
		for (int i = 0; i < ctx.getChildCount(); ++i) {
		    if (ctx.getChild(i) instanceof List_of_identifiersContext) {
			identifier = (IdentifierContext) ctx.getChild(i)
				.getChild(0);
			break;
		    }
		}
		int line = identifier.getStart().getLine();
		int charPos = identifier.getStart().getCharPositionInLine();
		
		Compiler.reportParseError("Variable '" + name + "' <" + line
			+ ", " + charPos
			+ "> has not been declared in the module port connections.");
	    }
	}
	is_var_identifier = false; /* turn off var stack */
    }
    
    @Override
    public void enterOUTPUT_DECLARATION_REG(
	    Verilog2001Parser.OUTPUT_DECLARATION_REGContext ctx) {
	/* output reg clk; output reg [1:0]a; output reg [4:0]a, b; */
	
	/* intialize range so that is handled */
	MSB_range = 0;
	LSB_range = 0;
	is_var_identifier = true; /* turn on var stack */
    }
    
    @Override
    public void exitOUTPUT_DECLARATION_REG(
	    Verilog2001Parser.OUTPUT_DECLARATION_REGContext ctx) {
	ParseRegWire new_reg;
	
	while (!var_stack.empty()) {
	    String name = var_stack.pop();
	    
	    /* create the var */
	    new_reg = new ParseRegWire(visitor);
	    new_reg.addReg(name, MSB_range - LSB_range + 1);
	    new_reg.setRole(WireRoleType.OUTPUT);
	    
	    /* add to the hash and list */
	    vars_list.add(new_reg);
	    hash_vars.put(name, new_reg);
	}
    }
    
    /*
     * -------------------------------------------------------------------------
     * - -----------
     * --------------------------------------------------------------
     * -------------------------- This listener is for identifiers...very common
     * so turned on and off by parse points
     * --------------------------------------
     * --------------------------------------------------
     * ------------------------
     * --------------------------------------------------------------
     */
    @Override
    public void exitIdentifier(Verilog2001Parser.IdentifierContext ctx) {
	if (is_var_identifier || is_var_assign_left) {
	    if (!(ctx
		    .getParent() instanceof Verilog2001Parser.Port_referenceContext)) {
		var_stack.push(ctx.getText());
	    }
	}
    }
    
    /*
     * -------------------------------------------------------------------------
     * - -----------
     * --------------------------------------------------------------
     * -------------------------- These handle ranges in [ : ] returning only
     * integers
     * ------------------------------------------------------------------
     * ----------------------
     * ----------------------------------------------------
     * ----------------------------------
     */
    @Override
    public void enterMsb_constant_expression(
	    Verilog2001Parser.Msb_constant_expressionContext ctx) {
	MSB_range = Integer.parseInt(ctx.getText());
    }
    
    @Override
    public void enterLsb_constant_expression(
	    Verilog2001Parser.Lsb_constant_expressionContext ctx) {
	LSB_range = Integer.parseInt(ctx.getText());
    }
    
    /*
     * -------------------------------------------------------------------------
     * - -----------
     * --------------------------------------------------------------
     * -------------------------- Traverses all variables on left side and
     * updates there status...removes outputs from var list and hash
     * ------------
     * --------------------------------------------------------------
     * --------------
     * ------------------------------------------------------------
     * --------------------------
     */
    @Override
    public void enterVariable_lvalue(
	    Verilog2001Parser.Variable_lvalueContext ctx) {
	is_var_assign_left = true;
    }
    
    @Override
    public void exitVariable_lvalue(
	    Verilog2001Parser.Variable_lvalueContext ctx) {
	is_var_assign_left = false;
    }
    
    @Override
    public void exitBlocking_assignment(
	    Verilog2001Parser.Blocking_assignmentContext ctx) {
	String name = var_stack.pop();
	ParseRegWire var = hash_vars.get(name);
	
	var.setCombinational();
    }
    
    @Override
    public void exitNonblocking_assignment(
	    Verilog2001Parser.Nonblocking_assignmentContext ctx) {
	String name = var_stack.pop();
	ParseRegWire var = hash_vars.get(name);
	
	if (var == null) {
	    this.Compiler.reportParseError(
		    "Assignment to undeclared variable '" + name + "'.");
	} else {
	    var.setSequential();
	}
    }
    
    @Override
    public void exitContinuous_assign(
	    Verilog2001Parser.Continuous_assignContext ctx) {
	String name = var_stack.pop();
	ParseRegWire var = hash_vars.get(name);
	
	if (var != null) {
	    // var.setCombinational();
	} else {
	    // Report if the variable has not been declared.
	    Variable_lvalueContext lValue = (Variable_lvalueContext) ctx
		    .getChild(1);
	    int line = lValue.getStart().getLine();
	    int charPos = lValue.getStart().getCharPositionInLine();
	    
	    Compiler.reportParseError("Variable '" + name + "' <" + line + ", "
		    + charPos + "> has not been declared.");
	}
    }
    
    @Override
    public void exitVariable_init(Verilog2001Parser.Variable_initContext ctx) {
	
    }
    
    @Override
    public void enterSEQUENTIAL_ALWAYS(
	    Verilog2001Parser.SEQUENTIAL_ALWAYSContext ctx) {
	this.isInSequAlways = true;
    }
    
    @Override
    public void exitSEQUENTIAL_ALWAYS(
	    Verilog2001Parser.SEQUENTIAL_ALWAYSContext ctx) {
	this.isInSequAlways = false;
    }
    
    @Override
    public void exitList_of_port_connections(
	    Verilog2001Parser.List_of_port_connectionsContext ctx) {
	
	// Cycle through list of ports
	for (int i = 0; i < ctx.getChildCount(); ++i) {
	    
	    ParseTree child = ctx.getChild(i);
	    
	    if (child instanceof Verilog2001Parser.IdentifierContext) {
		
		String port_identifier = child.getText();
		
		// Executed if the vars_list does not contain a variable named
		// the same as this identifier
		if (hash_vars.get(port_identifier) == null) {
		    
		    // Report identifier and position
		    IdentifierContext name = (IdentifierContext) child;
		    int line = name.getStart().getLine();
		    int charPos = name.getStart().getCharPositionInLine();
		    
		    Compiler.reportParseError(
			    "Variable '" + port_identifier + "' <" + line + ", "
				    + charPos + "> has not been declared.");
		}
	    }
	}
    }
    
    @Override
    public void enterList_of_ports(Verilog2001Parser.List_of_portsContext ctx) {
	is_var_identifier = true;
    }
    
    @Override
    public void exitList_of_ports(Verilog2001Parser.List_of_portsContext ctx) {
	// Cycle through list of ports
	for (int i = 0; i < ctx.getChildCount(); ++i) {
	    
	    ParseTree child = ctx.getChild(i);
	    
	    if (child instanceof Verilog2001Parser.PortContext) {
		
		String port_identifier = child.getChild(0).getChild(0)
			.getText();
		
		// If this identifier has already been used, report the error.
		// Else use it.
		if (ports_list.contains(port_identifier)) {
		    
		    // Report identifier and position
		    IdentifierContext name = (IdentifierContext) child
			    .getChild(0).getChild(0);
		    int line = name.getStart().getLine();
		    int charPos = name.getStart().getCharPositionInLine();
		    
		    Compiler.reportParseError("Module port '" + port_identifier
			    + "' <" + line + ", " + charPos
			    + "> has already been used.");
		} else {
		    ports_list.add(port_identifier);
		}
	    }
	}
	
	is_var_identifier = false;
    }
    
    @Override
    public void exitModule_instantiation(
	    Verilog2001Parser.Module_instantiationContext ctx) {
	
	String moduleName = ctx.getChild(0).getText();
	String instanceName = ctx.getChild(1).getChild(0).getChild(0).getText();
	ParseTree newTree = null;
	//
	// // Make a new tree if necessary
	// if (!subTreesHash.containsKey(moduleName)) {
	// try {
	// newTree = Compiler.loadParseTreeFromFile(moduleName);
	// subTrees.add(newTree);
	// subTreesHash.put(moduleName, newTree);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// else {
	newTree = subTreesHash.get(moduleName);
	// }
	
	// Create the new module instance
	if (newTree != null) {
	    ModuleInstance newModule = new ModuleInstance(parser, Compiler, sim,
		    newTree, moduleName);
	    sim.getRootModuleInstance().getSubModulesHash().put(instanceName,
		    newModule);
	    sim.getRootModuleInstance().getSubModulesList().add(newModule);
	}
    }
    
    // @Override
    // public void
    // exitList_of_identifiers(Verilog2001Parser.List_of_identifiersContext ctx)
    // {
    //
    // for(int i = 0; i < ctx.getChildCount(); ++i) {
    //
    // ParseTree child = ctx.getChild(i);
    //
    // if (child instanceof Verilog2001Parser.IdentifierContext) {
    //
    // String identifier = child.getText();
    //
    // if (!vars_list.contains(identifier)) {
    //
    // // Report identifier and position
    // IdentifierContext name = (IdentifierContext)child;
    // int line = name.getStart().getLine();
    // int charPos = name.getStart().getCharPositionInLine();
    //
    // this.reportParseError("Module port '" + identifier + "' <" + line + ", "
    // + charPos + "> has already been used.");
    // }
    // }
    // }
    // }
}
