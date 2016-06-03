module MemTest(clk);
	input clk;

	reg write;
	wire data;
	wire clear;
	wire out;

	Latch mod0(data, write, clear, out);

	always @(*)
	begin
		if(out == 0)
		begin
			data = 1;
			clear = 0;
		end
		else
		begin
			data = 0;
			clear = 1;
		end
	end

	always @(posedge clk or negedge clk)
	begin
		write <= ~clk;
	end
endmodule