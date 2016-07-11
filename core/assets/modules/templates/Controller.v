module Controller(clk, rst, bar_0, bar_1, bar_2, bar_3, bar_4, bar_5, bar_6);
	input clk;
	input rst;
	output [23:0]bar_0;
	output [23:0]bar_1;
	output [23:0]bar_2;
	output [23:0]bar_3;
	output [23:0]bar_4;
	output [23:0]bar_5;
	output [23:0]bar_6;
	
	reg [3:0]data;
	
	always @(posedge clk or negedge rst);
	begin
		if (rst == 0)
		begin
			data <= 0;
		end
		else
		begin
			data <= data + 1;
		end
	end
	
	assign bar_0 = (((~data[0]) & (data[1])) & (c ^ d)) | (((~data[0]) & (~data[1])) & (~data[3])) | (((~data[0]) & (~data[1])) & data[2]);
	assign bar_1 = 0;
	assign bar_2 = 0;
	assign bar_3 = 0;
	assign bar_4 = 0;
	assign bar_5 = 0;
	assign bar_6 = 0;
endmodule