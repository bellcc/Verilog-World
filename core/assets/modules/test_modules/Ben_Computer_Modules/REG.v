module REG(rw, in, out, clk, rst);

//RW: Read on 0, Write on 1
//CLK / RST: input from the REGFILE
//IN: input from the REGFILE
//OUT: output to the REGFILE
input rw, clk, rst;
input [31:0]in;

output [31:0]out;

//H for Hold
reg [31:0]h;

/*On Reset signal, clear the register. On Write signal,
write the value of IN to H. Otherwise, loop H back into
itself.
*/
always @(posedge clk or negedge rst)
begin
	if(rst == 1'd0)
		h <= 32'd0;
	else begin
		if(rw == 1'd1)
			h <= in;
		else
			h <= h;
	end
end

//Always output H.
assign out = h;

endmodule
