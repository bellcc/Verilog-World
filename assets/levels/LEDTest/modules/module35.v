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
	
assign a = ~((i[0] & ~i[1] & ~i[2]) | (i[0] & ~i[3]) | (i[1] & i[2]) | (~i[0] & i[2]) | (~i[0] & ~i[1] & ~i[3]) | (~i[0] & i[1] & i[3]));
assign b = ~((~i[0] & ~i[1]) | (~i[1] & ~i[3]) | (i[0] & ~i[2] & i[3]) | (~i[0] & ~i[2] & ~i[3]) | (~i[0] & i[2] & i[3]));
assign c = ~((~i[0] & i[3]) | (~i[0] & ~i[2]) | (~i[2] & i[3]) | (i[0] & ~i[1]) | (i[1] & ~i[0]));
assign d = ~((i[0] & ~i[2]) | (i[1] & ~i[2] & i[3]) | (~i[0] & ~i[1] & ~i[3]) | (i[1] & i[2] & ~i[3]) | (~i[1] & i[2] & i[3]));
assign e = ~((~i[1] & ~i[3]) | (i[0] & i[1]) | (i[0] & i[2]) | (i[2] & ~i[3]));
assign f = ~((~i[2] & ~i[3]) | (~i[0] & i[1] & ~i[2]) | (i[1] & ~i[3]) | (i[0] & ~i[1]) | (i[0] & i[2]));
assign g = ~((~i[0] & i[1] & ~i[2]) | (i[0] & i[3]) | (i[0] & ~i[1]) | (i[2] & ~i[3]) | (~i[1] & i[2]));	

endmodule
