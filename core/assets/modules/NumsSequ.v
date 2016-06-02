module Nums(clk);
	
	wire zero;
	wire one;
	wire [7:0]countSum;
	wire cout;
	reg [7:0]count;

	assign zero = 0;
	assign one = 1;

	Adder_8 mod0(count, one, zero, countSum ,cout);

	always @(posedge clk or negedge clk)
	begin
		count <= countSum;
	end
endmodule