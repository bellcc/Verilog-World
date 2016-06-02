package edu.miamioh.SchematicRenderer;

import edu.miamioh.simulator.*;
import edu.miamioh.simulator.AntlrGen.Verilog2001Parser;

import java.util.ArrayList;

/**
 * Visits nodes on the parse tree to build the schematic.
 *
 * Created by shaffebd.
 */
public class SchematicVisitor<T> extends edu.miamioh.simulator.AntlrGen.Verilog2001BaseVisitor<T> {
    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
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
     * @param ctx
     */
    @Override
    public T visitContinuous_assign(Verilog2001Parser.Continuous_assignContext ctx) {
        return super.visitContinuous_assign(ctx);
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
    public T visitADD_SUB(Verilog2001Parser.ADD_SUBContext ctx) {
        return super.visitADD_SUB(ctx);
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
    public T visitName_of_instance(Verilog2001Parser.Name_of_instanceContext ctx) {
        return super.visitName_of_instance(ctx);
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
    public T visitNonblocking_assignment(Verilog2001Parser.Nonblocking_assignmentContext ctx) {
        return super.visitNonblocking_assignment(ctx);
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
    public T visitNumber(Verilog2001Parser.NumberContext ctx) {
        return super.visitNumber(ctx);
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
    public T visitInput_declaration(Verilog2001Parser.Input_declarationContext ctx) {
        return super.visitInput_declaration(ctx);
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
    public T visitList_of_port_connections(Verilog2001Parser.List_of_port_connectionsContext ctx) {
        return super.visitList_of_port_connections(ctx);
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
    public T visitModule_instantiation(Verilog2001Parser.Module_instantiationContext ctx) {
        return super.visitModule_instantiation(ctx);
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
    public T visitUNOT(Verilog2001Parser.UNOTContext ctx) {
        return super.visitUNOT(ctx);
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
    public T visitList_of_identifiers(Verilog2001Parser.List_of_identifiersContext ctx) {
        return super.visitList_of_identifiers(ctx);
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
     * @param ctx
     */
    @Override
    public T visitLOR(Verilog2001Parser.LORContext ctx) {
        return super.visitLOR(ctx);
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
     * @param ctx
     */
    @Override
    public T visitReg_declaration(Verilog2001Parser.Reg_declarationContext ctx) {
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
     * @param ctx
     */
    @Override
    public T visitLNOT(Verilog2001Parser.LNOTContext ctx) {
        return super.visitLNOT(ctx);
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
     * @param ctx
     */
    @Override
    public T visitBlocking_assignment(Verilog2001Parser.Blocking_assignmentContext ctx) {
        return super.visitBlocking_assignment(ctx);
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
     * @param ctx
     */
    @Override
    public T visitCOMPARES(Verilog2001Parser.COMPARESContext ctx) {
        return super.visitCOMPARES(ctx);
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
    public T visitLAND(Verilog2001Parser.LANDContext ctx) {
        return super.visitLAND(ctx);
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
        return super.visitCase_statement(ctx);
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
     * @param ctx
     */
    @Override
    public T visitBLOGIC(Verilog2001Parser.BLOGICContext ctx) {
        return super.visitBLOGIC(ctx);
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
    public T visitMULT_DIV_MOD(Verilog2001Parser.MULT_DIV_MODContext ctx) {
        return super.visitMULT_DIV_MOD(ctx);
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
     * @param ctx
     */
    @Override
    public T visitOUTPUT_DECLARATION_NO_REG(Verilog2001Parser.OUTPUT_DECLARATION_NO_REGContext ctx) {
        return super.visitOUTPUT_DECLARATION_NO_REG(ctx);
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
     * @param ctx
     */
    @Override
    public T visitUMINUS(Verilog2001Parser.UMINUSContext ctx) {
        return super.visitUMINUS(ctx);
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
    public T visitNet_declaration(Verilog2001Parser.Net_declarationContext ctx) {
        return super.visitNet_declaration(ctx);
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
        return super.visitConditional_statement(ctx);
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
        return super.visitIDENT(ctx);
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
    public T visitQUES(Verilog2001Parser.QUESContext ctx) {
        return super.visitQUES(ctx);
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
     * @param ctx
     */
    @Override
    public T visitSEQUENTIAL_ALWAYS(Verilog2001Parser.SEQUENTIAL_ALWAYSContext ctx) {
        return super.visitSEQUENTIAL_ALWAYS(ctx);
    }
}
