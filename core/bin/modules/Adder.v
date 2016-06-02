module Test(a, b, c);

	wire [7:0]num1;
	wire [7:0]num2;
	wire [7:0]result;
	wire test;

	assign num1 = 8'd173;
	assign num2 = 8'd92;

	wire cin, cout;
	wire c0, c1, c2, c3, c4, c5, c6;

	wire num10, num11, num12, num13, num14, num15, num16, num17;
	wire num20, num21, num22, num23, num24, num25, num26, num27;
	wire result0, result1, result2, result3, result4, result5, result6, result7;

	assign num10 = num1[0];
	assign num11 = num1[1];
	assign num12 = num1[2];
	assign num13 = num1[3];
	assign num14 = num1[4];
	assign num15 = num1[5];
	assign num16 = num1[6];
	assign num17 = num1[7];

	assign num20 = num2[0];
	assign num21 = num2[1];
	assign num22 = num2[2];
	assign num23 = num2[3];
	assign num24 = num2[4];
	assign num25 = num2[5];
	assign num26 = num2[6];
	assign num27 = num2[7];

	FullAdder adder0(num10, num20, result0, cin, c0);
	FullAdder adder1(num11, num21, result1, c0, c1);
	FullAdder adder2(num12, num22, result2, c1, c2);
	FullAdder adder3(num13, num23, result3, c2, c3);
	FullAdder adder4(num14, num24, result4, c3, c4);
	FullAdder adder5(num15, num25, result5, c4, c5);
	FullAdder adder6(num16, num26, result6, c5, c6);
	FullAdder adder7(num17, num27, result7, c6, cout);

	assign test = result0;

endmodule