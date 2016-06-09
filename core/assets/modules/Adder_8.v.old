module Adder_8(a, b, cin, sum, cout);

	input cin;
	input [7:0]a;
	input [7:0]b;
	output [7:0]sum;
	output cout;

	wire c0, c1, c2, c3, c4, c5, c6;

	wire num10, num11, num12, num13, num14, num15, num16, num17;
	wire num20, num21, num22, num23, num24, num25, num26, num27;
	wire result0, result1, result2, result3, result4, result5, result6, result7;

	assign num10 = a[0];
	assign num11 = a[1];
	assign num12 = a[2];
	assign num13 = a[3];
	assign num14 = a[4];
	assign num15 = a[5];
	assign num16 = a[6];
	assign num17 = a[7];

	assign num20 = b[0];
	assign num21 = b[1];
	assign num22 = b[2];
	assign num23 = b[3];
	assign num24 = b[4];
	assign num25 = b[5];
	assign num26 = b[6];
	assign num27 = b[7];

	FullAdder adder0(num10, num20, result0, cin, c0);
	FullAdder adder1(num11, num21, result1, c0, c1);
	FullAdder adder2(num12, num22, result2, c1, c2);
	FullAdder adder3(num13, num23, result3, c2, c3);
	FullAdder adder4(num14, num24, result4, c3, c4);
	FullAdder adder5(num15, num25, result5, c4, c5);
	FullAdder adder6(num16, num26, result6, c5, c6);
	FullAdder adder7(num17, num27, result7, c6, cout);

	assign sum[0] = result0;
	assign sum[1] = result1;
	assign sum[2] = result2;
	assign sum[3] = result3;
	assign sum[4] = result4;
	assign sum[5] = result5;
	assign sum[6] = result6;
	assign sum[7] = result7;

endmodule