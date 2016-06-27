module Wall(clk, rst, color);
	output [23:0]color;
	input clk, rst;
	
	reg colorWire;
	
	assign color = colorWire;
	
	always @(posedge clk or negedge rst)
	begin
		if (rst == 0)
		begin
			colorWire <= 0;
		end
		else
		begin
			colorWire <= colorWire + 5;
		end
	end

endmodule