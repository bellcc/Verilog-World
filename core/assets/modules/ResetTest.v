module ResetTest(clk, rst);
	input clk, rst;

	reg [1:0]test;
	wire result;

	assign result = test[1] & test[1];

	always @(posedge clk or negedge rst)
	begin
		if (rst == 1'b0)
		begin
			test <= 2'b01;
		end
		else
		begin
			test <= 2'b10;
		end
	end

endmodule