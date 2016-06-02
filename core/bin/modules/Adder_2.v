module Adder_8(a, b, cin, sum, cout);

	input cin;
	input [1:0]a;
	input [1:0]b;
	output [1:0]sum;
	output cout;

	wire c0, c1, c2, c3, c4, c5, c6;

	wire num10, num11;
	wire num20, num21;
	wire result0, result1;

	assign num10 = a[0];
	assign num11 = a[1];

	assign num20 = b[0];
	assign num21 = b[1];

	FullAdder adder0(num10, num20, result0, cin, c0);
	FullAdder adder7(num11, num21, result1, c0, cout);

	assign sum[0] = result0;
	assign sum[1] = result1;

endmodule