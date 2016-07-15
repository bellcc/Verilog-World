module REGFILE(in, out0, out1, addr0, addr1, ctrw, clk, rst);

input [31:0]in;
input [7:0]addr0, addr1;
input ctrw; //Read on 0, Write on 1
input clk, rst;

output [31:0]out0, out1;

wire [31:0]Rout0;
wire [31:0]Rout1;
wire [31:0]Rout2;
wire [31:0]Rout3;
wire [31:0]Rout4;
wire [31:0]Rout5;
wire [31:0]Rout6;
wire [31:0]Rout7;
wire [31:0]Rout8;
wire [31:0]Rout9;
wire [31:0]Rout10;
wire [31:0]Rout11;
wire [31:0]Rout12;
wire [31:0]Rout13;
wire [31:0]Rout14;
wire [31:0]Rout15;

reg [15:0]rw = 8'd0;

reg [31:0]out0, out1;

//Declare registers
REG register0 (rw[0], in, Rout0, clk, rst);
REG register1 (rw[1], in, Rout1, clk, rst);
REG register2 (rw[2], in, Rout2, clk, rst);
REG register3 (rw[3], in, Rout3, clk, rst);
REG register4 (rw[4], in, Rout4, clk, rst);
REG register5 (rw[5], in, Rout5, clk, rst);
REG register6 (rw[6], in, Rout6, clk, rst);
REG register7 (rw[7], in, Rout7, clk, rst);
REG register8 (rw[8], in, Rout8, clk, rst);
REG register9 (rw[9], in, Rout9, clk, rst);
REG register10 (rw[10], in, Rout10, clk, rst);
REG register11 (rw[11], in, Rout11, clk, rst);
REG register12 (rw[12], in, Rout12, clk, rst);
REG register13 (rw[13], in, Rout13, clk, rst);
REG register14 (rw[14], in, Rout14, clk, rst);
REG register15 (rw[15], in, Rout15, clk, rst);

//Read from registers. Reading is combinational, so no sequential block necessary.
always @(*)
begin
	case(addr0)
		
		8'd0: begin	
			out0 = Rout0;
		end
		
		8'd1: begin	
			out0 = Rout1;
		end	

		8'd2: begin	
			out0 = Rout2;
		end

		8'd3: begin	
			out0 = Rout3;
		end
		
		8'd4: begin	
			out0 = Rout4;
		end

		8'd5: begin	
			out0 = Rout5;
		end

		8'd6: begin	
			out0 = Rout6;
		end

		8'd7: begin	
			out0 = Rout7;
		end

		8'd8: begin	
			out0 = Rout8;
		end

		8'd9: begin	
			out0 = Rout9;
		end

		8'd10: begin	
			out0 = Rout10;
		end

		8'd11: begin	
			out0 = Rout11;
		end

		8'd12: begin	
			out0 = Rout12;
		end

		8'd13: begin	
			out0 = Rout13;
		end

		8'd14: begin	
			out0 = Rout14;
		end
	
		8'd15: begin	
			out0 = Rout15;
		end
		
		default: begin
			out0 = 32'd0;
		end
	endcase
	
	case(addr1)
		
		8'd0: begin	
			out1 = Rout0;
		end
		
		8'd1: begin	
			out1 = Rout1;
		end	

		8'd2: begin	
			out1 = Rout2;
		end

		8'd3: begin	
			out1 = Rout3;
		end
		
		8'd4: begin	
			out1 = Rout4;
		end

		8'd5: begin	
			out1 = Rout5;
		end

		8'd6: begin	
			out1 = Rout6;
		end

		8'd7: begin	
			out1 = Rout7;
		end

		8'd8: begin	
			out1 = Rout8;
		end

		8'd9: begin	
			out1 = Rout9;
		end

		8'd10: begin	
			out1 = Rout10;
		end

		8'd11: begin	
			out1 = Rout11;
		end

		8'd12: begin	
			out1 = Rout12;
		end

		8'd13: begin	
			out1 = Rout13;
		end

		8'd14: begin	
			out1 = Rout14;
		end
	
		8'd15: begin	
			out1 = Rout15;
		end
		
		default: begin
			out1 = 32'd0;
		end
		endcase
		
end

//Writing is sequential, But assigning the write signal to a register is not. 
always @(*)
begin
	if (ctrw == 1'd1) begin
			case(addr0)

				8'd0: begin
					rw[0] = ctrw;
					rw[15:1] = 15'd0;
				end
			
				8'd1: begin
					rw[0] = 1'd0;
					rw[1] = ctrw;
					rw[15:2] = 14'd0;
				end		
				
				8'd2: begin
					rw[1:0] = 2'd0;
					rw[2] = ctrw;
					rw[15:3] = 13'd0;
				end		
					
				8'd3: begin
					rw[2:0] = 3'd0;
					rw[3] = ctrw;
					rw[15:4] = 12'd0;
				end		
						
				8'd4: begin
					rw[3:0] = 4'd0;
					rw[4] = ctrw;
					rw[15:5] = 11'd0;
				end		
							
				8'd5: begin
					rw[4:0] = 5'd0;
					rw[5] = ctrw;
					rw[15:6] = 10'd0;
				end		
								
				8'd6: begin
					rw[5:0] = 6'd0;
					rw[6] = ctrw;
					rw[15:7] = 9'd0;
				end		
								
				8'd7: begin
					rw[6:0] = 7'd0;
					rw[7] = ctrw;
					rw[15:8] = 8'd0;
				end		
								
				8'd8: begin
					rw[7:0] = 8'd0;
					rw[8] = ctrw;
					rw[15:9] = 7'd0;
				end		
								
				8'd9: begin
					rw[8:0] = 9'd0;
					rw[9] = ctrw;
					rw[15:10] = 6'd0;
				end		
								
				8'd10: begin
					rw[9:0] = 10'd0;
					rw[10] = ctrw;
					rw[15:11] = 5'd0;
				end		
								
				8'd11: begin
					rw[10:0] = 11'd0;
					rw[11] = ctrw;
					rw[15:12] = 4'd0;
				end		
								
				8'd12: begin
					rw[11:0] = 12'd0;
					rw[12] = ctrw;
					rw[15:13] = 3'd0;
				end		
								
				8'd13: begin
					rw[12:0] = 13'd0;
					rw[13] = ctrw;
					rw[15:14] = 2'd0;
				end		
				
				8'd14: begin
					rw[13:0] = 14'd0;
					rw[14] = ctrw;
					rw[15] = 1'd0;
				end		
				
				8'd15: begin
					rw[14:0] = 15'd0;
					rw[15] = ctrw;
				end		
				
				default: begin
					rw = 8'd0;
				end
			endcase
	end
	else begin
		rw = 8'd0;
	end
end
endmodule
