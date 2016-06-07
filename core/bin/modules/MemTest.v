module MemTest(clk);
	input clk;

	wire [1:0]one;
	wire [1:0]cin;
	reg [1:0]sum;
	wire cout;
	wire [1:0]tempSum;

	assign one = 2'd1;
	assign cin = 2'd0;

	Adder_2 gate0(sum, one, cin, tempSum, cout);

	always @(posedge clk or negedge clk)
	begin
		sum <= tempSum;
	end
endmodule