package edu.miamioh.SchematicRenderer;

import java.util.ArrayList;

/**
 * This class describes all gates, including ports.
 * Created by shaffebd.
 */
public class Gate {

    Constants constants = new Constants();

    private String type = "";
    private String id = "";
    private int level = 0;
    private int cx = 0, cy = 0;
    private int inputs = 0;
    private ArrayList<Integer> inputYCoords = new ArrayList<>();
    private ArrayList<Integer> inputXCoords = new ArrayList<>();
    private int scaledGS = constants.gateSize * constants.scaleFactor;

    public Gate(String type, int inputs, String id, int level) {

        switch (type) {

            case ("AND"):
                this.type = "AND";
                this.setNumOfInputs(inputs);
                this.id = id;
                this.level = level;
                break;

            case ("OR"):
                this.type = "OR";
                this.setNumOfInputs(inputs);
                this.id = id;
                this.level = level;
                break;

            case ("XOR"):
                this.type = "XOR";
                this.setNumOfInputs(inputs);
                this.id = id;
                this.level = level;
                break;

            case ("NOT"):
                this.type = "NOT";
                this.setNumOfInputs(1);
                this.id = id;
                this.level = level;
                break;

            default:
                this.type = "INVALID_ASSIGNMENT";
                this.setNumOfInputs(0);
                this.id = "INVALID_ASSIGNMENT";
                this.level = 0;
                break;

        }

    }

    private void setInputXCoords() {

        switch (this.getType()) {

            case("AND"):

                for(int i = 0; i < this.getNumOfInputs(); i++){
                    inputXCoords.add(this.cx - scaledGS / 2);
                }
                break;

            case("NOT"):
                inputXCoords.add(this.cx - scaledGS / 2);
                break;

            case("OR"):
            case("XOR"):
                for(int i = 0; i < this.getNumOfInputs(); i++){
                    inputXCoords.add(this.cx - scaledGS / 2);
                }
                break;

        }

    }

    private void setInputYCoords() {

        int y;

        switch (this.getType()) {

            case("AND"):
                for(int i = 1; i <= this.getNumOfInputs(); i++) {

                    y = this.cy - scaledGS / 2 - scaledGS / (this.getNumOfInputs() * 2) + (int)((i / (float)this.getNumOfInputs()) * (scaledGS)); //casting inspired by Connor
                    inputYCoords.add(y);

                }
                break;

            case("NOT"):
                inputYCoords.add(this.cy);
                break;

            case("OR"):
            case("XOR"):
                for(int i = 1; i <= this.getNumOfInputs(); i++) {

                    y = this.cy - scaledGS / 2 - scaledGS / (this.getNumOfInputs() * 2) + (int)((i / (float)this.getNumOfInputs()) * (scaledGS)); //casting inspired by Connor
                    inputYCoords.add(y);

                }
                break;

        }

    }

    public String getType() {

        return this.type;

    }

    public String getID() {

        return this.id;

    }

    public void setCX(int cx) {

        this.cx = cx;
        setInputXCoords();

    }

    public int getCX() {

        return this.cx;

    }

    public void setCY(int cy) {

        this.cy = cy;
        setInputYCoords();

    }

    public int getCY() {

        return this.cy;

    }

    public void setNumOfInputs(int numOfInputs) {

        this.inputs = numOfInputs;

    }

    public int getNumOfInputs() {

        return this.inputs;

    }

    public int getPortX(String gatePort) {

        String[] name = gatePort.split("~");
        int portNum = Integer.parseInt(name[1]);

        switch (this.getType()) {

            case("AND"):
                if(name[0].equals("IN"))
                    return inputXCoords.get(portNum);
                else
                    return this.getCX() + scaledGS / 2;

            case("OR"):
            case("XOR"):
                if(name[0].equals("IN"))
                    return inputXCoords.get(portNum);
                else
                    return this.getCX() + scaledGS / 2;

            case("NOT"):
                if(name[0].equals("IN"))
                    return inputXCoords.get(0);
                else
                    return this.getCX() + scaledGS / 2;

            default:
                return 0;

        }

    }

    public int getPortY(String gatePort) {

        String[] name = gatePort.split("~");
        int portNum = Integer.parseInt(name[1]);

        switch (this.getType()) {

            case("AND"):
                if(name[0].equals("IN"))
                    return inputYCoords.get(portNum);
                else
                    return this.getCY();

            case("OR"):
            case("XOR"):
                if(name[0].equals("IN"))
                    return inputYCoords.get(portNum);
                else
                    return this.getCY();

            case("NOT"):
                return this.getCY();

            default:
                return 0;

        }

    }

    public int getLevel() {

        return this.level;

    }

}
