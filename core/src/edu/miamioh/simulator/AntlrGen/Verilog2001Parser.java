package edu.miamioh.simulator.AntlrGen;
// Generated from Verilog2001.g4 by ANTLR 4.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Verilog2001Parser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__30=1, T__29=2, T__28=3, T__27=4, T__26=5, T__25=6, T__24=7, T__23=8, 
		T__22=9, T__21=10, T__20=11, T__19=12, T__18=13, T__17=14, T__16=15, T__15=16, 
		T__14=17, T__13=18, T__12=19, T__11=20, T__10=21, T__9=22, T__8=23, T__7=24, 
		T__6=25, T__5=26, T__4=27, T__3=28, T__2=29, T__1=30, T__0=31, Simple_identifier=32, 
		White_space=33, Unsigned_number=34, Decimal_base=35, Binary_base=36, Octal_base=37, 
		Hex_base=38, Escaped_identifier=39, One_line_comment=40, Block_comment=41, 
		SUB=42, TILDE=43, MULT=44, DIV=45, MOD=46, ADD=47, BITWISE_AND=48, BITWISE_NAND=49, 
		BITWISE_OR=50, BITWISE_NOR=51, BITWISE_XOR=52, BITWISE_XNOR=53, SHIFT_LEFT=54, 
		SHIFT_RIGHT=55, GT=56, GTE=57, LT=58, LTE=59, NOTEQUAL=60, EQUAL=61, LOGICAL_AND=62, 
		LOGICAL_OR=63, LOGICAL_NOT=64;
	public static final String[] tokenNames = {
		"<INVALID>", "'default'", "'='", "'('", "'posedge'", "','", "'always'", 
		"'reg'", "'output'", "'assign'", "'endcase'", "']'", "'@'", "'module'", 
		"'@(*)'", "';'", "'if'", "'wire'", "'endmodule'", "'?'", "'parameter'", 
		"'negedge'", "'.'", "'case'", "':'", "'['", "'or'", "'else'", "'begin'", 
		"'end'", "'input'", "')'", "Simple_identifier", "White_space", "Unsigned_number", 
		"Decimal_base", "Binary_base", "Octal_base", "Hex_base", "Escaped_identifier", 
		"One_line_comment", "Block_comment", "'-'", "'~'", "'*'", "'/'", "'%'", 
		"'+'", "'&'", "'~&'", "'|'", "'~|'", "'^'", "'~^'", "'<<'", "'>>'", "'>'", 
		"'>='", "'<'", "'<='", "'!='", "'=='", "'&&'", "'||'", "'!'"
	};
	public static final int
		RULE_inst_name = 0, RULE_source_text = 1, RULE_description = 2, RULE_module_declaration = 3, 
		RULE_list_of_ports = 4, RULE_port = 5, RULE_port_reference = 6, RULE_module_item = 7, 
		RULE_port_declaration = 8, RULE_parameter_declaration = 9, RULE_parameter_declaration_ = 10, 
		RULE_list_of_param_assignments = 11, RULE_param_assignment = 12, RULE_input_declaration = 13, 
		RULE_output_declaration = 14, RULE_reg_declaration = 15, RULE_net_declaration = 16, 
		RULE_module_instantiation = 17, RULE_module_instance = 18, RULE_name_of_instance = 19, 
		RULE_list_of_port_connections = 20, RULE_continuous_assign = 21, RULE_variable_init = 22, 
		RULE_always_construct = 23, RULE_statement = 24, RULE_blocking_assignment = 25, 
		RULE_nonblocking_assignment = 26, RULE_variable_lvalue = 27, RULE_conditional_statement = 28, 
		RULE_case_statement = 29, RULE_case_item = 30, RULE_constant_expression = 31, 
		RULE_range_expression = 32, RULE_expression = 33, RULE_range = 34, RULE_msb_constant_expression = 35, 
		RULE_lsb_constant_expression = 36, RULE_instance_identifier = 37, RULE_module_identifier = 38, 
		RULE_module_instance_identifier = 39, RULE_topmodule_identifier = 40, 
		RULE_identifier_types = 41, RULE_list_of_identifiers = 42, RULE_identifier = 43, 
		RULE_number = 44, RULE_decimal_number = 45, RULE_binary_number = 46, RULE_octal_number = 47, 
		RULE_hex_number = 48;
	public static final String[] ruleNames = {
		"inst_name", "source_text", "description", "module_declaration", "list_of_ports", 
		"port", "port_reference", "module_item", "port_declaration", "parameter_declaration", 
		"parameter_declaration_", "list_of_param_assignments", "param_assignment", 
		"input_declaration", "output_declaration", "reg_declaration", "net_declaration", 
		"module_instantiation", "module_instance", "name_of_instance", "list_of_port_connections", 
		"continuous_assign", "variable_init", "always_construct", "statement", 
		"blocking_assignment", "nonblocking_assignment", "variable_lvalue", "conditional_statement", 
		"case_statement", "case_item", "constant_expression", "range_expression", 
		"expression", "range", "msb_constant_expression", "lsb_constant_expression", 
		"instance_identifier", "module_identifier", "module_instance_identifier", 
		"topmodule_identifier", "identifier_types", "list_of_identifiers", "identifier", 
		"number", "decimal_number", "binary_number", "octal_number", "hex_number"
	};

	@Override
	public String getGrammarFileName() { return "Verilog2001.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Verilog2001Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Inst_nameContext extends ParserRuleContext {
		public Topmodule_identifierContext topmodule_identifier() {
			return getRuleContext(Topmodule_identifierContext.class,0);
		}
		public List<Instance_identifierContext> instance_identifier() {
			return getRuleContexts(Instance_identifierContext.class);
		}
		public Instance_identifierContext instance_identifier(int i) {
			return getRuleContext(Instance_identifierContext.class,i);
		}
		public Inst_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inst_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterInst_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitInst_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitInst_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inst_nameContext inst_name() throws RecognitionException {
		Inst_nameContext _localctx = new Inst_nameContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_inst_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); topmodule_identifier();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==22) {
				{
				{
				setState(99); match(22);
				setState(100); instance_identifier();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Source_textContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(Verilog2001Parser.EOF, 0); }
		public DescriptionContext description(int i) {
			return getRuleContext(DescriptionContext.class,i);
		}
		public List<DescriptionContext> description() {
			return getRuleContexts(DescriptionContext.class);
		}
		public Source_textContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterSource_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitSource_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitSource_text(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Source_textContext source_text() throws RecognitionException {
		Source_textContext _localctx = new Source_textContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_source_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==13) {
				{
				{
				setState(106); description();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DescriptionContext extends ParserRuleContext {
		public Module_declarationContext module_declaration() {
			return getRuleContext(Module_declarationContext.class,0);
		}
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); module_declaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Module_declarationContext extends ParserRuleContext {
		public Token MODULE_DECL;
		public List_of_portsContext list_of_ports() {
			return getRuleContext(List_of_portsContext.class,0);
		}
		public List<Module_itemContext> module_item() {
			return getRuleContexts(Module_itemContext.class);
		}
		public Module_itemContext module_item(int i) {
			return getRuleContext(Module_itemContext.class,i);
		}
		public Module_identifierContext module_identifier() {
			return getRuleContext(Module_identifierContext.class,0);
		}
		public Module_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterModule_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitModule_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitModule_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_declarationContext module_declaration() throws RecognitionException {
		Module_declarationContext _localctx = new Module_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_module_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); ((Module_declarationContext)_localctx).MODULE_DECL = match(13);
			setState(117); module_identifier();
			setState(119);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(118); list_of_ports();
				}
			}

			setState(121); match(15);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 8) | (1L << 9) | (1L << 17) | (1L << 20) | (1L << 30) | (1L << Simple_identifier) | (1L << Escaped_identifier))) != 0)) {
				{
				{
				setState(122); module_item();
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128); match(18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_of_portsContext extends ParserRuleContext {
		public List<PortContext> port() {
			return getRuleContexts(PortContext.class);
		}
		public PortContext port(int i) {
			return getRuleContext(PortContext.class,i);
		}
		public List_of_portsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_of_ports; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterList_of_ports(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitList_of_ports(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitList_of_ports(this);
			else return visitor.visitChildren(this);
		}
	}

	public final List_of_portsContext list_of_ports() throws RecognitionException {
		List_of_portsContext _localctx = new List_of_portsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_list_of_ports);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(3);
			setState(131); port();
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(132); match(5);
				setState(133); port();
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139); match(31);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PortContext extends ParserRuleContext {
		public Port_referenceContext port_reference() {
			return getRuleContext(Port_referenceContext.class,0);
		}
		public PortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_port; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterPort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitPort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitPort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PortContext port() throws RecognitionException {
		PortContext _localctx = new PortContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_port);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_la = _input.LA(1);
			if (_la==Simple_identifier || _la==Escaped_identifier) {
				{
				setState(141); port_reference();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Port_referenceContext extends ParserRuleContext {
		public Constant_expressionContext constant_expression() {
			return getRuleContext(Constant_expressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Range_expressionContext range_expression() {
			return getRuleContext(Range_expressionContext.class,0);
		}
		public Port_referenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_port_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterPort_reference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitPort_reference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitPort_reference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Port_referenceContext port_reference() throws RecognitionException {
		Port_referenceContext _localctx = new Port_referenceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_port_reference);
		try {
			setState(155);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144); identifier();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145); identifier();
				setState(146); match(25);
				setState(147); constant_expression();
				setState(148); match(11);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(150); identifier();
				setState(151); match(25);
				setState(152); range_expression();
				setState(153); match(11);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Module_itemContext extends ParserRuleContext {
		public Port_declarationContext port_declaration() {
			return getRuleContext(Port_declarationContext.class,0);
		}
		public Continuous_assignContext continuous_assign() {
			return getRuleContext(Continuous_assignContext.class,0);
		}
		public Parameter_declarationContext parameter_declaration() {
			return getRuleContext(Parameter_declarationContext.class,0);
		}
		public Net_declarationContext net_declaration() {
			return getRuleContext(Net_declarationContext.class,0);
		}
		public Reg_declarationContext reg_declaration() {
			return getRuleContext(Reg_declarationContext.class,0);
		}
		public Always_constructContext always_construct() {
			return getRuleContext(Always_constructContext.class,0);
		}
		public Module_instantiationContext module_instantiation() {
			return getRuleContext(Module_instantiationContext.class,0);
		}
		public Module_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterModule_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitModule_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitModule_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_itemContext module_item() throws RecognitionException {
		Module_itemContext _localctx = new Module_itemContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_module_item);
		try {
			setState(166);
			switch (_input.LA(1)) {
			case 8:
			case 30:
				enterOuterAlt(_localctx, 1);
				{
				setState(157); port_declaration();
				setState(158); match(15);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 2);
				{
				setState(160); net_declaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 3);
				{
				setState(161); reg_declaration();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 4);
				{
				setState(162); parameter_declaration();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 5);
				{
				setState(163); continuous_assign();
				}
				break;
			case Simple_identifier:
			case Escaped_identifier:
				enterOuterAlt(_localctx, 6);
				{
				setState(164); module_instantiation();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 7);
				{
				setState(165); always_construct();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Port_declarationContext extends ParserRuleContext {
		public Input_declarationContext input_declaration() {
			return getRuleContext(Input_declarationContext.class,0);
		}
		public Output_declarationContext output_declaration() {
			return getRuleContext(Output_declarationContext.class,0);
		}
		public Port_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_port_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterPort_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitPort_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitPort_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Port_declarationContext port_declaration() throws RecognitionException {
		Port_declarationContext _localctx = new Port_declarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_port_declaration);
		try {
			setState(170);
			switch (_input.LA(1)) {
			case 30:
				enterOuterAlt(_localctx, 1);
				{
				setState(168); input_declaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 2);
				{
				setState(169); output_declaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_declarationContext extends ParserRuleContext {
		public Parameter_declaration_Context parameter_declaration_() {
			return getRuleContext(Parameter_declaration_Context.class,0);
		}
		public Parameter_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterParameter_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitParameter_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitParameter_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_declarationContext parameter_declaration() throws RecognitionException {
		Parameter_declarationContext _localctx = new Parameter_declarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameter_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172); parameter_declaration_();
			setState(173); match(15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_declaration_Context extends ParserRuleContext {
		public List_of_param_assignmentsContext list_of_param_assignments() {
			return getRuleContext(List_of_param_assignmentsContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public Parameter_declaration_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_declaration_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterParameter_declaration_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitParameter_declaration_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitParameter_declaration_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_declaration_Context parameter_declaration_() throws RecognitionException {
		Parameter_declaration_Context _localctx = new Parameter_declaration_Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_parameter_declaration_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); match(20);
			setState(177);
			_la = _input.LA(1);
			if (_la==25) {
				{
				setState(176); range();
				}
			}

			setState(179); list_of_param_assignments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_of_param_assignmentsContext extends ParserRuleContext {
		public List<Param_assignmentContext> param_assignment() {
			return getRuleContexts(Param_assignmentContext.class);
		}
		public Param_assignmentContext param_assignment(int i) {
			return getRuleContext(Param_assignmentContext.class,i);
		}
		public List_of_param_assignmentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_of_param_assignments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterList_of_param_assignments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitList_of_param_assignments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitList_of_param_assignments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final List_of_param_assignmentsContext list_of_param_assignments() throws RecognitionException {
		List_of_param_assignmentsContext _localctx = new List_of_param_assignmentsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_list_of_param_assignments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); param_assignment();
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(182); match(5);
				setState(183); param_assignment();
				}
				}
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_assignmentContext extends ParserRuleContext {
		public Constant_expressionContext constant_expression() {
			return getRuleContext(Constant_expressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Param_assignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterParam_assignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitParam_assignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitParam_assignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_assignmentContext param_assignment() throws RecognitionException {
		Param_assignmentContext _localctx = new Param_assignmentContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_param_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189); identifier();
			setState(190); match(2);
			setState(191); constant_expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Input_declarationContext extends ParserRuleContext {
		public List_of_identifiersContext list_of_identifiers() {
			return getRuleContext(List_of_identifiersContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public Input_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterInput_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitInput_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitInput_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Input_declarationContext input_declaration() throws RecognitionException {
		Input_declarationContext _localctx = new Input_declarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_input_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); match(30);
			setState(195);
			_la = _input.LA(1);
			if (_la==25) {
				{
				setState(194); range();
				}
			}

			setState(197); list_of_identifiers();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Output_declarationContext extends ParserRuleContext {
		public Output_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_output_declaration; }
	 
		public Output_declarationContext() { }
		public void copyFrom(Output_declarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OUTPUT_DECLARATION_NO_REGContext extends Output_declarationContext {
		public List_of_identifiersContext list_of_identifiers() {
			return getRuleContext(List_of_identifiersContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public OUTPUT_DECLARATION_NO_REGContext(Output_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterOUTPUT_DECLARATION_NO_REG(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitOUTPUT_DECLARATION_NO_REG(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitOUTPUT_DECLARATION_NO_REG(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OUTPUT_DECLARATION_REGContext extends Output_declarationContext {
		public List_of_identifiersContext list_of_identifiers() {
			return getRuleContext(List_of_identifiersContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public OUTPUT_DECLARATION_REGContext(Output_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterOUTPUT_DECLARATION_REG(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitOUTPUT_DECLARATION_REG(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitOUTPUT_DECLARATION_REG(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Output_declarationContext output_declaration() throws RecognitionException {
		Output_declarationContext _localctx = new Output_declarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_output_declaration);
		int _la;
		try {
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new OUTPUT_DECLARATION_NO_REGContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(199); match(8);
				setState(201);
				_la = _input.LA(1);
				if (_la==25) {
					{
					setState(200); range();
					}
				}

				setState(203); list_of_identifiers();
				}
				break;

			case 2:
				_localctx = new OUTPUT_DECLARATION_REGContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(204); match(8);
				setState(205); match(7);
				setState(207);
				_la = _input.LA(1);
				if (_la==25) {
					{
					setState(206); range();
					}
				}

				setState(209); list_of_identifiers();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reg_declarationContext extends ParserRuleContext {
		public List_of_identifiersContext list_of_identifiers() {
			return getRuleContext(List_of_identifiersContext.class,0);
		}
		public Variable_initContext variable_init() {
			return getRuleContext(Variable_initContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public Reg_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterReg_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitReg_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitReg_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Reg_declarationContext reg_declaration() throws RecognitionException {
		Reg_declarationContext _localctx = new Reg_declarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_reg_declaration);
		int _la;
		try {
			setState(223);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); match(7);
				setState(214);
				_la = _input.LA(1);
				if (_la==25) {
					{
					setState(213); range();
					}
				}

				setState(216); list_of_identifiers();
				setState(217); match(15);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219); match(7);
				setState(220); variable_init();
				setState(221); match(15);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Net_declarationContext extends ParserRuleContext {
		public List_of_identifiersContext list_of_identifiers() {
			return getRuleContext(List_of_identifiersContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public Net_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_net_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterNet_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitNet_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitNet_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Net_declarationContext net_declaration() throws RecognitionException {
		Net_declarationContext _localctx = new Net_declarationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_net_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225); match(17);
			setState(227);
			_la = _input.LA(1);
			if (_la==25) {
				{
				setState(226); range();
				}
			}

			setState(229); list_of_identifiers();
			setState(230); match(15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Module_instantiationContext extends ParserRuleContext {
		public List<Module_instanceContext> module_instance() {
			return getRuleContexts(Module_instanceContext.class);
		}
		public Module_identifierContext module_identifier() {
			return getRuleContext(Module_identifierContext.class,0);
		}
		public Module_instanceContext module_instance(int i) {
			return getRuleContext(Module_instanceContext.class,i);
		}
		public Module_instantiationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_instantiation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterModule_instantiation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitModule_instantiation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitModule_instantiation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_instantiationContext module_instantiation() throws RecognitionException {
		Module_instantiationContext _localctx = new Module_instantiationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_module_instantiation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); module_identifier();
			setState(233); module_instance();
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(234); match(5);
				setState(235); module_instance();
				}
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(241); match(15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Module_instanceContext extends ParserRuleContext {
		public List_of_port_connectionsContext list_of_port_connections() {
			return getRuleContext(List_of_port_connectionsContext.class,0);
		}
		public Name_of_instanceContext name_of_instance() {
			return getRuleContext(Name_of_instanceContext.class,0);
		}
		public Module_instanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_instance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterModule_instance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitModule_instance(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitModule_instance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_instanceContext module_instance() throws RecognitionException {
		Module_instanceContext _localctx = new Module_instanceContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_module_instance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243); name_of_instance();
			setState(244); match(3);
			setState(245); list_of_port_connections();
			setState(246); match(31);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Name_of_instanceContext extends ParserRuleContext {
		public Module_instance_identifierContext module_instance_identifier() {
			return getRuleContext(Module_instance_identifierContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public Name_of_instanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name_of_instance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterName_of_instance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitName_of_instance(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitName_of_instance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Name_of_instanceContext name_of_instance() throws RecognitionException {
		Name_of_instanceContext _localctx = new Name_of_instanceContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_name_of_instance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248); module_instance_identifier();
			setState(250);
			_la = _input.LA(1);
			if (_la==25) {
				{
				setState(249); range();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_of_port_connectionsContext extends ParserRuleContext {
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public List_of_port_connectionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_of_port_connections; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterList_of_port_connections(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitList_of_port_connections(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitList_of_port_connections(this);
			else return visitor.visitChildren(this);
		}
	}

	public final List_of_port_connectionsContext list_of_port_connections() throws RecognitionException {
		List_of_port_connectionsContext _localctx = new List_of_port_connectionsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_list_of_port_connections);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); identifier();
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(253); match(5);
				setState(254); identifier();
				}
				}
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Continuous_assignContext extends ParserRuleContext {
		public Variable_lvalueContext variable_lvalue() {
			return getRuleContext(Variable_lvalueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Continuous_assignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continuous_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterContinuous_assign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitContinuous_assign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitContinuous_assign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Continuous_assignContext continuous_assign() throws RecognitionException {
		Continuous_assignContext _localctx = new Continuous_assignContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_continuous_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260); match(9);
			setState(261); variable_lvalue();
			setState(262); match(2);
			setState(263); expression(0);
			setState(264); match(15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_initContext extends ParserRuleContext {
		public Variable_lvalueContext variable_lvalue() {
			return getRuleContext(Variable_lvalueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public Variable_initContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterVariable_init(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitVariable_init(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitVariable_init(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_initContext variable_init() throws RecognitionException {
		Variable_initContext _localctx = new Variable_initContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_variable_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			_la = _input.LA(1);
			if (_la==25) {
				{
				setState(266); range();
				}
			}

			setState(269); variable_lvalue();
			setState(270); match(2);
			setState(271); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Always_constructContext extends ParserRuleContext {
		public Always_constructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_always_construct; }
	 
		public Always_constructContext() { }
		public void copyFrom(Always_constructContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class COMBONATIONAL_ALWAYSContext extends Always_constructContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public COMBONATIONAL_ALWAYSContext(Always_constructContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterCOMBONATIONAL_ALWAYS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitCOMBONATIONAL_ALWAYS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitCOMBONATIONAL_ALWAYS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SEQUENTIAL_ALWAYSContext extends Always_constructContext {
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public SEQUENTIAL_ALWAYSContext(Always_constructContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterSEQUENTIAL_ALWAYS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitSEQUENTIAL_ALWAYS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitSEQUENTIAL_ALWAYS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Always_constructContext always_construct() throws RecognitionException {
		Always_constructContext _localctx = new Always_constructContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_always_construct);
		try {
			setState(287);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new COMBONATIONAL_ALWAYSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(273); match(6);
				setState(274); match(14);
				setState(275); statement();
				}
				break;

			case 2:
				_localctx = new SEQUENTIAL_ALWAYSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(276); match(6);
				setState(277); match(12);
				setState(278); match(3);
				setState(279); match(4);
				setState(280); identifier();
				setState(281); match(26);
				setState(282); match(21);
				setState(283); identifier();
				setState(284); match(31);
				setState(285); statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Blocking_assignmentContext blocking_assignment() {
			return getRuleContext(Blocking_assignmentContext.class,0);
		}
		public Case_statementContext case_statement() {
			return getRuleContext(Case_statementContext.class,0);
		}
		public Conditional_statementContext conditional_statement() {
			return getRuleContext(Conditional_statementContext.class,0);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public Nonblocking_assignmentContext nonblocking_assignment() {
			return getRuleContext(Nonblocking_assignmentContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_statement);
		int _la;
		try {
			setState(306);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(289); match(28);
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 15) | (1L << 16) | (1L << 23) | (1L << 28) | (1L << Simple_identifier) | (1L << Escaped_identifier))) != 0)) {
					{
					{
					setState(290); statement();
					}
					}
					setState(295);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(296); match(29);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(297); blocking_assignment();
				setState(298); match(15);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(300); case_statement();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(301); conditional_statement();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(302); nonblocking_assignment();
				setState(303); match(15);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(305); match(15);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Blocking_assignmentContext extends ParserRuleContext {
		public Variable_lvalueContext variable_lvalue() {
			return getRuleContext(Variable_lvalueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Blocking_assignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blocking_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterBlocking_assignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitBlocking_assignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitBlocking_assignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Blocking_assignmentContext blocking_assignment() throws RecognitionException {
		Blocking_assignmentContext _localctx = new Blocking_assignmentContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_blocking_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308); variable_lvalue();
			setState(309); match(2);
			setState(310); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Nonblocking_assignmentContext extends ParserRuleContext {
		public Variable_lvalueContext variable_lvalue() {
			return getRuleContext(Variable_lvalueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Nonblocking_assignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonblocking_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterNonblocking_assignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitNonblocking_assignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitNonblocking_assignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Nonblocking_assignmentContext nonblocking_assignment() throws RecognitionException {
		Nonblocking_assignmentContext _localctx = new Nonblocking_assignmentContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_nonblocking_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312); variable_lvalue();
			setState(313); match(LTE);
			setState(314); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_lvalueContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Range_expressionContext range_expression() {
			return getRuleContext(Range_expressionContext.class,0);
		}
		public Variable_lvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_lvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterVariable_lvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitVariable_lvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitVariable_lvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_lvalueContext variable_lvalue() throws RecognitionException {
		Variable_lvalueContext _localctx = new Variable_lvalueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_variable_lvalue);
		try {
			setState(322);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(316); identifier();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(317); identifier();
				setState(318); match(25);
				setState(319); range_expression();
				setState(320); match(11);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Conditional_statementContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Conditional_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterConditional_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitConditional_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitConditional_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conditional_statementContext conditional_statement() throws RecognitionException {
		Conditional_statementContext _localctx = new Conditional_statementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_conditional_statement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(324); match(16);
			setState(325); match(3);
			setState(326); expression(0);
			setState(327); match(31);
			setState(328); statement();
			setState(338);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(329); match(27);
					setState(330); match(16);
					setState(331); match(3);
					setState(332); expression(0);
					setState(333); match(31);
					setState(334); statement();
					}
					} 
				}
				setState(340);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			setState(343);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(341); match(27);
				setState(342); statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Case_statementContext extends ParserRuleContext {
		public Case_itemContext case_item(int i) {
			return getRuleContext(Case_itemContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<Case_itemContext> case_item() {
			return getRuleContexts(Case_itemContext.class);
		}
		public Case_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterCase_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitCase_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitCase_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Case_statementContext case_statement() throws RecognitionException {
		Case_statementContext _localctx = new Case_statementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_case_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); match(23);
			setState(346); match(3);
			setState(347); expression(0);
			setState(348); match(31);
			setState(349); case_item();
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 3) | (1L << Simple_identifier) | (1L << Unsigned_number) | (1L << Decimal_base) | (1L << Binary_base) | (1L << Octal_base) | (1L << Hex_base) | (1L << Escaped_identifier) | (1L << SUB) | (1L << TILDE))) != 0)) {
				{
				{
				setState(350); case_item();
				}
				}
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(356); match(10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Case_itemContext extends ParserRuleContext {
		public Case_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case_item; }
	 
		public Case_itemContext() { }
		public void copyFrom(Case_itemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FCASE_ITEMContext extends Case_itemContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FCASE_ITEMContext(Case_itemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterFCASE_ITEM(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitFCASE_ITEM(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitFCASE_ITEM(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DEFAULTContext extends Case_itemContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public DEFAULTContext(Case_itemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterDEFAULT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitDEFAULT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitDEFAULT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Case_itemContext case_item() throws RecognitionException {
		Case_itemContext _localctx = new Case_itemContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_case_item);
		try {
			setState(365);
			switch (_input.LA(1)) {
			case 3:
			case Simple_identifier:
			case Unsigned_number:
			case Decimal_base:
			case Binary_base:
			case Octal_base:
			case Hex_base:
			case Escaped_identifier:
			case SUB:
			case TILDE:
				_localctx = new FCASE_ITEMContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(358); expression(0);
				setState(359); match(24);
				setState(360); statement();
				}
				break;
			case 1:
				_localctx = new DEFAULTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(362); match(1);
				setState(363); match(24);
				setState(364); statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constant_expressionContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Constant_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterConstant_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitConstant_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitConstant_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constant_expressionContext constant_expression() throws RecognitionException {
		Constant_expressionContext _localctx = new Constant_expressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_constant_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367); number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Range_expressionContext extends ParserRuleContext {
		public Decimal_numberContext decimal_number() {
			return getRuleContext(Decimal_numberContext.class,0);
		}
		public Lsb_constant_expressionContext lsb_constant_expression() {
			return getRuleContext(Lsb_constant_expressionContext.class,0);
		}
		public Msb_constant_expressionContext msb_constant_expression() {
			return getRuleContext(Msb_constant_expressionContext.class,0);
		}
		public Range_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterRange_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitRange_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitRange_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Range_expressionContext range_expression() throws RecognitionException {
		Range_expressionContext _localctx = new Range_expressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_range_expression);
		try {
			setState(374);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(369); decimal_number();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(370); msb_constant_expression();
				setState(371); match(24);
				setState(372); lsb_constant_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class INUMBERContext extends ExpressionContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public INUMBERContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterINUMBER(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitINUMBER(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitINUMBER(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BRACKETSContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BRACKETSContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterBRACKETS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitBRACKETS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitBRACKETS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LNOTContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public LNOTContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterLNOT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitLNOT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitLNOT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class COMPARESContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public COMPARESContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterCOMPARES(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitCOMPARES(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitCOMPARES(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LANDContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public LANDContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterLAND(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitLAND(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitLAND(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ADD_SUBContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ADD_SUBContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterADD_SUB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitADD_SUB(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitADD_SUB(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LORContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public LORContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterLOR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitLOR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitLOR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BLOGICContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public BLOGICContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterBLOGIC(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitBLOGIC(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitBLOGIC(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UMINUSContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UMINUSContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterUMINUS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitUMINUS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitUMINUS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IDENTContext extends ExpressionContext {
		public Identifier_typesContext identifier_types() {
			return getRuleContext(Identifier_typesContext.class,0);
		}
		public IDENTContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterIDENT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitIDENT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitIDENT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MULT_DIV_MODContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public MULT_DIV_MODContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterMULT_DIV_MOD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitMULT_DIV_MOD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitMULT_DIV_MOD(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class QUESContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public QUESContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterQUES(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitQUES(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitQUES(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UNOTContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UNOTContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterUNOT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitUNOT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitUNOT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 66;
		enterRecursionRule(_localctx, 66, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			switch (_input.LA(1)) {
			case SUB:
				{
				_localctx = new UMINUSContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(377); match(SUB);
				setState(378); expression(13);
				}
				break;
			case TILDE:
				{
				_localctx = new UNOTContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(379); match(TILDE);
				setState(380); expression(12);
				}
				break;
			case 3:
				{
				_localctx = new BRACKETSContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(381); match(3);
				setState(382); expression(0);
				setState(383); match(31);
				}
				break;
			case Simple_identifier:
			case Escaped_identifier:
				{
				_localctx = new IDENTContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(385); identifier_types();
				}
				break;
			case Unsigned_number:
			case Decimal_base:
			case Binary_base:
			case Octal_base:
			case Hex_base:
				{
				_localctx = new INUMBERContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(386); number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(418);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(416);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						_localctx = new MULT_DIV_MODContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(389);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(390);
						((MULT_DIV_MODContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((MULT_DIV_MODContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(391); expression(12);
						}
						break;

					case 2:
						{
						_localctx = new ADD_SUBContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(392);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(393);
						((ADD_SUBContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SUB || _la==ADD) ) {
							((ADD_SUBContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(394); expression(11);
						}
						break;

					case 3:
						{
						_localctx = new BLOGICContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(395);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(396);
						((BLOGICContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BITWISE_AND) | (1L << BITWISE_NAND) | (1L << BITWISE_OR) | (1L << BITWISE_NOR) | (1L << BITWISE_XOR) | (1L << BITWISE_XNOR) | (1L << SHIFT_LEFT) | (1L << SHIFT_RIGHT))) != 0)) ) {
							((BLOGICContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(397); expression(10);
						}
						break;

					case 4:
						{
						_localctx = new COMPARESContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(398);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(399);
						((COMPARESContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << GTE) | (1L << LT) | (1L << LTE) | (1L << NOTEQUAL) | (1L << EQUAL))) != 0)) ) {
							((COMPARESContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(400); expression(9);
						}
						break;

					case 5:
						{
						_localctx = new LNOTContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(401);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(402); match(LOGICAL_NOT);
						setState(403); expression(8);
						}
						break;

					case 6:
						{
						_localctx = new LANDContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(404);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(405); match(LOGICAL_AND);
						setState(406); expression(7);
						}
						break;

					case 7:
						{
						_localctx = new LORContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(407);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(408); match(LOGICAL_OR);
						setState(409); expression(6);
						}
						break;

					case 8:
						{
						_localctx = new QUESContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(410);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(411); match(19);
						setState(412); expression(0);
						setState(413); match(24);
						setState(414); expression(5);
						}
						break;
					}
					} 
				}
				setState(420);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RangeContext extends ParserRuleContext {
		public Lsb_constant_expressionContext lsb_constant_expression() {
			return getRuleContext(Lsb_constant_expressionContext.class,0);
		}
		public Msb_constant_expressionContext msb_constant_expression() {
			return getRuleContext(Msb_constant_expressionContext.class,0);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421); match(25);
			setState(422); msb_constant_expression();
			setState(423); match(24);
			setState(424); lsb_constant_expression();
			setState(425); match(11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Msb_constant_expressionContext extends ParserRuleContext {
		public Decimal_numberContext decimal_number() {
			return getRuleContext(Decimal_numberContext.class,0);
		}
		public Msb_constant_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_msb_constant_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterMsb_constant_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitMsb_constant_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitMsb_constant_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Msb_constant_expressionContext msb_constant_expression() throws RecognitionException {
		Msb_constant_expressionContext _localctx = new Msb_constant_expressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_msb_constant_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427); decimal_number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Lsb_constant_expressionContext extends ParserRuleContext {
		public Decimal_numberContext decimal_number() {
			return getRuleContext(Decimal_numberContext.class,0);
		}
		public Lsb_constant_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lsb_constant_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterLsb_constant_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitLsb_constant_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitLsb_constant_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lsb_constant_expressionContext lsb_constant_expression() throws RecognitionException {
		Lsb_constant_expressionContext _localctx = new Lsb_constant_expressionContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_lsb_constant_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429); decimal_number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Instance_identifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Instance_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instance_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterInstance_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitInstance_identifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitInstance_identifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instance_identifierContext instance_identifier() throws RecognitionException {
		Instance_identifierContext _localctx = new Instance_identifierContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_instance_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431); identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Module_identifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Module_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterModule_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitModule_identifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitModule_identifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_identifierContext module_identifier() throws RecognitionException {
		Module_identifierContext _localctx = new Module_identifierContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_module_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433); identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Module_instance_identifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Module_instance_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_instance_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterModule_instance_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitModule_instance_identifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitModule_instance_identifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_instance_identifierContext module_instance_identifier() throws RecognitionException {
		Module_instance_identifierContext _localctx = new Module_instance_identifierContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_module_instance_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435); identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Topmodule_identifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Topmodule_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topmodule_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterTopmodule_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitTopmodule_identifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitTopmodule_identifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Topmodule_identifierContext topmodule_identifier() throws RecognitionException {
		Topmodule_identifierContext _localctx = new Topmodule_identifierContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_topmodule_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437); identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Identifier_typesContext extends ParserRuleContext {
		public Identifier_typesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_types; }
	 
		public Identifier_typesContext() { }
		public void copyFrom(Identifier_typesContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BIT_ACCESSContext extends Identifier_typesContext {
		public Constant_expressionContext constant_expression() {
			return getRuleContext(Constant_expressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public BIT_ACCESSContext(Identifier_typesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterBIT_ACCESS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitBIT_ACCESS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitBIT_ACCESS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GENERALContext extends Identifier_typesContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public GENERALContext(Identifier_typesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterGENERAL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitGENERAL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitGENERAL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Identifier_typesContext identifier_types() throws RecognitionException {
		Identifier_typesContext _localctx = new Identifier_typesContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_identifier_types);
		try {
			setState(445);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				_localctx = new BIT_ACCESSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(439); identifier();
				setState(440); match(25);
				setState(441); constant_expression();
				setState(442); match(11);
				}
				break;

			case 2:
				_localctx = new GENERALContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(444); identifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_of_identifiersContext extends ParserRuleContext {
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public List_of_identifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_of_identifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterList_of_identifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitList_of_identifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitList_of_identifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final List_of_identifiersContext list_of_identifiers() throws RecognitionException {
		List_of_identifiersContext _localctx = new List_of_identifiersContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_list_of_identifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447); identifier();
			setState(452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(448); match(5);
				setState(449); identifier();
				}
				}
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode Simple_identifier() { return getToken(Verilog2001Parser.Simple_identifier, 0); }
		public TerminalNode Escaped_identifier() { return getToken(Verilog2001Parser.Escaped_identifier, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			_la = _input.LA(1);
			if ( !(_la==Simple_identifier || _la==Escaped_identifier) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public Hex_numberContext hex_number() {
			return getRuleContext(Hex_numberContext.class,0);
		}
		public Binary_numberContext binary_number() {
			return getRuleContext(Binary_numberContext.class,0);
		}
		public Decimal_numberContext decimal_number() {
			return getRuleContext(Decimal_numberContext.class,0);
		}
		public Octal_numberContext octal_number() {
			return getRuleContext(Octal_numberContext.class,0);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_number);
		try {
			setState(461);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(457); decimal_number();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(458); octal_number();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(459); binary_number();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(460); hex_number();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decimal_numberContext extends ParserRuleContext {
		public TerminalNode Decimal_base() { return getToken(Verilog2001Parser.Decimal_base, 0); }
		public TerminalNode Unsigned_number(int i) {
			return getToken(Verilog2001Parser.Unsigned_number, i);
		}
		public List<TerminalNode> Unsigned_number() { return getTokens(Verilog2001Parser.Unsigned_number); }
		public Decimal_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterDecimal_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitDecimal_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitDecimal_number(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal_numberContext decimal_number() throws RecognitionException {
		Decimal_numberContext _localctx = new Decimal_numberContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_decimal_number);
		int _la;
		try {
			setState(469);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(463); match(Unsigned_number);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(465);
				_la = _input.LA(1);
				if (_la==Unsigned_number) {
					{
					setState(464); match(Unsigned_number);
					}
				}

				setState(467); match(Decimal_base);
				setState(468); match(Unsigned_number);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_numberContext extends ParserRuleContext {
		public TerminalNode Binary_base() { return getToken(Verilog2001Parser.Binary_base, 0); }
		public TerminalNode Unsigned_number(int i) {
			return getToken(Verilog2001Parser.Unsigned_number, i);
		}
		public List<TerminalNode> Unsigned_number() { return getTokens(Verilog2001Parser.Unsigned_number); }
		public Binary_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterBinary_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitBinary_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitBinary_number(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_numberContext binary_number() throws RecognitionException {
		Binary_numberContext _localctx = new Binary_numberContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_binary_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			_la = _input.LA(1);
			if (_la==Unsigned_number) {
				{
				setState(471); match(Unsigned_number);
				}
			}

			setState(474); match(Binary_base);
			setState(475); match(Unsigned_number);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Octal_numberContext extends ParserRuleContext {
		public TerminalNode Octal_base() { return getToken(Verilog2001Parser.Octal_base, 0); }
		public TerminalNode Unsigned_number(int i) {
			return getToken(Verilog2001Parser.Unsigned_number, i);
		}
		public List<TerminalNode> Unsigned_number() { return getTokens(Verilog2001Parser.Unsigned_number); }
		public Octal_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octal_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterOctal_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitOctal_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitOctal_number(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Octal_numberContext octal_number() throws RecognitionException {
		Octal_numberContext _localctx = new Octal_numberContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_octal_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
			_la = _input.LA(1);
			if (_la==Unsigned_number) {
				{
				setState(477); match(Unsigned_number);
				}
			}

			setState(480); match(Octal_base);
			setState(481); match(Unsigned_number);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Hex_numberContext extends ParserRuleContext {
		public TerminalNode Unsigned_number(int i) {
			return getToken(Verilog2001Parser.Unsigned_number, i);
		}
		public TerminalNode Hex_base() { return getToken(Verilog2001Parser.Hex_base, 0); }
		public List<TerminalNode> Unsigned_number() { return getTokens(Verilog2001Parser.Unsigned_number); }
		public Hex_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hex_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).enterHex_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Verilog2001Listener ) ((Verilog2001Listener)listener).exitHex_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Verilog2001Visitor ) return ((Verilog2001Visitor<? extends T>)visitor).visitHex_number(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Hex_numberContext hex_number() throws RecognitionException {
		Hex_numberContext _localctx = new Hex_numberContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_hex_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484);
			_la = _input.LA(1);
			if (_la==Unsigned_number) {
				{
				setState(483); match(Unsigned_number);
				}
			}

			setState(486); match(Hex_base);
			setState(487); match(Unsigned_number);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 33: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 11);

		case 1: return precpred(_ctx, 10);

		case 2: return precpred(_ctx, 9);

		case 3: return precpred(_ctx, 8);

		case 4: return precpred(_ctx, 7);

		case 5: return precpred(_ctx, 6);

		case 6: return precpred(_ctx, 5);

		case 7: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3B\u01ec\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\2\7\2h"+
		"\n\2\f\2\16\2k\13\2\3\3\7\3n\n\3\f\3\16\3q\13\3\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\5\5\5z\n\5\3\5\3\5\7\5~\n\5\f\5\16\5\u0081\13\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\7\6\u0089\n\6\f\6\16\6\u008c\13\6\3\6\3\6\3\7\5\7\u0091\n\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u009e\n\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u00a9\n\t\3\n\3\n\5\n\u00ad\n\n\3\13\3\13\3\13"+
		"\3\f\3\f\5\f\u00b4\n\f\3\f\3\f\3\r\3\r\3\r\7\r\u00bb\n\r\f\r\16\r\u00be"+
		"\13\r\3\16\3\16\3\16\3\16\3\17\3\17\5\17\u00c6\n\17\3\17\3\17\3\20\3\20"+
		"\5\20\u00cc\n\20\3\20\3\20\3\20\3\20\5\20\u00d2\n\20\3\20\5\20\u00d5\n"+
		"\20\3\21\3\21\5\21\u00d9\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u00e2\n\21\3\22\3\22\5\22\u00e6\n\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\7\23\u00ef\n\23\f\23\16\23\u00f2\13\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\5\25\u00fd\n\25\3\26\3\26\3\26\7\26\u0102\n\26\f\26"+
		"\16\26\u0105\13\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\5\30\u010e\n\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\5\31\u0122\n\31\3\32\3\32\7\32\u0126\n\32\f\32\16"+
		"\32\u0129\13\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32"+
		"\u0135\n\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\5\35\u0145\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\7\36\u0153\n\36\f\36\16\36\u0156\13\36\3\36\3\36\5\36"+
		"\u015a\n\36\3\37\3\37\3\37\3\37\3\37\3\37\7\37\u0162\n\37\f\37\16\37\u0165"+
		"\13\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \5 \u0170\n \3!\3!\3\"\3\"\3\"\3"+
		"\"\3\"\5\"\u0179\n\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0186\n#\3#\3"+
		"#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\7#\u01a3\n#\f#\16#\u01a6\13#\3$\3$\3$\3$\3$\3$\3%\3%\3&\3&\3\'"+
		"\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3+\3+\3+\3+\5+\u01c0\n+\3,\3,\3,\7,\u01c5"+
		"\n,\f,\16,\u01c8\13,\3-\3-\3.\3.\3.\3.\5.\u01d0\n.\3/\3/\5/\u01d4\n/\3"+
		"/\3/\5/\u01d8\n/\3\60\5\60\u01db\n\60\3\60\3\60\3\60\3\61\5\61\u01e1\n"+
		"\61\3\61\3\61\3\61\3\62\5\62\u01e7\n\62\3\62\3\62\3\62\3\62\2\3D\63\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL"+
		"NPRTVXZ\\^`b\2\7\3\2.\60\4\2,,\61\61\3\2\629\3\2:?\4\2\"\"))\u01f9\2d"+
		"\3\2\2\2\4o\3\2\2\2\6t\3\2\2\2\bv\3\2\2\2\n\u0084\3\2\2\2\f\u0090\3\2"+
		"\2\2\16\u009d\3\2\2\2\20\u00a8\3\2\2\2\22\u00ac\3\2\2\2\24\u00ae\3\2\2"+
		"\2\26\u00b1\3\2\2\2\30\u00b7\3\2\2\2\32\u00bf\3\2\2\2\34\u00c3\3\2\2\2"+
		"\36\u00d4\3\2\2\2 \u00e1\3\2\2\2\"\u00e3\3\2\2\2$\u00ea\3\2\2\2&\u00f5"+
		"\3\2\2\2(\u00fa\3\2\2\2*\u00fe\3\2\2\2,\u0106\3\2\2\2.\u010d\3\2\2\2\60"+
		"\u0121\3\2\2\2\62\u0134\3\2\2\2\64\u0136\3\2\2\2\66\u013a\3\2\2\28\u0144"+
		"\3\2\2\2:\u0146\3\2\2\2<\u015b\3\2\2\2>\u016f\3\2\2\2@\u0171\3\2\2\2B"+
		"\u0178\3\2\2\2D\u0185\3\2\2\2F\u01a7\3\2\2\2H\u01ad\3\2\2\2J\u01af\3\2"+
		"\2\2L\u01b1\3\2\2\2N\u01b3\3\2\2\2P\u01b5\3\2\2\2R\u01b7\3\2\2\2T\u01bf"+
		"\3\2\2\2V\u01c1\3\2\2\2X\u01c9\3\2\2\2Z\u01cf\3\2\2\2\\\u01d7\3\2\2\2"+
		"^\u01da\3\2\2\2`\u01e0\3\2\2\2b\u01e6\3\2\2\2di\5R*\2ef\7\30\2\2fh\5L"+
		"\'\2ge\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\3\3\2\2\2ki\3\2\2\2ln\5"+
		"\6\4\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7"+
		"\2\2\3s\5\3\2\2\2tu\5\b\5\2u\7\3\2\2\2vw\7\17\2\2wy\5N(\2xz\5\n\6\2yx"+
		"\3\2\2\2yz\3\2\2\2z{\3\2\2\2{\177\7\21\2\2|~\5\20\t\2}|\3\2\2\2~\u0081"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177"+
		"\3\2\2\2\u0082\u0083\7\24\2\2\u0083\t\3\2\2\2\u0084\u0085\7\5\2\2\u0085"+
		"\u008a\5\f\7\2\u0086\u0087\7\7\2\2\u0087\u0089\5\f\7\2\u0088\u0086\3\2"+
		"\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\7!\2\2\u008e\13\3\2\2\2"+
		"\u008f\u0091\5\16\b\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\r"+
		"\3\2\2\2\u0092\u009e\5X-\2\u0093\u0094\5X-\2\u0094\u0095\7\33\2\2\u0095"+
		"\u0096\5@!\2\u0096\u0097\7\r\2\2\u0097\u009e\3\2\2\2\u0098\u0099\5X-\2"+
		"\u0099\u009a\7\33\2\2\u009a\u009b\5B\"\2\u009b\u009c\7\r\2\2\u009c\u009e"+
		"\3\2\2\2\u009d\u0092\3\2\2\2\u009d\u0093\3\2\2\2\u009d\u0098\3\2\2\2\u009e"+
		"\17\3\2\2\2\u009f\u00a0\5\22\n\2\u00a0\u00a1\7\21\2\2\u00a1\u00a9\3\2"+
		"\2\2\u00a2\u00a9\5\"\22\2\u00a3\u00a9\5 \21\2\u00a4\u00a9\5\24\13\2\u00a5"+
		"\u00a9\5,\27\2\u00a6\u00a9\5$\23\2\u00a7\u00a9\5\60\31\2\u00a8\u009f\3"+
		"\2\2\2\u00a8\u00a2\3\2\2\2\u00a8\u00a3\3\2\2\2\u00a8\u00a4\3\2\2\2\u00a8"+
		"\u00a5\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\21\3\2\2"+
		"\2\u00aa\u00ad\5\34\17\2\u00ab\u00ad\5\36\20\2\u00ac\u00aa\3\2\2\2\u00ac"+
		"\u00ab\3\2\2\2\u00ad\23\3\2\2\2\u00ae\u00af\5\26\f\2\u00af\u00b0\7\21"+
		"\2\2\u00b0\25\3\2\2\2\u00b1\u00b3\7\26\2\2\u00b2\u00b4\5F$\2\u00b3\u00b2"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\5\30\r\2"+
		"\u00b6\27\3\2\2\2\u00b7\u00bc\5\32\16\2\u00b8\u00b9\7\7\2\2\u00b9\u00bb"+
		"\5\32\16\2\u00ba\u00b8\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2"+
		"\u00bc\u00bd\3\2\2\2\u00bd\31\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0"+
		"\5X-\2\u00c0\u00c1\7\4\2\2\u00c1\u00c2\5@!\2\u00c2\33\3\2\2\2\u00c3\u00c5"+
		"\7 \2\2\u00c4\u00c6\5F$\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c8\5V,\2\u00c8\35\3\2\2\2\u00c9\u00cb\7\n\2\2"+
		"\u00ca\u00cc\5F$\2\u00cb\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd"+
		"\3\2\2\2\u00cd\u00d5\5V,\2\u00ce\u00cf\7\n\2\2\u00cf\u00d1\7\t\2\2\u00d0"+
		"\u00d2\5F$\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2"+
		"\2\u00d3\u00d5\5V,\2\u00d4\u00c9\3\2\2\2\u00d4\u00ce\3\2\2\2\u00d5\37"+
		"\3\2\2\2\u00d6\u00d8\7\t\2\2\u00d7\u00d9\5F$\2\u00d8\u00d7\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\5V,\2\u00db\u00dc\7\21"+
		"\2\2\u00dc\u00e2\3\2\2\2\u00dd\u00de\7\t\2\2\u00de\u00df\5.\30\2\u00df"+
		"\u00e0\7\21\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00d6\3\2\2\2\u00e1\u00dd\3"+
		"\2\2\2\u00e2!\3\2\2\2\u00e3\u00e5\7\23\2\2\u00e4\u00e6\5F$\2\u00e5\u00e4"+
		"\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\5V,\2\u00e8"+
		"\u00e9\7\21\2\2\u00e9#\3\2\2\2\u00ea\u00eb\5N(\2\u00eb\u00f0\5&\24\2\u00ec"+
		"\u00ed\7\7\2\2\u00ed\u00ef\5&\24\2\u00ee\u00ec\3\2\2\2\u00ef\u00f2\3\2"+
		"\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\u00f4\7\21\2\2\u00f4%\3\2\2\2\u00f5\u00f6\5(\25\2"+
		"\u00f6\u00f7\7\5\2\2\u00f7\u00f8\5*\26\2\u00f8\u00f9\7!\2\2\u00f9\'\3"+
		"\2\2\2\u00fa\u00fc\5P)\2\u00fb\u00fd\5F$\2\u00fc\u00fb\3\2\2\2\u00fc\u00fd"+
		"\3\2\2\2\u00fd)\3\2\2\2\u00fe\u0103\5X-\2\u00ff\u0100\7\7\2\2\u0100\u0102"+
		"\5X-\2\u0101\u00ff\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104+\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\7\13\2\2"+
		"\u0107\u0108\58\35\2\u0108\u0109\7\4\2\2\u0109\u010a\5D#\2\u010a\u010b"+
		"\7\21\2\2\u010b-\3\2\2\2\u010c\u010e\5F$\2\u010d\u010c\3\2\2\2\u010d\u010e"+
		"\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\58\35\2\u0110\u0111\7\4\2\2\u0111"+
		"\u0112\5D#\2\u0112/\3\2\2\2\u0113\u0114\7\b\2\2\u0114\u0115\7\20\2\2\u0115"+
		"\u0122\5\62\32\2\u0116\u0117\7\b\2\2\u0117\u0118\7\16\2\2\u0118\u0119"+
		"\7\5\2\2\u0119\u011a\7\6\2\2\u011a\u011b\5X-\2\u011b\u011c\7\34\2\2\u011c"+
		"\u011d\7\27\2\2\u011d\u011e\5X-\2\u011e\u011f\7!\2\2\u011f\u0120\5\62"+
		"\32\2\u0120\u0122\3\2\2\2\u0121\u0113\3\2\2\2\u0121\u0116\3\2\2\2\u0122"+
		"\61\3\2\2\2\u0123\u0127\7\36\2\2\u0124\u0126\5\62\32\2\u0125\u0124\3\2"+
		"\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\u012a\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u0135\7\37\2\2\u012b\u012c\5"+
		"\64\33\2\u012c\u012d\7\21\2\2\u012d\u0135\3\2\2\2\u012e\u0135\5<\37\2"+
		"\u012f\u0135\5:\36\2\u0130\u0131\5\66\34\2\u0131\u0132\7\21\2\2\u0132"+
		"\u0135\3\2\2\2\u0133\u0135\7\21\2\2\u0134\u0123\3\2\2\2\u0134\u012b\3"+
		"\2\2\2\u0134\u012e\3\2\2\2\u0134\u012f\3\2\2\2\u0134\u0130\3\2\2\2\u0134"+
		"\u0133\3\2\2\2\u0135\63\3\2\2\2\u0136\u0137\58\35\2\u0137\u0138\7\4\2"+
		"\2\u0138\u0139\5D#\2\u0139\65\3\2\2\2\u013a\u013b\58\35\2\u013b\u013c"+
		"\7=\2\2\u013c\u013d\5D#\2\u013d\67\3\2\2\2\u013e\u0145\5X-\2\u013f\u0140"+
		"\5X-\2\u0140\u0141\7\33\2\2\u0141\u0142\5B\"\2\u0142\u0143\7\r\2\2\u0143"+
		"\u0145\3\2\2\2\u0144\u013e\3\2\2\2\u0144\u013f\3\2\2\2\u01459\3\2\2\2"+
		"\u0146\u0147\7\22\2\2\u0147\u0148\7\5\2\2\u0148\u0149\5D#\2\u0149\u014a"+
		"\7!\2\2\u014a\u0154\5\62\32\2\u014b\u014c\7\35\2\2\u014c\u014d\7\22\2"+
		"\2\u014d\u014e\7\5\2\2\u014e\u014f\5D#\2\u014f\u0150\7!\2\2\u0150\u0151"+
		"\5\62\32\2\u0151\u0153\3\2\2\2\u0152\u014b\3\2\2\2\u0153\u0156\3\2\2\2"+
		"\u0154\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0159\3\2\2\2\u0156\u0154"+
		"\3\2\2\2\u0157\u0158\7\35\2\2\u0158\u015a\5\62\32\2\u0159\u0157\3\2\2"+
		"\2\u0159\u015a\3\2\2\2\u015a;\3\2\2\2\u015b\u015c\7\31\2\2\u015c\u015d"+
		"\7\5\2\2\u015d\u015e\5D#\2\u015e\u015f\7!\2\2\u015f\u0163\5> \2\u0160"+
		"\u0162\5> \2\u0161\u0160\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2"+
		"\2\u0163\u0164\3\2\2\2\u0164\u0166\3\2\2\2\u0165\u0163\3\2\2\2\u0166\u0167"+
		"\7\f\2\2\u0167=\3\2\2\2\u0168\u0169\5D#\2\u0169\u016a\7\32\2\2\u016a\u016b"+
		"\5\62\32\2\u016b\u0170\3\2\2\2\u016c\u016d\7\3\2\2\u016d\u016e\7\32\2"+
		"\2\u016e\u0170\5\62\32\2\u016f\u0168\3\2\2\2\u016f\u016c\3\2\2\2\u0170"+
		"?\3\2\2\2\u0171\u0172\5Z.\2\u0172A\3\2\2\2\u0173\u0179\5\\/\2\u0174\u0175"+
		"\5H%\2\u0175\u0176\7\32\2\2\u0176\u0177\5J&\2\u0177\u0179\3\2\2\2\u0178"+
		"\u0173\3\2\2\2\u0178\u0174\3\2\2\2\u0179C\3\2\2\2\u017a\u017b\b#\1\2\u017b"+
		"\u017c\7,\2\2\u017c\u0186\5D#\17\u017d\u017e\7-\2\2\u017e\u0186\5D#\16"+
		"\u017f\u0180\7\5\2\2\u0180\u0181\5D#\2\u0181\u0182\7!\2\2\u0182\u0186"+
		"\3\2\2\2\u0183\u0186\5T+\2\u0184\u0186\5Z.\2\u0185\u017a\3\2\2\2\u0185"+
		"\u017d\3\2\2\2\u0185\u017f\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0184\3\2"+
		"\2\2\u0186\u01a4\3\2\2\2\u0187\u0188\f\r\2\2\u0188\u0189\t\2\2\2\u0189"+
		"\u01a3\5D#\16\u018a\u018b\f\f\2\2\u018b\u018c\t\3\2\2\u018c\u01a3\5D#"+
		"\r\u018d\u018e\f\13\2\2\u018e\u018f\t\4\2\2\u018f\u01a3\5D#\f\u0190\u0191"+
		"\f\n\2\2\u0191\u0192\t\5\2\2\u0192\u01a3\5D#\13\u0193\u0194\f\t\2\2\u0194"+
		"\u0195\7B\2\2\u0195\u01a3\5D#\n\u0196\u0197\f\b\2\2\u0197\u0198\7@\2\2"+
		"\u0198\u01a3\5D#\t\u0199\u019a\f\7\2\2\u019a\u019b\7A\2\2\u019b\u01a3"+
		"\5D#\b\u019c\u019d\f\6\2\2\u019d\u019e\7\25\2\2\u019e\u019f\5D#\2\u019f"+
		"\u01a0\7\32\2\2\u01a0\u01a1\5D#\7\u01a1\u01a3\3\2\2\2\u01a2\u0187\3\2"+
		"\2\2\u01a2\u018a\3\2\2\2\u01a2\u018d\3\2\2\2\u01a2\u0190\3\2\2\2\u01a2"+
		"\u0193\3\2\2\2\u01a2\u0196\3\2\2\2\u01a2\u0199\3\2\2\2\u01a2\u019c\3\2"+
		"\2\2\u01a3\u01a6\3\2\2\2\u01a4\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5"+
		"E\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a7\u01a8\7\33\2\2\u01a8\u01a9\5H%\2\u01a9"+
		"\u01aa\7\32\2\2\u01aa\u01ab\5J&\2\u01ab\u01ac\7\r\2\2\u01acG\3\2\2\2\u01ad"+
		"\u01ae\5\\/\2\u01aeI\3\2\2\2\u01af\u01b0\5\\/\2\u01b0K\3\2\2\2\u01b1\u01b2"+
		"\5X-\2\u01b2M\3\2\2\2\u01b3\u01b4\5X-\2\u01b4O\3\2\2\2\u01b5\u01b6\5X"+
		"-\2\u01b6Q\3\2\2\2\u01b7\u01b8\5X-\2\u01b8S\3\2\2\2\u01b9\u01ba\5X-\2"+
		"\u01ba\u01bb\7\33\2\2\u01bb\u01bc\5@!\2\u01bc\u01bd\7\r\2\2\u01bd\u01c0"+
		"\3\2\2\2\u01be\u01c0\5X-\2\u01bf\u01b9\3\2\2\2\u01bf\u01be\3\2\2\2\u01c0"+
		"U\3\2\2\2\u01c1\u01c6\5X-\2\u01c2\u01c3\7\7\2\2\u01c3\u01c5\5X-\2\u01c4"+
		"\u01c2\3\2\2\2\u01c5\u01c8\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6\u01c7\3\2"+
		"\2\2\u01c7W\3\2\2\2\u01c8\u01c6\3\2\2\2\u01c9\u01ca\t\6\2\2\u01caY\3\2"+
		"\2\2\u01cb\u01d0\5\\/\2\u01cc\u01d0\5`\61\2\u01cd\u01d0\5^\60\2\u01ce"+
		"\u01d0\5b\62\2\u01cf\u01cb\3\2\2\2\u01cf\u01cc\3\2\2\2\u01cf\u01cd\3\2"+
		"\2\2\u01cf\u01ce\3\2\2\2\u01d0[\3\2\2\2\u01d1\u01d8\7$\2\2\u01d2\u01d4"+
		"\7$\2\2\u01d3\u01d2\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5"+
		"\u01d6\7%\2\2\u01d6\u01d8\7$\2\2\u01d7\u01d1\3\2\2\2\u01d7\u01d3\3\2\2"+
		"\2\u01d8]\3\2\2\2\u01d9\u01db\7$\2\2\u01da\u01d9\3\2\2\2\u01da\u01db\3"+
		"\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01dd\7&\2\2\u01dd\u01de\7$\2\2\u01de"+
		"_\3\2\2\2\u01df\u01e1\7$\2\2\u01e0\u01df\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1"+
		"\u01e2\3\2\2\2\u01e2\u01e3\7\'\2\2\u01e3\u01e4\7$\2\2\u01e4a\3\2\2\2\u01e5"+
		"\u01e7\7$\2\2\u01e6\u01e5\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e8\3\2"+
		"\2\2\u01e8\u01e9\7(\2\2\u01e9\u01ea\7$\2\2\u01eac\3\2\2\2,ioy\177\u008a"+
		"\u0090\u009d\u00a8\u00ac\u00b3\u00bc\u00c5\u00cb\u00d1\u00d4\u00d8\u00e1"+
		"\u00e5\u00f0\u00fc\u0103\u010d\u0121\u0127\u0134\u0144\u0154\u0159\u0163"+
		"\u016f\u0178\u0185\u01a2\u01a4\u01bf\u01c6\u01cf\u01d3\u01d7\u01da\u01e0"+
		"\u01e6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}