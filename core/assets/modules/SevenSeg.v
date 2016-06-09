module SevenSeg (char, a, b, c, d, e, f, g);

input [3:0]char;

wire [3:0] i;
output wire	a;
output wire	b;
output wire	c;
output wire	d;
output wire	e;
output wire	f;
output wire	g;

assign i[0] = char[3];
assign i[1] = char[2];
assign i[2] = char[1];
assign i[3] = char[0];

assign a = !((!i[1]&!i[3])|(!i[0]&i[2])|(i[1]&i[2])|(i[1]&i[3]&!i[0])|(i[0]&!i[1]&!i[2]));
assign b = !((!i[1]&!i[3])|(!i[0]&!i[1])|(i[0]&i[3]&!i[2])|(i[2]&i[3]&!i[0])|(!i[0]&!i[2]&!i[3]));
assign c = !((i[3]&!i[2])|(i[0]&!i[1])|(i[1]&!i[0])|(i[3]&!i[0])|(!i[0]&!i[2]));
assign d = !((i[0]&!i[2])|(i[1]&i[3]&!i[2])|(i[2]&i[3]&!i[1])|(i[0]&i[1]&!i[3])|(i[2]&!i[0]&!i[3])|(!i[0]&!i[1]&!i[3]));
assign e = !((i[0]&i[1])|(i[2]&!i[3])|(i[0]&i[2])|(!i[1]&!i[3]));
assign f = !((i[1]&!i[0])|(i[1]&i[2])|(i[0]&!i[1])|(!i[0]&!i[3]&!i[2]));
assign g = !(i[0]|(!i[3]&i[2])|(!i[1]&i[2])|(i[1]&!i[2]));

endmodule