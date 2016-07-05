package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.BSpline;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import edu.miamioh.verilogWorld.VerilogWorldMain;

import static edu.miamioh.schematicRenderer.SchematicRendererController.frame;
import static edu.miamioh.schematicRenderer.SchematicRendererController.gateSize;
import static edu.miamioh.schematicRenderer.SchematicRendererController.scaleFactor;

/**
 * Created by shaffebd.
 */
class GateRenderer {

    private ShapeRenderer renderer;

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

        //Differing offsets because the object should be rectangular.
        float lx = cx - gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 4;
        float rx = cx + gateSize * scaleFactor / 4;
        float rxp = cx + gateSize * scaleFactor / 2;
        float ty = cy + gateSize * scaleFactor / 4;
        float w = rx - lx;
        float h = ty - by;

        //fill in
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.rect(lx, by, w, h);
        renderer.triangle(rx, by, rxp, cy, rx, ty);
        //Outline
//        renderer.setColor(Color.BLACK);
//        renderer.rectLine(lx, by, lx, ty, scaleFactor / 20);
//        renderer.rectLine(lx, by, rx, by, scaleFactor / 20);
//        renderer.rectLine(lx, ty, rx, ty, scaleFactor / 20);
//        renderer.rectLine(rx, ty, rxp, cy, scaleFactor / 20);
//        renderer.rectLine(rx, by, rxp, cy, scaleFactor / 20);
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
        float lx = cx - gateSize * scaleFactor / 4;
        float lxp = cx - gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 4;
        float rx = cx + gateSize * scaleFactor / 2;
        float ty = cy + gateSize * scaleFactor / 4;
        float w = rx - lx;
        float h = ty - by;

        //Fill in
        renderer.rect(lx, by, w, h);
        renderer.triangle(lx, by, lxp, cy, lx, ty);

        //Outline
//        renderer.setColor(Color.BLACK);
//        renderer.rectLine(lxp, cy, lx, ty, scaleFactor / 20);
//        renderer.rectLine(lx, ty, rx, ty, scaleFactor / 20);
//        renderer.rectLine(rx, ty, rx, by, scaleFactor / 20);
//        renderer.rectLine(rx, by, lx, by, scaleFactor / 20);
//        renderer.rectLine(lx, by, lxp, cy, scaleFactor / 20);

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
        float lx = cx - gateSize * scaleFactor / 2;
        float rxt = cx + gateSize * scaleFactor / 4;
        float rxc = rxt + gateSize * scaleFactor / 8;
        float by = cy - gateSize * scaleFactor / 4;
        float ty = cy + gateSize * scaleFactor / 4;
        float cr = gateSize * scaleFactor / 8;

        //Fill in
        renderer.triangle(lx, by, lx, ty, rxt, cy);
        renderer.circle(rxc, cy, cr);

        //Outline
//        renderer.setColor(Color.BLACK);
//        renderer.rectLine(lx, by, rxt, cy, scaleFactor / 20);
//        renderer.rectLine(rxt, cy, lx, ty, scaleFactor / 20);
//        renderer.rectLine(lx, ty, lx, by, scaleFactor / 20);
//        renderer.setAutoShapeType(true);
//        renderer.circle(rxc, cy, cr);

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
//        float rx = cx + gateSize * scaleFactor / 2;
        float lx = cx - gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 2;
        float ty = cy + gateSize * scaleFactor / 2;
        float cr = gateSize * scaleFactor / 2;
        float w = cx - lx;
        float h = ty - by;
//        int curveFactor = scaleFactor / 6;

        //Fill in
        renderer.rect(lx, by, w, h);
        renderer.circle(cx, cy, cr);

        //Outline
//        renderer.setColor(Color.BLACK);
//        renderer.setAutoShapeType(true);
//        renderer.line(cx, by, lx, by);
//        renderer.line(lx, by, lx, ty);
//        renderer.line(lx, ty, cx, ty);
//        top curve
//        renderer.curve(cx, ty, cx + gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
//                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 3 + curveFactor, cy +
//                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
//        bottom curve
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
        float lx = cx - gateSize * scaleFactor * 3 / 4;
        float rx = cx - gateSize * scaleFactor / 4;
        float by = cy - gateSize * scaleFactor / 2;
        float ty = cy + gateSize * scaleFactor / 2;
        float w = rx - lx;
        float h = ty - by;
        float cr = gateSize * scaleFactor / 2;
//        int curveFactor = scaleFactor / 6;

        //Fill in
        renderer.rect(lx, by, w, h);
        renderer.circle(rx, cy, cr);

        //Outline
//        renderer.setColor(Color.BLACK);
//        renderer.line(cx, by, lx, by);
//        renderer.line(lx, by, lx, ty);
//        renderer.line(lx, ty, cx, ty);//        //top curve
//        renderer.curve(cx, ty, cx + gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
//                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 3 + curveFactor, cy +
//                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
//        bottom curve
//        renderer.curve(cx, by, cx + gateSize * scaleFactor / 6 + curveFactor, cy - gateSize *
//                scaleFactor / 3 - curveFactor, cx + gateSize * scaleFactor / 3 + curveFactor, cy -
//                gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);

        //Draw NOT

        float cxn = rx + gateSize * scaleFactor / 2;
        float crn = gateSize * scaleFactor / 8;
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

//        //Get the stage
//        Stage stage = SchematicRendererScreen.getScreen().getSchematicStage();
//
//        //Set up the pixmap and texture
//        Vector2 point1 = new Vector2();
//        Table mainTable = new Table();
//        Texture texture;
//        Image image;
//        Path<Vector2> path;
//        float segments = 10000;
//        //Start a new pixmap
//        int scaledGS = (int)(gateSize * scaleFactor);
//        Pixmap pixmap = new Pixmap(scaledGS, scaledGS, Pixmap.Format.RGBA8888);
//        pixmap.setColor(color);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(color);
        float lx = cx - gateSize * scaleFactor / 2;
        float lxrc = lx + gateSize * scaleFactor / 4;
        float rx = cx + gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 2;
        float ty = cy + gateSize * scaleFactor / 2;
        float curveFactor = scaleFactor / 6;
//        float tcx = cx + curveFactor;
//        float tcy = cy + curveFactor;
//        float bcx = tcx;
//        float bcy = cy - curveFactor;
//
//        Vector2[] topCurve = {new Vector2(lx, ty), new Vector2(tcx, tcy), new Vector2(rx, cy)};
//        Vector2[] bottomCurve = {new Vector2(lx, by), new Vector2(bcx, bcy), new Vector2(rx, cy)};
//        Vector2[] sideCurve = {new Vector2(lx, by), new Vector2(lxrc, cy), new Vector2(lx, ty)};
//
//        //draw the path
//        path = new BSpline<>();
//        ((BSpline<Vector2>)path).set(topCurve, 3, false);
//        float t;
//        for(int i = 0; i <= segments; i++){
//            t = i / segments;
//            path.valueAt(point1, t);
//            pixmap.drawPixel((int)point1.x, (int)point1.y);
//        }
//
//        Array<Vector2> queue = new Array<Vector2>(Vector2.class);
//        Color tempColor = new Color();
//        Vector2 point;
//        queue.add(new Vector2(cx, cy));
//
//        while (queue.size > 0){
//            point = queue.pop();
//
//            if (tempColor.set(pixmap.getPixel((int)point.x, (int)point.y)).a == 0){
//                pixmap.drawPixel((int)point.x, (int)point.y);
//                queue.add(new Vector2((int)point.x+1, (int)point.y));
//                queue.add(new Vector2((int)point.x-1, (int)point.y));
//                queue.add(new Vector2((int)point.x, (int)point.y+1));
//                queue.add(new Vector2((int)point.x, (int)point.y-1));
//            }
//        }
//
//
        //top curve
//        int ex = (lx + rx) / 2;
//        int eyt = (ty + cy) / 2;
//        int eyb = (by + cy) / 2;
//        int eh = (int)Math.sqrt(Math.pow(rx - lx, 2) + Math.pow(ty - cy, 2));
//        renderer.ellipse(rx, cy, curveFactor, eh);
        renderer.curve(lx, ty, cx - gateSize * scaleFactor / 6 + curveFactor, cy + gateSize *
                scaleFactor / 3 + curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy +
                gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
        //bottom curve
//        renderer.arc(lx, ty, gateSize * scaleFactor, 270, 60);
        renderer.curve(lx, by, cx - gateSize * scaleFactor / 6 + curveFactor, cy - gateSize *
                scaleFactor / 3 - curveFactor, cx + gateSize * scaleFactor / 6 + curveFactor, cy -
                gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);
        //side curve
//        renderer.setColor(Color.WHITE);
//        renderer.arc(lx, cy, gateSize * scaleFactor / 2, 270, 180);
        renderer.curve(lx, by, lxrc, cy - gateSize * scaleFactor / 3, lxrc, cy + gateSize *
                scaleFactor / 3, lx, ty, 1000);

//        texture = new Texture(pixmap);
//        image = new Image(texture);
//        image.setPosition(cx, cy);
//        stage.addActor(image);
//
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
        float lx = cx - gateSize * scaleFactor / 2;
        float lxrc = lx + gateSize * scaleFactor / 4;
        float rx = cx + gateSize * scaleFactor / 4;
        float by = cy - gateSize * scaleFactor / 2;
        float ty = cy + gateSize * scaleFactor / 2;
        float curveFactor = scaleFactor / 6;

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
//        renderer.set(ShapeRenderer.ShapeType.Filled);
        float rxc = rx + gateSize * scaleFactor / 8;
        float cr = gateSize * scaleFactor / 8;
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
        float lxr = cx - gateSize * scaleFactor / 4;
        float lxrc = lxr + gateSize * scaleFactor / 4; //Redundant (could be just cx) but left it for consistency
        float lxl = cx - gateSize * scaleFactor / 2;
        float lxlc = lxl + gateSize * scaleFactor / 4;
        float rx = cx + gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 2;
        float ty = cy + gateSize * scaleFactor / 2;
        float curveFactor = scaleFactor / 6;

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
        float lxr = cx - gateSize * scaleFactor / 4;
        float lxrc = lxr + gateSize * scaleFactor / 4; //Redundant (could be just cx) but left it for consistency
        float lxl = cx - gateSize * scaleFactor / 2;
        float lxlc = lxl + gateSize * scaleFactor / 4;
        float rx = cx + gateSize * scaleFactor / 4;
        float by = cy - gateSize * scaleFactor / 2;
        float ty = cy + gateSize * scaleFactor / 2;
        float curveFactor = scaleFactor / 6;

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
//        renderer.set(ShapeRenderer.ShapeType.Filled);
        float rxc = rx + gateSize * scaleFactor / 8;
        float cr = gateSize * scaleFactor / 8;
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
        float lx = cx - gateSize * scaleFactor / 2;
        float rx = cx + gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 4;
        float w = gateSize * scaleFactor;
        float h = gateSize * scaleFactor / 2;

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
        float lx = cx - gateSize * scaleFactor / 2;
        float rx = cx + gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 2;
        float w = gateSize * scaleFactor;

        float clklx = cx - gateSize * scaleFactor / 4;
        float clkrx = cx + gateSize * scaleFactor / 4;
        float clky = cy - gateSize * scaleFactor / 4;

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
        float lx = cx - gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 2;
        float w = gateSize * scaleFactor;

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
        float lx = cx - gateSize * scaleFactor / 2;
        float by = cy - gateSize * scaleFactor / 2;
        float width = gateSize * scaleFactor;

        renderer.setColor(Color.BLUE);
        renderer.x(cx, cy, gateSize);
        renderer.rect(lx, by, width, width);

        renderer.end();
    }
}


