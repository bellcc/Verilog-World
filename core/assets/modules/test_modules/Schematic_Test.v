module schematic_Test (in, out);

input in;

wire a, b, c, d, e, f, g, h, i, j, k, l, m, n;
wire [1:0]o;
assign o = 2'b11;

output [15:0]out;

assign a = in;
assign b = ~in;
assign c = -in;
assign d = a * b;
assign e = a + b;
assign f = a & b;
assign g = a < b;
assign h = a ! b;
assign i = a && b;
assign j = a || b;
assign k = a ? b : c;
assign l = (a | b);
assign m = o[0];
assign n = 1'b1;

assign out[0] = a;
assign out[1] = b;
assign out[2] = c;
assign out[3] = d;
assign out[4] = e;
assign out[5] = f;
assign out[6] = g;
assign out[7] = h;
assign out[8] = i;
assign out[9] = j;
assign out[10] = k;
assign out[11] = l;
assign out[12] = m;
assign out[13] = n;
assign out[15:14] = o;

endmodule