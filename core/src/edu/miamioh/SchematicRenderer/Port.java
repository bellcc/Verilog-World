package edu.miamioh.SchematicRenderer;

import edu.miamioh.util.Constants;

/**
 * Created by shaffebd.
 */
public class Port {

    private String type = "";
    private String id = "";
    private int level = 0;
    private int cx = 0, cy = 0;

    public Port(String type, String id, int level) {

        switch (type) {

            case ("INPUT"):
                this.type = "INPUT";
                this.id = id;
                break;

            case ("OUTPUT"):
                this.type = "OUTPUT";
                this.id = id;
                this.level = level;
                break;

            default:
                this.type = "INVALID_ASSIGNMENT";
                this.id = "INVALID_ASSIGNMENT";
                break;

        }

    }

    public String getType() {

        return this.type;

    }

    public String getID() {

        return this.id;

    }

    public int getLevel() {

        return this.level;

    }

    public void setCX(int cx) {

        this.cx = cx;

    }

    public int getCX() {

        return this.cx;

    }

    public void setCY(int cy) {

        this.cy = cy;

    }

    public int getCY() {

        return this.cy;

    }

    public int getPortX() {

        Constants constants = new Constants();

        switch (this.getType()) {

            case("INPUT"):
                return this.getCX() + constants.gateSize * constants.scaleFactor / 2;

            case("OUTPUT"):
                return this.getCX() - constants.gateSize * constants.scaleFactor / 2;

            default:
                return 0;

        }

    }

    public int getPortY() {

        return this.getCY();

    }
}
