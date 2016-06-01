module Nums(in, out);
	
	reg [7:0]a;
	reg [7:0]b;
	reg [7:0]sum;
	reg cin, cout;

	assign a = 8'd151;
	assign b = 8'd105;
	assign cin = 0;

	Adder_8 mod0(a, b, cin, sum ,cout);
endmodule