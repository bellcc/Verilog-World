module FullAdder(bit1, bit2, sum, cin, cout);
	input bit1, bit2, cin;
	output sum, cout;
	
	assign sum = (bit1 ^ bit2) ^ cin;
	assign cout = (bit1 & bit2) | (bit1 & cin) | (bit2 & cin);
endmodule