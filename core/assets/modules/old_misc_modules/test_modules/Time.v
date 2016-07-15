module Time(clk);
	input clk;
	reg test;

	always @(posedge clk or negedge clk)
	begin
		if (clk == 0) {
			test <= 1;
		else
			test <= 0;
	end
endmodule}