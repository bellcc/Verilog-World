module PC (b, s, out, clk, rw, rst);

/*
rw = Read/Write; Read on 0, Write on 1.
s = Switch; in = out on 0, in = b on 1.
b = Branch; used to overwrite the PC during branches or jumps.
*/

input wire rw, s, clk, rst;
input wire [31:0] b;

reg [31:0] in;

output wire [31:0]out;

always @(*) begin

	case(s)
		
		1'd0: begin
			if (out < 32'd16)
				in = out + 1;
			else
				in = 32'd0;
		end
		
		1'd1: begin
			in = b;
		end
	endcase
end

REG pc (rw, in, out, clk, rst);

endmodule
