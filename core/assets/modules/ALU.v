module ALU(inst, a, b, o, statupd8);

/*
Summary for reference:
Input instructions are: NOP, ADD, SUB, EQZ (EQual to Zero), LTZ (Less Than Zero).
Instructions are 8', 0-4 respectively. A and B are 32' inputs and O is a 32'
output. CLK and RST should be signals provided by the control to reset and 
activate the ALU in time with everything else.
*/

input [31:0]a, b;
input [7:0]inst;

output [31:0]o; 
output statupd8;

parameter NOP = 8'd0,
	EQZ = 8'd1,
	LTZ = 8'd2,
	ADD = 8'd3,
	SUB = 8'd4;

reg [31:0]o;
reg statupd8;

//Do the math between clock cycles to boost speed
always @(*)
begin
	case(inst)
//When A == 0, Statupd8 goes HIGH.
	EQZ: begin
		if(a == 0) begin
			statupd8 = 1'd1;
			o = 32'd0;
			end
		else begin
			statupd8 = 1'd0;
			o = 32'd0;
			end
	end
//When A < 0, Statupd8 goes HIGH.
	LTZ: begin
		if(a[31] == 1'd1) begin
			statupd8 = 1'd1;
			o = 32'd0;
			end
		else begin
			statupd8 = 1'd0;
			o = 32'd0;
			end
		end

	ADD: begin
		o = a + b;
		statupd8 = 1'd0;
	end

	SUB: begin
		o = a - b;
		statupd8 = 1'd0;
	end

	default: begin
		o = 32'd0;
		statupd8 = 1'd0;
	end
	endcase
end
		
endmodule
