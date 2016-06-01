module Nums(in, out);
	
	reg [1:0]a;
	reg [1:0]b;
	reg [1:0]sum;
	reg cin, cout;

	assign a = 2'd3;
	assign b = 2'd3;
	assign cin = 0;

	Adder_2 mod0(a, b, cin, sum ,cout);
endmodule