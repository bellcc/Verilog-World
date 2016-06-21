module SchemTest(in, clk, out);

input [3:0]in;
input clk;

//wire [1:0]e;

reg a, b, c, d;

//assign e[0] = in[0];
//assign e[1] = in[1];
//assign a = e[0];
//assign b = e[1];
//assign c = in[2];
//assign d = in[3];

//always @(*) begin
//	a = in[0];
//	b = in[1];
//	c = in[2];
//	d = in[3];
//end

always @(posedge clk or negedge clk) begin
	a <= in[0];
	b <= in[1];
	c <= in[2];
	d <= in[3];
end

output out;

assign out = (a&b)|(c&d);
//assign out = -in[0];

endmodule