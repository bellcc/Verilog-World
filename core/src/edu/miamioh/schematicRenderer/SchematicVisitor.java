package edu.miamioh.schematicRenderer;

import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.Stack;

/**
 * Visits nodes on the parse tree to build the schematic.
 *
 * Created by shaffebd.
 */
class SchematicVisitor<T> extends edu.miamioh.simulator.AntlrGen.Verilog2001BaseVisitor<T> {

    private SchematicRenderer schematic;

    private String lValue, rValue, gateValue;
    private Stack<String> gateInputs = new Stack<>();

    SchematicVisitor(SchematicRenderer schematic) {this.schematic = schematic;}

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor is not used.</p>
     *
     * @param ctx
    */
    @Override
    public T visitInstance_identifier(Verilog2001Parser.Instance_identifierContext ctx) {
        return super.visitInstance_identifier(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitList_of_param_assignments(Verilog2001Parser.List_of_param_assignmentsContext ctx) {
        return super.visitList_of_param_assignments(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor is only used as a node.</p>
     *
     * @param ctx
     */
    @Override
    public T visitBinary_number(Verilog2001Parser.Binary_numberContext ctx) {
        return super.visitBinary_number(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor creates a new assignment of "expression = wire."
     * Left-hand arguments are always wires or outputs.</p>
     *
     * @param ctx
     */
    @Override
    public T visitContinuous_assign(Verilog2001Parser.Continuous_assignContext ctx)     {

        T t = super.visitContinuous_assign(ctx);

        //Get lValue -> variable assigning
        lValue = gateInputs.pop();

        //Get rValue -> variable getting assigned
        rValue = gateInputs.pop();

        schematic.connect(lValue, rValue);

        return t;
    }

    /**
     * Gets the ID of a new Gate.
     * @param exp   Parse Tree of the BLOGIC gate.
     * @return      ID of the new Gate.
     */
    private String gateValue(ParseTree exp) {

//        if (exp instanceof Verilog2001Parser.Variable_lvalueContext) {
//            //Return ID of a variable *(left hand arg)
//            return exp.getText();
//        } else
            if (exp instanceof TerminalNode) {
            //Return ID of a logic gate
            String id = getNewLogicType_String(exp);
            GateType gType = getNewLogicType_GateType(exp);
            id += getNumOfGates(gType);
            return id;
//        } else if (exp instanceof Verilog2001Parser.IDENTContext) {
//            return exp.getText();
        } else
            return "";
    }

    /**
     * Translates the text from the BLOGIC node to its String conversion of
     * GateType.
     *
     * @param ctx ParseTree with root_module of this BLOGIC.
     * @return The String of this BLOGIC.
     */
    private String getNewLogicType_String(ParseTree ctx) {

        switch(ctx.getText()){

            case("&"):
                return "AND";

            case("~&"):
                return "NAND";

            case("|"):
                return "OR";

            case("~|"):
                return "NOR";

            case("^"):
                return "XOR";

            case("~^"):
                return "XNOR";

            case("~"):
                return "NOT";

            default:
                return "NULL";
        }
    }

    /**
     * Translates the text of the BLOGIC node into its GateType.
     *
     * @param ctx ParseTree with root_module of this BLOGIC.
     * @return The GateType of this BLOGIC.
     */
    private GateType getNewLogicType_GateType(ParseTree ctx) {

        switch(ctx.getText()){

            case("&"):
                return GateType.AND;

            case("~&"):
                return GateType.NAND;

            case("|"):
                return GateType.OR;

            case("~|"):
                return GateType.NOR;

            case("^"):
                return GateType.XOR;

            case("~^"):
                return GateType.XNOR;

            case("~"):
                return GateType.NOT;

            default:
                return GateType.BLANK;

        }
    }

    /**
     * Gets the number of Gates of the same type.
     *
     * @param type The type of Gate being counted.
     * @return The number of Gates of type Type.
     */
    private int getNumOfGates(GateType type){
        return schematic.getGateCount(type);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor adds an Adder or Subtractor. (Currently just adds a
     * Blank gate.)</p>
     *
     * @param ctx
     */
    @Override
    public T visitADD_SUB(Verilog2001Parser.ADD_SUBContext ctx) {
        T t = super.visitADD_SUB(ctx);

        lValue = gateInputs.pop();
        rValue = gateInputs.pop();

        gateValue = "AddSub";
        GateType type = GateType.BLANK;
        gateValue += getNumOfGates(type);
        int level = schematic.getLevel(lValue) + 1;

        schematic.addGate(type, gateValue, level);

        schematic.connect(lValue, gateValue);
        schematic.connect(rValue, gateValue);

        gateInputs.push(gateValue);

        return t;    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitName_of_instance(Verilog2001Parser.Name_of_instanceContext ctx) {
        return super.visitName_of_instance(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor creates an assignment of the format "variable <=
     * expression."</p>
     *
     * @param ctx
     */
    @Override
    public T visitNonblocking_assignment(Verilog2001Parser.Nonblocking_assignmentContext ctx) {

        //Process the children
        T t = super.visitNonblocking_assignment(ctx);

        //Get lValue -> expression being assigned
        lValue = gateInputs.pop();

        //Get rValue -> variable getting assigned
        rValue = gateInputs.pop();

        schematic.connect(lValue, rValue);

        return t;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor is not used.</p>
     *
     * @param ctx
     */
    @Override
    public T visitNumber(Verilog2001Parser.NumberContext ctx) {
        return super.visitNumber(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code exp}.</p>
     *
     * <p>This visitor creates a new Input Port object in the schematic for
     * every Input identifier in the Input_declaration. Range expressions
     * are supported but not required.</p>
     *
     * @param exp
     */
    @Override
    public T visitInput_declaration(Verilog2001Parser.Input_declarationContext exp) {
        
        ParseTree inputIdentifiers = exp.list_of_identifiers();
        int numOfInputs = inputIdentifiers.getChildCount();
        String id;
        
        if(exp.getChild(1) instanceof Verilog2001Parser.RangeContext){
            int msb = Integer.parseInt(exp.getChild(1).getChild(1).getText());
            ParseTree idList = exp.list_of_identifiers();
            for(int j = 0; j < numOfInputs; j++) {
                if (idList.getChild(j) instanceof Verilog2001Parser.IdentifierContext){
                    for (int i = 0; i <= msb; i++) {
                        id = exp.getChild(2).getChild(j).getText();
                        schematic.addInput(id + "[" + i + "]");
                    }
                }
            }
        } else {
            for (int i = 0; i < numOfInputs; i++){
                if(inputIdentifiers.getChild(i) instanceof Verilog2001Parser.IdentifierContext) {
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
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor only calls its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitList_of_port_connections(Verilog2001Parser.List_of_port_connectionsContext ctx) {
        return super.visitList_of_port_connections(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor only calls its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitModule_instantiation(Verilog2001Parser.Module_instantiationContext ctx) {
        return super.visitModule_instantiation(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor creates a new Not gate with one input. Inputs can be
     * gates, ports or wires.</p>
     *
     * @param exp
     */
    @Override
    public T visitUNOT(Verilog2001Parser.UNOTContext exp) {

        T t = super.visitUNOT(exp);

        lValue = gateInputs.pop();
        gateValue = gateValue(exp.getChild(0));

        GateType type = getNewLogicType_GateType(exp.getChild(0));
        //id = gateValue
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
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor only calls its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitList_of_identifiers(Verilog2001Parser.List_of_identifiersContext ctx) {
        return super.visitList_of_identifiers(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor is visited but performs no actions.</p>
     *
     * @param ctx
     */
    @Override
    public T visitIdentifier(Verilog2001Parser.IdentifierContext ctx) {
        return super.visitIdentifier(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitSource_text(Verilog2001Parser.Source_textContext ctx) {
        return super.visitSource_text(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor does not need to perform any functions besides calling
     * its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitPort_declaration(Verilog2001Parser.Port_declarationContext ctx) {
        return super.visitPort_declaration(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor adds an LOR gate. (Currently just adds a Blank gate.)</p>
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

        return t;    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor does not need to perform any functions besides calling
     * its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitPort(Verilog2001Parser.PortContext ctx) {
        return super.visitPort(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitParam_assignment(Verilog2001Parser.Param_assignmentContext ctx) {
        return super.visitParam_assignment(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor does not need to perform any functions besides calling
     * its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitModule_identifier(Verilog2001Parser.Module_identifierContext ctx) {
        return super.visitModule_identifier(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitDecimal_number(Verilog2001Parser.Decimal_numberContext ctx) {
        return super.visitDecimal_number(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor does not need to perform any functions besides calling
     * its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitPort_reference(Verilog2001Parser.Port_referenceContext ctx) {
        return super.visitPort_reference(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor creates a new Reg object for every Reg Identifier in
     * a Reg_declaration. Range expressions are accepted but not required.</p>
     *
     * @param ctx
     */
    @Override
    public T visitReg_declaration(Verilog2001Parser.Reg_declarationContext ctx) {

        ParseTree regIdentifiers = ctx.list_of_identifiers();
        int numOfRegs = regIdentifiers.getChildCount();
        String id;

        if(ctx.getChild(1) instanceof Verilog2001Parser.RangeContext){
            int msb = Integer.parseInt(ctx.getChild(1).getChild(1).getText());
            ParseTree idList = ctx.list_of_identifiers();
            for(int j = 0; j < numOfRegs; j++) {
                if (idList.getChild(j) instanceof Verilog2001Parser.IdentifierContext){
                    for (int i = 0; i <= msb; i++) {
                        id = ctx.getChild(2).getChild(j).getText();
                        schematic.addGate(GateType.REG, id + "[" + i + "]", 1);
                    }
                }
            }
        } else {
            for (int i = 0; i < numOfRegs; i++){
                if(regIdentifiers.getChild(i) instanceof Verilog2001Parser.IdentifierContext) {
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
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitVariable_init(Verilog2001Parser.Variable_initContext ctx) {
        return super.visitVariable_init(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitINUMBER(Verilog2001Parser.INUMBERContext ctx) {
        return super.visitINUMBER(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor does not need to perform any functions besides calling
     * its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitCOMBONATIONAL_ALWAYS(Verilog2001Parser.COMBONATIONAL_ALWAYSContext ctx) {
        return super.visitCOMBONATIONAL_ALWAYS(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor only calls its childre.</p>
     *
     * @param ctx
     */
    @Override
    public T visitBRACKETS(Verilog2001Parser.BRACKETSContext ctx) {
        return super.visitBRACKETS(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitMsb_constant_expression(Verilog2001Parser.Msb_constant_expressionContext ctx) {
        return super.visitMsb_constant_expression(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor adds an LNOT gate. (Currently just adds a Blank gate.)
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

        return t;    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitModule_item(Verilog2001Parser.Module_itemContext ctx) {
        return super.visitModule_item(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitOUTPUT_DECLARATION_REG(Verilog2001Parser.OUTPUT_DECLARATION_REGContext ctx) {
        return super.visitOUTPUT_DECLARATION_REG(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor creates an assignment of the format "variable <=
     * expression."</p>
     *
     * @param ctx
     */
    @Override
    public T visitBlocking_assignment(Verilog2001Parser.Blocking_assignmentContext ctx) {

        T t = super.visitBlocking_assignment(ctx);

        //Get lValue -> variable assigning
        lValue = gateInputs.pop();

        //Get rValue -> variable getting assigned
        rValue = gateInputs.pop();

        schematic.connect(lValue, rValue);

        return t;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitHex_number(Verilog2001Parser.Hex_numberContext ctx) {
        return super.visitHex_number(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor adds a Comparator. (Currently just adds a Blank gate.)
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

        return t;    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor adds a LAND gate. (Currently just adds a Blank gate.)</p>
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

        return t;    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitDescription(Verilog2001Parser.DescriptionContext ctx) {
        return super.visitDescription(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitRange(Verilog2001Parser.RangeContext ctx) {
        return super.visitRange(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitOctal_number(Verilog2001Parser.Octal_numberContext ctx) {
        return super.visitOctal_number(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitCase_statement(Verilog2001Parser.Case_statementContext ctx) {
        T t = super.visitCase_statement(ctx);

        return t;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitGENERAL(Verilog2001Parser.GENERALContext ctx) {
        return super.visitGENERAL(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor does not need to perform any functions besides calling
     * its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitList_of_ports(Verilog2001Parser.List_of_portsContext ctx) {
        return super.visitList_of_ports(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
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
        //id = rValue
        int lLevel, rLevel;
        lLevel = schematic.getLevel(lValue);
        rLevel = schematic.getLevel(rValue);
        int level = ((lLevel > rLevel)? lLevel : rLevel) + 1;
        schematic.addGate(type, gateValue, level);

        schematic.connect(lValue, gateValue);
        schematic.connect(rValue, gateValue);

        gateInputs.push(gateValue);

        return t;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor adds a multiplication, division, or modulous block.
     * (currently just a Blank gate.)</p>
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
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitRange_expression(Verilog2001Parser.Range_expressionContext ctx) {
        return super.visitRange_expression(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitTopmodule_identifier(Verilog2001Parser.Topmodule_identifierContext ctx) {
        return super.visitTopmodule_identifier(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitFCASE_ITEM(Verilog2001Parser.FCASE_ITEMContext ctx) {
        return super.visitFCASE_ITEM(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitConstant_expression(Verilog2001Parser.Constant_expressionContext ctx) {
        return super.visitConstant_expression(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitStatement(Verilog2001Parser.StatementContext ctx) {
        return super.visitStatement(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitModule_instance_identifier(Verilog2001Parser.Module_instance_identifierContext ctx) {
        return super.visitModule_instance_identifier(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param exp
     */
    @Override
    public T visitOUTPUT_DECLARATION_NO_REG(Verilog2001Parser.OUTPUT_DECLARATION_NO_REGContext exp) {
        ParseTree outputIdentifiers = exp.list_of_identifiers();
        int numOfOutputs = outputIdentifiers.getChildCount();
        String id;

        if(exp.getChild(1) instanceof Verilog2001Parser.RangeContext){
            int msb = Integer.parseInt(exp.getChild(1).getChild(1).getText());
            ParseTree idList = exp.list_of_identifiers();
            for(int j = 0; j < numOfOutputs; j++) {
                if (idList.getChild(j) instanceof Verilog2001Parser.IdentifierContext){
                    for (int i = 0; i <= msb; i++) {
                        id = exp.getChild(2).getChild(j).getText();
                        schematic.addOutput(id + "[" + i + "]");
                    }
                }
            }
        } else {
            for (int i = 0; i < numOfOutputs; i++){
                if(outputIdentifiers.getChild(i) instanceof Verilog2001Parser.IdentifierContext) {
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
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitParameter_declaration_(Verilog2001Parser.Parameter_declaration_Context ctx) {
        return super.visitParameter_declaration_(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitParameter_declaration(Verilog2001Parser.Parameter_declarationContext ctx) {
        return super.visitParameter_declaration(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitModule_declaration(Verilog2001Parser.Module_declarationContext ctx) {
        return super.visitModule_declaration(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitVariable_lvalue(Verilog2001Parser.Variable_lvalueContext ctx) {
        gateInputs.push(ctx.getText());
        return super.visitVariable_lvalue(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitModule_instance(Verilog2001Parser.Module_instanceContext ctx) {
        return super.visitModule_instance(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitLsb_constant_expression(Verilog2001Parser.Lsb_constant_expressionContext ctx) {
        return super.visitLsb_constant_expression(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor adds a sign inverter. (Currently just a blank Gate.)</p>
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
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param exp
     */
    @Override
    public T visitNet_declaration(Verilog2001Parser.Net_declarationContext
                                              exp) {

        ParseTree netIdentifiers = exp.list_of_identifiers();
        int numOfNets = netIdentifiers.getChildCount();
        String id;
        
        if(exp.getChild(1) instanceof Verilog2001Parser.RangeContext){
            int msb = Integer.parseInt(exp.getChild(1).getChild(1).getText());
            ParseTree idList = exp.list_of_identifiers();
            for(int j = 0; j < numOfNets; j++) {
                if (idList.getChild(j) instanceof Verilog2001Parser.IdentifierContext){
                    for (int i = 0; i <= msb; i++) {
                        id = exp.getChild(2).getChild(j).getText();
                        schematic.addGate(GateType.WIRE, id + "[" + i + "]", 1);
                    }
                }
            }
        } else {
            for (int i = 0; i < numOfNets; i++){
                if(netIdentifiers.getChild(i) instanceof Verilog2001Parser.IdentifierContext) {
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
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitConditional_statement(Verilog2001Parser.Conditional_statementContext ctx) {
        T t = super.visitConditional_statement(ctx);

        return t;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
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
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor creates a new Question switch ( 'if' ? 'then' :
     * 'else' ). (Currently just makes a new Blank gate.)</p>
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

        return t;    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitBIT_ACCESS(Verilog2001Parser.BIT_ACCESSContext ctx) {
        return super.visitBIT_ACCESS(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitInst_name(Verilog2001Parser.Inst_nameContext ctx) {
        return super.visitInst_name(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public T visitDEFAULT(Verilog2001Parser.DEFAULTContext ctx) {
        return super.visitDEFAULT(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * <p>This visitor does not need to perform any functions besides calling
     * its children.</p>
     *
     * @param ctx
     */
    @Override
    public T visitSEQUENTIAL_ALWAYS(Verilog2001Parser.SEQUENTIAL_ALWAYSContext ctx) {
        return super.visitSEQUENTIAL_ALWAYS(ctx);
    }
}
