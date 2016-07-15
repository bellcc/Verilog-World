module Latch(data, write, clear, out);
	input data, write, clear;
	output out;

	wire w0, w1, w2;

	assign w0 = data & write;
	assign w1 = ~w0 | clear;
	assign w2 = ~w1;

	assign out = w2 | w0;
endmodule