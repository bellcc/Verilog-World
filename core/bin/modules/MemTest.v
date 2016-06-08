module MemTest(clk);
	input clk;

	wire [3:0]one;
	wire [3:0]cin;
	reg [3:0]sum;
	wire cout;
	wire [3:0]tempSum;

	assign sum = 4'd1;
	assign one = 4'd1;
	assign cin = 4'd0;

	Adder_2 gate0(sum, sum, cin, tempSum, cout);

	always @(posedge clk or negedge clk)
	begin
		sum <= tempSum;
	end
endmodule