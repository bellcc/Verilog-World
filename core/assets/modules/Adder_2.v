module Adder_8(a, b, cin, sum, cout);

	input cin;
	input [3:0]a;
	input [3:0]b;
	output [3:0]sum;
	output cout;

	wire c0, c1, c2;

	assign sum[0] = (a[0] ^ b[0]) ^ cin;
	assign c0 = (a[0] & b[0]) | (a[0] & cin) | (b[0] & cin);
	assign sum[1] = (a[1] ^ b[1]) ^ c0;
	assign c1 = (a[1] & b[1]) | (a[1] & c0) | (b[1] & c0);
	assign sum[2] = (a[2] ^ b[2]) ^ c1;
	assign c2 = (a[2] & b[2]) | (a[2] & c1) | (b[2] & c1);
	assign sum[3] = (a[3] ^ b[3]) ^ c2;
	assign cout = (a[3] & b[3]) | (a[3] & c2) | (b[3] & c2);

endmodule