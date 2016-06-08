module WireTest(in, out);
	reg [3:0]var;
	reg [1:0]result;

	assign var = 4'b1011

	wire var0, var1, var2, var3;
	wire result0, result1;

	assign var0 = var[0];
	assign var1 = var[1];
	assign var2 = var[2];
	assign var3 = var[3];

	AND_GATE gate0(var0, var1, result0);
	AND_GATE gate1(var2, var3, result1);

	assign result[0] = result0;
	assign result[1] = result1;
endmodule