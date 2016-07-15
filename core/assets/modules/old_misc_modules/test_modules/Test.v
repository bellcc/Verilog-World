module Test(a, b, c, out);
	input a, b, c;
	output out;
	wire result;

	reg test1, test2, test3;
	assign test1 = 1;
	assign test2 = 1;
	assign test3 = 1;
	
	AND_GATE gate0(test1, test2, test3, result);

	assign out = result;
endmodule