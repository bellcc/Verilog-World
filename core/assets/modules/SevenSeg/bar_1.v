module bar_1(color, data);
	output [23:0]color;
	input data;
	
	if (data == 0)
	begin
		assign color = (255 << 16) | (255 << 8) | 255;
	end
	else
	begin
		assign color = (100 << 16) | (100 << 8) | 100;
	end
	
endmodule