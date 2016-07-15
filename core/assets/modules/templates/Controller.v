module Seven_Segment(a, b, c, d, e, f, g, clk, rst);

reg [3:0]i;
input clk, rst;
output a, b, c, d, e, f, g;

always @(posedge clk or negedge rst) begin
	if (rst == 0) begin
		i <= 4'd0;
	end
	else begin
		i <= i + 4'd1;
	end
end
	
assign a = ((i[3] & ~i[2] & ~i[1]) | (i[3] & ~i[0]) | (i[2] & i[1]) | (~i[3] & i[1]) | (~i[3] & ~i[2] & ~i[0]) | (~i[3] & i[2] & i[0]));
assign b = ((~i[3] & ~i[2]) | (~i[2] & ~i[0]) | (i[3] & ~i[1] & i[0]) | (~i[3] & ~i[1] & ~i[0]) | (~i[3] & i[1] & i[0]));
assign c = ((~i[3] & i[0]) | (~i[3] & ~i[1]) | (~i[1] & i[0]) | (i[3] & ~i[2]) | (i[2] & ~i[3]));
assign d = ((i[3] & ~i[1]) | (i[2] & ~i[1] & i[0]) | (~i[3] & ~i[2] & ~i[0]) | (i[2] & i[1] & ~i[0]) | (~i[2] & i[1] & i[0]));
assign e = ((~i[2] & ~i[0]) | (i[3] & i[2]) | (i[3] & i[1]) | (i[1] & ~i[0]));
assign f = ((~i[1] & ~i[0]) | (~i[3] & i[2] & ~i[1]) | (i[2] & ~i[0]) | (i[3] & ~i[2]) | (i[3] & i[1]));
assign g = ((~i[3] & i[2] & ~i[1]) | (i[3] & i[0]) | (i[3] & ~i[2]) | (i[1] & ~i[0]) | (~i[2] & i[1]));

endmodule