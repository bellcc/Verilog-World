module Led(color, data);
	output [23:0]color;
	input data;
	
	if (data == 0)
	begin
		assign color = (80 << 16) | (80 << 8) | 80;
	end
	else
	begin
		assign color = (255 << 16) | (0 << 8) | 0;
	end
	
endmodule