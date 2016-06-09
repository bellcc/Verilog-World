module ResetTest(clk, rst);
	input clk, rst;

	reg [1:0]test;

	always @(posedge clk or negedge rst)
	begin
		if (rst == 2'b00)
		begin
			test <= 2'b01;
		end
		else
		begin
			test <= 2'b10;
		end
	end

endmodule