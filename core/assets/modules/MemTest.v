module MemTest(clk);
	input clk;

	wire [7:0]cin;
	reg [7:0]sumOne;
	reg [7:0]sumTwo;
	wire cout;
	wire [7:0]tempSum;
	reg choose;

	assign cin = 7'd0;

	Adder_8 gate0(sumOne, sumTwo, cin, tempSum, cout);

	always @(posedge clk or negedge clk)
	begin
		if(choose == 0)
		begin
			sumOne <= 1;
			sumTwo <= 1;
			choose <= 1;
		end
		else 
		begin
			sumOne <= sumTwo;
			sumTwo <= tempSum;
		end
	end
endmodule}
