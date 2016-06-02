package edu.miamioh.SchematicRenderer;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import edu.miamioh.util.Constants;
import java.util.ArrayList;

/**
 * Created by shaffebd.
 */
public class SchematicRenderer implements Disposable {

    ArrayList<Gate> gates = new ArrayList<>();
    ArrayList<Port> ports = new ArrayList<>();
    ArrayList<String> io = new ArrayList<>();
    Constants constants = new Constants();

    private int xCenter = 0;
    private int xcount = 0;
    private int yCenter = 0;
    private int maxLevel = 0;
    private int midWidth = constants.WINDOW_WIDTH / 2;
    private int midHeight = constants.WINDOW_HEIGHT / 2;

    /**
     * Default schematic Renderer constructor. Does nothing.
     */
    public SchematicRenderer() {
    }

    public void addGate(String type, int inputs, String id, int level) {

        if (level > maxLevel)
            maxLevel = level;
        Gate temp = new Gate(type, inputs, id, level);
        gates.add(temp);

    }

    public void addInput(String id) {

        Port temp = new Port("INPUT", id, 0);
        ports.add(temp);

    }

    public void addOutput(String id) {

        Port temp = new Port("OUTPUT", id, maxLevel++);
        ports.add(temp);

    }

    public void render(ShapeRenderer renderer, int scaleFactor) {

        GateRenderer grenderer = new GateRenderer();

        int totalOuts = 0;
        int totalIns = 0;
        int skips = 0;

        Port tempP;
        Gate tempG;

        for (int i = 0; i < ports.size(); i++) {

            tempP = ports.get(i);
            if (tempP.getType().equals("INPUT")) {

                tempP.setCX(getXCenter(0));
                tempP.setCY(getYCenter(i - totalOuts));
                grenderer.render(renderer, tempP.getType(), tempP.getCX(), tempP.getCY(), scaleFactor);

            } else
                totalOuts++;
        }

        for (int p = 1; p <= maxLevel; p++) {
            for (int j = 0; j < gates.size(); j++) {

                tempG = gates.get(j);
                if (tempG.getLevel() == p) {

                    tempG.setCX(getXCenter(p));
                    tempG.setCY(getYCenter(j - skips));
                    grenderer.render(renderer, tempG.getType(), tempG.getCX(), tempG.getCY(), scaleFactor);

                } else
                    skips++;
            }
            skips = 0;
        }

        for (int k = 0; k < ports.size(); k++) {

            tempP = ports.get(k);
            if (tempP.getType().equals("OUTPUT")) {

                tempP.setCX(getXCenter(maxLevel++));
                tempP.setCY(getYCenter(k - totalIns));
                grenderer.render(renderer, tempP.getType(), tempP.getCX(), tempP.getCY(), scaleFactor);
            } else
                totalIns++;
        }

    }

    private int getXCenter(int level) {

        xCenter = constants.leftEdge + constants.gateSize * constants.scaleFactor / 2 + level * constants.gateSize * constants.scaleFactor * 2;

        return xCenter;

    }

    private int getYCenter(int row) {

        yCenter = constants.bottomEdge + constants.gateSize * constants.scaleFactor / 2 + row * constants.gateSize * constants.scaleFactor * 2;

        return this.yCenter;

    }

    public void connectPorts(ShapeRenderer renderer, String idFrom, String gatePortFrom, String idTo, String gatePortTo) {

        int x1, y1, x2, y2, i;
        x1 = y1 = x2 = y2 = 0;
        Gate tempG;
        Port tempP = null;

        for(i = 0; i < ports.size(); i++){
            if(ports.get(i).getID().equals(idFrom)) {
                tempP = ports.get(i);
                x1 = tempP.getPortX();
                y1 = tempP.getPortY();
                i = ports.size();
            }
        }

        if(tempP == null) {
            for(i = 0; i < gates.size(); i++){
                if(gates.get(i).getID().equals(idFrom)) {
                    tempG = gates.get(i);
                    x1 = tempG.getPortX(gatePortFrom);
                    y1 = tempG.getPortY(gatePortFrom);
                    i = gates.size();
                }
            }
        }

        tempP = null;
        tempG = null;

        for(i = 0; i < ports.size(); i++){
            if(ports.get(i).getID().equals(idTo)) {
                tempP = ports.get(i);
                x2 = tempP.getPortX();
                y2 = tempP.getPortY();
                i = ports.size();
            }
        }

        if(tempP == null) {
            for(i = 0; i < gates.size(); i++){
                if(gates.get(i).getID().equals(idTo)) {
                    tempG = gates.get(i);
                    x2 = tempG.getPortX(gatePortTo);
                    y2 = tempG.getPortY(gatePortTo);
                    i = gates.size();
                }
            }
        }

        if(x1 != 0 & y1 != 0 & x2 != 0 & y2 != 0){

            int xm = (x2 + x1) / 2;

            renderer.line(x1, y1, xm, y1);
            renderer.line(xm, y1, xm, y2);
            renderer.line(xm, y2, x2, y2);

        }

    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {

    }

    public void resize(int width, int height) {

    }
}
