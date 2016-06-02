module Nums(in, out);
	
	reg [7:0]a;
	reg [7:0]b;
	reg [7:0]sum;
	reg cin, cout;

<<<<<<< HEAD
	assign a = 8'd141;
=======
	assign a = 8'd149;
>>>>>>> master
	assign b = 8'd107;
	assign cin = 0;

	Adder_8 mod0(a, b, cin, sum ,cout);
endmodule