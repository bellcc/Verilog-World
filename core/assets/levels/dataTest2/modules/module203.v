module Seven_Segment(a, clk, rst);

reg i;
input clk, rst;
output a;

always @(posedge clk or negedge rst)
begin
	if (rst == 0)
	begin
		i <= 4'd0;
	end
	else
	begin
		i <= ~i;
	end
end

assign a = i;	

endmodule