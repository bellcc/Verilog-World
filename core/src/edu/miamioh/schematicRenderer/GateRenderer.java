package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static edu.miamioh.util.Constants.gateSize;
import static edu.miamioh.util.Constants.scaleFactor;
import static edu.miamioh.util.Constants.frame;

/**
 * Created by shaffebd.
 */
class GateRenderer {

    private ShapeRenderer renderer;
//    Color color;

    /**
     * Default constructor
     */
    GateRenderer(ShapeRenderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Renders a specified Gate of type {@link GateType} at the x and y
     * coordinates.
     *  @param type The Type of Gate to be rendered. Includes Ports.
     * @param x    The X coordinate to draw the gate at. Corresponds to the CX
     *             of the gate.
     * @param y    The Y coordinate to draw the gate at. Corresponds to the CY
     *             of the gate.
     * @param color The color of the gate.
     */
    public void render(GateType type, int x, int y, Color color) {

        switch (type) {

            case INPUT:
                drawINPUT(x, y, color);
                break;

            case OUTPUT:
                drawOUTPUT(x, y, color);
                break;

            case AND:
                drawAND(x, y, color);
                break;

            case NAND:
                drawNAND(x, y, color);
                break;

            case OR:
                drawOR(x, y, color);
                break;

            case NOR:
                drawNOR(x, y, color);
                break;

            case XOR:
                drawXOR(x, y, color);
                break;

            case XNOR:
                drawXNOR(x, y, color);
                break;

            case NOT:
                drawNOT(x, y, color);
                break;

            case WIRE:
                drawWire(x, y, color);
                break;

            case REG:
                drawReg(x, y, color);
                break;

            default:
                drawBlank(x, y, color);
                break;
        }
    }

    /**
     * Draws an INPUT gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location of the center in pixels relative to the bottom left corner.
     * @param cy Vertical location of the center in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawINPUT(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        //Differing offsets because the object should be rectangular.
        int lx = cx - gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 4;
        int rx = cx + gateSize * scaleFactor / 4;
        int rxp = cx + gateSize * scaleFactor / 2;
        int ty = cy + gateSize * scaleFactor / 4;
        int w = rx - lx;
        int h = ty - by;

//        renderer.line(lx, by, lx, ty);
//        renderer.line(lx, by, rx, by);
//        renderer.line(lx, ty, rx, ty);
//        renderer.line(rx, ty, rxp, cy);
//        renderer.line(rx, by, rxp, cy);
        renderer.rect(lx, by, w, h);
        renderer.triangle(rx, by, rxp, cy, rx, ty);
        renderer.end();

        if (frame) frame(cx, cy);
    }

    /**
     * Draws an OUTPUT gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location in pixels relative to the bottom left corner.
     * @param cy Vertical location in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawOUTPUT(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        //Differing offsets because the object should be rectangular.
        int lx = cx - gateSize * scaleFactor / 4;
        int lxp = cx - gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 4;
        int rx = cx + gateSize * scaleFactor / 2;
        int ty = cy + gateSize * scaleFactor / 4;
        int w = rx - lx;
        int h = ty - by;

//        renderer.line(lxp, cy, lx, ty);
//        renderer.line(lx, ty, rx, ty);
//        renderer.line(rx, ty, rx, by);
//        renderer.line(rx, by, lx, by);
//        renderer.line(lx, by, lxp, cy);
        renderer.rect(lx, by, w, h);
        renderer.triangle(lx, by, lxp, cy, lx, ty);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws a NOT gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location in pixels relative to the bottom left corner.
     * @param cy Vertical location in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawNOT(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        int lx = cx - gateSize * scaleFactor / 2;
        int rxt = cx + gateSize * scaleFactor / 4;
        int rxc = rxt + gateSize * scaleFactor / 8;
        int by = cy - gateSize * scaleFactor / 4;
        int ty = cy + gateSize * scaleFactor / 4;
        int cr = gateSize * scaleFactor / 8;

//        renderer.line(lx, by, rxt, cy);
//        renderer.line(rxt, cy, lx, ty);
//        renderer.line(lx, ty, lx, by);
        renderer.triangle(lx, by, lx, ty, rxt, cy);
        renderer.circle(rxc, cy, cr);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws an AND gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location in pixels relative to the bottom left corner.
     * @param cy Vertical location in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawAND(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        int lx = cx - gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 2;
        int ty = cy + gateSize * scaleFactor / 2;
        int cr = gateSize * scaleFactor / 2;
        int w = cx - lx;
        int h = ty - by;
//        int curveFactor = scaleFactor / 4;

//        renderer.line(cx, by, lx, by);
//        renderer.line(lx, by, lx, ty);
//        renderer.line(lx, ty, cx, ty);
        renderer.rect(lx, by, w, h);
        renderer.circle(cx, cy, cr);
//        //top curve
//        renderer.curve(cx, ty, cx + gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
//                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 3 + curveFactor, cy +
//                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
//        //bottom curve
//        renderer.curve(cx, by, cx + gateSize * scaleFactor / 6 + curveFactor, cy - gateSize *
//                scaleFactor / 3 - curveFactor, cx + gateSize * scaleFactor / 3 + curveFactor, cy -
//                gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws an AND gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location in pixels relative to the bottom left corner.
     * @param cy Vertical location in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawNAND(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        //Draw AND
        int lx = cx - gateSize * scaleFactor * 3 / 4;
        int rx = cx - gateSize * scaleFactor / 4;
        int by = cy - gateSize * scaleFactor / 2;
        int ty = cy + gateSize * scaleFactor / 2;
        int w = rx - lx;
        int h = ty - by;
        int cr = gateSize * scaleFactor / 2;
//        int curveFactor = scaleFactor / 4;

//        renderer.line(cx, by, lx, by);
//        renderer.line(lx, by, lx, ty);
//        renderer.line(lx, ty, cx, ty);
        renderer.rect(lx, by, w, h);
        renderer.circle(rx, cy, cr);
//        //top curve
//        renderer.curve(cx, ty, cx + gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
//                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 3 + curveFactor, cy +
//                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
//        //bottom curve
//        renderer.curve(cx, by, cx + gateSize * scaleFactor / 6 + curveFactor, cy - gateSize *
//                scaleFactor / 3 - curveFactor, cx + gateSize * scaleFactor / 3 + curveFactor, cy -
//                gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);

        //Draw NOT
        int cxn = rx + gateSize * scaleFactor / 2;
        int crn = gateSize * scaleFactor / 8;
        renderer.circle(cxn, cy, crn);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws an OR gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location in pixels relative to the bottom left corner.
     * @param cy Vertical location in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawOR(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        int lx = cx - gateSize * scaleFactor / 2;
        int lxrc = lx + gateSize * scaleFactor / 4;
        int rx = cx + gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 2;
        int ty = cy + gateSize * scaleFactor / 2;
        int curveFactor = scaleFactor / 6;

        //top curve
        renderer.curve(lx, ty, cx - gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy +
                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
        //bottom curve
        renderer.curve(lx, by, cx - gateSize * scaleFactor / 6 + curveFactor, cy - gateSize *
                scaleFactor / 3 - curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy -
                gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);
        //side curve
        renderer.curve(lx, by, lxrc, cy - gateSize * scaleFactor / 3, lxrc, cy + gateSize *
                scaleFactor / 3, lx, ty, 1000);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws an OR gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location in pixels relative to the bottom left corner.
     * @param cy Vertical location in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawNOR(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        int lx = cx - gateSize * scaleFactor / 2;
        int lxrc = lx + gateSize * scaleFactor / 4;
        int rx = cx + gateSize * scaleFactor / 4;
        int by = cy - gateSize * scaleFactor / 2;
        int ty = cy + gateSize * scaleFactor / 2;
        int curveFactor = scaleFactor / 6;

        //top curve
        renderer.curve(lx, ty, cx - gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy +
                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
        //bottom curve
        renderer.curve(lx, by, cx - gateSize * scaleFactor / 6 + curveFactor, cy - gateSize *
                scaleFactor / 3 - curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy -
                gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);
        //side curve
        renderer.curve(lx, by, lxrc, cy - gateSize * scaleFactor / 3, lxrc, cy + gateSize *
                scaleFactor / 3, lx, ty, 1000);

        //Draw NOT
        renderer.set(ShapeRenderer.ShapeType.Filled);
        int rxc = rx + gateSize * scaleFactor / 8;
        int cr = gateSize * scaleFactor / 8;
        renderer.circle(rxc, cy, cr);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws an XOR gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location in pixels relative to the bottom left corner.
     * @param cy Vertical location in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawXOR(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        int lxr = cx - gateSize * scaleFactor / 4;
        int lxrc = lxr + gateSize * scaleFactor / 4; //Redundant (could be just cx) but left it for consistency
        int lxl = cx - gateSize * scaleFactor / 2;
        int lxlc = lxl + gateSize * scaleFactor / 4;
        int rx = cx + gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 2;
        int ty = cy + gateSize * scaleFactor / 2;
        int curveFactor = scaleFactor / 6;

        //top curve
        renderer.curve(lxr, ty, cx - gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy +
                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
        //bottom curve
        renderer.curve(lxr, by, cx - gateSize * scaleFactor / 6 + curveFactor, cy - gateSize *
                scaleFactor / 3 - curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy -
                gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);
        //side curve (right)
        renderer.curve(lxr, by, lxrc, cy - gateSize * scaleFactor / 3, lxrc, cy + gateSize *
                scaleFactor / 3, lxr, ty, 1000);
        //side curve (left)
        renderer.curve(lxl, by, lxlc, cy - gateSize * scaleFactor / 3, lxlc, cy + gateSize *
                scaleFactor / 3, lxl, ty, 1000);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws an XOR gate at a variable location with a variable scale factor.
     *  @param cx Horizontal location in pixels relative to the bottom left corner.
     * @param cy Vertical location in pixels relative to the bottom left corner.
     * @param color The color of the gate.
     */
    private void drawXNOR(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        int lxr = cx - gateSize * scaleFactor / 4;
        int lxrc = lxr + gateSize * scaleFactor / 4; //Redundant (could be just cx) but left it for consistency
        int lxl = cx - gateSize * scaleFactor / 2;
        int lxlc = lxl + gateSize * scaleFactor / 4;
        int rx = cx + gateSize * scaleFactor / 4;
        int by = cy - gateSize * scaleFactor / 2;
        int ty = cy + gateSize * scaleFactor / 2;
        int curveFactor = scaleFactor / 6;

        //top curve
        renderer.curve(lxr, ty, cx - gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy +
                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
        //bottom curve
        renderer.curve(lxr, by, cx - gateSize * scaleFactor / 6 + curveFactor, cy - gateSize *
                scaleFactor / 3 - curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy -
                gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);
        //side curve (right)
        renderer.curve(lxr, by, lxrc, cy - gateSize * scaleFactor / 3, lxrc, cy + gateSize *
                scaleFactor / 3, lxr, ty, 1000);
        //side curve (left)
        renderer.curve(lxl, by, lxlc, cy - gateSize * scaleFactor / 3, lxlc, cy + gateSize *
                scaleFactor / 3, lxl, ty, 1000);

        //Draw NOT
        renderer.set(ShapeRenderer.ShapeType.Filled);
        int rxc = rx + gateSize * scaleFactor / 8;
        int cr = gateSize * scaleFactor / 8;
        renderer.circle(rxc, cy, cr);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws a Wire 'Net' object.
     *  @param cx Center X coordinate.
     * @param cy Center Y coordinate.
     * @param color The color of the gate.
     */
    private void drawWire(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        int lx = cx - gateSize * scaleFactor / 2;
        int rx = cx + gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 4;
        int w = gateSize * scaleFactor;
        int h = gateSize * scaleFactor / 2;

        renderer.rect(lx, by, w, h);
        renderer.line(lx, cy, rx, cy);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws a new Reg object. Reg's are composed of Flip-Flops.
     *  @param cx Center X coordinate.
     * @param cy Center Y coordinate.
     * @param color The color of the gate.
     */
    private void drawReg(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        int lx = cx - gateSize * scaleFactor / 2;
        int rx = cx + gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 2;
        int w = gateSize * scaleFactor;

        int clklx = cx - gateSize * scaleFactor / 4;
        int clkrx = cx + gateSize * scaleFactor / 4;
        int clky = cy - gateSize * scaleFactor / 4;

        renderer.rect(lx, by, w, w);
        renderer.line(lx, cy, rx, cy);
        renderer.triangle(clklx, by, clkrx, by, cx, clky);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws a blank Gate.
     *  @param cx Center X coordinate.
     * @param cy Center Y coordinate.
     * @param color The color of the gate.
     */
    private void drawBlank(int cx, int cy, Color color) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        int lx = cx - gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 2;
        int w = gateSize * scaleFactor;

        renderer.rect(lx, by, w, w);

        renderer.end();
        if (frame) frame(cx, cy);
    }

    /**
     * Draws a frame around each gate to make sure they're within the size
     * limits.
     *
     * @param cx Center X coordinate of the frame, same as the calling gate.
     * @param cy Center Y coordinate of the frame, same as the calling gate.
     */
    private void frame(int cx, int cy) {

        //Set to Line in case the calling shape was Filled
        renderer.begin(ShapeRenderer.ShapeType.Line);

        //Draw the box
        int lx = cx - gateSize * scaleFactor / 2;
        int by = cy - gateSize * scaleFactor / 2;
        int width = gateSize * scaleFactor;

        renderer.setColor(Color.BLUE);
        renderer.x(cx, cy, gateSize);
        renderer.rect(lx, by, width, width);

        renderer.end();
    }
}


