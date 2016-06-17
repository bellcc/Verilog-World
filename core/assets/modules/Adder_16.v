module Adder_16(a, b, cin, sum, cout);

	input cin;
	input [15:0]a;
	input [15:0]b;
	output [15:0]sum;
	output cout;

	wire c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14;

	assign sum[0] = (a[0] ^ b[0]) ^ cin;
	assign c0 = (a[0] & b[0]) | (a[0] & cin) | (b[0] & cin);
	assign sum[1] = (a[1] ^ b[1]) ^ c0;
	assign c1 = (a[1] & b[1]) | (a[1] & c0) | (b[1] & c0);
	assign sum[2] = (a[2] ^ b[2]) ^ c1;
	assign c2 = (a[2] & b[2]) | (a[2] & c1) | (b[2] & c1);
	assign sum[3] = (a[3] ^ b[3]) ^ c2;
	assign c3 = (a[3] & b[3]) | (a[3] & c2) | (b[3] & c2);
	assign sum[4] = (a[4] ^ b[4]) ^ c3;
	assign c4 = (a[4] & b[4]) | (a[4] & c3) | (b[4] & c3);
	assign sum[5] = (a[5] ^ b[5]) ^ c4;
	assign c5 = (a[5] & b[5]) | (a[5] & c4) | (b[5] & c4);
	assign sum[6] = (a[6] ^ b[6]) ^ c5;
	assign c6 = (a[6] & b[6]) | (a[6] & c5) | (b[6] & c5);
	assign sum[7] = (a[7] ^ b[7]) ^ c6;
	assign c7 = (a[7] & b[7]) | (a[7] & c6) | (b[7] & c6);

	assign sum[8] = (a[8] ^ b[8]) ^ c7;
	assign c8 = (a[8] & b[8]) | (a[8] & c7) | (b[8] & c7);
	assign sum[9] = (a[9] ^ b[9]) ^ c8;
	assign c9 = (a[9] & b[9]) | (a[9] & c8) | (b[9] & c8);
	assign sum[10] = (a[10] ^ b[10]) ^ c9;
	assign c10 = (a[10] & b[10]) | (a[10] & c9) | (b[10] & c9);
	assign sum[11] = (a[11] ^ b[11]) ^ c10;
	assign c11 = (a[11] & b[11]) | (a[11] & c10) | (b[11] & c10);
	assign sum[12] = (a[12] ^ b[12]) ^ c11;
	assign c12 = (a[12] & b[12]) | (a[12] & c11) | (b[12] & c11);
	assign sum[13] = (a[13] ^ b[13]) ^ c12;
	assign c13 = (a[13] & b[13]) | (a[13] & c12) | (b[13] & c12);
	assign sum[14] = (a[14] ^ b[14]) ^ c13;
	assign c14 = (a[14] & b[14]) | (a[14] & c13) | (b[14] & c13);
	assign sum[15] = (a[15] ^ b[15]) ^ c14;
	assign cout = (a[15] & b[15]) | (a[15] & c14) | (b[15] & c14);

endmodule