module VM(in, clk, rst, button0, button1, button2, a, b, c, d, e, f, g);

/*
Top level module. Used to instantiate the next level modules, and do some logic to hook them all together.
*/
input wire [16:0]in;//Switches
input wire rst; //Switch
input wire clk; //Button R24 or PIN_Y2
input wire button0, button1, button2; //Buttons

wire [7:0]State, NState; //FSM outputs from the Control.

wire [31:0]char; //8 character hex display
wire [3:0]hex0, hex1, hex2, hex3, hex4, hex5, hex6, hex7;
assign char[3:0] = hex0;//Assignments so I don't have to type out the char vectors so much
assign char[7:4] = hex1;
assign char[11:8] = hex2;
assign char[15:12] = hex3;
assign char[19:16] = hex4;
assign char[23:20] = hex5;
assign char[27:24] = hex6;
assign char[31:28] = hex7;

//=======================//

//PC
wire pcrw, pcs;
wire [31:0]pcb, pcout;

//Memory
wire [31:0]memin, memout;
reg [7:0]memaddr;
wire [7:0]memco;
wire [1:0]memaddrs;
wire memrw;

//REGFILE
reg [31:0]rfin;
wire [31:0]rfci;
wire [31:0]rfout0, rfout1;
wire [7:0]rfaddr0, rfaddr1;
wire [1:0]rfs;
wire rfrw;

//ALU - does not respond to RST or CLK.
wire [31:0]aluA, aluB, aluO;
wire [7:0]aluinst;

//Control
wire [31:0]inst;
assign inst = memout;
wire status;
wire go; 
assign go = button0;
wire lock;
assign lock = button2;

//========================//

//Logic to set up for Control

//ALU
assign aluA = rfout0;
assign aluB = rfout1;

reg [31:0]aluBuffer;
//Buffer the ALU output so that the RegFile doesn't write it to the wrong place.
always @(posedge clk or negedge rst) begin
	if(rst == 1'd0) begin
		aluBuffer <= 32'd0;
	end
	else begin
		aluBuffer <= aluO;
	end
end

//Memory
always @(*) begin
	case(memaddrs)//Mem address switch

		2'd0: begin //Pass-through the pc as usual
			memaddr = pcout[7:0];
		end

		2'd1: begin //Pass-through the register for a specific memory allocation
			memaddr = rfout0[7:0];
		end
		
		2'd2: begin //Get addr from the Control
			memaddr = memco;
		end
		
		default: begin
			memaddr = 8'd0;
		end
	endcase
//	case (memins)//Mem input switch
	
//		1'd0: begin
//			memin = 
end

//REGFILE
always @(*) begin
	case(rfs)//RegFile input switch

		2'd0: begin //Input the ALU mathematical output
			rfin = aluBuffer;
		end

		2'd1: begin //Input a special value from the Control
			rfin = rfci;
		end
	
		2'd2: begin //Input the memory output
			rfin = memout;
		end

		2'd3: begin //Input the RegFile secondary output
			rfin = rfout1;
		end
	endcase
end

//========================//

//Declarations

PC pc (pcb, pcs, pcout, clk, pcrw, rst);
MEMORY mem (memaddr, clk, memin, memrw, memout);
REGFILE rf (rfin, rfout0, rfout1, rfaddr0, rfaddr1, rfrw, clk, rst);
ALU alu (aluinst, aluA, aluB, aluO, status);
CONTROL ct (inst, pcrw, pcs, pcb, aluinst, status, rfaddr0, rfaddr1, rfci, rfs, rfrw, memrw, memaddrs, memco, State, NState, go, lock, in[7:0], clk, rst);

//========================//

//Logic for testing
/*
//PC testing
assign char = pcout;
*/
/*
//Memory testing
assign memaddr[4:0] = in[4:0];
assign memin[11:0] = in[16:5];
assign memrw = in[16];
assign char[15:0] = rfout0[15:0];
assign char[31:16] = rfout1[15:0];
*/
/*
//REGFILE testing
//Assign switches to the RF input
assign rfaddr0[3:0] = in[3:0];
assign rfaddr1[3:0] = in[7:4];
assign rfin[7:0] = in[15:8];
assign rfrw = in[16];
assign char[15:0] = RFout0[15:0];
assign char[31:16] = RFout1[15:0];
*/
/*
//ALU testing
assign aluinst = in[7:0];
assign aluA = in[11:8];
assign aluB = in[15:12];
assign char = aluO[3:0];
*/

//CONTROL testing
//Display
assign hex0 = State[3:0];
assign hex1 = State[7:4];
assign hex2 = rfout0[3:0];
assign hex3 = rfout0[7:4];
assign hex4 = rfout0[11:8];
assign hex5 = rfout0[15:12];
assign hex6 = pcout[3:0];
assign hex7 = pcout[7:4];

//Instructions
/*
assign inst[3:0] = in[3:0];//OPcode
assign inst[7:4] = 4'd0;//Filler
assign inst[11:8] = in[7:4];//Register 1
assign inst[15:12] = 4'd0;//Filler
assign inst[19:16] = in[11:8];//Register 2
assign inst[23:20] = 4'd0;//Filler
assign inst[27:24] = in[15:12];//Optional third byte
assign inst[31:28] = 4'd0;//Filler
*/
/*
//VM testing
//Display
assign hex0[1:0] = rfs;
assign hex0[3:2] = 2'd0;
assign hex4 = in[3:0];
assign hex5 = in[7:4];
assign hex1 = rfin[3:0];
assign hex2 = rfin[11:8];
assign hex3 = rfin[27:24];
//Instructions
assign rfs = in[1:0];//Switches 0-1
*/
//========================//
//Output each 32-bit hex code to the display
output wire [7:0]a, b, c, d, e, f, g;

SevenSeg disp0 (char[3:0], a[0], b[0], c[0], d[0], e[0], f[0], g[0]);
SevenSeg disp1 (char[7:4], a[1], b[1], c[1], d[1], e[1], f[1], g[1]);
SevenSeg disp2 (char[11:8], a[2], b[2], c[2], d[2], e[2], f[2], g[2]);
SevenSeg disp3 (char[15:12], a[3], b[3], c[3], d[3], e[3], f[3], g[3]);
SevenSeg disp4 (char[19:16], a[4], b[4], c[4], d[4], e[4], f[4], g[4]);
SevenSeg disp5 (char[23:20], a[5], b[5], c[5], d[5], e[5], f[5], g[5]);
SevenSeg disp6 (char[27:24], a[6], b[6], c[6], d[6], e[6], f[6], g[6]);
SevenSeg disp7 (char[31:28], a[7], b[7], c[7], d[7], e[7], f[7], g[7]);

endmodule
