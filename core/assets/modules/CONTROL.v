module CONTROL (inst, pcrw, pcs, pcb, aluinst, status, regaddr0, regaddr1, regci, regs, regrw, memrw, memaddrs, memaddr, State, NState, go, lock, in, clk, rst);

////Setup
//Control wires
input wire [31:0]inst;
input wire [7:0]in;
input wire clk, rst, go, lock;

//PC wires
output reg pcrw, pcs;
output reg [31:0]pcb;

//ALU wires
output reg [7:0]aluinst;
input wire status;

//Regfile wires
output reg [7:0]regaddr0, regaddr1;
output reg [31:0]regci;
output reg [1:0]regs;
output reg regrw;

//Memory wires
output reg memrw;
output reg [1:0]memaddrs;
output reg [7:0]memaddr;

//Finite State Machine
output reg [7:0]State, NState;
		 reg [7:0]RState;
		 reg [31:0]instFETCH;

//States
parameter STOP 	= 8'h00,	//Do nothing until the GO is given.
			FETCH 	= 8'h01, 	//Read opcode from Mem at addr=pc
			DECODE 	= 8'h02,	//Decode the opcode and decided which execution is necessary
			
			//Executions
			ADD 		= 8'h05,		//Set the ALU to add and set the regaddr's to the given values
			SUB 		= 8'h06,		//Set the ALU to subtract and set the regaddr's to the given values
			HL 		= 8'h07,		//Write the given value into the RegFile at the given addr
			HW 		= 8'h08,		//Write to the Mem at the given addr with the given data
			CP 		= 8'h09,		//Copy from the first RegFile addr to the second
			MV 		= 8'h0A,		//Move from the first RegFile addr to the second
			BEQ 		= 8'h0B,	//Branch to the given PC if regaddr0 == 0
			BLT 		= 8'h0C,	//Branch to the given PC if regaddr0 < 0
			JUMP 		= 8'h0D,	//Set the PC to the given value
			
			UPDATE 	= 8'h04,	//Sync all the registers and update the PC
			HOLD 		= 8'h0E,	//Begin waiting for something to sync
			WAIT 		= 8'h0F,	//Wait for another tick
			RESUME 	= 8'h11,//Sync complete, resume the instruction
			
			LATCH 	= 8'h12;	//Used to prevent the program from running multiple times due to my slow button presses.
			
//Supplementary parameters
parameter read 	= 1'd0,
			write 	= 1'd1,	
			rfao 		= 2'd0,	//regs -> aluO
			rfci 		= 2'd1,	//regs -> regci
			rfmo 		= 2'd2,	//regs -> memout
			rfro1 	= 2'd3,	//regs -> regout1
			pcpc 		= 1'd0,	//pcs -> pcout
			pcbr 		= 1'd1,	//pcs -> pcb
			alunop 	= 8'd0,	//ALUinst NOP
			alueqz 	= 8'd1,	//ALUinst EQZ
			alultz 	= 8'd2,	//ALUinst LTZ
			aluadd 	= 8'd3,	//ALUinst ADD
			alusub 	= 8'd4,	//ALUinst SUB
			mempo 	= 2'd0,	//Memaddrs -> pcout
			memr0 	= 2'd1, 	//Memaddrs -> regout0
			memco 	= 2'd2;	//Memaddrw -> memci

always @(posedge clk or negedge rst) begin
	if(rst == 1'd0) begin
		State <= STOP;
	end
	else begin
		State <= NState;
	end
end

always @(*) begin
	case(State)

		STOP: begin
			if(!go) begin
				NState = LATCH;
			end
			else begin
				NState = STOP;
			end
		end
		
		LATCH: begin
			if(!lock) begin
				NState = FETCH;
			end
			else begin
				NState = LATCH;
			end
		end
		
		FETCH: begin
			NState = DECODE;
		end
		
		DECODE: begin
			NState = HOLD;
		end
		
		//Executions
		ADD: begin
			NState = UPDATE;
		end
		
		SUB: begin
			NState = UPDATE;
		end
		
		HL: begin
			NState = UPDATE;
		end
		
		HW: begin
			NState = UPDATE;
		end
		
		CP: begin
			NState = UPDATE;
		end
		
		MV: begin
			NState = DECODE;
		end
		
		BEQ: begin
			NState = UPDATE;
		end
		
		BLT: begin
			NState = UPDATE;
		end
		
		JUMP: begin
			NState = UPDATE;
		end
		
		UPDATE: begin
			NState = HOLD;
		end
		
		HOLD: begin
			NState = WAIT;
		end
		
		WAIT: begin
			NState = RESUME;
		end
		
		RESUME: begin
			NState = RState;
		end
		
		default: begin
			NState = STOP;
		end
	endcase
end

always @(posedge clk or negedge rst) begin

	if(rst == 1'd0) begin
		
		//Set Resume State
		RState <= STOP;
		
		//Set RWs to Read
		pcrw <= read;
		regrw <= read;
		memrw <= read;
		
		//Set Switches to default
		pcs <= pcpc; 
		regs <= rfci;
		memaddrs <= mempo;
		
		//Set Control outputs
		pcb <= 32'd0;
		regci <= 32'd0;
		memaddr <= 8'd0;
		regaddr0 <= 8'd0;
		regaddr1 <= 8'd0;
		
	end
	else begin

		case(State)
	
			STOP: begin
				pcrw <= read;
				regrw <= read;
				memrw <= read;
				pcs <= pcpc;
				regs <= rfci;
				memaddrs <= mempo;
				memaddr <= 8'd0;
				pcb <= 32'd0;
				regci <= 32'd0;
				regaddr0 <= in;
				regaddr1 <= 8'd0;
				aluinst <= alunop;
				RState <= STOP;
			end
			
			LATCH: begin
				pcrw <= read;
				regrw <= read;
				memrw <= read;
				pcs <= pcpc;
				regs <= rfci;
				memaddrs <= mempo;
				memaddr <= memaddr;
				pcb <= pcb;
				regci <= regci;
				regaddr0 <= regaddr0;
				regaddr1 <= regaddr0;
				aluinst <= alunop;
				RState <= STOP;
			end
									
			FETCH: begin
				instFETCH <= inst;
			
				//prevent latches
				pcrw <= read;
				regrw <= read;
				memrw <= read;
				pcs <= pcpc;
				regs <= rfci;
				memaddrs <= mempo;
				memaddr <= memaddr;
				pcb <= pcb;
				regci <= regci;
				regaddr0 <= regaddr0;
				regaddr1 <= regaddr1;
				aluinst <= alunop;
				RState <= STOP;
			end

			DECODE: begin
				//Determine OP code
				case (instFETCH[7:0])
				
				ADD: begin
					regaddr0 <= instFETCH[15:8];//aluA
					regaddr1 <= instFETCH[23:16];//aluB
					regci <= regci;
					regs <= rfao;
					aluinst <= aluadd;
					memaddrs <= mempo;
					RState <= ADD;
				end
				
				SUB: begin
					regaddr0 <= instFETCH[15:8];//aluA
					regaddr1 <= instFETCH[23:16];//aluB
					regci <= regci;
					regs <= rfao;
					aluinst <= alusub;
					memaddrs <= mempo;
					RState <= SUB;
				end
				
				HL: begin
					regaddr0 <= instFETCH[15:8];//Load INTO REG
					memaddr <= instFETCH[23:16];//Load FROM MEM
					memaddrs <= memco;
					regs <= rfmo;
					RState <= HL;
					
					//prevent latches
					regaddr1 <= regaddr1;
					aluinst <= alunop;
					regci <= 32'd0;
				end
				
				CP: begin
					regaddr0 <= instFETCH[23:16];//Move TO
					regaddr1 <= instFETCH[15:8];//Move FROM
					regs <= rfro1;
					RState <= CP;
					
					//prevent latches
					regci <= regci;
					aluinst <= alunop;
					memaddrs <= mempo;
				end
				
				MV: begin
					regaddr0 <= instFETCH[23:16];//Move TO
					regaddr1 <= instFETCH[15:8];//Move FROM
					regs <= rfro1;
					RState <= MV;
					
					//prevent latches
					regci <= regci;
					aluinst <= alunop;
					memaddrs <= mempo;
				end
				
				BEQ: begin
					regaddr0 <= instFETCH[15:8];//aluA
					aluinst <= alueqz;
					RState <= BEQ;
					
					//prevent latches
					regaddr1 <= regaddr1;
					regci <= regci;
					regs <= rfci;
					memaddrs <= mempo;
				end
				
				BLT: begin
					regaddr0 <= instFETCH[15:8];//aluA
					aluinst <= alultz;
					RState <= BLT;
					
					//prevent latches
					regaddr1 <= regaddr1;
					regci <= regci;
					regs <= rfci;
					memaddrs <= mempo;
				end
				
				JUMP: begin
					regaddr0 <= regaddr0;
					regaddr1 <= regaddr1;
					regci <= regci;
					regs <= rfci;
					aluinst <= alunop;
					memaddrs <= mempo;
					RState <= JUMP;
				end
				
				default: begin
					regaddr0 <= regaddr0;
					regaddr1 <= regaddr1;
					regci <= regci;
					regs <= rfci;
					aluinst <= alunop;
					memaddrs <= memaddrs;
					RState <= STOP;
				end
				
				endcase
				
				//prevent latches
				instFETCH <= instFETCH;
				pcrw <= read;
				regrw <= read;
				memrw <= read;
				pcs <= pcpc;
				pcb <= pcb;
			end

			/*Executions:
			ADD and SUB: Assign a register to save previously computed values to.
			HL: Copies a number from the Memory into the Register.
			CP and MV: Copies a value from regaddr1 to regaddr0. Move then overwrites
				whatever was in regaddr1 with zeroes.
			BEQ: Overwrites the PC with a specific value if regaddr0 == 0.
			BLT: Overwrites the PC with a specific value if regaddr0 < 0.
			JUMP: Overwrites the PC with a specific value.
			*/
			ADD: begin
				regaddr0 <= instFETCH[31:24];//regaddr to write to
				regaddr1 <= regaddr1;
				regci <= regci;
				regs <= rfao;
				aluinst <= alunop;
				regrw <= write;
				
				//prevent latches
				instFETCH <= instFETCH;
				pcrw <= write;
				memrw <= read;
				pcs <= pcpc;
				memaddrs <= mempo;
				pcb <= pcb;
				RState <= STOP;
			end
			
			SUB: begin
				regaddr0 <= instFETCH[31:24];//regaddr to write to
				regaddr1 <= regaddr1;
				regci <= regci;
				regs <= rfao;
				aluinst <= alunop;
				regrw <= write;
				
				//prevent latches
				instFETCH <= instFETCH;
				pcrw <= write;
				memrw <= read;
				pcs <= pcpc;
				memaddrs <= mempo;
				pcb <= pcb;
				RState <= STOP;
			end
			
			HL: begin
				//relevant controls
				regaddr0 <= instFETCH[15:8];//Load INTO REG
				memaddr <= instFETCH[23:16];//Load FROM MEM
				memaddrs <= memco;
				memrw <= read;
				regs <= rfmo;
				regrw <= write;
				RState <= UPDATE;
				
				//prevent latches
				instFETCH <= instFETCH;
				pcrw <= write;
				pcs <= pcpc;
				pcb <= pcb;
				aluinst <= alunop;
				regaddr1 <= regaddr1;
				regci <= regci;
			end
			
			CP: begin
				//relevant controls
				regaddr0 <= instFETCH[23:16];
				regaddr1 <= instFETCH[15:8];
				regs <= rfro1;
				regrw <= write;
				
				//prevent latches
				instFETCH <= instFETCH;
				pcrw <= write;
				memrw <= read;
				pcs <= pcpc;
				memaddrs <= mempo;
				pcb <= pcb;
				regci <= regci;
				aluinst <= alunop;
				RState <= STOP;
			end
			
			MV: begin
				//relevant controls
				regaddr0 <= instFETCH[23:16];
				regaddr1 <= instFETCH[15:8];
				regs <= rfro1;
				regrw <= write;
				instFETCH[7:0] <= 8'h06;
				instFETCH[15:8] <= instFETCH[15:8];
				instFETCH[23:16] <= 8'h00;
				instFETCH[31:24] <= instFETCH[31:24];
				
				//prevent latches
				pcrw <= write;
				memrw <= read;
				pcs <= pcpc;
				memaddrs <= mempo;
				pcb <= pcb;
				regci <= regci;
				aluinst <= alunop;
				RState <= STOP;
			end
			
			BEQ: begin
				if(status == 1'd1) begin
					pcs <= pcbr;
					pcb[7:0] <= instFETCH[23:16];
					pcb[31:8] <= 24'd0;
				end
				else begin
					pcs <= pcpc;
					pcb <= 32'd0;
				end
				
				//prevent latches
				instFETCH <= instFETCH;
				memrw <= read;
				memaddrs <= mempo;
				pcrw <= write;
				regaddr0 <= regaddr0;
				regaddr1 <= regaddr1;
				regci <= regci;
				regs <= rfci;
				aluinst <= alunop;
				regrw <= read;
				RState <= STOP;
			end
			
			BLT: begin
				if(status == 1'd1) begin
					pcs <= pcbr;
					pcb[7:0] <= instFETCH[23:16];
					pcb[31:8] <= 24'd0;
				end
				else begin
					pcs <= pcpc;
					pcb <= 32'd0;
				end
				
				//prevent latches
				instFETCH <= instFETCH;
				memrw <= read;
				memaddrs <= mempo;
				pcrw <= write;
				regaddr0 <= regaddr0;
				regaddr1 <= regaddr1;
				regci <= regci;
				regs <= rfci;
				aluinst <= alunop;
				regrw <= read;
				RState <= STOP;
			end
			
			JUMP: begin
				pcs <= pcbr;
				pcb[7:0] <= instFETCH[15:8];
				pcb[31:8] <= 24'd0;
				
				//prevent latches
				instFETCH <= instFETCH;
				memrw <= read;
				memaddrs <= mempo;
				pcrw <= write;
				regaddr0 <= regaddr0;
				regaddr1 <= regaddr1;
				regci <= regci;
				regs <= rfci;
				aluinst <= alunop;
				regrw <= read;
				RState <= STOP;
			end
			
			/*
			Wait/Sync segment; Maintains the current state of all the variables
			and delays the FSM by 3 ticks to allow all signals to come to rest.
			*/
			HOLD: begin
				RState <= RState;
				instFETCH <= instFETCH;
				pcrw <= read;
				regrw <= regrw;
				memrw <= memrw;
				pcs <= pcs;
				regs <= regs;
				memaddrs <= memaddrs;
				pcb <= pcb;
				regci <= regci;
				regaddr0 <= regaddr0;
				regaddr1 <= regaddr1;
				aluinst <= aluinst;
			end
			
			WAIT: begin
				RState <= RState;
				instFETCH <= instFETCH;
				pcrw <= pcrw;
				regrw <= regrw;
				memrw <= memrw;
				pcs <= pcs;
				regs <= regs;
				memaddrs <= memaddrs;
				pcb <= pcb;
				regci <= regci;
				regaddr0 <= regaddr0;
				regaddr1 <= regaddr1;
				aluinst <= aluinst;
			end
			
			RESUME: begin
				RState <= RState;
				instFETCH <= instFETCH;
				pcrw <= pcrw;
				regrw <= regrw;
				memrw <= memrw;
				pcs <= pcs;
				regs <= regs;
				memaddrs <= memaddrs;
				pcb <= pcb;
				regci <= regci;
				regaddr0 <= regaddr0;
				regaddr1 <= regaddr1;
				aluinst <= aluinst;
			end
			
			/*
			Update; Sets all the controls back to defaults
			to prepare for the next fetch.
			*/
			UPDATE: begin
				instFETCH <= 32'd0;
				pcrw <= read;
				regrw <= read;
				memrw <= read;
				pcs <= pcpc;
				regs <= rfci;
				memaddrs <= mempo;
				pcb <= 32'd0;
				regci <= 32'd0;
				regaddr0 <= 8'd0;
				regaddr1 <= 8'd0;
				aluinst <= alunop;
				RState <= FETCH;
			end
			
			//Defaults are the same as the Update except it halts the FSM.
			default: begin
				instFETCH <= 32'd0;
				pcrw <= read;
				regrw <= read;
				memrw <= read;
				pcs <= pcpc;
				regs <= rfci;
				memaddrs <= mempo;
				pcb <= 32'd0;
				regci <= 32'd0;
				regaddr0 <= 8'd0;
				regaddr1 <= 8'd0;
				aluinst <= alunop;
				RState <= STOP;
			end
		endcase
	end
end

endmodule
