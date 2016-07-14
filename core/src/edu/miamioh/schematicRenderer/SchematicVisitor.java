package edu.miamioh.schematicRenderer;

import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * Visits nodes on the parse tree to build the schematic.
 *
 * Created by bdshaffer73.
 */
class SchematicVisitor<T>
		extends edu.miamioh.simulator.AntlrGen.Verilog2001BaseVisitor<T> {
	
	private SchematicRenderer schematic;
	
	private String			  lValue, rValue, gateValue;
	private Stack<String>	  gateInputs = new Stack<>();
	
	/**
	 * Creates a new ParseTree Visitor to collect data for the schematic.
	 * 
	 * @param schematic
	 *            The Schematic that is getting the data.
	 */
	SchematicVisitor(SchematicRenderer schematic) {
		this.schematic = schematic;
	}
	
	// Public override methods
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor creates a new assignment of "expression = wire." Left-hand
	 * arguments are always wires or outputs.
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitContinuous_assign(
			Verilog2001Parser.Continuous_assignContext ctx) {
		
		T t = super.visitContinuous_assign(ctx);
		
		// Get lValue -> variable assigning
		lValue = gateInputs.pop();
		
		// Get rValue -> variable getting assigned
		rValue = gateInputs.pop();
		
		schematic.connect(lValue, rValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor adds an Adder or Subtractor. (Currently just adds a Blank
	 * gate.)
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitADD_SUB(Verilog2001Parser.ADD_SUBContext ctx) {
		T t = super.visitADD_SUB(ctx);
		
		lValue = gateInputs.pop();
		rValue = gateInputs.pop();
		
		gateValue = "AddSub_";
		GateType type = GateType.BLANK;
		gateValue += getNumOfGates(type);
		int level = schematic.getLevel(lValue) + 1;
		
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		schematic.connect(rValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor creates an assignment of the format "variable <=
	 * expression."
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitNonblocking_assignment(
			Verilog2001Parser.Nonblocking_assignmentContext ctx) {
		
		// Process the children
		T t = super.visitNonblocking_assignment(ctx);
		
		// Get lValue -> expression being assigned
		lValue = gateInputs.pop();
		
		// Get rValue -> variable getting assigned
		rValue = gateInputs.pop();
		
		schematic.connect(lValue, rValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code exp}.
	 * </p>
	 *
	 * <p>
	 * This visitor creates a new Input Port object in the schematic for every
	 * Input identifier in the Input_declaration. Range expressions are
	 * supported but not required.
	 * </p>
	 *
	 * @param exp
	 */
	@Override
	public T visitInput_declaration(
			Verilog2001Parser.Input_declarationContext exp) {
		
		ParseTree inputIdentifiers = exp.list_of_identifiers();
		int numOfInputs = inputIdentifiers.getChildCount();
		String id;
		
		if (exp.getChild(1) instanceof Verilog2001Parser.RangeContext) {
			int msb = Integer.parseInt(exp.getChild(1).getChild(1).getText());
			ParseTree idList = exp.list_of_identifiers();
			for (int j = 0; j < numOfInputs; j++) {
				if (idList.getChild(
						j) instanceof Verilog2001Parser.IdentifierContext) {
					for (int i = 0; i <= msb; i++) {
						id = exp.getChild(2).getChild(j).getText();
						schematic.addInput(id + "[" + i + "]");
					}
				}
			}
		} else {
			for (int i = 0; i < numOfInputs; i++) {
				if (inputIdentifiers.getChild(
						i) instanceof Verilog2001Parser.IdentifierContext) {
					id = inputIdentifiers.getChild(i).getText();
					schematic.addInput(id);
				}
			}
		}
		return super.visitInput_declaration(exp);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor creates a new Not gate with one input. Inputs can be gates,
	 * ports or wires.
	 * </p>
	 *
	 * @param exp
	 */
	@Override
	public T visitUNOT(Verilog2001Parser.UNOTContext exp) {
		
		T t = super.visitUNOT(exp);
		
		lValue = gateInputs.pop();
		gateValue = gateValue(exp.getChild(0));
		
		GateType type = getNewLogicType_GateType(exp.getChild(0));
		int lLevel = schematic.getLevel(lValue);
		int level = lLevel + 1;
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor adds an LOR gate. (Currently just adds a Blank gate.)
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitLOR(Verilog2001Parser.LORContext ctx) {
		T t = super.visitLOR(ctx);
		
		lValue = gateInputs.pop();
		rValue = gateInputs.pop();
		
		gateValue = "LOR";
		GateType type = GateType.BLANK;
		gateValue += getNumOfGates(type);
		int level = schematic.getLevel(lValue) + 1;
		
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		schematic.connect(rValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor creates a new Reg object for every Reg Identifier in a
	 * Reg_declaration. Range expressions are accepted but not required.
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitReg_declaration(
			Verilog2001Parser.Reg_declarationContext ctx) {
		
		ParseTree regIdentifiers = ctx.list_of_identifiers();
		int numOfRegs = regIdentifiers.getChildCount();
		String id;
		
		if (ctx.getChild(1) instanceof Verilog2001Parser.RangeContext) {
			int msb = Integer.parseInt(ctx.getChild(1).getChild(1).getText());
			ParseTree idList = ctx.list_of_identifiers();
			for (int j = 0; j < numOfRegs; j++) {
				if (idList.getChild(
						j) instanceof Verilog2001Parser.IdentifierContext) {
					for (int i = 0; i <= msb; i++) {
						id = ctx.getChild(2).getChild(j).getText();
						schematic.addGate(GateType.REG, id + "[" + i + "]", 1);
					}
				}
			}
		} else {
			for (int i = 0; i < numOfRegs; i++) {
				if (regIdentifiers.getChild(
						i) instanceof Verilog2001Parser.IdentifierContext) {
					id = regIdentifiers.getChild(i).getText();
					schematic.addGate(GateType.REG, id, 1);
				}
			}
		}
		
		return super.visitReg_declaration(ctx);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor adds an LNOT gate. (Currently just adds a Blank gate.)
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitLNOT(Verilog2001Parser.LNOTContext ctx) {
		T t = super.visitLNOT(ctx);
		
		lValue = gateInputs.pop();
		rValue = gateInputs.pop();
		
		gateValue = "LNOT";
		GateType type = GateType.BLANK;
		gateValue += getNumOfGates(type);
		int level = schematic.getLevel(lValue) + 1;
		
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		schematic.connect(rValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor creates an assignment of the format "variable <=
	 * expression."
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitBlocking_assignment(
			Verilog2001Parser.Blocking_assignmentContext ctx) {
		
		T t = super.visitBlocking_assignment(ctx);
		
		// Get lValue -> variable assigning
		lValue = gateInputs.pop();
		
		// Get rValue -> variable getting assigned
		rValue = gateInputs.pop();
		
		schematic.connect(lValue, rValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor adds a Comparator. (Currently just adds a Blank gate.)
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitCOMPARES(Verilog2001Parser.COMPARESContext ctx) {
		T t = super.visitCOMPARES(ctx);
		
		lValue = gateInputs.pop();
		rValue = gateInputs.pop();
		
		gateValue = "Compares";
		GateType type = GateType.BLANK;
		gateValue += getNumOfGates(type);
		int level = schematic.getLevel(lValue) + 1;
		
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		schematic.connect(rValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor adds a LAND gate. (Currently just adds a Blank gate.)
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitLAND(Verilog2001Parser.LANDContext ctx) {
		T t = super.visitLAND(ctx);
		
		lValue = gateInputs.pop();
		rValue = gateInputs.pop();
		
		gateValue = "LAND";
		GateType type = GateType.BLANK;
		gateValue += getNumOfGates(type);
		int level = schematic.getLevel(lValue) + 1;
		
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		schematic.connect(rValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * @param exp
	 */
	@Override
	public T visitBLOGIC(Verilog2001Parser.BLOGICContext exp) {
		
		T t = super.visitBLOGIC(exp);
		
		lValue = gateInputs.pop();
		rValue = gateInputs.pop();
		gateValue = gateValue(exp.getChild(1));
		
		GateType type = getNewLogicType_GateType(exp.getChild(1));
		int lLevel, rLevel;
		lLevel = schematic.getLevel(lValue);
		rLevel = schematic.getLevel(rValue);
		int level = ((lLevel > rLevel) ? lLevel : rLevel) + 1;
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		schematic.connect(rValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor adds a multiplication, division, or modulous block.
	 * (currently just a Blank gate.)
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitMULT_DIV_MOD(Verilog2001Parser.MULT_DIV_MODContext ctx) {
		T t = super.visitMULT_DIV_MOD(ctx);
		
		lValue = gateInputs.pop();
		rValue = gateInputs.pop();
		
		gateValue = "MultDivMod";
		GateType type = GateType.BLANK;
		gateValue += getNumOfGates(type);
		int level = schematic.getLevel(lValue) + 1;
		
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		schematic.connect(rValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * @param exp
	 */
	@Override
	public T visitOUTPUT_DECLARATION_NO_REG(
			Verilog2001Parser.OUTPUT_DECLARATION_NO_REGContext exp) {
		ParseTree outputIdentifiers = exp.list_of_identifiers();
		int numOfOutputs = outputIdentifiers.getChildCount();
		String id;
		
		if (exp.getChild(1) instanceof Verilog2001Parser.RangeContext) {
			int msb = Integer.parseInt(exp.getChild(1).getChild(1).getText());
			ParseTree idList = exp.list_of_identifiers();
			for (int j = 0; j < numOfOutputs; j++) {
				if (idList.getChild(
						j) instanceof Verilog2001Parser.IdentifierContext) {
					for (int i = 0; i <= msb; i++) {
						id = exp.getChild(2).getChild(j).getText();
						schematic.addOutput(id + "[" + i + "]");
					}
				}
			}
		} else {
			for (int i = 0; i < numOfOutputs; i++) {
				if (outputIdentifiers.getChild(
						i) instanceof Verilog2001Parser.IdentifierContext) {
					id = outputIdentifiers.getChild(i).getText();
					schematic.addOutput(id);
				}
			}
		}
		
		return super.visitOUTPUT_DECLARATION_NO_REG(exp);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitVariable_lvalue(
			Verilog2001Parser.Variable_lvalueContext ctx) {
		gateInputs.push(ctx.getText());
		return super.visitVariable_lvalue(ctx);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor adds a sign inverter. (Currently just a blank Gate.)
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitUMINUS(Verilog2001Parser.UMINUSContext ctx) {
		T t = super.visitUMINUS(ctx);
		
		lValue = gateInputs.pop();
		
		gateValue = "MINUS";
		GateType type = GateType.BLANK;
		gateValue += getNumOfGates(type);
		int level = schematic.getLevel(lValue) + 1;
		
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * @param exp
	 */
	@Override
	public T visitNet_declaration(
			Verilog2001Parser.Net_declarationContext exp) {
		
		ParseTree netIdentifiers = exp.list_of_identifiers();
		int numOfNets = netIdentifiers.getChildCount();
		String id;
		
		if (exp.getChild(1) instanceof Verilog2001Parser.RangeContext) {
			int msb = Integer.parseInt(exp.getChild(1).getChild(1).getText());
			ParseTree idList = exp.list_of_identifiers();
			for (int j = 0; j < numOfNets; j++) {
				if (idList.getChild(
						j) instanceof Verilog2001Parser.IdentifierContext) {
					for (int i = 0; i <= msb; i++) {
						id = exp.getChild(2).getChild(j).getText();
						schematic.addGate(GateType.WIRE, id + "[" + i + "]", 1);
					}
				}
			}
		} else {
			for (int i = 0; i < numOfNets; i++) {
				if (netIdentifiers.getChild(
						i) instanceof Verilog2001Parser.IdentifierContext) {
					id = netIdentifiers.getChild(i).getText();
					schematic.addGate(GateType.WIRE, id, 1);
				}
			}
		}
		return super.visitNet_declaration(exp);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitIDENT(Verilog2001Parser.IDENTContext ctx) {
		gateInputs.push(ctx.getText());
		return super.visitIDENT(ctx);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 *
	 * <p>
	 * This visitor creates a new Question switch ( 'if' ? 'then' : 'else' ).
	 * (Currently just makes a new Blank gate.)
	 * </p>
	 *
	 * @param ctx
	 */
	@Override
	public T visitQUES(Verilog2001Parser.QUESContext ctx) {
		T t = super.visitQUES(ctx);
		
		lValue = gateInputs.pop();
		String mValue = gateInputs.pop();
		rValue = gateInputs.pop();
		
		gateValue = "Ques";
		GateType type = GateType.BLANK;
		gateValue += getNumOfGates(type);
		int level = schematic.getLevel(lValue) + 1;
		
		schematic.addGate(type, gateValue, level);
		
		schematic.connect(lValue, gateValue);
		schematic.connect(mValue, gateValue);
		schematic.connect(rValue, gateValue);
		
		gateInputs.push(gateValue);
		
		return t;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitSEQUENTIAL_ALWAYS(
			@NotNull Verilog2001Parser.SEQUENTIAL_ALWAYSContext ctx) {
		
		JOptionPane.showMessageDialog(null,
				"Sequential Always blocks are not supported.",
				"Schematic Compiler: Unsupported construct", JOptionPane.WARNING_MESSAGE);
		
		return super.defaultResult();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitCOMBONATIONAL_ALWAYS(
			@NotNull Verilog2001Parser.COMBONATIONAL_ALWAYSContext ctx) {
		JOptionPane.showMessageDialog(null,
				"Combinational Always blocks are not supported.",
				"Schematic Compiler: Unsupported construct", JOptionPane.WARNING_MESSAGE);
		return super.defaultResult();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public T visitParameter_declaration_(
			@NotNull Verilog2001Parser.Parameter_declaration_Context ctx) {
		JOptionPane.showMessageDialog(null, "Parameters are not supported.",
				"Schematic Compiler: Unsupported constructed", JOptionPane.ERROR_MESSAGE);
		return super.defaultResult();
	}
	
	// Private methods
	
	/**
	 * Gets the ID of a new Gate.
	 * 
	 * @param exp
	 *            Parse Tree of the BLOGIC gate.
	 * @return ID of the new Gate.
	 */
	private String gateValue(ParseTree exp) {
		
		if (exp instanceof TerminalNode) {
			// Return ID of a logic gate
			String id = getNewLogicType_String(exp);
			GateType gType = getNewLogicType_GateType(exp);
			id += "_" + getNumOfGates(gType);
			return id;
		} else
			return "";
	}
	
	/**
	 * Translates the text from the BLOGIC node to its String conversion of
	 * GateType.
	 *
	 * @param ctx
	 *            ParseTree with root_module of this BLOGIC.
	 * @return The String of this BLOGIC.
	 */
	private String getNewLogicType_String(ParseTree ctx) {
		
		switch (ctx.getText()) {
			
			case ("&"):
				return "AND";
			
			case ("~&"):
				return "NAND";
			
			case ("|"):
				return "OR";
			
			case ("~|"):
				return "NOR";
			
			case ("^"):
				return "XOR";
			
			case ("~^"):
				return "XNOR";
			
			case ("~"):
				return "NOT";
			
			default:
				return "NULL";
		}
	}
	
	/**
	 * Translates the text of the BLOGIC node into its GateType.
	 *
	 * @param ctx
	 *            ParseTree with root_module of this BLOGIC.
	 * @return The GateType of this BLOGIC.
	 */
	private GateType getNewLogicType_GateType(ParseTree ctx) {
		
		switch (ctx.getText()) {
			
			case ("&"):
				return GateType.AND;
			
			case ("~&"):
				return GateType.NAND;
			
			case ("|"):
				return GateType.OR;
			
			case ("~|"):
				return GateType.NOR;
			
			case ("^"):
				return GateType.XOR;
			
			case ("~^"):
				return GateType.XNOR;
			
			case ("~"):
				return GateType.NOT;
			
			default:
				return GateType.BLANK;
			
		}
	}
	
	/**
	 * Gets the number of Gates of the same type.
	 *
	 * @param type
	 *            The type of Gate being counted.
	 * @return The number of Gates of type Type.
	 */
	private int getNumOfGates(GateType type) {
		return schematic.getGateCount(type);
	}
}
