module AND_3_ALT(a, b, c, out);
	input a, b, c;
	output out;
	assign out = a & (b & c);
endmodule
