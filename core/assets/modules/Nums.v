module Nums(in, out);
	
	reg [7:0]a;
	reg [7:0]b;
	reg [7:0]sum;
	reg cout;

	assign a = 8'd63;
	assign b = 8'd111;

	Adder_8 mod0(a, b, sum ,cout);
endmodule