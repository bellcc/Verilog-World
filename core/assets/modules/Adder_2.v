module Adder_8(a, b, cin, sum, cout);

	input cin;
	input [1:0]a;
	input [1:0]b;
	output [1:0]sum;
	output cout;

	wire c0;

	assign sum[0] = (a[0] ^ b[0]) ^ cin;
	assign c0 = (a[0] & b[0]) | (a[0] & cin) | (b[0] & cin);
	assign sum[1] = (a[1] ^ b[1]) ^ c0;
	assign cout = (a[1] & b[1]) | (a[1] & c0) | (b[1] & c0);

endmodule