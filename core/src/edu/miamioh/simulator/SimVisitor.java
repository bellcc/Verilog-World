package edu.miamioh.simulator;
/*
The MIT License (MIT)

Copyright (c) 2014 Peter Jamieson, Naoki Mizuno, and Boyu Zhang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */


import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import edu.miamioh.simulator.AntlrGen.Verilog2001BaseVisitor;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;

public class SimVisitor extends Verilog2001BaseVisitor<Value>
{
	private boolean							is_sequential_sim_cycle;
	private int								cycle_time;

	private boolean							is_combinational;
	private boolean							is_sequential;

	private ModuleInstance					module;
	private Hashtable<String, ModuleInstance> subModules_hash;
	private ArrayList<ModuleInstance> 		subModules_list;
	private Hashtable<String, ParseRegWire>	hash_vars;
	private ArrayList<ParseRegWire>			vars_list;
	private ArrayList<String>				ports_list;

	private int								new_val_idx;
	private int								old_val_idx;
	
	private int 							state;
	public final static int 				STEADY = 0;
	public final static int					NOT_STEADY = 1;
	
	private Value case_expression;

	public SimVisitor(ModuleInstance module, 
					  Hashtable<String, ModuleInstance> subModules_hash,
					  ArrayList<ModuleInstance> subModules_list)
	{
		this.is_combinational = false;
		this.is_sequential = module.isSequ();

		this.is_sequential_sim_cycle = false;
		this.cycle_time = 0;

		this.new_val_idx = 0;
		this.old_val_idx = 1;

		this.subModules_hash = subModules_hash;
		this.subModules_list = subModules_list;
		this.module = module;
		this.ports_list = module.getPorts_list();
		this.vars_list = module.getVars_list();
		this.hash_vars = module.getHash_vars();
		
		this.state = SimVisitor.STEADY;
	}

	/* --------------------------------------------------------------------------
	 * -----------
	 * --------------------------------------------------------------
	 * -------------------------- Simulate
	 * --------------------------------------
	 * --------------------------------------------------
	 * ------------------------
	 * -------------------------------------------------------------- */
	
	/* toggles the access index into the values */
	public void next_sim_cycle()
	{
		/* toggle the idx for old and new */
		new_val_idx = old_val_idx;
		old_val_idx = (new_val_idx == 1) ? 0 : 1;
	}

	public void clean_sim_cycle()
	{
		if (is_sequential_sim_cycle)
		{
			/* Makes sure the sequential registers keep value and catches
			 * inferred latches */
			for (int i = 0; i < vars_list.size(); i++)
			{
				vars_list.get(i).seqUpdate(cycle_time, new_val_idx, old_val_idx);
			}
		}
		this.is_sequential_sim_cycle = false;
	}
	
	// Only called for root module
	public void toggleSequClock() {
		
		/* toggle between sequential sims and combinational sims */
		is_sequential_sim_cycle = (is_sequential_sim_cycle) ? false : true;
		
		// Pass the clock signal
		int clockValue = 0;
		if (is_sequential_sim_cycle) {clockValue = 1;}
		
		// Update root module clock
		ParseRegWire wire = module.getHash_vars().get("clk");
		if (wire != null) {
			wire.setValue(new_val_idx, clockValue, is_sequential);
		}
		
		// Update clock in all other modules
		for (ModuleInstance sub : this.subModules_list) {
			wire = sub.getHash_vars().get("clk");
			
			if (wire != null) {
				wire.setValue(new_val_idx, clockValue, is_sequential);
			}
		}
	}
	
	// The update flag tells the simulator if the given
	// wire has already been updated for a sequential clock cycle.
	public void resetSequUpdateFlag() {
		
		// Reset wires in root module
		for(ParseRegWire wire : module.getVars_list()) {
			wire.resetUpdateFlag();
		}
		
		// Reset wires in all other modules
		for (ModuleInstance sub : this.subModules_list) {
			for(ParseRegWire wire : sub.getVars_list()) {
				wire.resetUpdateFlag();
			}
		}
	}
	
	// Updates sim information from parent module into child module
	public void syncSimInfo(SimVisitor visitor) {
		
		this.new_val_idx = visitor.new_val_idx;
		this.old_val_idx = visitor.old_val_idx;
		this.is_sequential_sim_cycle = visitor.is_sequential_sim_cycle;
	}
	
	public int getOldIndex() {return this.old_val_idx;}
	public void setState(int state) {this.state = state;}
	public int getState() {return this.state;}

	/* --------------------------------------------------------------------------
	 * -----------
	 * --------------------------------------------------------------
	 * -------------------------- Handle the conditional IFs
	 * --------------------
	 * --------------------------------------------------------------------
	 * ------
	 * --------------------------------------------------------------------
	 * ------------ */
	@Override
	public Value visitConditional_statement(
			Verilog2001Parser.Conditional_statementContext ctx)
	{
		for (int i = 0; i < ctx.statement().size(); i++)
		{
			Verilog2001Parser.ExpressionContext expr = ctx.expression(i);
			int test = ctx.statement().size();
			if (ctx.expression(i) == null && i == ctx.statement().size() - 1)
			{
				/* ELSE statment */
				visit(ctx.statement(i));
				break;
			}
			else if (visit(ctx.expression(i)).asBoolean())
			{
				/* IF - this if condition is true, then evaluate statement */
				visit(ctx.statement(i));
				break;
			}
		}

		return null;
	}

	@Override
	public
			Value
			visitCase_statement(Verilog2001Parser.Case_statementContext ctx)
	{
		case_expression = visit(ctx.expression());

		for (int i = 0; i < ctx.case_item().size(); i++)
		{
			if (visit(ctx.case_item(i)).asBoolean())
			{
				/* If one evaluates true then exit */
				break;
			}
		}

		return null;

	}

	@Override
	public Value visitFCASE_ITEM(Verilog2001Parser.FCASE_ITEMContext ctx)
	{
		Value constant_expression = visit(ctx.expression());

		if (constant_expression.asInt() == case_expression.asInt())
		{
			visit(ctx.statement());
			return new Value(true);
		}
		return new Value(false);
	}

	@Override
	public Value visitDEFAULT(Verilog2001Parser.DEFAULTContext ctx)
	{
		/* If we get to the default then it's true */
		visit(ctx.statement());
		return new Value(true);
	}

	/* --------------------------------------------------------------------------
	 * -----------
	 * --------------------------------------------------------------
	 * -------------------------- Handle the always blocks
	 * ----------------------
	 * ------------------------------------------------------------------
	 * --------
	 * ------------------------------------------------------------------
	 * ------------ */
	@Override
	public Value visitCOMBONATIONAL_ALWAYS(
			Verilog2001Parser.COMBONATIONAL_ALWAYSContext ctx)
	{
		is_combinational = true;

		/* visit the children of the always block */
		visitChildren(ctx);

		is_combinational = false;

		return null;
	}

	@Override
	public Value visitSEQUENTIAL_ALWAYS(
			Verilog2001Parser.SEQUENTIAL_ALWAYSContext ctx)
	{
		is_sequential = true;

		/* visit the children of the always block */
		visitChildren(ctx);

		is_sequential = false;

		return null;
	}

	/* --------------------------------------------------------------------------
	 * -----------
	 * --------------------------------------------------------------
	 * -------------------------- Handle statements
	 * ------------------------------
	 * ----------------------------------------------------------
	 * ----------------
	 * ---------------------------------------------------------------------- */
	@Override
	public Value visitBlocking_assignment(
			Verilog2001Parser.Blocking_assignmentContext ctx)
	{
		if (!is_combinational)
		{
			System.out.println("ERROR: Blocking Statement in a sequential block");
			return new Value(false);
		}

		// System.out.println("Visit:"+ctx.getText()+" Line:"+ctx.start.getLine());

		Value left = visit(ctx.variable_lvalue());
		Value right = visit(ctx.expression());

		/* Update the data structure with the right value */
		left.setVar(new_val_idx, right.asInt(), is_sequential);

		// System.out.println("AssignBlocking:"+left.getVarName()+" Value = "+right.asInt());

		return null;
	}

	@Override
	public Value visitNonblocking_assignment(
			Verilog2001Parser.Nonblocking_assignmentContext ctx)
	{
		/* Only store on simulate cycles */
		if (!is_sequential)
		{
			System.out.println("ERROR: Non Blocking Statement in a combinational block");
			return new Value(false);
		}

		// System.out.println("Visit:"+ctx.getText());

		Value left = visit(ctx.variable_lvalue());
		Value right = visit(ctx.expression());

		/* Update the data structure with the right value */
		if (this.is_sequential_sim_cycle) {
			left.setVar(new_val_idx, right.asInt(), is_sequential);
		}

		return null;
	}

	@Override
	public Value visitContinuous_assign(
			Verilog2001Parser.Continuous_assignContext ctx)
	{
		// System.out.println("Continuous Visit:"+ctx.getText()+" Line:"+ctx.start.getLine());

		Value left = visit(ctx.variable_lvalue());
		Value right = visit(ctx.expression());

		/* Update the data structure with the right value */
		left.setVar(new_val_idx, right.asInt(), is_sequential);

		// System.out.println("Continuous assign:"+left.getVarName()+" Value = "+right.asInt());

		return null;
	}
	
	@Override
	public Value visitVariable_init(Verilog2001Parser.Variable_initContext ctx) {
		
		Value left = visit(ctx.variable_lvalue());
		Value right = visit(ctx.expression());

		/* Update the data structure with the right value */
		left.setVar(new_val_idx, right.asInt(), is_sequential);
		
		return null; 
	}

	/* --------------------------------------------------------------------------
	 * -----------
	 * --------------------------------------------------------------
	 * -------------------------- Handle assignment statements
	 * ------------------
	 * ----------------------------------------------------------------------
	 * ----
	 * ----------------------------------------------------------------------
	 * ------------ */
	@Override
	public Value visitVariable_lvalue(
			Verilog2001Parser.Variable_lvalueContext ctx)
	{
		String ident = ctx.identifier().getText();
		ParseRegWire regWire = hash_vars.get(ident);
		Value Size = null;

		if (ctx.range_expression() != null)
		{
			/* Visit the decimal number ... NOTE range is not done here! */
			Size = visit(ctx.range_expression().decimal_number());
		}

		if (regWire != null)
		{
			return new Value(ValueType.REG_WIRE_T, regWire, (Size != null ? Size.asInt() : -1));
		}

		/*
		 * Previously executed only when ports were present in the code.
		 * Executed if the wire and port were both null
		 */
		System.out.println("Error: No left hand side called: " + ident);

		return null;
	}

	/* --------------------------------------------------------------------------
	 * -----------
	 * --------------------------------------------------------------
	 * -------------------------- Handle expressions
	 * ----------------------------
	 * ------------------------------------------------------------
	 * --------------
	 * ------------------------------------------------------------------------ */
	@Override
	public Value visitUMINUS(Verilog2001Parser.UMINUSContext ctx)
	{
		// System.out.println("UMINUS");
		Value unary = visit(ctx.expression());
		return new Value(Integer.valueOf(-unary.asInt()));

	}

	@Override
	public Value visitUNOT(Verilog2001Parser.UNOTContext ctx)
	{
		// System.out.println("UNOT");
		Value unary = visit(ctx.expression());
		return new Value(Integer.valueOf(~unary.asInt()));
	}

	@Override
	public Value visitMULT_DIV_MOD(Verilog2001Parser.MULT_DIV_MODContext ctx)
	{
		// System.out.println("*/%");
		Value left = visit(ctx.expression(0));
		Value right = visit(ctx.expression(1));
		int mask1 = (1 << left.getSize()) - 1;
		int mask2 = (1 << right.getSize()) - 1;

		switch (ctx.op.getType())
		{
			case (Verilog2001Parser.MULT):
			{
				return new Value(Integer.valueOf(left.asInt() * right.asInt()), (mask1 >= mask2) ? 2 * mask1 : 2 * mask2);
			}
			case (Verilog2001Parser.DIV):
			{
				return new Value(Integer.valueOf(left.asInt() / right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.MOD):
			{
				return new Value(Integer.valueOf(left.asInt() % right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}
			default:
			{
				System.out.println("Error: Not ADD SUB called: " + ctx.getText());
			}
		}

		return null;

	}

	@Override
	public Value visitADD_SUB(Verilog2001Parser.ADD_SUBContext ctx)
	{
		// System.out.println("+-");
		Value left = visit(ctx.expression(0));
		Value right = visit(ctx.expression(1));
		int mask1 = (1 << left.getSize()) - 1;
		int mask2 = (1 << right.getSize()) - 1;

		switch (ctx.op.getType())
		{
			case (Verilog2001Parser.SUB):
			{
				return new Value(Integer.valueOf(left.asInt() - right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.ADD):
			{
				return new Value(Integer.valueOf(left.asInt() + right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}
			default:
			{
				System.out.println("Error: Not ADD SUB called: " + ctx.getText());
			}
		}

		return null;

	}

	@Override
	public Value visitBLOGIC(Verilog2001Parser.BLOGICContext ctx)
	{
		// System.out.println("Binary Logic");
		Value left = visit(ctx.expression(0));
		Value right = visit(ctx.expression(1));
		int mask1 = (1 << left.getSize()) - 1;
		int mask2 = (1 << right.getSize()) - 1;

		switch (ctx.op.getType())
		{
			case (Verilog2001Parser.BITWISE_AND):
			{
				return new Value(Integer.valueOf(left.asInt() & right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.BITWISE_NAND):
			{
				return new Value(Integer.valueOf(~(left.asInt() & right.asInt())), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.BITWISE_OR):
			{
				return new Value(Integer.valueOf(left.asInt() | right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.BITWISE_NOR):
			{
				return new Value(Integer.valueOf(~(left.asInt() | right.asInt())), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.BITWISE_XOR):
			{
				return new Value(Integer.valueOf(left.asInt() ^ right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.BITWISE_XNOR):
			{
				return new Value(Integer.valueOf(~(left.asInt() ^ right.asInt())), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.SHIFT_LEFT):
			{
				return new Value(Integer.valueOf(left.asInt() << right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}
			case (Verilog2001Parser.SHIFT_RIGHT):
			{
				return new Value(Integer.valueOf(left.asInt() >> right.asInt()), (mask1 >= mask2) ? mask1 : mask2);
			}

			default:
			{
				System.out.println("Error: No Binary operator called: " + ctx.getText());
			}
		}

		return null;
	}

	@Override
	public Value visitCOMPARES(Verilog2001Parser.COMPARESContext ctx)
	{
		Value left = visit(ctx.expression(0));
		if (left == null) {return null;}
		Value right = visit(ctx.expression(1));

		switch (ctx.op.getType())
		{
			case (Verilog2001Parser.NOTEQUAL):
			{
				return new Value(Boolean.valueOf(left.asInt() != right.asInt()));
			}
			case (Verilog2001Parser.EQUAL):
			{
				return new Value(Boolean.valueOf(left.asInt() == right.asInt()));
			}
			case (Verilog2001Parser.LT):
			{
				return new Value(Boolean.valueOf(left.asInt() < right.asInt()));
			}
			case (Verilog2001Parser.LTE):
			{
				return new Value(Boolean.valueOf(left.asInt() <= right.asInt()));
			}
			case (Verilog2001Parser.GT):
			{
				return new Value(Boolean.valueOf(left.asInt() > right.asInt()));
			}
			case (Verilog2001Parser.GTE):
			{
				return new Value(Boolean.valueOf(left.asInt() >= right.asInt()));
			}
			default:
			{
				System.out.println("Error: No lofical operator called: " + ctx.getText());
			}
		}

		return null;
	}

	@Override
	public Value visitLNOT(Verilog2001Parser.LNOTContext ctx)
	{
		Value unary = visit(ctx.expression(0));
		return new Value(Boolean.valueOf(!unary.asBoolean()));
	}

	@Override
	public Value visitLAND(Verilog2001Parser.LANDContext ctx)
	{
		Value left = visit(ctx.expression(0));
		Value right = visit(ctx.expression(1));
		return new Value(Boolean.valueOf(left.asBoolean() && right.asBoolean()));
	}

	@Override
	public Value visitLOR(Verilog2001Parser.LORContext ctx)
	{
		Value left = visit(ctx.expression(0));
		Value right = visit(ctx.expression(1));
		return new Value(Boolean.valueOf(left.asBoolean() || right.asBoolean()));
	}

	@Override
	public Value visitQUES(Verilog2001Parser.QUESContext ctx)
	{
		System.out.println("?");
		System.out.println("Error: Not implemented yet");
		return null;
	}

	@Override
	public Value visitBRACKETS(Verilog2001Parser.BRACKETSContext ctx)
	{
		/* just pass results up */
		return visit(ctx.expression());
	}

	@Override
	public Value visitIDENT(Verilog2001Parser.IDENTContext ctx)
	{
		/* Gets numbers */
		Value next = visit(ctx.identifier_types());
		// System.out.println("Visit Expression:"+next.asInt());
		return next;
	}

	@Override
	public Value visitBIT_ACCESS(Verilog2001Parser.BIT_ACCESSContext ctx)
	{
		String ident = ctx.identifier().getText();
		ParseRegWire regWire = hash_vars.get(ident);
		Value number = visit(ctx.constant_expression());

		if (regWire != null)
		{
			int value_of_bit = regWire.getIntegerBit(number.asInt(), old_val_idx);
			// System.out.println("RegWire bit:"+ident+" loc: "+number.asInt()+" val:"+value_of_bit);
			return new Value(value_of_bit, 1);
		}

		/*
		 * Previously executed only when ports were present in the code.
		 * Executed if the wire and port were both null
		 */
		System.out.println("Error: No item called: " + ident);

		return visitChildren(ctx);
	}

	@Override
	public Value visitGENERAL(Verilog2001Parser.GENERALContext ctx)
	{
		String ident = ctx.getText();
		ParseRegWire regWire = hash_vars.get(ident);

		if (regWire != null)
		{
			// System.out.println("General Ident:"+ident+" val:"+regWire.getValue(old_val_idx));
			return new Value(regWire.getValue(old_val_idx), 30);
		}

		System.out.println("Error: No item called: " + ident);

		return null;
	}

	@Override
	public Value visitINUMBER(Verilog2001Parser.INUMBERContext ctx)
	{
		/* Visit your children and return the appropriate Value - holds size if
		 * need be */
		return visitChildren(ctx);
	}

	@Override
	public
			Value
			visitDecimal_number(Verilog2001Parser.Decimal_numberContext ctx)
	{
		/* get the 2nd list array item and convert to int */
		if (ctx.Unsigned_number().size() == 1)
		{
			String numberText = ctx.Unsigned_number().get(0).getText();
			return new Value(Integer.parseInt(numberText), 30);
		}
		else
		{
			return new Value(Integer.parseInt(ctx.Unsigned_number().get(1).getText()), Integer.parseInt(ctx.Unsigned_number().get(0).getText()));
		}
	}

	@Override
	public Value visitBinary_number(Verilog2001Parser.Binary_numberContext ctx)
	{
		/* get the 2nd list array item and convert to int */
		int test = Integer.parseInt(ctx.Unsigned_number().get(1).getText(), 2);
		int test1 = Integer.parseInt(ctx.Unsigned_number().get(0).getText());
		return new Value(test, test1);
	}

	@Override
	public Value visitOctal_number(Verilog2001Parser.Octal_numberContext ctx)
	{
		return new Value(Integer.parseInt(ctx.Unsigned_number().get(1).getText(), 8), Integer.parseInt(ctx.Unsigned_number().get(0).getText()));
	}

	@Override
	public Value visitHex_number(Verilog2001Parser.Hex_numberContext ctx)
	{
		return new Value(Integer.parseInt(ctx.Unsigned_number().get(1).getText(), 16), Integer.parseInt(ctx.Unsigned_number().get(0).getText()));
	}
	
	@Override
	public Value visitModule_instantiation(Verilog2001Parser.Module_instantiationContext ctx) {
		
		// Get the module
		String moduleName = ctx.module_instance(0).name_of_instance().module_instance_identifier().getText();
		ModuleInstance targetModule = subModules_hash.get(moduleName);
		
		SimVisitor visitor = targetModule.getVisitor();
		
		// Connects wires to new module
		Verilog2001Parser.List_of_port_connectionsContext ports = ctx.module_instance(0).list_of_port_connections();
		for(int i = 0; i < ports.identifier().size(); ++i) {
			
			// Get the target wire in the new module
			String port_name = targetModule.getPorts_list().get(i);
			ParseRegWire targetWire = targetModule.getHash_vars().get(port_name);
			
			// We are only setting inputs for now
			if (targetWire.getRole() != WireRoleType.INPUT) {
				continue;
			}
			
			// Grab the wire the new module wants
			String identifier = ports.identifier(i).getText();
			ParseRegWire inWire = module.getHash_vars().get(identifier);
			
			// Set their values equal to eachother
			targetWire.setValue(new_val_idx, inWire.getValue(old_val_idx), is_sequential);
		}
		
		// Simulate new module
		visitor.syncSimInfo(this); // Must syncronize old and new value indicies
		visitor.visit(targetModule.getParseTree());
		
		// Connect output of that module to the required wires
		for(int i = 0; i < ports.identifier().size(); ++i) {
			
			// Get the target wire in the new module
			String port_name = targetModule.getPorts_list().get(i);
			ParseRegWire outWire = targetModule.getHash_vars().get(port_name);
			
			// We are only setting outputs now
			if (outWire.getRole() != WireRoleType.OUTPUT) {
				continue;
			}
			
			// Grab the wire the new module wants
			String identifier = ports.identifier(i).getText();
			ParseRegWire targetWire = module.getHash_vars().get(identifier);
			
			// Set their values equal to eachother
			targetWire.setValue(new_val_idx, outWire.getValue(old_val_idx), is_sequential);
		}
		
		// Propagate state changes in sub modules to the super module
		if (visitor.getState() == SimVisitor.NOT_STEADY) {
			this.state = SimVisitor.NOT_STEADY;
		}
		
		return null;
	}
}
