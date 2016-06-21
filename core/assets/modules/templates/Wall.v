module MemTest(clk, rst, out);
	input clk, rst;
	output [15:0]out;

	wire [15:0]cin;
	reg [15:0]sumOne;
	reg [15:0]sumTwo;
	wire cout;
	wire [15:0]tempSum;

	assign cin = 1'd0;
	assign out = sumOne;

	Adder_16 gate0(sumOne, sumTwo, cin, tempSum, cout);

	always @(posedge clk or negedge rst)
	begin
		if(rst == 0)
		begin
			sumOne <= 1;
			sumTwo <= 1;
		end
		else 
		begin
			sumOne <= sumTwo;
			sumTwo <= tempSum;
		end
	end
endmodule