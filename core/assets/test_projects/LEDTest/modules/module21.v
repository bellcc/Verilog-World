module Led(color, data);
	output [23:0]color;
	input data;

	reg colorLine;

	assign color = colorLine;
	
	always @(*)
	begin
		if (data == 0)
		begin
			colorLine = (80 << 16) | (80 << 8) | 80;
		end
		else
		begin
			colorLine = (255 << 16) | (0 << 8) | 0;
		end
	end
	
endmodule