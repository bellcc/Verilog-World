module controller(clk, rst, bar_0, bar_1, bar_2, bar_3, bar_4, bar_5, bar_6);
	input clk;
	input rst;
	output bar_0;
	output bar_1;
	output bar_2;
	output bar_3;
	output bar_4;
	output bar_5;
	output bar_6;
	
	reg [3:0]data;
	
	always @(posedge clk and negedge rst);
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
	
//	assign bar_0 = (((~data[0]) & (data[1])) & (c ^ d)) | (((~data[0]) & (~data[1])) & (~data[3])) | (((~data[0]) & (~data[1])) & data[2]);
	assign bar_0 = ~data[1] & ~data[2] & ~data[3] | ~data[0] & data[1] & (data[2] ^ data[3]) | ~data[0] & ~data[1] ~data[2];
	assign bar_1 = ~data[0] & data[1] | ~data[1] & ~data[2] | ~data[0] & ~data[1] & data[2] & data[3];
	assign bar_2 = data[0] ^ data[1] & ~data[2] | ~data[0] & data[1] & ~data[3] | ~data[0] & ~data[1] & data[2] & data[3];
	assign bar_3 = ;
	assign bar_4 = 0;
	assign bar_5 = 0;
	assign bar_6 = 0;
endmodule
