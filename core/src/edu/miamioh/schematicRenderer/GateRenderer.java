package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.miamioh.util.Constants;

/**
 * Created by shaffebd.
 */
class GateRenderer {

    Constants constants = new Constants();

    /**
     * Default constructor
     */
    public GateRenderer() {
    }

    public void render(ShapeRenderer renderer, String type, int x, int y, int scaleFactor) {

        switch (type) {

            case ("INPUT"):
                drawINPUT(renderer, x, y, scaleFactor);
                break;

            case ("OUTPUT"):
                drawOUTPUT(renderer, x, y, scaleFactor);
                break;

            case ("AND"):
                drawAND(renderer, x, y, scaleFactor);
                break;

            case ("OR"):
                drawOR(renderer, x, y, scaleFactor);
                break;

            case ("XOR"):
                drawXOR(renderer, x, y, scaleFactor);
                break;

            case ("NOT"):
                drawNOT(renderer, x, y, scaleFactor);
                break;

            default:
                break;
        }

    }

    /**
     * Draws an INPUT gate at a variable location with a variable scale factor.
     *
     * @param renderer    A ShapeRenderer object
     * @param cx          Horizontal location of the center in pixels relative to the bottom left corner.
     * @param cy          Vertical location of the center in pixels relative to the bottom left corner.
     * @param scaleFactor Integer scaling to make the schematic fill more of the screen.
     */
    public void drawINPUT(ShapeRenderer renderer, int cx, int cy, int scaleFactor) {

        //Differing offsets because the object should be rectangular.
        int lx = cx - constants.gateSize * scaleFactor / 2;
        int by = cy - constants.gateSize * scaleFactor / 4;
        int rx = cx + constants.gateSize * scaleFactor / 4;
        int rxp = cx + constants.gateSize * scaleFactor / 2;
        int ty = cy + constants.gateSize * scaleFactor / 4;

        renderer.line(lx, by, lx, ty);
        renderer.line(lx, by, rx, by);
        renderer.line(lx, ty, rx, ty);
        renderer.line(rx, ty, rxp, cy);
        renderer.line(rx, by, rxp, cy);

        if (constants.frame)
            frame(renderer, cx, cy, scaleFactor);
    }

    /**
     * Draws an OUTPUT gate at a variable location with a variable scale factor.
     *
     * @param renderer    A ShapeRenderer object
     * @param cx          Horizontal location in pixels relative to the bottom left corner.
     * @param cy          Vertical location in pixels relative to the bottom left corner.
     * @param scaleFactor Integer scaling to make the schematic fill more of the screen.
     */
    public void drawOUTPUT(ShapeRenderer renderer, int cx, int cy, int scaleFactor) {

        //Differing offsets because the object should be rectangular.
        int lx = cx - constants.gateSize * scaleFactor / 4;
        int lxp = cx - constants.gateSize * scaleFactor / 2;
        int by = cy - constants.gateSize * scaleFactor / 4;
        int rx = cx + constants.gateSize * scaleFactor / 2;
        int ty = cy + constants.gateSize * scaleFactor / 4;

        renderer.line(lxp, cy, lx, ty);
        renderer.line(lx, ty, rx, ty);
        renderer.line(rx, ty, rx, by);
        renderer.line(rx, by, lx, by);
        renderer.line(lx, by, lxp, cy);

        if (constants.frame)
            frame(renderer, cx, cy, scaleFactor);
    }

    /**
     * Draws a NOT gate at a variable location with a variable scale factor.
     *
     * @param renderer    A ShapeRenderer object
     * @param cx          Horizontal location in pixels relative to the bottom left corner.
     * @param cy          Vertical location in pixels relative to the bottom left corner.
     * @param scaleFactor Integer scaling to make the schematic fill more of the screen.
     */
    public void drawNOT(ShapeRenderer renderer, int cx, int cy, int scaleFactor) {

        int lx = cx - constants.gateSize * scaleFactor / 2;
        int rxt = cx + constants.gateSize * scaleFactor / 4;
        int rxc = rxt + constants.gateSize * scaleFactor / 8;
        int by = cy - constants.gateSize * scaleFactor / 4;
        int ty = cy + constants.gateSize * scaleFactor / 4;
        int cr = constants.gateSize * scaleFactor / 8;

        renderer.line(lx, by, rxt, cy);
        renderer.line(rxt, cy, lx, ty);
        renderer.line(lx, ty, lx, by);
        renderer.circle(rxc, cy, cr);

        if (constants.frame)
            frame(renderer, cx, cy, scaleFactor);
    }

    /**
     * Draws an AND gate at a variable location with a variable scale factor.
     *
     * @param renderer    A ShapeRenderer object
     * @param cx          Horizontal location in pixels relative to the bottom left corner.
     * @param cy          Vertical location in pixels relative to the bottom left corner.
     * @param scaleFactor Integer scaling to make the schematic fill more of the screen.
     */
    public void drawAND(ShapeRenderer renderer, int cx, int cy, int scaleFactor) {

        int lx = cx - constants.gateSize * scaleFactor / 2;
        int rx = cx + constants.gateSize * scaleFactor / 2;
//        int cr = constants.gateSize * scaleFactor / 2;
        int by = cy - constants.gateSize * scaleFactor / 2;
        int ty = cy + constants.gateSize * scaleFactor / 2;
        int curveFactor = scaleFactor / 4;

        renderer.line(cx, by, lx, by);
        renderer.line(lx, by, lx, ty);
        renderer.line(lx, ty, cx, ty);
        //top curve
        renderer.curve(cx, ty, cx + constants.gateSize * scaleFactor / 6 + curveFactor, cy + constants.gateSize *
                scaleFactor / 3 + curveFactor, cx + constants.gateSize * scaleFactor / 3 + curveFactor, cy +
                constants.gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
        //bottom curve
        renderer.curve(cx, by, cx + constants.gateSize * scaleFactor / 6 + curveFactor, cy - constants.gateSize *
                scaleFactor / 3 - curveFactor, cx + constants.gateSize * scaleFactor / 3 + curveFactor, cy -
                constants.gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);

        if (constants.frame)
            frame(renderer, cx, cy, scaleFactor);
    }

    /**
     * Draws an OR gate at a variable location with a variable scale factor.
     *
     * @param renderer    A ShapeRenderer object
     * @param cx          Horizontal location in pixels relative to the bottom left corner.
     * @param cy          Vertical location in pixels relative to the bottom left corner.
     * @param scaleFactor Integer scaling to make the schematic fill more of the screen.
     */
    public void drawOR(ShapeRenderer renderer, int cx, int cy, int scaleFactor) {

        int lx = cx - constants.gateSize * scaleFactor / 2;
        int lxrc = lx + constants.gateSize * scaleFactor / 4;
        int rx = cx + constants.gateSize * scaleFactor / 2;
        int by = cy - constants.gateSize * scaleFactor / 2;
        int ty = cy + constants.gateSize * scaleFactor / 2;
        int curveFactor = scaleFactor / 3;

        //top curve
        renderer.curve(lx, ty, cx - constants.gateSize * scaleFactor / 6 + curveFactor, cy + constants.gateSize *
                scaleFactor / 3 + curveFactor, cx + constants.gateSize * scaleFactor / 6 + curveFactor, cy +
                constants.gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
        //bottom curve
        renderer.curve(lx, by, cx - constants.gateSize * scaleFactor / 6 + curveFactor, cy - constants.gateSize *
                scaleFactor / 3 - curveFactor, cx + constants.gateSize * scaleFactor / 6 + curveFactor, cy -
                constants.gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);
        //side curve
        renderer.curve(lx, by, lxrc, cy - constants.gateSize * scaleFactor / 3, lxrc, cy + constants.gateSize *
                scaleFactor / 3, lx, ty, 1000);

        if (constants.frame)
            frame(renderer, cx, cy, scaleFactor);
    }

    /**
     * Draws an XOR gate at a variable location with a variable scale factor.
     *
     * @param renderer    A ShapeRenderer object
     * @param cx          Horizontal location in pixels relative to the bottom left corner.
     * @param cy          Vertical location in pixels relative to the bottom left corner.
     * @param scaleFactor Integer scaling to make the schematic fill more of the screen.
     */
    public void drawXOR(ShapeRenderer renderer, int cx, int cy, int scaleFactor) {

        int lxr = cx - constants.gateSize * scaleFactor / 4;
        int lxrc = lxr + constants.gateSize * scaleFactor / 4; //Redundant (could be just cx) but left it for consistency
        int lxl = cx - constants.gateSize * scaleFactor / 2;
        int lxlc = lxl + constants.gateSize * scaleFactor / 4;
        int rx = cx + constants.gateSize * scaleFactor / 2;
        int by = cy - constants.gateSize * scaleFactor / 2;
        int ty = cy + constants.gateSize * scaleFactor / 2;
        int curveFactor = scaleFactor / 3;

        //top curve
        renderer.curve(lxr, ty, cx - constants.gateSize * scaleFactor / 6 + curveFactor, cy + constants.gateSize *
                scaleFactor / 3 + curveFactor, cx + constants.gateSize * scaleFactor / 6 + curveFactor, cy +
                constants.gateSize * scaleFactor / 6 + curveFactor, rx, cy, 1000);
        //bottom curve
        renderer.curve(lxr, by, cx - constants.gateSize * scaleFactor / 6 + curveFactor, cy - constants.gateSize *
                scaleFactor / 3 - curveFactor, cx + constants.gateSize * scaleFactor / 6 + curveFactor, cy -
                constants.gateSize * scaleFactor / 6 - curveFactor, rx, cy, 1000);
        //side curve (right)
        renderer.curve(lxr, by, lxrc, cy - constants.gateSize * scaleFactor / 3, lxrc, cy + constants.gateSize *
                scaleFactor / 3, lxr, ty, 1000);
        //side curve (left)
        renderer.curve(lxl, by, lxlc, cy - constants.gateSize * scaleFactor / 3, lxlc, cy + constants.gateSize *
                scaleFactor / 3, lxl, ty, 1000);

        if (constants.frame)
            frame(renderer, cx, cy, scaleFactor);
    }

    private void frame(ShapeRenderer renderer, int cx, int cy, int scaleFactor) {

        int lx = cx - constants.gateSize * scaleFactor / 2;
        int by = cy - constants.gateSize * scaleFactor / 2;
        int width = constants.gateSize * scaleFactor;
        int height = width;

        renderer.setColor(Color.BLUE);
        renderer.x(cx, cy, constants.gateSize);
        renderer.rect(lx, by, width, height);
        renderer.setColor(Color.BLACK);
    }
}


